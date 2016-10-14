import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;


// represents a tichu player
public class Player {
	public static final int HASNTPLAYED=0;
	public static final int PASSED = 1;
	public static final int PLAYED = 2;
	public static final int HASNOCARDSLEFT = 3;
	public static final int WONTHETRICK = 4;

	/**
	 * represents the type of evaluation used by the Player 
	 */
	public int evaluationType;
	
	/**
	 * contains the Player's hand
	 */
	public Hand hand;
	
	/**
	 * contains the Cards to be passed to other Players 
	 * at the start of each Round.
	 */
	public ArrayList<Card> exchange;
	
	/**
	 * represents the name of the Player
	 */
	public String name;
	
	/**
	 * contains the root node, therefore the whole search tree, of the Player
	 */
	private Node node;
	
	/**
	 * contains all the leaves of the search tree.
	 * used for auxiliary purposes.
	 * the Nodes in this list are sorted with a descending order
	 * according to their value 
	 */
	private ArrayList<Node> possibleNodeList; //all possible combinations of the cards in hand
	
	/**
	 * contains all the tricks (Cards) won by the Player during the round
	 * After each round the trick is cleared. 
	 */
	public ArrayList<Card> trick;
	
	/**
	 * represents the status of the Player. this must take tha value of one of the constants:
	 * Player.HASNTPLAYED, Player.PASSED, Player.PLAYED, Player.HASNOCARDSLEFT or Player.WONTHETRICK.
	 */
	public int status;
	
	/**
	 * represents the position of the Player on the table. This must have the values 0, 1, 2 or 3
	 */
	public int index;
	
	/**
	 * contains the position of the Player's partner
	 */
	public int partner;
	
	/**
	 * is set to true if Player has said tichu.
	 */
	public boolean saidTichu;
	
	/**
	 * is set to true if Player has said grande tichu
	 */
	public boolean saidGrandeTichu;
	
	/**
	 * contains the best node of the search tree
	 */
	private Node bestNode;
	
	/**
	 * represents the play threshold below which the 
	 * Player is obliged to pass
	 */
	private double playThreshold;
	
	/**
	 * contains all the combination coefficients
	 */
	public final float[]combinationWeight;
	
	/**
	 * contains 3 int values which represent the behavior of the Player towards its partner
	 */
	public final int[] partnership; 
	
	/**
	 * this array consists of 5 columns and 13 rows. Each position in the first four columns take 
	 * the value 0 or 1 if the card that it represents is known or unknown respectively.
	 * Each position of the fifth column is the sum of each row. 
	 */
	public int[][] cardCount;
	
	public final boolean isUser;
	
	
	/**
	 * cTor
	 * @param idx: the position of the player
	 */
	public Player(int idx, boolean _isUser) {
		hand = new Hand();
		exchange = new ArrayList<Card>();
		possibleNodeList = new ArrayList<Node>();
		status = HASNTPLAYED;
		index = idx;
		partner = (idx+2)%4;
		saidTichu=false;
		saidGrandeTichu=false;
		node = new Node();
		name = ""+idx;
		evaluationType = 0;
		trick = new ArrayList<Card>();
		cardCount = new int[13][5];
		initializeCardCount();
		setPlayThreshold(0);
		combinationWeight = new float[] {0, 0, 0, 0, 0, 0, 0, 0, 0,	0};
		partnership = new int[] {0, 0, 0};
		isUser = _isUser;
	}
	
	/**
	 * Configures the player properties (name, combination weights, evaluation type and partnership)
	 * via the files "playerXconfig.properties" located in the same folder with the project
	 */
	public void configPlayer() {
		String file = "/home/marthavk/workspace/mini-tichu/src/main/resources/player" + index + "config.properties";
		Properties prop = new Properties();
		
    	try {
    		
            //load a properties file
    		prop.load(new FileInputStream(file));
    		
    		//set evaluation type
    		evaluationType = Integer.parseInt(prop.getProperty("evaluation"));
    		if (evaluationType<0||evaluationType>4) {
    			GameDemo.message.append("incorrect evaluation type, default value will be used(0)");
    		}
    		
    		//set combination weights
    		for (int i=0; i<this.combinationWeight.length; i++) {
    			String combType = Combination.typeToString(i+1);
    			this.combinationWeight[i] = Float.parseFloat(prop.getProperty(combType)); 
    		}    		
    		//set partnership
    		partnership[0] = Integer.parseInt(prop.getProperty("biggestSingle"));
    		partnership[1] = Integer.parseInt(prop.getProperty("difference"));
    		partnership[2] = Integer.parseInt(prop.getProperty("biggestComb"));
    		
    		//set name
    		name = prop.getProperty("name");
    		
    		//set threshold
    		playThreshold = Float.parseFloat(prop.getProperty("threshold"));
    		
    		
        } 
    	catch (IOException ex) {
    		GameDemo.message.append("Configuration Error!");
    		ex.printStackTrace();
        }
    	
 
	}
	
	/**
	 * constructs the Player's search tree
	 * then sets the bestNode which is the leaf-node 
	 * with the greater value
	 */
	public void setSearchTree() {
		node = new Node();
		this.possibleNodeList.clear();
		node.setRemainingHand(this.getPlayerHand());		
		Search.search(node, this);	
		this.constructPossibleNodeList();			
		if (this.evaluationType==4) {
			int size = possibleNodeList.size();
			
			int pos = (int)(Math.random()*size);
			System.out.println("**********POSITION = "+pos + " SIZE = " +size);
			bestNode = this.possibleNodeList.get(pos);
		}
		else {
			this.sortPossibleNodeList();	
			bestNode = this.possibleNodeList.get(0);
		}
	
	}
	
	
	/**
	 * resets Player's fields
	 */
	public void resetPlayer() {
		this.initializeCardCount();	
		hand.clear();
		exchange.clear();
		possibleNodeList.clear();
		status = HASNTPLAYED;				
		saidTichu=false;
		saidGrandeTichu=false;
		node.clearNode();			
		trick.clear();
		saidGrandeTichu=false;
		saidTichu=false;
	}
	
	/**
	 * sets the Player Hand
	 * @param h: reference
	 */
	public void setPlayerHand(Hand h) {
		hand = h;
	}
	
	/**
	 * sets the playThreshold
	 * @param pt
	 */
	public void setPlayThreshold(float pt) {
		playThreshold = pt;
	}
	
	/**
	 * sets one-by-one the possibleNodeList of the Player
	 * @param nodesList: the list to be copied to possibleNodeList field
	 */
	public void setPossibleNodeList(ArrayList<Node> nodesList) {
		Iterator<Node> iter = nodesList.iterator();
		while (iter.hasNext()) {
			possibleNodeList.add(iter.next());
		}
	}
	
	/**
	 * stores all leaves from search tree into the array possibleNodeList of the player.
	 */
	public void constructPossibleNodeList() {
		ArrayList<Node> leaves = new ArrayList<Node> (); 
		leaves = this.getNode().findLeaves(leaves);
		if (leaves.isEmpty()) {
			this.getNode().getCombinationList().addAsSingle(this.getNode().getRemainingHand());
			leaves.add(this.getNode());
			this.setPossibleNodeList(leaves);
		}
		else {
			Iterator<Node> iter = leaves.iterator();
			while (iter.hasNext()) {
				Node temp = iter.next();
				temp.getCombinationList().addAsSingle(temp.getRemainingHand());
				temp.setNodeValue(Evaluation.evaluateNode(temp, this));
			}
			this.setPossibleNodeList(leaves);
				
		}
		
	}
	
	
	/**
	 * sorts the array possibleNodeList by each node's value		
	 */
	public void sortPossibleNodeList() {
		
		double maxValue = 0;
		  ArrayList<Node> sorted = new ArrayList<Node>();
	      while (this.possibleNodeList.size() > 0) {
	         int pos = 0;  // Position of minimal value.
	         maxValue = this.possibleNodeList.get(0).getNodeValue();
	         
	         for (int i = 1; i < this.possibleNodeList.size(); i++) {
	        	 double value1 = this.possibleNodeList.get(i).getNodeValue();
	        	 if (value1 > maxValue) {
	        		 pos=i;
	        		 maxValue=value1;
	        	 }
	         }
	        Node temp = this.possibleNodeList.get(pos);
	        this.possibleNodeList.remove(pos);
	        sorted.add(temp);
	      }
	      this.possibleNodeList = sorted;
	}
	
	/**
	 * 
	 * @return Hand of current Player
	 */
	public Hand getPlayerHand() {
		return hand;
	}
	
	/**
	 * 
	 * @return the possibleNodeList of current Player
	 */
	public ArrayList<Node> getPossibleNodeList() {
		return possibleNodeList;
	}
	
	/**
	 * 
	 * @return the root Node of the search that corresponds
	 * to current Player
	 */
	public Node getNode() {
		return node;
	}
	
	/**
	 * 
	 * @return the leaf node with the greater value
	 */
	public Node getBestNode() {
		return bestNode;
	}
	
	/**
	 * 
	 * @return the type of evaluation used to evaluate Nodes, 
	 * CombLists and Combinations
	 */
	public int getEvaluationType() {
		return evaluationType;
	}
	
	/**
	 * 
	 * @return the status of current Player
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * 
	 * @return the index of the partner of current Player
	 */
	public int getPartner() {
		return partner;
	}
	
	/**
	 * 
	 * @return the current number of Cards
	 * in Player's Hand
	 */
	public int getNumberOfCards() {
		return hand.getCardCount();
	}
    
	/**
	 * 
	 * @return the playThreshold of this Player
	 */
	public double getPlayThreshold() {
		return playThreshold;
	}
	
	/**
	 * Sets all table cells of the first 4 columns = 1
	 * and all cells of the 5th column = 4
	 */
	public void initializeCardCount() {
		for (int i=0; i<13; i++) {
			for (int j=0; j<5; j++) {
				cardCount[i][j] = 1;
			}
		}
		for (int i=0; i<13; i++) {
			cardCount[i][4]=4;
		}
	}
	
	/**
	 * 
	 * @return the String representation of Player's status
	 */
	public String statusToString() {
		switch(status) {
		case HASNTPLAYED: return "has not yet played";
		case PASSED: return "passed";
		case PLAYED: return "played";
		case HASNOCARDSLEFT: return "has no cards left";
		case WONTHETRICK: return "won the trick";
		default: return "invalid playing condition";
		}
			
	}
	
	/**
	 * EXCHANGE FUNCTION:
	 * sets the list of the exchange Cards for this Player
	 * removes every possible doublet from 2 to J constructs the search tree
	 * for the Hand without the doublet and finds the best node from this tree.
	 * The function does this for every possible doublet and saves them in a list to find, 
	 * afterwards, the best of all Node. To that Node must correspond a doublet of Cards
	 * which are the Cards to be exchanged to the opponents.
	 * Also uses getHigestSingle() from class Node to set the Card that corresponds to the partner 
	 */
	public void setExchangeCards() { 
		int n = this.getPlayerHand().countCardsSmallerThanNumber(11);
		if (this.evaluationType!=4) {
			ArrayList<Node> bestNodesList = new ArrayList<Node> ();		
			for (int i=0; i<n-1; i++) {
				for (int j=i+1; j<n; j++) {
					Node rootNode = new Node(this.hand.copyHand(), new CombList(), this);
					Hand tempHand = this.hand.copyHand();
					tempHand.removeCard(this.hand.getCard(i));
					tempHand.removeCard(this.hand.getCard(j));
					rootNode.setRemainingHand(tempHand);
					Search.search(rootNode, this);
					Node bestNode = Search.findBestNodefromAllLeaves(rootNode, this);				
					bestNode.exchangeCards.add(this.hand.getCard(i));
					bestNode.exchangeCards.add(this.hand.getCard(j));
					bestNodesList.add(bestNode);						
					Search.allLeaves.clear();	
				}//end for j
			}//end for i
			int pos = 0;	//position of best node
			double maxValue = -100;
			for (int i=0; i<bestNodesList.size(); i++) {
				if (bestNodesList.get(i).getNodeValue()>maxValue) {
					pos = i;
					maxValue = bestNodesList.get(i).getNodeValue();
				}
			}
			Node bestOfAllNode = bestNodesList.get(pos);
			Card biggestSingle = bestOfAllNode.getHigestSingle();				
			bestOfAllNode.exchangeCards.add(biggestSingle);
			this.exchange = bestOfAllNode.exchangeCards;
		}
		else {
			int i,j;
			do {
				i = (int)(Math.random()*n);
				j = (int)(Math.random()*n);				
			}while(i==j&&j!=(this.getNumberOfCards()-1));
			this.exchange.add(this.getPlayerHand().getCard(i));
			this.exchange.add(this.getPlayerHand().getCard(j));
			this.exchange.add(this.getPlayerHand().getCard(this.getNumberOfCards()-1));
		}		
	}
	
	/**
	 * 
	 * @return true if Player had no Cards left in his hand
	 * false otherwise
	 */
	public boolean hasNoCardsLeft() {
		if (this.getNumberOfCards()==0)
			return true;
		return false;				
	} 
	
	
	/**
	 * plays a Combination and sets Players status accordingly to the Object returned by playOrPass
	 * if the Object is null Player's status is set to PASSED, else to PLAYED.
	 */
	public Combination selectCombinationToPlay() {
		Combination currentComb = new Combination();
		if (this.status==WONTHETRICK)
			currentComb = this.setTableCombination();														
		else			
			currentComb = playOrPass(GameDemo.tichu.tichuTable.getTopCombination());				
		if (currentComb!=null && this.hasNoCardsLeft())										
			GameDemo.tichu.currentRound.rank.add(this.index);				
		return currentComb;
	
	}
	
	public void play(Combination c) {
		this.removeComb(c);
		GameDemo.tichu.tichuTable.addCombinationOnTable(c);
		GameDemo.message.append(this.name + " played: " + c.toString());
		this.status=PLAYED;
		GameDemo.tichu.currentRound.removeCombFromOtherPlayersCount(this, 
				GameDemo.tichu.tichuTable.getTopCombination());
		if (this.hasNoCardsLeft()) {				
			GameDemo.tichu.currentRound.rank.add(this.index);
		}
		else {
			if (!this.isUser)
				this.setSearchTree();
		}			
	}
	
	public void pass() {
		GameDemo.message.append(this.name + " passed");
		this.status = PASSED;
	}
	
	public Hand indexToHand(int[] selection) {
		Hand tempHand = new Hand();		
		for (int i=0; i<selection.length; i++) {							
			tempHand.addCard(this.hand.getCard(selection[i]));			
		}		
		return tempHand;	
	}
	
	public Combination userPlay(Hand h) {
		Combination currentComb = Rules.constructCombination(h);
		//current combination is not a valid combination
		if (currentComb == null)
			return null;
		//current combination is a valid combination
		else {
			//current combination cannot be played
			if (!Rules.canBePlayed(currentComb, GameDemo.tichu.tichuTable.getTopCombination())&&(this.status!=Player.WONTHETRICK))
				return null;				
		}							
		this.removeComb(currentComb);		
	//	GameDemo
		
			//GameDemo.cardList.remove(i);
		//GameDemo.cardList.validate();
		GameDemo.tichu.tichuTable.addCombinationOnTable(currentComb);	
		GameDemo.message.append(this.name + " played: " + currentComb.toString());
		this.status=PLAYED;
		if (this.hasNoCardsLeft())			
				GameDemo.tichu.currentRound.rank.add(this.index);					
		return currentComb;
	}
	
	
	
	/**
	 * Checks every Combination allowed to be played over the Combination @param c.
	 * If the subtracted Node (Node without the Combination c) is not becoming worse than 
	 * the playThreshold then it returns the Combination.
	 * @return combination to be played, null if player passes
	 */
	public Combination playOrPass(Combination c) {
		if (this.evaluationType!=4) {			
			double currentValue;
			double bestValue = this.bestNode.getNodeValue();  
			boolean isPartnership = (GameDemo.tichu.players[this.partner].getStatus()==Player.PLAYED
					&&(GameDemo.tichu.players[(this.partner+1)%4].getStatus()==Player.PASSED
					||GameDemo.tichu.players[(this.partner+1)%4].getStatus()==Player.HASNOCARDSLEFT))
					&&(!GameDemo.tichu.players[this.partner].hasNoCardsLeft());
				CombList allowed = this.bestNode.getCombinationList().getAllPlayableCombinations(c);	
				if (allowed.getCombCount()!=0) {				
				Iterator<Combination> iter = allowed.getList().iterator();
				while (iter.hasNext()) {
					Combination currentComb = iter.next();
					currentValue = Evaluation.evaluateSubtractedList(this.bestNode.getCombinationList(), this, currentComb);				
					if (currentValue>bestValue*this.playThreshold) {
						if (isPartnership) {							
							if(playOverPartner(currentComb,c)) 
								return currentComb;							
						}						
						else		
							return currentComb;
					}//end_if
				}//end_while
			}//end_if	
		}
		else {
			CombList allowed = this.bestNode.getCombinationList().getAllPlayableCombinations(c);
			if (!allowed.getList().isEmpty()) {
				return allowed.getList().get((int)(Math.random()*(double)(allowed.getList().size())));
			}
		}
		
		return null;	
	}
	
	
	/**
	 * Checks if Combination 
	 * @param combToPlay is allowed to played over 
	 * @param lastComb (Combinatio played by Player's partner) 
	 * according to the rules set by table partnership. 
	 * @return true if Player is allowed to play the Combination, false otherwise
	 */
	public boolean playOverPartner(Combination combToPlay, Combination lastComb) {
		if (combToPlay.getType()==Combination.SINGLE) {
			if (combToPlay.getValue()<=this.partnership[0] 
					&&(combToPlay.getValue()<lastComb.getValue()+this.partnership[1])) 
				return true;
		}			
		if (combToPlay.getNumberOfCards()<=this.partnership[2]	&&combToPlay.getValue()<=this.partnership[0]
						&&(combToPlay.getValue()<lastComb.getValue()+this.partnership[1])) 					
			return true;
		
		return false;
	}
		
	/**
	 *Prints player's hand condition
	 */
	public void printHand() {
		if (this.hasNoCardsLeft())
			GameDemo.message.append(name + " Has No Cards Left");
		else
			GameDemo.message.append(name + ": " + this.getPlayerHand().toString());
	}
	
	/**
	 * Prints Player's configuration
	 */
	public void printConfig() {
		GameDemo.message.append("Player:" + name);
		GameDemo.message.append("Hand:" + hand.toString());
		GameDemo.message.append("Cards Exchanged:" + exchange.toString());
		GameDemo.message.append(this.statusToString());
		GameDemo.message.append("Evaluation = " + evaluationType);
		GameDemo.message.append("play threshold = " + playThreshold);
		GameDemo.message.append("Combination weights :");
		for (int i=0; i<combinationWeight.length; i++) {
			GameDemo.message.append(Combination.typeToString(i+1) + "= " + combinationWeight[i]);
		}
		GameDemo.message.append("Biggest Single = " + partnership[0]);
		GameDemo.message.append("Biggest Difference = " + partnership[1]);
		GameDemo.message.append("Biggest Combination (Cards) = " + partnership[2]);		
	}
	
	/**
	 * sets the Combination the player is about to play in case he has won the previous trick
	 * searches between the combinations of the best node and finds the one with the worst value
	 * @return the Combination to be played
	 */
	public Combination setTableCombination() {
		if (this.evaluationType!=4) {
		Iterator<Combination> iter = this.bestNode.getCombinationList().getList().iterator();
		double value = 100000;
		double tempValue = 0;
		Combination worst = new Combination();		
		while (iter.hasNext()) {
			Combination temp = iter.next();				
			tempValue = Evaluation.evaluateCombinaton(this.bestNode.getCombinationList(), this, temp);
			if (tempValue<value) {
				worst = temp;
				value = tempValue;
			}
		}						
		return worst;
		}
		else {
			return this.getBestNode().getCombinationList().getList().get(0);
		}			
	}
	
	/**
	 * string representation of Player (name, index and hand)
	 */
	public @Override String toString() {
		String str = new String("Player #" + index + ", " + name + ": " + hand.toString());
		if (this.saidGrandeTichu) {
			str = str + "\nSaid Large Tichu";
			return str;
		}			
		else if (this.saidTichu) {
			str = str + "\nSaid Tichu";
			return str;
		}
		else
			return str;
	}
	
	/**
	 * @return the string representation of exchangeCards list
	 */
	public String exchangeCardsToString() {
		String str = new String("player"+ this.name + " exchanged: [");
		for (int i=0; i<this.exchange.size(); i++) {
			str += this.exchange.get(i).toString() + ", ";
		}
		return str + "]";
	}
	
	/**
	 * @return the string representation of the trick
	 */
	public String trickToString() {
		return this.trick.toString()  + "= " + countTrickPoints() + " points";
	}
	
	/**
	 * Adds a list of cards @param cards to Player's trick
	 */
	public void addCardsToTrick(ArrayList<Card> cards) {
		for (int i=0; i<cards.size(); i++) 
			trick.add(cards.get(i));		
	}
	
	/**
	 * @return the sum of points of Cards in the trick
	 */
	public int countTrickPoints() {
		int points = 0;
		for (int i=0; i<this.trick.size(); i++) {
			switch(this.trick.get(i).getValue()) {
			case 5: 	points += 5;
						break;
			case 10: 	points += 10;
						break;
			case Card.KING:		points += 10;
								break;
			default:	break;		
			}
		}		
		return points;
	}
	
	/**
	 * @param c: the combination(Cards) to be removed from the player's hand
	 */
	public void removeComb(Combination c) {			
		for (int i=0; i<c.getNumberOfCards(); i++) 
			this.getPlayerHand().removeCard(c.getCardList().get(i));				
	}
	
	/**
	 * this function decreases the value for every cell on the table 
	 * that corresponds to value and suit of every Card in the list
	 * @param cardList  
	 * normally the four first columns must be turned from one to zero as
	 * each one corresponds to a card in the deck
	 * the cells of the fifth column that correspond to each
	 * Card's value are decreased by one.
	 */
	public void removeCardListFromCardCount(ArrayList<Card> cardList) {
		for (int i=0; i<cardList.size(); i++) {
			Card temp = cardList.get(i);
			this.cardCount[temp.getValue()-2][temp.getSuit()]--;
			this.cardCount[temp.getValue()-2][4]--;
		}
	}	
	
	public boolean HandIsStrong() {
		/**
		 * TODO
		 */
		return false;
	}
	
	

}//end of Class Player



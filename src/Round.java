import java.util.ArrayList;

// represents a game round
public class Round{
	/**
	 * contains the index of the Players who run out of Cards
	 * the index are added in the list with a specific order so
	 * the rank also contains the order with which the Players ran out of Cards  
	 */
	public ArrayList<Integer> rank;
	
	/**
	 * contains the scores of the round
	 */
	public int[] roundScores;
	
	
	/**
	 * cTor:
	 * sets up a new Round, clears the Table and the Players Hands
	 */
	public Round () {		
		rank = new ArrayList<Integer>();
		roundScores = new int[2];
		roundScores[0] = 0;
		roundScores[1] = 0;
	}
	
	public void reSetRound() {
		for (int i=0; i<4; i++) {			
			GameDemo.tichu.players[i].resetPlayer();						
		}
		GameDemo.tichu.tichuTable.clear();
		GameDemo.tichu.tichuDeck = new Deck();
		rank = new ArrayList<Integer>();
		roundScores = new int[2];
		roundScores[0] = 0;
		roundScores[1] = 0;
	}
	

	
	/**
	 * Checks if the round is over
	 * @return true if currentRound is Over
	 */
	public boolean isOver() {
		return ((GameDemo.tichu.players[0].hasNoCardsLeft()&&GameDemo.tichu.players[2].hasNoCardsLeft())
				||GameDemo.tichu.players[1].hasNoCardsLeft()&&GameDemo.tichu.players[3].hasNoCardsLeft());		
	}
	
	/**
	 * Sets the round scores counting the trick of each player
	 */
	public void setRoundScores() {
		if ((rank.get(0)+rank.get(1))%2==0) {
			// 1-2 IS MADE
			int firstPlayer = rank.get(0);
			int secondPlayer = rank.get(1);
			//Check if first player has declared Tichu or Grande Tichu
			if (GameDemo.tichu.players[firstPlayer].saidGrandeTichu)
				roundScores[firstPlayer%2] += 200;
			else {
				if (GameDemo.tichu.players[firstPlayer].saidTichu)
					roundScores[firstPlayer%2] += 100;
			}
			
			//Check if second player has declared Tichu or Grande Tichu
			if (GameDemo.tichu.players[secondPlayer].saidGrandeTichu)
				roundScores[secondPlayer%2] -= 200;
			else {
				if (GameDemo.tichu.players[secondPlayer].saidTichu)
					roundScores[secondPlayer%2] += 100;
			}
			//add the 1-2 points
			roundScores[firstPlayer%2] += 200;
				
		}
		else {
			/*LAST PLAYER'S TRICK AND HAND*/
			GameDemo.tichu.players[rank.get(0)].addCardsToTrick(GameDemo.tichu.players[rank.get(3)].trick);
			//add last's player hand to opponent's team trick
			GameDemo.tichu.players[(rank.get(3)+1)%2].addCardsToTrick(GameDemo.tichu.players[rank.get(3)].getPlayerHand().getHand());
			/*FIRST PLAYER*/			
			int firstPlayer = rank.get(0);
			//check for grande tichu
			if (GameDemo.tichu.players[firstPlayer].saidGrandeTichu)
				roundScores[firstPlayer%2] += 200;
			else {
			//check for tichu
				if (GameDemo.tichu.players[firstPlayer].saidTichu)
					roundScores[firstPlayer%2] += 100;
			}
			//add points
			roundScores[firstPlayer%2] += GameDemo.tichu.players[firstPlayer].countTrickPoints();
			
			/*SECOND THIRD AND LAST PLAYER*/
			for (int i=1; i<3; i++) {
				int currentPlayer = rank.get(i);
				//check for grande tichu
				if (GameDemo.tichu.players[currentPlayer].saidGrandeTichu)
					roundScores[currentPlayer%2] -= 200;
				else {
				//check for tichu
					if (GameDemo.tichu.players[currentPlayer].saidTichu)
						roundScores[currentPlayer%2] -= 100;
				}
				//add points
				roundScores[currentPlayer%2] += GameDemo.tichu.players[currentPlayer].countTrickPoints();
			}
			
		}	
	}
	
	/** 
	 * @return a String representation of the scores
	 */
	public String scoresToString() {
		String str = new String();
		str += "team 1: " + roundScores[0];
		str += "\nteam 2: " + roundScores[1];
		return str;
	}
	/**
	 * Deals the whole deck between the 4 Players
	 */
	public void deal() {		
		for (int i=0; i<4; i++) {
			GameDemo.tichu.tichuDeck.dealANumberOfCards(13, GameDemo.tichu.players[i]);			
			GameDemo.tichu.players[i].getPlayerHand().sortByValue();			
		}		
	}
	
	/**
	 * @return the sum of the Cards still in Players Hands
	 */
	public int getTotalCardCount() {
		int cardCount = 0;
		for (int i=0;i<4;i++) {
			cardCount += GameDemo.tichu.players[i].getNumberOfCards();
		}
		return cardCount;
	}
	
	/**
	 * @param p the Player
	 * @return the sum of the Cards still in the Hands of the opponent Players
	 */
	public int getOpponentCardCount(Player p) {		
		int idx = p.index;
		return GameDemo.tichu.players[(idx+1)%4].getNumberOfCards() + GameDemo.tichu.players[(idx+3)%4].getNumberOfCards();
	}
	
	/**
	 * Lets the user deal all Cards manually for all Players
	 */
	public void dealCardsManually() {
		for (int i=0; i<4; i++) {
			GameDemo.tichu.tichuDeck.dealCardsManually(13, GameDemo.tichu.players[i]);
			GameDemo.tichu.players[i].getPlayerHand().sortByValue();
			GameDemo.tichu.players[i].printHand();
		}
	}
	
	/**
	 * Prints the best Nodes of every Player
	 */
	public void printBestNodes() {
		for (int i=0; i<4; i++) {			
			GameDemo.message.append("Player " + GameDemo.tichu.players[i].name);
			GameDemo.message.append(Evaluation.nodeEvaluationToString(GameDemo.tichu.players[i].getBestNode(), GameDemo.tichu.players[i]));
		}
	}
		
	/**
	 * Calls the setNode() function for every player so as to construct 
	 * a tree with all possible combinations with each hand
	 * Remaining Cards of all Leaf Nodes are added to the Combination List as Single Combinations
	 */
	public void setPlayerNodes() {
		for (int i=0; i<4; i++) { 
			//if (!GameDemo.tichu.players[i].isUser)
				GameDemo.tichu.players[i].setSearchTree();
		}
	}
	
	/**
	 * Exchanges Cards between the 4 players.
	 */
	public void exchangeCards() {
		try {
		   for (int i=0; i<4; i++) {
			   GameDemo.tichu.players[(i+3)%4].getPlayerHand().addCard(GameDemo.tichu.players[i].exchange.get(0));
			   GameDemo.tichu.players[(i+1)%4].getPlayerHand().addCard(GameDemo.tichu.players[i].exchange.get(1));
			   GameDemo.tichu.players[(i+2)%4].getPlayerHand().addCard(GameDemo.tichu.players[i].exchange.get(2));
			   GameDemo.tichu.players[i].getPlayerHand().removeCard(GameDemo.tichu.players[i].exchange.get(0));
			   GameDemo.tichu.players[i].getPlayerHand().removeCard(GameDemo.tichu.players[i].exchange.get(1));
			   GameDemo.tichu.players[i].getPlayerHand().removeCard(GameDemo.tichu.players[i].exchange.get(2));			   
		   }
		   for (int i=0; i<4; i++)
			   GameDemo.tichu.players[i].getPlayerHand().sortByValue();
		}
		catch (NullPointerException e) {
			GameDemo.message.append("ERROR: in Round.exchangeCards(): NullPointerException");
		}
		catch (IndexOutOfBoundsException b) {
			GameDemo.message.append("ERROR: in Round.exchangeCards(): IndexOutOfBoundsException");
		}	
	}
	
	/**
	 * Sets the exchange Cards for all Players
	 */
	public void setExchangeCardsForAllPlayers() {
			for (int index = 0; index<4; index++) {
				GameDemo.tichu.players[index].setExchangeCards();
		}
		
	}
	
	/**
	 * Prints the exchange Cards for all Players
	 */
	public void printExchangeCardsForAllPlayers() {
		for (int i=0; i<4; i++) {
			GameDemo.message.append(GameDemo.tichu.players[i].exchangeCardsToString());
		}
	}
	
	/**
	 * Prints the current Hand of each player
	 * if the Player has run out of Cards the function 
	 * prints the corresponding message 
	 */
	public void printAllPlayersCurrentHands() {
		for (int i=0; i<4; i++) {
			if (GameDemo.tichu.players[i].hasNoCardsLeft())
				GameDemo.message.append(GameDemo.tichu.players[i].name + " Has No Cards Left");
			else
				GameDemo.message.append(GameDemo.tichu.players[i].toString());				
		}
	}
	
	/**
	 * Removes a Combination from all Player's card Count except for Player in @param p
	 * @param c: the Combination to be removed
	 */
	public void removeCombFromOtherPlayersCount(Player p, Combination c) {
		for (int i=0; i<4; i++) {
			if (i!=p.index) 
				GameDemo.tichu.players[i].removeCardListFromCardCount(c.getCardList());
		}
	}
	
	/**
	 * for each Player the function sets its Hand as known-Cards
	 * modifying its cardCount array
	 */
	public void removeHandFromPlayersCount() {
		for (int i=0; i<4; i++) {
			GameDemo.tichu.players[i].removeCardListFromCardCount(GameDemo.tichu.players[i].getPlayerHand().getHand());
		}
	}
	
	/**
	 * resets the cardCount array for all Players
	 */
	public void resetCardCountForAllPlayers() {
		for (int i=0; i<4; i++)
			GameDemo.tichu.players[i].initializeCardCount();
	}
	
	/**
	 * resets all Players
	 */
	public void resetAllPlayers() {
		for (int i=0; i<4; i++)
			GameDemo.tichu.players[i].resetPlayer();
	}
	
	/**
	 * prints the configuration of all Players
	 */
	public void printPlayersConfig() {
		GameDemo.message.append("CONFIGURATIONS:");
		for (int i=0; i<4; i++) { 		
			GameDemo.tichu.players[i].printConfig();
		}
	}	
	
}

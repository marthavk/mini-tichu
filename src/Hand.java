
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.ListModel;


//represents a hand of cards
public class Hand extends JPanel{

  /**
   * represents the hand of a Player
   */
   private ArrayList<Card> hand;   // The cards in the hand.
   
   
   
   public Hand() {
	   setSize(300, 100);
	   setBackground(Color.black);
	   setVisible(true);
	   hand = new ArrayList<Card>();
   }
              
   /**
    * Removes all Cards from the Hand
    */
   public void clear() {
      hand.clear();
   }
   
   /**
    * Checks if the hand contains a specific card value
    * @param c 
    * @return true if the Hand contains Card, false otherwise
    */ 
   public boolean containsValue(int value) {
	   for (int i=0; i<this.hand.size(); i++) {
		   if (this.getCard(i).getValue()==value)
			   return true;
	   }	   
	   return false;
   }
   
   /**
    * Checks if the Hand contains a specific Card
    * defined by its value and suit
    * @param value
    * @param suit
    * @return true if the Hand contains the Card, false otherwise
    */
   public boolean containsCard(int value, int suit) {
	   for (int i=0; i<this.hand.size(); i++) {
		   if ((this.getCard(i).getValue()==value)&&(this.getCard(i).getSuit()==suit))
			   return true;
	   }
	   return false;
   }
   
   /**
    * counts the Cards on the Hand that are of less value than the @param value
    * @return the number of Cards smaller than value.
    */
   public int countCardsSmallerThanNumber(int value) {
	   int number = 0;
	   for (int i=0; i<this.getCardCount(); i++) {
		   if (this.getCard(i).getValue()<value)
			   number++;
	   }	   
	   return number;
   }
   
   /**
    * @param value
    * @param suit
    * @return the Card represented by its value and suit (given as arguments).
    */
   public Card getCard(int value, int suit) {
	   Iterator<Card> iter = this.hand.iterator();	   
	   while (iter.hasNext()) {
		   Card temp=iter.next();
		   if ((temp.getValue()==value) && (temp.getSuit()==suit)) 
			   return temp;
	   }
	   return null;
   }
      
   
   /**
    * Adds a specific Card in the Hand
    * @param c: the Card to be added
    */
   public void addCard(Card c) {
      if (c == null)
         throw new NullPointerException("Can't add a null card to a hand.");
      hand.add(c);
   }
   
   /**
    * @return the Hand represented by an ArrayList of Cards
    */
   public ArrayList<Card> getHand() {
	   return hand;
   }
  

   /**
    * Removes a specific Card from the Hand
    * @param c: the Card to be removed
    */
   public void removeCard(Card c) {
      hand.remove(c);      
   }

   /**
    * Removes a Card from the Hand located in a specific position
    * @param position: position of the Card to be removed
    */
   public void removeCard(int position) {
      if (position < 0 || position >= hand.size())
         throw new IllegalArgumentException("Position does not exist in hand: "
               + position);
      hand.remove(position);
   }
      
   /**
    * 
    * @param co the Combination including the Cards to be removed
    * @return a duplicate of current Hand without the Cards found in the Combination
    */
   
   
   public Hand removeCombination(Combination co) {
	   Hand h = this.copyHand();
	   Iterator<Card> iter = co.getCardList().iterator();
	   while(iter.hasNext())
		   h.removeCard(iter.next());	   
	   return h;
   }
   
   
   /**
    * removes cards found in a combination from current hand
    * @param co the combinations including the cards to be removed
    */
   
   public void removeCombfromThisHand(Combination co) {	   	 
	   Iterator<Card> iter = co.getCardList().iterator();	   
	   while (iter.hasNext()) {		   
		   this.removeCard(iter.next());
	   }	   
   }
   
   /**
    * 
    * @return the number of the cards currently in the Hand
    */
   public int getCardCount() {
      return hand.size();
   }
   
   /**
    * gets a Card in a specified position
    * @param position where the Card is located
    * @return Card requested
    */
   public Card getCard(int position) {
      if (position < 0 || position >= hand.size())
         throw new IllegalArgumentException("Position does not exist in hand: "
               + position);
       return (Card)hand.get(position);
   }
   
   public Vector<ImageIcon> getImageCardList() {
	   Vector<ImageIcon> cardList = new Vector<ImageIcon>(); 
	   Iterator<Card> iter= hand.iterator();
	   while (iter.hasNext()) {
		   Card tempCard = iter.next();
		   cardList.add(new ImageIcon(Card.getCardFilename(tempCard.getValue(), tempCard.getSuit())));
	   }
	   return cardList;
   }
   
      
   /**
    * Makes a deep duplicate of current Hand 
    * @return the cloned Hand
    */
   public Hand copyHand () {
	   Hand handCopy = new Hand();
	   for (int i=0; i<this.getCardCount(); i++)
		   handCopy.addCard(this.getCard(i));	   		   
	   return handCopy;
   }
   
   /**
    * Sorts the Hand by value. Cards with the same value are sorted by suit 
    */
   public void sortByValue() {
      ArrayList<Card> newHand = new ArrayList<Card>();
      while (hand.size() > 0) {
         int pos = 0;  // Position of minimal card.
         Card c = (Card)hand.get(0);  // Minimal card.
         for (int i = 1; i < hand.size(); i++) {
            Card c1 = (Card)hand.get(i);
            if ( c1.getValue() < c.getValue() ||
                    (c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ) {
                pos = i;
                c = c1;
            }
         }
         hand.remove(pos);
         newHand.add(c);
      }      
      hand = newHand;
   }
   
   /**
    * Sorts Hand by suit. Cards with same suit are sorted by value
    */
   public void sortBySuit() {
	      ArrayList<Card> newHand = new ArrayList<Card>();
	      while (hand.size() > 0) {
	         int pos = 0;  // Position of minimal card.
	         Card c = (Card)hand.get(0);  // Minimal card.
	         for (int i = 1; i < hand.size(); i++) {
	            Card c1 = (Card)hand.get(i);
	            if ( c1.getSuit() < c.getSuit() ||
	                    (c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ) {
	                pos = i;
	                c = c1;
	            }
	         }
	         hand.remove(pos);
	         newHand.add(c);
	      }
	      hand = newHand;
	   }
   
   /**
    * String representation of the hand
    */
   public @Override String toString() {

//	   return hand.toString();
	   String str = new String();

	   for (int i=0; i<this.hand.size(); i++) {
		   str += "\n" + this.hand.get(i).toString();
	   }
	  str += "\n";
	  return str;
	  
   }
	
	/**
	 * Searches all possible combinations of this hand
	 * @param: the type of Combination to be searched	
	 */
   
	public Combination findCombinations(int code) {		
		switch(code) {
		case Combination.PAIR: return this.findPair();
		case Combination.THREE: return this.findThreeOfAKind();
		case Combination.STRAIGHT: return this.findStraight();
		case Combination.STAIR:	return this.findStairs();		
		case Combination.BOMBFOUR: return this.findBombFourOfAKind();
		case Combination.BOMBFLUSH: return this.findBombFlush();
		case Combination.KING: return this.findKing();
		case Combination.ACE:	return this.findAce();
		
		default:	return null;
		}		   		   			
	}
	
   
	/**
	 * Creates a list of the combinations to be searched
	 * @return the list
	 */
	public static ArrayList<Integer> createListofSearchCombinations(int start, int end){
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i=start; i<=end; i++)
			a.add(i);
		return a;
	}

	/**
	 * Finds the first pair of Cards  
	 * @return: the pair
	 */
	
	   public Combination findPair() {
		   int i=0;		   	   		   
		   		   		   	  
		   Hand h = this.copyHand();		   

		   while (i<h.getCardCount()-1){
			   int currentValue=h.getCard(i).getValue();
			   if (currentValue==h.getCard(i+1).getValue()) {	
				   
				   ArrayList<Card> combination = new ArrayList<Card>();
				   combination.add(h.getCard(i));
				   combination.add(h.getCard(i+1));
				   Combination pair = new Combination(combination, Combination.PAIR, currentValue, currentValue, 2);
				  	
				   return pair;
			   }
			   i++;
		   }
		   return null;		   
	   }
	   	 
	
	/**
	 * Finds the first three Cards of the same value 
	 * @return these cards as a Combination 
	 */
	
	   public Combination findThreeOfAKind() {
		   int a,b,c, i=0;		   
		   
		   Hand h = this.copyHand();		   
		   ArrayList<Card> combination = new ArrayList<Card>();
		   
		   while (i<h.getCardCount() -2) {
			   a=h.getCard(i).getValue();
			   b=h.getCard(i+1).getValue();
			   c=h.getCard(i+2).getValue();
			   if (a==b && b==c) {				   
				   combination.add(h.getCard(i));
				   combination.add(h.getCard(i+1));
				   combination.add(h.getCard(i+2));
				   Combination three = new Combination(combination, Combination.THREE, a, a, 3);				  
				   return three;
			   }
			   i++;
		   }
		   return null;
	   }
	
	   
	 /**
	 * Finds the largest straight (5 or more cards of consecutive value)	   	
	 * @return the Cards as a Combination
	 */
	
	   public Combination findStraight() {		   
		   int number=1, distance;		  
		   Card lastCard;
		   Hand h = this.copyHand();		   
		   ArrayList<Card> combination = new ArrayList<Card>();
		   if(h.getCardCount()>0) {
			   lastCard = h.getCard(0);		   
			   for (int i=0;i<h.getCardCount()-1;i++) {
				   distance = h.getCard(i+1).getValue()-h.getCard(i).getValue();			   
				   if(distance==1){				   
					   number++;
					   combination.add(lastCard);
					   lastCard = h.getCard(i+1);					   
				   }	   
				   if (distance>1) {
					   if (number>=5)  {
						   break;					   
					   }				   					  
					   else {					   
						   combination.clear();					   
						   number=1;
						   lastCard = h.getCard(i+1);					
					   }					   
				   }					   				   			   
			   }		   
			   if(number>=5) {			   
				   combination.add(lastCard);
				   Combination straight = new Combination(combination, Combination.STRAIGHT, 
						   lastCard.getValue(), 0, number);
				   straight.setAuxValue(straight.getAverageCardValue());
				   return straight;
			   }		   	
		   }
		   return null;
	   }
	   
	   /**	     
	    * Finds the biggest straight flush (5 or more Cards of consecutive value and same suit)
	    * @return the cards as a Combination
	    */
	
	   public Combination findBombFlush () {		  
		   Hand h = this.copyHand();
		   h.sortBySuit();
		   //Hand temp = new Hand(this.getHand().size());
		   for (int suit=0; suit<4; suit++) {
			  Hand temp = new Hand();
			  if(!hand.isEmpty()) {
				  int currentSuit = suit;
				  for (int i=0; i<h.getCardCount(); i++) {
					  if (currentSuit==h.getCard(i).getSuit()) {
						  temp.addCard(h.getCard(i));
					  }					   				  
				  }		   		 
				  Combination flush = temp.findStraight();
				  if (flush!=null) {
					  flush.setType(Combination.BOMBFLUSH);
					  return flush;
				  }
			  }   
		   }
		   return null;
		   	 
	   }
	   
	   /**
	    * Finds 2 or more consecutive pairs (pairs of consecutive value)
	    * @return the cards as a Combination
	    */
	
		public Combination findStairs() {	
			int number=1;
			boolean searchCompleted=false;
			Combination lastPair = new Combination();
			Combination pair = new Combination();
			CombList stairs = new CombList();
			CombList allPairs = new CombList();
			Hand h = this.copyHand();
			//finds all pairs
			pair=h.findPair();		
			do{
				if (pair!=null) {				
					allPairs.addComb(pair);
					h.removeCombfromThisHand(pair);				
					pair=h.findPair();
				}
			}while(pair!=null);
			
			if (!allPairs.getList().isEmpty()) {			
				Iterator<Combination> iter = allPairs.getList().iterator();
				pair=iter.next();
				if (pair!=null) {				
					while (iter.hasNext()&&!searchCompleted) {
						lastPair=iter.next();
						if(lastPair.getValue()==pair.getValue()+1) {						
							number++;						
							stairs.addComb(pair);
							pair=lastPair;
						}					
						else if(lastPair.getValue()>pair.getValue()+1) {
							if (number>=2) {
								searchCompleted=true;
								break;									
							}							
							else {		
								pair=lastPair;
								stairs.clear();							
								number=1;
							}					
						}
						else  continue;
					}	
						if (number>=2) {					
						stairs.addComb(pair);							
						ArrayList<Card> combination = stairs.mergeMultiple().getCardList();
						lastPair = stairs.getList().get(stairs.getList().size()-1);						
						Combination finalStair = new Combination(combination, Combination.STAIR, lastPair.getValue(), 
								0, number*2);
						finalStair.setAuxValue(finalStair.getAverageCardValue());
						return finalStair;
					}
				}//end if null
			}
			return null;	
		}
	  			
		/**
		 * Finds 4 cards with the same value
		 * @return the cards as a Combination
		 */
	
	   public Combination findBombFourOfAKind() {
		   int i=0;		
		   boolean sameValue;		    		   
		   Hand h = this.copyHand();		   
		   
		   
		   while (i<h.getCardCount() -3) {
			   sameValue=(h.getCard(i).getValue()==h.getCard(i+1).getValue())&&(h.getCard(i).getValue()==h.getCard(i+2).getValue()
					   &&h.getCard(i).getValue()==h.getCard(i+3).getValue());	 
			   if (sameValue) {		
				   ArrayList<Card> combination = new ArrayList<Card>();
				   combination.add(h.getCard(i));
				   combination.add(h.getCard(i+1));
				   combination.add(h.getCard(i+2));
				   combination.add(h.getCard(i+3));
				   Combination bomb = new Combination(combination, Combination.BOMBFOUR, 
						   h.getCard(i).getValue(), h.getCard(i).getValue(), 4);				  				  
				   return bomb;
			   }
			   i++;			   			   
		   }
		   return null;
	   }
	   
	   /**
	    * finds a King
	    * @return the King as a combination
	    */
	
	   public Combination findKing() {		   
		   Iterator<Card> iter = this.hand.iterator();	   
		   while (iter.hasNext()) {
			   Card temp=iter.next();
			   if (temp.getValue()==13) {
				   ArrayList<Card> combination = new ArrayList<Card>();
				   combination.add(temp);
				   Combination king = new Combination(combination, Combination.KING, Card.KING, Card.KING, 1);					   
				   return king;
			   }				   
		   }		   
		   return null;
	   }
	   
	   /**
	    * finds an Ace
	    * @return the ace as a combination
	    */
	
	   public Combination findAce() {		   
		   Iterator<Card> iter = this.hand.iterator();	   
		   while (iter.hasNext()) {
			   Card temp=iter.next();
			   if (temp.getValue()==14) {
				   ArrayList<Card> combination = new ArrayList<Card>();
				   combination.add(temp);
				   Combination ace = new Combination(combination, Combination.ACE, Card.ACE, Card.ACE, 1);				   
				   return ace;
			   }				   
		   }		   
		   return null;
	   }
	   
	   public DefaultListModel getListModel() {
		   DefaultListModel model = new DefaultListModel();
		   for (int i=0; i<this.getCardCount(); i++)
			   model.addElement(this.getCard(i).image);
		   return model;
	   }
	   
	   @Override
	   public void paintComponent (Graphics g){
		   for (int i = 0; i < getCardCount(); i++){  			
	          	getCard(i).drawCard(g, (int)( this.getX() +i*15.75), this.getY());
	        }
		   g.drawRect(getX(), getY(), 300, 100);
	   }
	   

}//end of Hand

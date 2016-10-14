import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

public class Combination extends JPanel{
	
	public static final int SINGLE = 1;
	public static final int PAIR = 2;
	public static final int THREE = 3;
	public static final int STAIR = 4;
	public static final int FULL = 5;
	public static final int STRAIGHT = 6;
	public static final int BOMBFOUR = 7;
	public static final int BOMBFLUSH = 8;
	public static final int KING = 9;
	public static final int ACE = 10;
	
	/**
	 * The list of Cards that form the Combination
	 */
	private ArrayList<Card> comb;
	
	/**
	 * Represents the type of the Combination and must be one of the values
	 * Combination.SINGLE, Combination.PAIR, Combination.THREE, Combination.STAIR, 
	 * Combination.FULL, Combination.STRAIGHT, Combination.BOMBFOUR, Combination.BOMBFLUSH,
	 * Combination.KING, Combination.ACE.
	 */
	private int type;
	
	/**
	 * The Combination's value: Takes the value of the highest card in the combination.
	 * Exception in combination full house: it takes the three of a kind value
	 */
	private int value;
	
	/**
	 * The auxiliary value takes the following values accordingly to the Combination:
	 * Combinations Single, Pair, Three, Bomb Four, King, Ace : auxValue = value
	 * Combinations Stair, Straight, Bomb Flush : Takes the average Card value
	 * Combination Full : Takes the value of the pair
	 */
	private float auxValue;
	
	/**
	 * The number of Cards which represent the Combination
	 */
	private int numberOfCards;


	
	/**
	 * cTor
	 * creates a null combination
	 */
	public Combination() {
		comb = new ArrayList<Card>();
		this.setSize(300, 100);
		setVisible(true);
	}
	
	/**
	 * cTor 
	 * creates a Combination setting the parameter fields
	 * @param c: the list of Cards who represent the Combinations
	 * @param combType: the type of the Combination
	 * @param combValue: the value of the Combination
	 * @param combAuxValue: the auxiliary value of the Combination
	 * @param combNumberOfCards: the number of Cards which form the Combination
	 */
	public Combination(ArrayList<Card> c, int combType, int combValue, 	
			float combAuxValue, int combNumberOfCards) 
	{
		comb = c;
		type = combType;
		value = combValue;
		auxValue = combAuxValue;
		numberOfCards = combNumberOfCards;
		this.setSize(300, 100);
		setVisible(true);
	}
		
	/**
	 * adds Card to current Combination
	 * @param c
	 */
	public void addCard(Card c) {
		comb.add(c);
	}
	
	/**
	 * removes card from 
	 * @param c
	 */
	public void removeCard(Card c){
		comb.remove(c);
	}
	
	/**
	 * checks if current Combination contains a Card representated
	 * by its parameters value and suit
	 * @param value
	 * @param suit
	 * @return
	 */
	public boolean containsCard(int value, int suit) {
		Iterator<Card> iter = this.comb.iterator();
		while (iter.hasNext()) {
			Card temp = iter.next();
			if ((temp.value==value)&&(temp.suit==suit))
				return true;
		}
		return false;
				
	}
	
	/**
	 * returns a range of cards from the combination
	 * useful for straight and flush
	 * @param a,b the upper and lower limits of the range
	 * i.e. if a = 0 and b = 4 the first 5 cards will be returned as a new Combination 
	 */
	public Combination getRangeOfCards(int a, int b) {
		try {
		Combination temp = new Combination();
			for (int i=a; i<b; i++) {
				temp.addCard(this.getCardList().get(i));
			}
			temp.setType(this.type);
			temp.setNumberOfCards(temp.getCardList().size());
			temp.setValue(temp.getCardList().get(temp.getNumberOfCards()-1).getValue());
			temp.setAuxValue(temp.getCardList().get(0).getValue());
			return temp;
		}
		catch (IndexOutOfBoundsException e) {
			GameDemo.message.append("Error: Index Out Of Bounds Exception in getRangeOfCards: a=" + a + " b=" + b + " size=" + this.getCardList().size());
			return null;
		}
	}
	
	
	/**
	 * clears combination
	 */
	public void clear() {
		this.comb.clear();
	}
	
	/**
	 * creates a deep clone of current combination
	 * @return the clone
	 */
	public Combination copyComb() {
		Combination newComb = new Combination();		
		Iterator <Card> iter = this.comb.iterator();
		while (iter.hasNext())			
			newComb.addCard(iter.next());
		newComb.setValue(this.getValue());
		newComb.setAuxValue(this.getAuxValue());
		newComb.setNumberOfCards(this.getNumberOfCards());
		newComb.setType(this.getType());
		return newComb;		
	}
	
	/**
	 * Creates a deep clone of current Combination and removes Card by position
	 * @param position
	 * @return
	 */
	public Combination removeCard(int position) {
		Combination temp = this.copyComb();
		temp.comb.remove(position);
		temp.setValue(temp.getCardList().get(temp.getCardList().size()-1).getValue());
		temp.setAuxValue(temp.getCardList().get(0).getValue());
		temp.setNumberOfCards(temp.getCardList().size());
		return temp; 
	}
	
	
	/**	  
	 * @return the current Combination
	 */ 	
	public ArrayList<Card> getCardList() {
		return this.comb;
	}		
	
	/**	 
	 * @return the type of the current combination
	 */
	public int getType () {
		return this.type;
	}
	
	/**
	 * Sets the type of the combination
	 * @param theType: number that corresponds to the type of combination
	 */
	public void setType (int theType) {
		this.type = theType;
	}
	
	/**
	 * @return value of the combination
	 * if n of a kind: returns the value of one card
	 * if stairs: returns the value of the highest pair
	 * if full house: returns the value of the "three of a kind"
	 * if straight(bomb or not): returns the highest card of the straight
	 */
	public int getValue () {
		return this.value;
	}
	
	/**
	 * Sets the value of the combination
	 * @param theValue
	 */
	public void setValue(int theValue) {
		this.value = theValue;
	}
	
	/**
	 * @return number of Cards in current Combination
	 */
	public int getNumberOfCards() {
		return this.numberOfCards;
	}
	
	/**
	 * Sets number of Cards in current Combination
	 * @param number
	 */
	public void setNumberOfCards(int number) {
		this.numberOfCards=number;
	}
	/**
	 * @return the auxiliary value
	 */
	public float getAuxValue() {
		return this.auxValue;
	}
	
	/**
	 * Sets the auxiliary value
	 * @param theValue: auxValue to be set
	 */
	public void setAuxValue(float theValue) {
		this.auxValue=theValue;
	}
	
	/**
	 * string representation of the Combination
	 */
	public @Override String toString() {
		return comb.toString();
	}
	
	
	
	/**
	 * string representation of the type of the Combination
	 * @param type
	 * @return
	 */
	public static String typeToString(int type) {
		switch(type) {
		case Combination.SINGLE:	return "single";
		case Combination.PAIR:		return "pair";
		case Combination.THREE:		return "three";
		case Combination.STAIR:		return "stairs";
		case Combination.FULL:		return "full";
		case Combination.STRAIGHT:	return "straight";
		case Combination.BOMBFOUR:	return "four";
		case Combination.BOMBFLUSH:	return "flush";
		case Combination.KING:		return "king";
		case Combination.ACE:		return "ace";
		default:					return "invalid combination type";
		}
	}
	
	/**
	 * @return the average value of the Combination Cards
	 */
	public float getAverageCardValue() {
		int sum = 0;
		for (int i=0; i<this.getNumberOfCards(); i++) {
			sum += this.getCardList().get(i).getValue(); 
		}
		return sum/this.getNumberOfCards();
	}

	
	/**
	 * Merges 2 combinations into one
	 * The final Combination value is the first Combination value
	 * the second Combination value is given as auxValue to new Combination
	 * this function is used to construct full houses
	 * @param co: Combination to merge with current Combination
	 * @param type: type of new Combination
	 */	
	public static Combination merge(Combination firstComb, Combination secondComb, int theType) {		
		Iterator <Card> iter = firstComb.getCardList().iterator();
		Iterator <Card> secondIter = secondComb.getCardList().iterator();
		Combination merged = new Combination();
		while (iter.hasNext())			
			merged.addCard(iter.next());
		while (secondIter.hasNext())
			merged.addCard(secondIter.next());
		merged.setValue(firstComb.getValue());
		merged.setAuxValue(secondComb.value);
		merged.setNumberOfCards(merged.getCardList().size());
		merged.setType(theType);
		return merged;
	}
	
		@Override
	   public void paintComponent (Graphics g){
			g.setColor(Color.pink);
			g.drawRect(this.getX(), this.getY(), 300, 100);
		   for (int i = 0; i < numberOfCards; i++){  			
	          	getCardList().get(i).drawCard(g, (int)( this.getX() +i*15.75), this.getY());	          	
	        }
		   
	   }
	
}//end of class Combination

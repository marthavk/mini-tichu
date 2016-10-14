import java.util.ArrayList;
import java.util.Scanner;


//represents a deck of playing cards
public class Deck  {

	/**
	 * The list of Cards forming the deck
	 */
	private ArrayList<Card> deck;
	
	/**
	 * The number of Cards already dealt
	 */
	private int cardsUsed;
	
	/**
	 * The list of Cards already dealt.
	 */
	public ArrayList<Card> cardsDealt;
  
	
   
	/**
	 * cTor
	 * constructs a 52 card tichu deck without the special cards
	 */
     public Deck() {    	     	 
    	 deck = new ArrayList<Card>();
    	 for ( int suit = 0; suit <= 3; suit++ ) {
    		 for ( int value = 2; value <= 14; value++ ) {    			
    			 deck.add(new Card(value, suit));
    		}    		     		 
      }
    	cardsUsed = 0;
        cardsDealt = new ArrayList<Card>();
    }//end of public Deck()
      
   /**
    * shuffles the deck  
    */
   public void shuffle() {
      for ( int i = deck.size()-1; i > 0; i-- ) {
         int rand = (int)(Math.random()*(i+1));
         Card temp = deck.get(i);
         deck.set(i, deck.get(rand));
         deck.set(rand, temp);
      }
      cardsUsed = 0;
   }
        
   /**
    * @return the cards not dealt yet
    */
   public int cardsLeft() {
      return deck.size() - cardsUsed;
   }
   
   /**
    * clears the cards that are already dealt
    */
   public void clearCardsDealt() {
	   this.cardsDealt.clear();
   }
   

   /**
    * Removes the next card from the deck and return it.  It is illegal
    * to call this method if there are no more cards in the deck.  You can
    * check the number of cards remaining by calling the cardsLeft() function.
    * @return the card which is removed from the deck.
    * @throws IllegalStateException if there are no cards left in the deck
    */
   public Card deal() {	  
	   boolean cardAlreadyDealt;
		  do {
			  cardAlreadyDealt = false;
			  if (cardsUsed == deck.size())
			         throw new IllegalStateException("No cards are left in the deck.");
			  cardsUsed++;
			  if (cardsDealt.contains(deck.get(cardsUsed-1))) 
				  cardAlreadyDealt = true;
			  		  			  	     		     		    
		  }while (cardAlreadyDealt);
		  
		  cardsDealt.add(deck.get(cardsUsed-1));	
	      return deck.get(cardsUsed-1);
      
   }
   
   /**
    * deals n Cards to Player p
    * @param n
    * @param p
    */
   
   public void dealANumberOfCards(int n, Player p) {
	   p.getPlayerHand().clear();
	   for( int i=0; i<n; i++) {
		   Card dealt = deal();
		   p.getPlayerHand().addCard(dealt);
		   this.cardsDealt.add(dealt);	   }
   }
   
   /**
    * Lets the user to define the Cards to deal to a Player
    * also checks if the Card defined by the user is already dealt and 
    * in that case displays a relevant error message
    * @param n
    * @param p
    */
   
   public void dealCardsManually(int n, Player p) {
	   p.getPlayerHand().clear();
	   boolean cardAlreadyDealt;	
	   Scanner scanIn;	   
	   scanIn = new Scanner(System.in);	   
	   int value, suit;
	   for (int i=0; i<n; i++) {
		   do {
			   cardAlreadyDealt=false;
			   GameDemo.message.append("Enter value and suit. For example for a 6 of pagoda enter:6 2");		    		    		    
				   value = scanIn.nextInt();
			       suit = scanIn.nextInt();	 
				                   
			       Card c = this.getCard(value, suit);
			       
			       if (cardsDealt.contains(c)) {
			    	   GameDemo.message.append("ERROR: this card is already dealt");
			    	   cardAlreadyDealt = true;
			       }
			       else if (c==null) {
			    	   GameDemo.message.append("ERROR: invalid value or suit, please try again");
			    	   cardAlreadyDealt = true;
			       }
			       else {
			    	   GameDemo.message.append("You added " + c.toString());
			    	   p.hand.addCard(c);
				       cardsDealt.add(c);
			       }   			   			   		       			       
		   }while (cardAlreadyDealt);	
	  }		   	   	   	   	  
   }
   
   
   /**
    * gets a Card for the deck defined by its value and suit
    * @param value
    * @param suit
    * @return the Card in the Deck, 
    * null if the Card does not exist in the Deck
    */
   public Card getCard(int value, int suit) {
	   for (int i=0; i<deck.size(); i++) {
		   if ((deck.get(i).getValue()==value)&&(deck.get(i).getSuit()==suit))
			   return deck.get(i);
	   }
	   return null;
	   
   }
   
  
} // end class Deck

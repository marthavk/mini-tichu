import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Card extends JPanel{
	
       // Codes for the 4 suits, plus Special Cards.
	   public final static int DIAMONDS = 0;   
	   public final static int SWORD = 1;
	   public final static int PAGODA = 2;
	   public final static int STAR = 3;
	  
	   /*
	    * Codes for the non-numeric cards.
	    * Cards 2 through 10 have their
	    * numerical values for their codes.
	    * Ace has the greatest value.
	    */
	   public final static int JACK = 11;	
	   public final static int QUEEN = 12; 
	   public final static int KING = 13;
	   public final static int ACE = 14;

	   	
	   /**
	    * The card's suit, one of the constants DIAMONDS, SWORDS, PAGODAS,
	    * or STARS The suit cannot be changed after the card is
	    * constructed.
	    */
	   public final int suit; 
	   
	   /**
	    * The card's value.  For a normal card, this is one of the values
	    * 2 through 14, with 14 representing ACE.  The value cannot be changed
	    * after the card is constructed.
	    */
	   public final int value;
	   
	  
	   public  ImageIcon image;
	  
	 
	   /**
	    * Creates a card with a specified suit and value.
	    * @param theValue: the value of the new card.  
	    * You can use the constants Card.ACE, Card.JACK, Card.QUEEN, and Card.KING.  	
	    * @param theSuit: the suit of the new card.  This must be one of the values
	    * Card.JADE, Card.SWORD, Card.PAGODA, or Card.STAR
	    * @throws IllegalArgumentException if the parameter values are not in the
	    * permissible ranges
	    */
	   public Card(int theValue, int theSuit) {
	      if (theSuit != DIAMONDS && theSuit != SWORD && theSuit != PAGODA && 
	            theSuit != STAR )
	         throw new IllegalArgumentException("Illegal playing card suit");
	      if ((theValue < 0 || theValue > Card.ACE))
	         throw new IllegalArgumentException("Illegal playing card value");
	      value = theValue;
	      suit = theSuit;	      
		  image = new ImageIcon(Card.getCardFilename(theValue, theSuit));
		  setSize(100,75);
		  setBackground(Color.darkGray);
		  setVisible(true);
	     // System.out.println(System.getProperty("user.dir"));
	      
	     
	        //		  ImageIO.read(this.getClass().getResource(getCardFilename(theValue, theSuit)));
	   
	     /* catch (IOException e) {
	    	   //e.printStackTrace();
	    	  System.out.println(System.getProperty("user.dir"));
	    	   System.out.println("Image not found: " + getCardFilename(theValue, theSuit));
	      } 
	    /* try {                
	          image = ImageIO.read(new File(getCardFilename(theValue, theSuit)));
	                    image = ImageIO.read(this.getClass().getResource(System.getProperty("user.dir") + getCardFilename(theValue, theSuit)));
	       } catch (IOException ex) {
	    	   System.out.println(ex.getMessage());
	    	  
	       }*/
	   }
	/*   
	   public Card(int theValue, int theSuit, ImageIcon img) {
		   if (theSuit != DIAMONDS && theSuit != SWORD && theSuit != PAGODA && 
		            theSuit != STAR )
		         throw new IllegalArgumentException("Illegal playing card suit");
		      if ((theValue < 0 || theValue > Card.ACE))
		         throw new IllegalArgumentException("Illegal playing card value");
		      value = theValue;
		      suit = theSuit;
		      try {                
		          image = ImageIO.read(new File("image name and path"));
		       } catch (IOException ex) {
		            // handle exception...
		       }
	   }*/

	   /**
	    * 
	    * @return suit of current Card
	    */
	   public int getSuit() {
	      return suit;
	   }	   
	   
	   /**
	    * 
	    * @return value of current Card
	    */
	   public int getValue() {
	      return value;
	   }
	   
	 
	   	   
	   /**
	    * 
	    * @return String representation of the card's suit.
	    */
	   public static String getSuitAsString(int cardSuit) {
	      switch ( cardSuit ) {
	      case DIAMONDS:	return "Diamonds";
	      case SWORD:   return "Swords";
	      case PAGODA: 	return "Pagodas";
	      case STAR:   	return "Stars";	      
	      default:      return "Unknown suit";
	      }
	   }
	   	  
	   /**
	    * @return String representation of the card's value.
	    */
	   public static String getValueAsString(int cardValue) {
	         switch ( cardValue ) {
	         case 2:   return "2";
	         case 3:   return "3";
	         case 4:   return "4";
	         case 5:   return "5";
	         case 6:   return "6";
	         case 7:   return "7";
	         case 8:   return "8";
	         case 9:   return "9";
	         case 10:  return "10";
	         case 11:  return "Jack";
	         case 12:  return "Queen";
	         case 13:  return "King";
	         case 14:  return "Ace";
	         default:  return "ERROR";	         
	      }
	   }
	   
	   /**
	    * @return String representation of the card
	    */
	   public @Override String toString() {	 
	         return getValueAsString(value) + " of " + getSuitAsString(suit);
	   }
	   
	   public static String getCardFilename(int cardValue, int cardSuit) {
		   return getValueAsString(cardValue) + "of" + getSuitAsString(cardSuit) + ".gif";
	   }
	   
	   public void drawCard(Graphics g, int x, int y) {
		   BufferedImage tempImage;
			try {
				tempImage = ImageIO.read(new File(Card.getCardFilename(getValue(), getSuit())));
				g.drawImage(tempImage, x, y, null);
			//	System.out.println("Card painted:" + card.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				GameDemo.message.append(e.getMessage());
				e.printStackTrace();
			}
	   }

	  
	/*   public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, null);            
	    }
	  */ 
	   
	   

	} // end class Card


/**
 * Contains 5 sets of testing Hands of 13 Cards (for each Player)
 * plus a mini set of one testing Hand of 8 Cards (for each Player)
 *
 */

public class TestingHands {
	
	public static void test1(Game game) {
		   for (int i=0; i<4; i++)
			   game.players[i].getPlayerHand().clear();
		   
		   /**ADD CARDS TO PLAYER #0**/	 	
		   game.players[0].getPlayerHand().addCard(game.tichuDeck.getCard(3, Card.PAGODA));
		   game.players[0].getPlayerHand().addCard(game.tichuDeck.getCard(5, Card.PAGODA));
		   game.players[0].getPlayerHand().addCard(game.tichuDeck.getCard(3, Card.DIAMONDS));
		   game.players[0].getPlayerHand().addCard(game.tichuDeck.getCard(6, Card.STAR));
		   game.players[0].getPlayerHand().addCard(game.tichuDeck.getCard(8, Card.SWORD));
		   game.players[0].getPlayerHand().addCard(game.tichuDeck.getCard(9, Card.STAR));
		   game.players[0].getPlayerHand().addCard(game.tichuDeck.getCard(10, Card.SWORD));
		   game.players[0].getPlayerHand().addCard(game.tichuDeck.getCard(Card.JACK, Card.PAGODA));
		   game.players[0].getPlayerHand().addCard(game.tichuDeck.getCard(Card.JACK, Card.DIAMONDS));
		   game.players[0].getPlayerHand().addCard(game.tichuDeck.getCard(Card.KING, Card.STAR));
		   game.players[0].getPlayerHand().addCard(game.tichuDeck.getCard(Card.QUEEN, Card.DIAMONDS));
		   game.players[0].getPlayerHand().addCard(game.tichuDeck.getCard(Card.ACE, Card.DIAMONDS));
		   game.players[0].getPlayerHand().addCard(game.tichuDeck.getCard(Card.ACE, Card.STAR));
		   
		   /**ADD CARDS TO PLAYER #1**/
		   game.players[1].getPlayerHand().addCard(game.tichuDeck.getCard(2, Card.STAR));
		   game.players[1].getPlayerHand().addCard(game.tichuDeck.getCard(3, Card.SWORD));
		   game.players[1].getPlayerHand().addCard(game.tichuDeck.getCard(5, Card.STAR));
		   game.players[1].getPlayerHand().addCard(game.tichuDeck.getCard(6, Card.SWORD));
		   game.players[1].getPlayerHand().addCard(game.tichuDeck.getCard(6, Card.DIAMONDS));
		   game.players[1].getPlayerHand().addCard(game.tichuDeck.getCard(7, Card.DIAMONDS));
		   game.players[1].getPlayerHand().addCard(game.tichuDeck.getCard(7, Card.PAGODA));
		   game.players[1].getPlayerHand().addCard(game.tichuDeck.getCard(7, Card.SWORD));
		   game.players[1].getPlayerHand().addCard(game.tichuDeck.getCard(8, Card.PAGODA));
		   game.players[1].getPlayerHand().addCard(game.tichuDeck.getCard(8, Card.DIAMONDS));
		   game.players[1].getPlayerHand().addCard(game.tichuDeck.getCard(9, Card.DIAMONDS));
		   game.players[1].getPlayerHand().addCard(game.tichuDeck.getCard(Card.JACK, Card.STAR));
		   game.players[1].getPlayerHand().addCard(game.tichuDeck.getCard(Card.QUEEN, Card.STAR));		   	   		   
		   
		   /**ADD CARDS TO PLAYER #2**/		   
		   game.players[2].getPlayerHand().addCard(game.tichuDeck.getCard(3, Card.STAR));
		   game.players[2].getPlayerHand().addCard(game.tichuDeck.getCard(5, Card.DIAMONDS));
		   game.players[2].getPlayerHand().addCard(game.tichuDeck.getCard(5, Card.SWORD));
		   game.players[2].getPlayerHand().addCard(game.tichuDeck.getCard(9, Card.SWORD));
		   game.players[2].getPlayerHand().addCard(game.tichuDeck.getCard(10, Card.STAR));
		   game.players[2].getPlayerHand().addCard(game.tichuDeck.getCard(10, Card.PAGODA));
		   game.players[2].getPlayerHand().addCard(game.tichuDeck.getCard(Card.JACK, Card.SWORD));
		   game.players[2].getPlayerHand().addCard(game.tichuDeck.getCard(Card.QUEEN, Card.PAGODA));
		   game.players[2].getPlayerHand().addCard(game.tichuDeck.getCard(Card.QUEEN, Card.SWORD));
		   game.players[2].getPlayerHand().addCard(game.tichuDeck.getCard(Card.KING, Card.DIAMONDS));
		   game.players[2].getPlayerHand().addCard(game.tichuDeck.getCard(Card.KING, Card.SWORD));
		   game.players[2].getPlayerHand().addCard(game.tichuDeck.getCard(Card.ACE, Card.PAGODA));
		   game.players[2].getPlayerHand().addCard(game.tichuDeck.getCard(Card.ACE, Card.SWORD));
		   
		   /**ADD CARDS TO PLAYER #3**/
		   game.players[3].getPlayerHand().addCard(game.tichuDeck.getCard(2, Card.SWORD));
		   game.players[3].getPlayerHand().addCard(game.tichuDeck.getCard(2, Card.DIAMONDS));
		   game.players[3].getPlayerHand().addCard(game.tichuDeck.getCard(2, Card.PAGODA));
		   game.players[3].getPlayerHand().addCard(game.tichuDeck.getCard(4, Card.DIAMONDS));
		   game.players[3].getPlayerHand().addCard(game.tichuDeck.getCard(4, Card.STAR));
		   game.players[3].getPlayerHand().addCard(game.tichuDeck.getCard(4, Card.PAGODA));
		   game.players[3].getPlayerHand().addCard(game.tichuDeck.getCard(4, Card.SWORD));
		   game.players[3].getPlayerHand().addCard(game.tichuDeck.getCard(6, Card.PAGODA));
		   game.players[3].getPlayerHand().addCard(game.tichuDeck.getCard(7, Card.STAR));
		   game.players[3].getPlayerHand().addCard(game.tichuDeck.getCard(8, Card.STAR));
		   game.players[3].getPlayerHand().addCard(game.tichuDeck.getCard(9, Card.PAGODA));
		   game.players[3].getPlayerHand().addCard(game.tichuDeck.getCard(10, Card.DIAMONDS));
		   game.players[3].getPlayerHand().addCard(game.tichuDeck.getCard(Card.KING, Card.PAGODA));
		  
		   for (int i=0; i<4; i++)
			   game.players[i].getPlayerHand().sortByValue();
		   
	}
	   /*
	public static void test2() {
		 for (int i=0; i<4; i++)
			   Game.players[i].getPlayerHand().clear();
		   
		   /**ADD CARDS TO PLAYER #0**/	 	
/*		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(2, Card.PAGODA));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(3, Card.PAGODA));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(4, Card.DIAMONDS));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(6, Card.STAR));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(7, Card.SWORD));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(8, Card.STAR));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(8, Card.SWORD));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(9, Card.PAGODA));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(9, Card.DIAMONDS));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(10, Card.STAR));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(10, Card.DIAMONDS));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(11, Card.DIAMONDS));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(13, Card.STAR));
		   
		   /**ADD CARDS TO PLAYER #1**/
/*		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(2, Card.STAR));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(2, Card.SWORD));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(3, Card.STAR));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(5, Card.SWORD));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(5, Card.DIAMONDS));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(8, Card.DIAMONDS));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(9, Card.STAR));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(9, Card.SWORD));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(11, Card.PAGODA));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(12, Card.DIAMONDS));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(12, Card.STAR));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(13, Card.DIAMONDS));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(13, Card.SWORD));		   	   		   
		   
		   /**ADD CARDS TO PLAYER #2**/		   
/*		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(2, Card.DIAMONDS));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(4, Card.PAGODA));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(4, Card.SWORD));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(6, Card.SWORD));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(6, Card.DIAMONDS));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(6, Card.PAGODA));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(7, Card.STAR));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(7, Card.PAGODA));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(7, Card.DIAMONDS));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(13, Card.PAGODA));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(14, Card.SWORD));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(14, Card.PAGODA));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(14, Card.DIAMONDS));
		   
		   /**ADD CARDS TO PLAYER #3**/
/*		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(3, Card.SWORD));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(3, Card.DIAMONDS));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(4, Card.STAR));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(5, Card.PAGODA));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(5, Card.STAR));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(8, Card.PAGODA));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(10, Card.SWORD));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(10, Card.PAGODA));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(11, Card.SWORD));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(11, Card.STAR));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(12, Card.SWORD));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(12, Card.PAGODA));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(14, Card.STAR));
		   
		   for (int i=0; i<4; i++)
			   Game.players[i].getPlayerHand().sortByValue();
	}
	*/
	
	/*
	public static void test3() {
		for (int i=0; i<4; i++)
			   Game.players[i].getPlayerHand().clear();
/*		   
		   /**ADD CARDS TO PLAYER #0**/	 	
/*		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(2, Card.PAGODA));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(4, Card.PAGODA));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(4, Card.DIAMONDS));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(5, Card.DIAMONDS));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(6, Card.DIAMONDS));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(7, Card.DIAMONDS));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(8, Card.DIAMONDS));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(8, Card.PAGODA));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(9, Card.SWORD));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(10, Card.STAR));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(11, Card.DIAMONDS));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(12, Card.DIAMONDS));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(11, Card.STAR));
		   
		   /**ADD CARDS TO PLAYER #1**/
/*		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(3, Card.STAR));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(4, Card.SWORD));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(5, Card.STAR));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(5, Card.PAGODA));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(6, Card.PAGODA));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(6, Card.SWORD));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(7, Card.STAR));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(8, Card.SWORD));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(12, Card.PAGODA));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(12, Card.SWORD));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(12, Card.STAR));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(13, Card.PAGODA));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(14, Card.SWORD));		   	   		   
		   
		   /**ADD CARDS TO PLAYER #2**/		   
/*		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(3, Card.DIAMONDS));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(3, Card.SWORD));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(5, Card.SWORD));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(7, Card.PAGODA));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(7, Card.SWORD));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(9, Card.PAGODA));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(10, Card.SWORD));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(10, Card.DIAMONDS));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(11, Card.SWORD));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(11, Card.PAGODA));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(13, Card.SWORD));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(14, Card.PAGODA));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(14, Card.DIAMONDS));
		   
		   /**ADD CARDS TO PLAYER #3**/
/*		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(2, Card.SWORD));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(2, Card.DIAMONDS));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(2, Card.STAR));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(3, Card.PAGODA));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(4, Card.STAR));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(6, Card.STAR));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(8, Card.STAR));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(9, Card.STAR));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(9, Card.DIAMONDS));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(10, Card.PAGODA));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(13, Card.STAR));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(13, Card.DIAMONDS));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(14, Card.STAR));
		   
		   for (int i=0; i<4; i++)
			   Game.players[i].getPlayerHand().sortByValue();
		
	}
	
	public static void test4() {
		for (int i=0; i<4; i++)
			   Game.players[i].getPlayerHand().clear();
		   
		   /**ADD CARDS TO PLAYER #0**/	 	
/*		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(4, Card.PAGODA));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(5, Card.PAGODA));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(6, Card.DIAMONDS));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(6, Card.STAR));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(7, Card.SWORD));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(7, Card.STAR));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(8, Card.SWORD));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(8, Card.PAGODA));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(10, Card.DIAMONDS));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(11, Card.STAR));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(11, Card.DIAMONDS));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(12, Card.DIAMONDS));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(13, Card.STAR));
		   
		   /**ADD CARDS TO PLAYER #1**/
/*		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(2, Card.STAR));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(2, Card.SWORD));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(4, Card.STAR));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(5, Card.DIAMONDS));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(5, Card.SWORD));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(7, Card.DIAMONDS));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(8, Card.STAR));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(9, Card.SWORD));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(9, Card.PAGODA));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(9, Card.STAR));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(10, Card.STAR));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(12, Card.PAGODA));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(14, Card.SWORD));		   	   		   
		   
		   /**ADD CARDS TO PLAYER #2**/		   
/*		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(2, Card.DIAMONDS));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(2, Card.PAGODA));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(3, Card.SWORD));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(3, Card.PAGODA));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(4, Card.DIAMONDS));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(6, Card.PAGODA));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(8, Card.DIAMONDS));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(10, Card.SWORD));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(11, Card.PAGODA));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(12, Card.STAR));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(12, Card.SWORD));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(14, Card.PAGODA));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(14, Card.DIAMONDS));
		   
		   /**ADD CARDS TO PLAYER #3**/
/*		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(3, Card.STAR));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(3, Card.DIAMONDS));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(4, Card.SWORD));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(5, Card.STAR));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(6, Card.SWORD));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(7, Card.PAGODA));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(9, Card.DIAMONDS));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(10, Card.PAGODA));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(11, Card.SWORD));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(13, Card.DIAMONDS));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(13, Card.SWORD));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(13, Card.PAGODA));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(14, Card.STAR));
		   
		   for (int i=0; i<4; i++)
			   Game.players[i].getPlayerHand().sortByValue();
	
	}
	
	public static void test5() {
		for (int i=0; i<4; i++)
			   Game.players[i].getPlayerHand().clear();
		   
		   /**ADD CARDS TO PLAYER #0**/	 	
/*		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(2, Card.PAGODA));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(3, Card.SWORD));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(4, Card.DIAMONDS));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(5, Card.STAR));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(5, Card.SWORD));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(6, Card.STAR));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(7, Card.SWORD));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(8, Card.PAGODA));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(8, Card.DIAMONDS));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(9, Card.STAR));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(10, Card.SWORD));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(10, Card.DIAMONDS));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(12, Card.STAR));
		   
		   /**ADD CARDS TO PLAYER #1**/
/*		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(2, Card.STAR));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(3, Card.DIAMONDS));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(3, Card.STAR));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(4, Card.PAGODA));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(4, Card.SWORD));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(6, Card.DIAMONDS));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(6, Card.PAGODA));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(9, Card.SWORD));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(9, Card.PAGODA));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(10, Card.STAR));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(11, Card.DIAMONDS));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(13, Card.PAGODA));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(14, Card.SWORD));		   	   		   
		   
		   /**ADD CARDS TO PLAYER #2**/		   
/*		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(2, Card.DIAMONDS));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(2, Card.SWORD));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(5, Card.DIAMONDS));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(7, Card.PAGODA));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(8, Card.STAR));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(8, Card.SWORD));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(10, Card.PAGODA));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(11, Card.SWORD));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(12, Card.DIAMONDS));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(12, Card.PAGODA));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(13, Card.DIAMONDS));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(13, Card.SWORD));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(14, Card.PAGODA));
		   
		   /**ADD CARDS TO PLAYER #3**/
/*		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(3, Card.PAGODA));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(4, Card.STAR));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(5, Card.PAGODA));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(6, Card.SWORD));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(7, Card.DIAMONDS));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(7, Card.STAR));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(9, Card.DIAMONDS));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(11, Card.PAGODA));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(11, Card.STAR));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(12, Card.SWORD));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(13, Card.STAR));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(14, Card.DIAMONDS));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(14, Card.STAR));
		   
		   for (int i=0; i<4; i++)
			   Game.players[i].getPlayerHand().sortByValue();
	}
	
	public static void miniTest() {
		   for (int i=0; i<4; i++)
			   Game.players[i].getPlayerHand().clear();
		   
		   /**ADD CARDS TO PLAYER #0**/	 	
/*		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(3, Card.PAGODA));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(3, Card.DIAMONDS));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(5, Card.PAGODA));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(6, Card.STAR));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(8, Card.SWORD));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(9, Card.STAR));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(10, Card.SWORD));		   
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(Card.ACE, Card.DIAMONDS));
		   Game.players[0].getPlayerHand().addCard(Game.tichuDeck.getCard(Card.ACE, Card.STAR));
		   
		   /**ADD CARDS TO PLAYER #1**/
/*		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(2, Card.STAR));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(3, Card.SWORD));		 
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(6, Card.DIAMONDS));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(7, Card.DIAMONDS));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(7, Card.PAGODA));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(7, Card.SWORD));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(8, Card.PAGODA));
		   Game.players[1].getPlayerHand().addCard(Game.tichuDeck.getCard(Card.QUEEN, Card.STAR));		   	   		   
		   
		   /**ADD CARDS TO PLAYER #2**/		   
/*		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(3, Card.STAR));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(5, Card.DIAMONDS));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(5, Card.SWORD));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(Card.QUEEN, Card.PAGODA));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(Card.QUEEN, Card.SWORD));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(Card.KING, Card.SWORD));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(Card.ACE, Card.PAGODA));
		   Game.players[2].getPlayerHand().addCard(Game.tichuDeck.getCard(Card.ACE, Card.SWORD));
		   
		   /**ADD CARDS TO PLAYER #3**/
/*		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(2, Card.SWORD));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(2, Card.DIAMONDS));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(2, Card.PAGODA));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(4, Card.DIAMONDS));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(8, Card.STAR));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(9, Card.PAGODA));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(10, Card.DIAMONDS));
		   Game.players[3].getPlayerHand().addCard(Game.tichuDeck.getCard(Card.KING, Card.PAGODA));
		   
		   for (int i=0; i<4; i++)
			   Game.players[i].getPlayerHand().sortByValue();		  
	} */
	
}

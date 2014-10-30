import java.util.ArrayList;


public class Rules {
	
	/**
	 * sets the player to start the trick
	 * if the round has not yet started then the first player is set randomly
	 * otherwise it is the one who won the last trick
	 */
	public static int setStartPlayer() {
		boolean firstTrick = (GameDemo.tichu.players[0].status==Player.HASNTPLAYED)
				&&(GameDemo.tichu.players[1].status==Player.HASNTPLAYED)
				&&(GameDemo.tichu.players[2].status==Player.HASNTPLAYED)
				&&(GameDemo.tichu.players[3].status==Player.HASNTPLAYED);
		if (firstTrick) {
			 //Random choice of player
			 double j = Math.random()*100%4;
			 int i = (int) j;
			 GameDemo.tichu.players[i].status=Player.WONTHETRICK;
			 return i;
			 
//			Game.players[0].status = Player.WONTHETRICK;
//			return 0;
	    }
		else {
			for (int i=0;i<4; i++) {
				if (GameDemo.tichu.players[i].status==Player.WONTHETRICK) {
					for (int j=i; j<i+4; j++) {
						if (!GameDemo.tichu.players[j%4].hasNoCardsLeft()) {
							GameDemo.tichu.players[j%4].status=Player.WONTHETRICK;
							return j%4;
						}
						else 
							GameDemo.tichu.players[j%4].status=Player.HASNOCARDSLEFT;
					}
				}
			}
		}
		return 0;
	}
	
	/**
	 * Checks if the current Combination is allowed by the rules
	 * to be played over the last combination
	 * @param last
	 * @param current
	 * @return true if the current Combination is allowed by the rules to be played over 
	 * the last Combination, false otherwise.
	 */
	public static boolean canBePlayed(Combination last, Combination current) {
		
		/* last combination on table is a single card:
		 * every single card with value greater than the value of the comb last can be played.
		 * any kind of bomb can be played.
		 */
		if (last.getType()==Combination.SINGLE) {			
			if (current.getType()==Combination.SINGLE&&current.getValue()>last.getValue())
				return true;
			if (current.getType()==Combination.KING||current.getType()==Combination.ACE
					||current.getType()==Combination.BOMBFOUR||current.getType()==Combination.BOMBFLUSH)
				return true;			
		}
		
		/* last combination on table is a king:
		 * any ace can be played
		 * any kind of bomb can be played
		 */
		else if (last.getType() == Combination.KING) {
			if (current.getType()==Combination.ACE||current.getType()==Combination.BOMBFOUR
					||current.getType()==Combination.BOMBFLUSH)
				return true;
			
		}
		
		/*last combination on table is an ace:
		 * any kind of bomb can be played
		 */
		else if (last.getType() == Combination.ACE) {
			if (current.getType()==Combination.BOMBFOUR||current.getType()==Combination.BOMBFLUSH)
				return true;
		}
		/*last combination on table is a four of a kind bomb:
		 * any four of a kind bomb of a greater value can be played
		 * any flush bomb can be played
		 */
		else if (last.getType() == Combination.BOMBFOUR) {
			if (current.getType()==Combination.BOMBFOUR&&current.getValue()>last.getValue())
				return true;
			if (current.getType()==Combination.BOMBFLUSH)
				return true;								
		}
		
		/*last combination on table is a bomb flush:
		 * any flush bomb with more cards can be played 
		 * any flush bomb with equal number of cards and of a greater value (high-value) can be played 
		 */
		else if (last.getType() == Combination.BOMBFLUSH) {
			if (current.getType()==Combination.BOMBFLUSH) {
				if (current.getNumberOfCards()>last.getNumberOfCards())
					return true;
				if ((current.getNumberOfCards()==last.getNumberOfCards())&&(current.getValue()>last.getValue()))
					return true;
			}
		}
		
		/*any other occasion:
		 * any Combination of the same type and number of cards and of a greater value can be played
		 */
		else {
			if (current.getType()==last.getType()
					&&current.getValue()>last.getValue()
					&&current.getNumberOfCards()==last.getNumberOfCards())
				return true;
		}
			
		/*
		 * if any of the above returns true then the current combination is not allowed to be played
		 */
		return false;
	}	
	
	/**
	 * 
	 */
	
	public static Combination constructCombination(Hand h) {	
		h.sortByValue();		
		int cardCt = h.getCardCount();
		if (cardCt == 1) {			
			if (h.findAce()!=null)
				return h.findAce();
			else if(h.findKing()!=null)
				return h.findKing();
			else
				return new Combination(h.getHand(), Combination.SINGLE, h.getCard(0).getValue(), 	
						h.getCard(0).getValue(), 1); 
		} // end if cardCt == 1
		else if (cardCt == 2) {
			Combination tempComb = h.findPair();
			if (tempComb!=null)
				return tempComb;
			else
				return null;
		} // end if cardCt == 2
		else if (cardCt == 3) {
			Combination tempComb = h.findThreeOfAKind();
			if (tempComb!=null)
				return tempComb;
			else
				return null;
		}//end if cardCt == 3
		else {
			Combination stairs = h.findStairs();
			Combination straight = h.findStraight();
			Combination flush = h.findBombFlush();
			Combination four = h.findBombFourOfAKind();			
			if (stairs!=null && (stairs.getNumberOfCards() == cardCt))
				return stairs;			
			else if (flush!=null && (flush.getNumberOfCards() == cardCt)) 
				return flush;
			else if (straight!=null && (straight.getNumberOfCards() == cardCt))
				return straight;
			else if (four!=null && (four.getNumberOfCards() == cardCt))
				return four;
			else if (cardCt == 5){
				//check for full house
				Combination three = h.findThreeOfAKind();
				if (three!=null) {
					h.removeCombfromThisHand(three);
					Combination pair = h.findPair();
					if (pair!=null)
						return Combination.merge(three, pair, Combination.FULL);
				}				
			}
			else return null;
		}//end else (cardCt > 3)		
		return null;
	}	
}

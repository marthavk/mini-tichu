import java.util.Iterator;
import java.util.Stack;

/**
 * TODO add description
 * 
 */

public class Table {
	
	
	/**
	 * contains all the Combinations on the Table
	 */
	public Stack<Combination> combinationsOnTable;
	
	/**
	 * cTor of a new Table. 
	 */
	public Table() {
		combinationsOnTable = new Stack<Combination>();
	}
	
	/**
	 * clears current Table
	 */
	public void clear() {				
		combinationsOnTable.clear();
	}
	
	/**
	 * Sets the playing condition of the remaining players to: HASNTPLAYED
	 * @param currentPlayer
	 */
	public void setPlayersCondition(Player currentPlayer) {
		int index = currentPlayer.index;
		if (currentPlayer.status==Player.PLAYED) {
			for (int i=1; i<4; i++) {
				if (GameDemo.tichu.players[(index+i)%4].hasNoCardsLeft()) {
					GameDemo.tichu.players[(index+i)%4].status=Player.HASNOCARDSLEFT;					
				}
				if (!(GameDemo.tichu.players[(index+i)%4].status==Player.HASNOCARDSLEFT))
					GameDemo.tichu.players[(index+i)%4].status=Player.HASNTPLAYED;			
			}
		}
	}
	
	
	/**
	 * Adds gained cards in the private trick of the trickWinner
	 * @param trickWinner
	 * @param winComb
	 */
	public void setBeforeClear(Player trickWinner, Combination winComb) {
		Iterator<Combination> iter = combinationsOnTable.iterator();
		while (iter.hasNext()) {
			Combination temp = iter.next();
			trickWinner.addCardsToTrick(temp.getCardList());	
		}
	}
	
	/**
	 * adds a Combination on the Table
	 * @param c
	 */
	public void addCombinationOnTable (Combination c) {
		combinationsOnTable.push(c);
	}
	
	
	/**
	 * @return the top Combination
	 */
	public Combination getTopCombination() {
		return combinationsOnTable.peek();
	}
	
	/**
	 * Check if the current trick is over
	 * @param pl the player who played last
	 * @return true if the trick is over
	 */
	public boolean isOver(int index) {		
		if((GameDemo.tichu.players[(index+1)%4].status==Player.PASSED||GameDemo.tichu.players[(index+1)%4].status==Player.HASNOCARDSLEFT)
				&&(GameDemo.tichu.players[(index+2)%4].status==Player.PASSED||GameDemo.tichu.players[(index+2)%4].status==Player.HASNOCARDSLEFT)
				&&(GameDemo.tichu.players[(index+3)%4].status==Player.PASSED||GameDemo.tichu.players[(index+3)%4].status==Player.HASNOCARDSLEFT)) {
			return true;			
		}
		return false;
	}

}
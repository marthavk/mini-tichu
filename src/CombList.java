import java.util.ArrayList;
import java.util.Iterator;

public class CombList {
	/**
	 * The list of Combinations which form the combinations list
	 */
	private ArrayList<Combination> combList;

	/**
	 * cTor:
	 * Creates an empty ArrayList of Combinations;
	 */
	public CombList() {
		combList = new ArrayList<Combination>();
	}

	/**
	 * Sorts by list by Type, then by Value (if two or more Combinations have the same type)
	 */
	public void SortByType() {
		ArrayList<Combination> sorted = new ArrayList<Combination>();
		while (combList.size() > 0) {
			int pos = 0; // Position of minimal combination.
			Combination co = combList.get(0); // Minimal combination
			for (int i = 1; i < combList.size(); i++) {
				Combination c1 = combList.get(i);
				if (c1.getType() < co.getType()
						|| (c1.getType() == co.getType() && c1.getValue() < co
								.getValue())) {
					pos = i;
					co = c1;
				}
			}
			combList.remove(pos);
			sorted.add(co);
		}
		combList = sorted;
	}
	
	/**
	 * 
	 * @return the list of Combinations
	 */
	public ArrayList<Combination> getList() {
		return this.combList;
	}

	/**
	 * creates a deep copy of the list,
	 * then adds a Combination to the new list
	 * @param co, the combination to be added
	 * @return CombList with Combination co added
	 */
	public CombList addCombToNewList(Combination co, Player p) {
		CombList cl = this.copyList();
		cl.combList.add(co);
		return cl;
	}
	
	/**
	 * adds Combination @param co to the current CombList
	 */
	public void addComb(Combination co) {
		this.combList.add(co);
	}

	
	/**
	 * removes Combination @param co from current CombList
	 * without reevaluating the CombList
	 */
	public void removeComb(Combination co) {
		this.combList.remove(co);
	}	

	/**
	 * creates a new CombList and adds the Combinations of current 
	 * CombList which match the @param type
	 * @return the new CombList
	 */
	public CombList getAllFromOneType(int type) {
		CombList newList = new CombList();
		Iterator<Combination> iter = combList.iterator();
		while (iter.hasNext()) {
			Combination temp = iter.next();
			if (temp.getType() == type)
				newList.addComb(temp);
		}
		
		return newList;
	}
	
	/**
	 * Creates a new CombList then adds all the single cards
	 * from current CombList
	 * @return the new CombList
	 */
	public ArrayList<Card> getAllSingleCards() {
		ArrayList<Card> singles = new ArrayList<Card>();
		for (int i=0; i<this.getCombCount(); i++) {
			if (this.getList().get(i).getNumberOfCards()==1)
				singles.add(this.getList().get(i).getCardList().get(0));
		}
		return singles;
	}
	
	/**
	 * Creates a new CombList then adds all the Combinations allowed
	 * to be played over the @param comb
	 * @return the new CombList
	 */
	public CombList getAllPlayableCombinations(Combination comb) {
		CombList newList = new CombList();
		Iterator<Combination> iter = combList.iterator();
		while (iter.hasNext()) {			
			Combination tempComb = iter.next();
			if (Rules.canBePlayed(comb, tempComb)) {
				newList.addComb(tempComb);								
			}
		}
		newList.SortByType();				
		return newList;		
	}

	/**
	 * Checks if current CombList contains at least one Combination
	 * of a specific type
	 * @param type
	 * @return true if currentCombList contains at list one Combination of this type
	 * false otherwise
	 */
	public boolean containsType(int type) {
		Iterator<Combination> iter = combList.iterator();
		while (iter.hasNext()) {
			Combination temp = iter.next();
			if ((temp.getType() == type)||(temp.getType() == Combination.BOMBFOUR) || (temp.getType()==Combination.BOMBFLUSH))
				return true;
		}
		return false;
	}

	/**
	 * Checks if current CombList contains at least one Combination 
	 * of a specific type with the same numberOfCards as the @param numberOfCards
	 * @param type
	 * @return
	 */
	public boolean containsType(int type, int numberOfCards) {
		Iterator<Combination> iter = combList.iterator();

		while (iter.hasNext()) {
			Combination temp = iter.next();
			if (numberOfCards == 1) {
				if (temp.getNumberOfCards() == numberOfCards)
					return true;
			} else {
				if ((temp.getType() == type)
						&& (temp.getNumberOfCards() == numberOfCards))
					return true;
			}
		}
		return false;
	}

	/**
	 * @param type
	 * @return the first Combination in the list of the specified type.
	 * null otherwise
	 */
	public Combination getCombination(int type) {
		Combination temp = new Combination();
		Iterator<Combination> iter = combList.iterator();
		while (iter.hasNext()) {
			temp = iter.next();
			if (temp.getType() == type)
				return temp;
		}
		return null;
	}
	
	
	/**
	 * @return the number of Cards of current CombList
	 */
	public int getCardCount() {
		int count = 0;
		Iterator<Combination> iter = this.combList.iterator();
		while (iter.hasNext())
			count += iter.next().getNumberOfCards();
		return count;
	}
	
	/**
	 * @return the number of Combinations of current CombList
	 */
	public int getCombCount() {
		return this.combList.size();
	}


	/**
	 * Clears the combList
	 */
	public void clear() {
		this.combList.clear();
	}

	/**
	 * string representation of the list
	 */
	public @Override String toString() {
		this.SortByType();
		return combList.toString();
	}

	/**
	 * creates a deep copy of the CombList 
	 * @return the new identical CombList
	 */
	public CombList copyList() {
		CombList newList = new CombList();
		Iterator<Combination> iter = this.combList.iterator();
		while (iter.hasNext())
			newList.combList.add(iter.next());
		return newList;
	}

	/**
	 * Merges current CombList into one Combination
	 * values of the new Combination are not specified
	 * used for creating Combinations of stairs
	 * @return: newComb Values of other fields are not set
	 */
	public Combination mergeMultiple() {
		Combination newComb = new Combination();
		Iterator<Combination> iterComb = this.combList.iterator();
		while (iterComb.hasNext()) {
			Iterator<Card> iterCard = iterComb.next().getCardList().iterator();
			while (iterCard.hasNext())
				newComb.addCard(iterCard.next());
		}
		return newComb;
	}// end merge multiple

	/**
	 * Adds Cards in the Hand as Combinations of SINGLE type.
	 * @param h
	 */
	public void addAsSingle(Hand h) {
		for (int i = 0; i < h.getCardCount(); i++) {			
			Combination single = new Combination();
			single.addCard(h.getCard(i));
			single.setValue(h.getCard(i).getValue());
			single.setAuxValue(h.getCard(i).getValue());
			single.setNumberOfCards(1);
			single.setType(Combination.SINGLE);
			this.addComb(single);
		}
		this.SortByType();
	}
	
}

import java.util.ArrayList;
import java.util.Iterator;

public class Search {
	
	/**
	 * contains all Nodes of the current search to String so as not to create the same node again
	 */
	public static ArrayList<String> allTichuNodes = new ArrayList<String>();
	
	/**
	 * contains all leaves of current search tree
	 */
	public static ArrayList<Node> allLeaves = new ArrayList<Node>();
	
	
	
	/**
	 * Finds all possible tichu combinations
	 * @param t the initial Node to be expanded
	 * @param p the Player who owns this Node
	 */
	public static void constructTree(Node t, Player p) {		
		Iterator<Integer> iter = Hand.createListofSearchCombinations(Combination.PAIR, Combination.ACE).iterator();
		while (iter.hasNext()) {
			Combination co = new Combination();			
			co=t.getRemainingHand().findCombinations(iter.next());			
			if (co!=null) {
				Node child = new Node (t.getRemainingHand().removeCombination(co), t.getCombinationList().addCombToNewList(co, p), p);				
				child.getCombinationList().SortByType();
					if (! allTichuNodes.contains(child.toString())) {											
						if ((co.getType()==Combination.STRAIGHT||co.getType()==Combination.BOMBFLUSH)
								&&(co.getNumberOfCards()>=5)) {
							CombList s = new CombList();
							s = createListOfNestedStraights(co);
							Iterator<Combination> straights = s.getList().iterator();
							  while (straights.hasNext()) {
								  Combination st = straights.next();
								  Node temp = new Node(t.getRemainingHand().removeCombination(st), 
										  t.getCombinationList().addCombToNewList(st, p), p);
								 if (! allTichuNodes.contains(temp.toString())) {
									  temp.getCombinationList().SortByType();
									  t.addChildNode(temp);									  
									  allTichuNodes.add(temp.toString());
									  constructTree(temp, p);
							  }
							}							
						}
						else {
							allTichuNodes.add(child.toString());					
							t.addChildNode(child);							
							constructTree(child, p);							
						}
					}
					else 
						t.setRemovedChild(true);
			}
		
		}
		if (t.isLeaf()&&t.hasRemovedChild()) {
			t.remove();
		}
	}
	
	/**
	 * finds all full houses in the Node and adds them as brothers to current Node
	 * @param t: the Node to be searched
	 * @param p: the Player who owns the Node
	 */
	public static void findFullHouse(Node t, Player p) {		
		Iterator<Combination> tripleIter = t.getCombinationList().getAllFromOneType(Combination.THREE).getList().iterator();
		while (tripleIter.hasNext()) {
			Combination triple = tripleIter.next();			
			Iterator<Combination> pairIter = t.getCombinationList().getAllFromOneType(Combination.PAIR).getList().iterator();			
			while (pairIter.hasNext()) {
				Combination pair = pairIter.next();
				CombList newList = t.getCombinationList().copyList();
				newList.removeComb(pair);
				newList.removeComb(triple);			
				Combination fullHouse = Combination.merge(triple, pair, Combination.FULL);									  					 
				newList.addComb(fullHouse);					  				  
				Node brother = new Node (t.getRemainingHand(), newList, p);
				//checks if same combination has been found before
				if (!allTichuNodes.contains(brother.toString())) {
					 allTichuNodes.add(brother.toString());					
					t.addBrotherNode(brother);					
					findFullHouse(brother, p);
				}
				else
					t.setRemovedChild(true);								
			}
		}
		if (t.isLeaf()&&t.hasRemovedChild())
			t.remove();
	}
	
	/**
	 * Creates a least of all the nested straights 
	 * when the straight consists of more than 5 cards
	 * @param straight 
	 * @return the list of nested straights
	 */
	public static CombList createListOfNestedStraights(Combination straight) {
		CombList straightList = new CombList();
		int numberOfCards = straight.getNumberOfCards();
		for (int i = 0; i <= numberOfCards - 5; i++) {
			for (int length = 5; length <= numberOfCards; length++) {
				if (numberOfCards >= (length + i))
					straightList.addComb(straight.getRangeOfCards(i, i + length));					
			}
		}
		return straightList;
	}
	
	/**
	 * Searches the Node, then adds full-houses to the leaves of the tree 
	 * @param t: the Node to be searched
	 * @param p: the Player who owns the Node
	 */
	public static void search(Node t, Player p) {
		constructTree(t, p);	
		allLeaves = t.findLeaves(allLeaves);
		Iterator<Node> iter = allLeaves.iterator();
		
		while (iter.hasNext()) {			
			Node temp = iter.next();
			findFullHouse(temp, p);
			
		}		
		allLeaves.clear();		
		allTichuNodes.clear();
	}
					
	/**
	 * Sorts the leaves of a search tree
	 * @param t: the root-Node of the tree
	 */
	public static void SortLeaves(Node t) {
		  Search.allLeaves.clear();
		  allLeaves = t.findLeaves(allLeaves);

		double maxValue = 0;
		  ArrayList<Node> sorted = new ArrayList<Node>();
	      while (Search.allLeaves.size() > 0) {
	         int pos = 0;  // Position of minimal value.
	         maxValue = Search.allLeaves.get(0).getNodeValue();
	         
	         for (int i = 1; i < Search.allLeaves.size(); i++) {
	        	 double value1 = Search.allLeaves.get(i).getNodeValue();
	        	 if (value1 > maxValue) {
	        		 pos=i;
	        		 maxValue=value1;
	        	 }
	         }
	         Node temp = allLeaves.get(pos);
	        Search.allLeaves.remove(pos);
	        sorted.add(temp);
	      }
	      allLeaves = sorted;
	      
	}
	
	/**
	 * Finds the best Node from all leaves of a tree
	 * @param n: the root-Node of the tree
	 * @param p: the Player who owns the Node
	 * @return: the best Node 
	 */
	public static Node findBestNodefromAllLeaves(Node n, Player p) {
		allLeaves.clear();
		n.evaluateTree(p);
		allLeaves = n.findLeaves(allLeaves);
		Iterator<Node> iter = allLeaves.iterator();		
		double value = -1000;
		Node bestNode = new Node();
		while (iter.hasNext()) {
			Node temp = iter.next();						
			if (temp.getNodeValue()>value) {
				bestNode = temp;
				value = temp.getNodeValue();
			}
		}
		return bestNode;		
	}

}

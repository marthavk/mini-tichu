import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


	
// represents a tree Node
public class Node {
	
	/**
	 * Each Node is represented by its CombList, its Hand and its value
	 * it also contains an list of children and one parent. 
	 */
	
	/**
	 * contains the Hand of the Node
	 */
	private Hand remainingHand;
	
	/**
	 * contains the CombList of the Node
	 */
	private CombList comb;
	
	/**
	 * contains the parent of the Node
	 */
	private Node parent = null;
	
	/**
	 * contains the list of children-Nodes of the Node
	 */
	private List<Node> children = null;
	
	/**
	 * is set to true if, during the Search process, 
	 * the Node has at least one child removed
	 */
	private boolean hasRemovedChild;
	
	/**
	 * represents the value of the Node (which is the same as the value of the CombLi
	 */
	private double value;
	
	/**
	 * contains the exchange Cards that correspond to the Node
	 * this variable is used when different search trees that correspond 
	 * to different exchange Cards are constructed
	 */
    public ArrayList<Card> exchangeCards;    
		  	  
	  /**
	   * cCtor of a Node instance
	   * @param h the Hand of the Node
	   * @param s the CombList of the Node
	   * @param p the Player to whom the Node corresponds
	   */
	  public Node(Hand h, CombList s, Player p) {
	    this.parent = null;
	    this.remainingHand = h;
	    this.comb=s;
	    this.children = new ArrayList<Node>();
	    this.hasRemovedChild=false;
	    this.value=Evaluation.listEvaluation(s, p);
	    this.exchangeCards=new ArrayList<Card> ();	    
	  }
	  
	  /**
	   * cCtor
	   * creates an empty node
	   */
	  public Node() {
		  this.parent=null;
		  this.remainingHand = new Hand();
		  this.comb = new CombList();
		  this.children = new ArrayList<Node>();
		  this.hasRemovedChild=false;
		  this.value=0;
		  this.exchangeCards=new ArrayList<Card>();
	  }

	  
	  /**
	   * remove Node from tree
	   */
	  public void remove() {
	    if (parent != null) {
	    	parent.removeChild(this);      
	    }
	  }

	  /**
	   * remove child Node
	   * @param child Node to be removed
	   */
	  private void removeChild(Node child) {
	    if (children.contains(child))
	      children.remove(child);

	  }
	  
	  /**
	   * clears the Node
	   */
	  void clearNode() {
		  children.clear();
		  comb.clear();		  
		  parent = null;
		  remainingHand.clear();
		  hasRemovedChild=false;
		  value=0;
		  this.exchangeCards.clear();    
	  }

	  /**
	   * add child Node
	   * @param child Node to be added
	   */
	  public void addChildNode(Node child) {
	    child.parent = this;
	    if (!children.contains(child))
	      children.add(child);
	  }
	  
	  /**
	   * adds a brother Node
	   * @param brother: Node to be added as brother
	   */
	  public void addBrotherNode (Node brother) {		  
		  if (this.getLevel()==0) 
			  this.createRootParent();
		  brother.parent = parent;
		  this.getParent().addChildNode(brother);
	  }

	  
	  /**
	   * Creates a Father if the node is orphan
	   */	  
	  public void createRootParent() {
		  if (this.getLevel()==0) {
			  Node father = new Node();
			  this.parent=father;
			  father.addChildNode(this);			  
		  }
		  else
			  System.out.println("Node is not orphan");		  
	  }
	 
	  /**
	   * @return level = distance from root
	   */
	  public int getLevel() {
	    int level = 0;
	    Node p = parent;
	    while (p != null) {
	      ++level;
	      p = p.parent;
	    }
	    return level;
	  }
	  
	  /**
	   * @return the root of the tree
	   */
	  public Node getRoot() {
		  Node p = parent;
		  while (p.getLevel() != 0)
			  p = p.parent;		    
		  return p;
	  }
	  
	  /**
	   * @return List of possible Combinations
	   */
	  public CombList getCombinationList () {
		  return this.comb;
	  }
	  
	  /**
	   * @return List of children of current Node
	   */
	  public List<Node> getChildren() {
	    return children;
	  }
	  	  
	  /**
	   * @return parent Node
	   */
	  public Node getParent() {
	    return parent;
	  }
	  
	  /**
	   * Sets the value of the Node
	   * @param nodeValue
	   */
	  public void setNodeValue(double nodeValue) {		  
		  this.value = nodeValue;
	  }
	  
	  /**
	   * @return true if Node has at least one child removed
	   */
	  public boolean hasRemovedChild() {
		  return hasRemovedChild;
	  }
	  
	  /**
	   * Sets the field hasRemovedChild
	   * @param b
	   */
	  public void setRemovedChild(boolean b) {
		  hasRemovedChild = b;
	  }

	  /**
	   * @return the remainingHand field
	   */
	  public Hand getRemainingHand() {
	    return remainingHand;
	  }
	  

	  /**
	   * set the remainingHand field
	   * @param h
	   */
	  public void setRemainingHand(Hand h) {
	    remainingHand.clear();
	    for (int i=0; i<h.getCardCount(); i++) {
	    	remainingHand.addCard(h.getCard(i));
	    }
	  }
	
	  /**
	   * sets the leaves of the node
	   */
	  public ArrayList<Node> findLeaves(ArrayList<Node> nodeList) {		  
		  Iterator<Node> iter = this.children.iterator();		  
		  while (iter.hasNext()) {
			  Node temp=iter.next();			  
			  if(temp.isLeaf()) 
				  nodeList.add(temp);
			  temp.findLeaves(nodeList);
		  }
		  return nodeList;
		  
	  }
	  
	  /**
	   * add the field remainingHand as single Combinations to node, then reevaluates the node
	   * @param p
	   */
	  public void addAllRemainingHandsAsSingle(Player p) {
		  ArrayList<Node> leaves = new ArrayList<Node>();
		  leaves = this.findLeaves(leaves);
		  Iterator<Node> iter = leaves.iterator();
		  while (iter.hasNext()) {			  
			  Node temp = iter.next();	
			  temp.getCombinationList().addAsSingle(temp.getRemainingHand());			  
			  this.setNodeValue(Evaluation.evaluateNode(this, p));
		  }		
	  }
	  
	  /**
	   * Prints the Leaves for the Node subtree
	   */
	  public void printLeaves() {		  
		  Iterator<Node> iter = this.children.iterator();		  
		  while (iter.hasNext()) {
			  Node temp=iter.next();			  			  
			  if(temp.isLeaf())				  
				  GameDemo.message.append(temp.toString()); 
			  temp.printLeaves();
		  }
	  }
	  
	  /**
	   * Prints the whole subtree of current Node (depth first)
	   */
	  public void printTree() {
		  Iterator<Node> iter = this.children.iterator();
		  while(iter.hasNext()) {
			  Node temp = iter.next();
			  GameDemo.message.append("*************************");
			  GameDemo.message.append("level = " + temp.getLevel());
			  GameDemo.message.append(temp.toString());
			  temp.printTree();
		  }
	  }
	  
	  /**
	   * Prints the all the leaves of current subtree and their value
	   * @param p: Player to whom that Node corresponds
	   */
	  public void printTreeEvaluation(Player p) {
		  Iterator<Node> iter = this.children.iterator();
		  while (iter.hasNext()) {
			  Node temp = iter.next();
			  if (temp.isLeaf()) 
				  GameDemo.message.append(Evaluation.nodeEvaluationToString(temp, p));
			  temp.printTreeEvaluation(p);
		  }
	  }	  
	
	  @Override
	  public String toString() {		  
		  return "\nCombinations: " + this.comb.toString() + "\nRemaining Hand: " 
	  + this.remainingHand.toString() + "\n= " + this.value ;
	  }
	  
	  /**
	   * @return the Node value
	   */
	  public double getNodeValue() {
		   return this.value;
	   }
	  
	  /**
	   * Evaluates subTree of current Node
	   * @param p: Player to whom that Node corresponds
	   */
	  public void evaluateTree(Player p) {
		  Iterator<Node> iter = this.children.iterator();
		  while (iter.hasNext()) {
			  Node temp = iter.next();		
			  this.setNodeValue(Evaluation.evaluateNode(this, p));			  
			  temp.evaluateTree(p);
		  }
	  }
	  
	 /**
	   * Removes Combination c from current subTree then reevaluates the subTree
	   * @param c
	   * @param p: Player to whom that Node corresponds
	   */
	  public void removeCombFromSubTree(Combination c, Player p) {
		  Iterator<Node> nodesIter = this.getChildren().iterator();
		  while(nodesIter.hasNext()) {			  
			  Node tempNode = nodesIter.next();
			  tempNode.getRemainingHand().removeCombfromThisHand(c);
			  tempNode.getCombinationList().removeComb(c);
			  tempNode.removeCombFromSubTree(c, p);
		  }
		  this.evaluateTree(p);
	  }	  
	  
	  /**
	   * @return true if node is leaf of the tree. False otherwise
	   */
	  public boolean isLeaf() {
		  if (this.children.isEmpty())
			  return true;		  
		  return false;
	}
	  
	  /**
	   * Find the highest single for exchange
	   * @return the highest single which is not part of a combination. 
	   * If there is not any then return the highest card.
	   */
	  public Card getHigestSingle() {	
			  ArrayList<Card> singles = this.getCombinationList().getAllSingleCards();			 
			  if (!singles.isEmpty()) {				  
				  return singles.get(singles.size()-1);
			  }
			  else	
				  return this.getRoot().getRemainingHand().getCard(this.getRoot().getRemainingHand().getCardCount()-1);			    		  		 	  		  		  
	  }	  	 

}//end of class Node

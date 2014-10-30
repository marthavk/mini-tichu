
import java.util.Iterator;


public class Evaluation {
	
	/**
	 * these constants represent the 5 evaluation types
	 */
	public static final int WONORMALIZATION = 0;	//evaluation without normalization
	public static final int CARDNORMALIZATION = 1;	//evaluation with card normalization
	public static final int COMBNORMALIZATION = 2;	//evaluation with combination normalization
	public static final int POSSIBILITIES = 3;	//evaluation using possible combinations still in the game
	public static final int NOEVALUATION = 4;

	/**
	 * @param n: the Node to be evaluated
	 * @param p: the Player who owns the Node
	 * @return the Node evaluation
	 */
	public static double evaluateNode(Node n, Player p) {
		return listEvaluation(n.getCombinationList(), p);
	}

	/**
	 * Evaluates a CombList.
	 * @param l the list to be evaluated
	 * @param p the Player who owns the list
	 * @return the evaluation of the list
	 */
	public static double listEvaluation(CombList l, Player p) {		
		double sum = 0;		
		Iterator<Combination> iter = l.getList().iterator();
		while (iter.hasNext()) {			
			Combination temp = iter.next();
			sum += simpleCombEvaluation(temp, p)*coefficient(temp, l, p);
		}
		return sum;
	}
	
	/**
	 * Creates a deep copy of the list, removes one Combination,
	 * then evaluates the decreased CombList
	 * @param l the list to be evaluated 
	 * @param p the Player who owns the list
	 * @param c the Combination to be removed from the list
	 * @return the CombList evaluation
	 */
	public static double evaluateSubtractedList(CombList l, Player p, Combination c) {

		CombList cloneList = l.copyList();

		cloneList.removeComb(c);
		if (cloneList.getList().isEmpty()) 	
			return 1000;		
		return listEvaluation(cloneList, p);
	}
	
	/**
	 * evaluates a single Combination
	 * @param l: the list where this Combination is stored
	 * 			 this parameter is needed when the evaluation is 
	 * 			 of type CARDNORMALIZATION or COMBNORMALIZATION and the 
	 * 			 number of Cards or Combinations in the list is needed 
	 * @param p: the Player who owns the Combination
	 * @param c: the Combination to be evaluated
	 * @return the Combination evaluation
	 */
	public static double evaluateCombinaton(CombList l, Player p, Combination c) {
		return simpleCombEvaluation(c,p)*coefficient(c,l,p);
	}
	
	/**
	 * evaluates a simple Combination as is the coefficient equals to 1. 
	 * @param c the Combination to be evaluated
	 * @param p the Player who owns the Combination
	 * @return the Combination simple evaluation
	 */
	private static double simpleCombEvaluation(Combination c, Player p) {		
		
		if (c.getType()==Combination.SINGLE) 				
			return (c.getValue()-10)*(p.combinationWeight[0]);					
		else if (c.getType() == Combination.STRAIGHT)
			return c.getAuxValue()*c.getNumberOfCards()*p.combinationWeight[c.getType()-1];			
		else	
			return c.getValue()*c.getNumberOfCards()*p.combinationWeight[c.getType()-1];		
	}
	
	/**
	 * @param c the Combination to find the Coefficient (used only in type PROBABILITYBOOST
	 * 			where the exact Combination is needed to compute all Combinations of a higher value)
	 * @param l the CombList to which this Combination corresponds
	 * @param p the Player who owns the Combination
	 * @return the coefficient value of the Combination
	 */
	private static double coefficient(Combination c, CombList l, Player p) {		
		switch (p.getEvaluationType()) {		
		case CARDNORMALIZATION:	return ((double)1)/l.getCardCount();								
		case COMBNORMALIZATION: return ((double)1)/l.getCombCount();								
		case POSSIBILITIES:		return possibilityCount(c,p);	
		case WONORMALIZATION:	return 1;
		default:				return 0;
		
		}
	}

	/**
	 * Searches the possibilities of Combinations highest than current Combination:
	 * @param c
	 * @param p the Player who owns the Combination
	 * @return
	 */
	private static double possibilityCount(Combination c, Player p) {
		long cCount = Possibilities.anyCombinationGreaterThanValue(c,p);
		double coeff = 1;
		if (cCount==0) {
			coeff = 4;
		}
		else {
			coeff = 1 + ((double)1/cCount);
		}
		return coeff;
	}

	/**
	 * 
	 * @param n: The Node to be represented as String
	 * @param p: The Player who owns the Node 
	 * @return string representation of Node n including its evaluation
	 */
	public static String nodeEvaluationToString(Node n, Player p) {
		Iterator<Combination> iter = n.getCombinationList().getList().iterator();
		String str = new String();
		while (iter.hasNext()) {
			Combination temp = iter.next();
			str += temp.toString() + " = " 	+ (float)simpleCombEvaluation(temp, p) + " x " + (float)coefficient(temp, n.getCombinationList(),p) + 
					" = " + (float)(simpleCombEvaluation(temp, p)*coefficient(temp, n.getCombinationList(), p))  
					 +"\n";	
		}
		str += "TOTAL NODE VALUE = " + (float)(n.getNodeValue()) + "\n";		
		return str;
	}

}//end of class Evaluation

public class Possibilities {
	/*
	 *n[i] = the numbers of unknown cards of a value i-2  
	 *s = the number of cards of the Combination 
	 *k = the number of cards of a player 
	 *t = number of cards still unknown 
	 */
	
	public static long anyCombinationGreaterThanValue(Combination c, Player p) {
		int[] numberCount = new int[13];
		for (int i=0; i<13; i++) 
			numberCount[i] = p.cardCount[i][4];
		switch(c.getType()) {
		case Combination.SINGLE: case Combination.KING: case Combination.ACE: 
			return singleGreaterThanValue(numberCount, c.getValue());
		case Combination.PAIR: case Combination.THREE: case Combination.BOMBFOUR:
			return sOfAKindGreaterThanValue(c.getNumberOfCards(), numberCount, c.getValue());
		case Combination.FULL: 
			return fullHouseGreaterThanValue(numberCount, c.getValue());
		case Combination.STRAIGHT: 
			return sCardStraightGreaterThanValue(c.getNumberOfCards(), numberCount, c.getValue());
		case Combination.STAIR:
			return sStairsGreaterThanValue(c.getNumberOfCards(), numberCount, c.getValue());
		case Combination.BOMBFLUSH:
			int suit = c.getCardList().get(0).getSuit();
			return sCardFlushGreaterThanValue(c.getNumberOfCards(), p.cardCount, c.getValue(), suit);
		
		default:
			return 0;
		}	
	}	
	
	/**
	 * 
	 * @param n,k 
	 * @return the binomial coefficient (n k) = (n!)/[(k!)*(n-k)!]
	 * if k>n returns 0
	 */
	public static long binomial(int n, int k) {
		if (n<k)
			return 0;
		long coeff = 1;
		for (int i = n - k + 1; i <= n; i++) {
			coeff *= i;
		}
		for (int i = 1; i <= k; i++) {
			coeff /= i;
		}
		return coeff;
	}
	
	/**
	 * 
	 * @param s, n, i
	 * @return the possibilities of the specific sOfAKind
	 */
	private static long sOfAKind(int s, int n[], int i) {
		return binomial(n[i-2], s);
	}
		
	/**
	 * 
	 * @param s, n 
	 * @param i the highest Card value of the straight
	 * @return the possibilities of the specific straight
	 */
	private static long sCardStraight(int s, int n[], int i) {		
		long product = 1; 
		for (int j=i-s+1; j<=i; j++) {
			product = product*n[j-2];
		}
		return product;
	}
	
	/**
	 * 
	 * @param s, n, suit
	 * @param i =the highest Card value of the straight flush
	 * @return the possibilities of the specific straight flush
	 */
	private static long sCardStraightFlush(int s, int n[][], int i, int suit){
		long product = 1;
		for (int j=i-2+1; j<=i; j++) {
			product = product*n[j-2][suit];
		}
		return product;
	}
	
	/**
	 * @param s,n
	 * @param i = the highest pair value
	 * @return the possibilities of the specific stairs
	 */
	private static long sStairs(int s, int n[], int i) {
		int p = s/2;	//number of pairs
		int smallestValue = i-p+1;
		long product = 1;
		for (int j=smallestValue; j<=i; j++) {
			product = product*binomial(n[j-2], 2);
		}
		return product;
	}
	
	/**
	 * 
	 * @param s, n
	 * @param i the value of the 3-of-a-kind combination 
	 * @return the possibilities of the full house defined by it's 3-of-a-kind value
	 */
	private static long fullHouse(int n[], int i) {
		long product = binomial(n[i-2], 3);
		long sum = 0;
		for (int j=2; j<14; j++) {
			if (j!=i) {
				sum += binomial(n[j-2],2);
			}
		}
		return product*sum;
	}
	
	///////////////  SUM FUNCTIONS ////////////////
	/*
	 * The following functions return the sum of the possibilities 
	 * of each Combination greater than a value defined in parameters
	 */
	
	
	public static long singleGreaterThanValue(int n[], int value) {
		long sum = 0;
		for (int i=value-1; i<n.length; i++)
			sum += n[i];
		return sum;
	}
	
	public static long sOfAKindGreaterThanValue(int s, int n[], int value) {
		long sum = 0;
		for (int j=value+1; j<=14; j++) {
			sum += sOfAKind(s, n, j);
		}
		return sum;
	}
	
	public static long sCardStraightGreaterThanValue(int s, int n[], int value) {
		long sum = 0;
		for (int j=value+1; j<=14; j++) {
			sum += sCardStraight(s, n, j);
		}
		return sum;
	}
	
	public static long sCardFlushGreaterThanValue(int s, int n[][], int value, int suit) {
		long sum = 0;
		for (int j=value+1; j<=14; j++) {
			sum += sCardStraightFlush(s, n, j, suit);
		}
		return sum;
	}
	
	public static long sStairsGreaterThanValue(int s, int n[], int value) {
		long sum = 0;
		for (int j=value+1; j<=14; j++) {
			sum += sStairs(s,n,j);
		}
		return sum;
	}
	
	public static long fullHouseGreaterThanValue(int n[], int value) {
		long sum = 0;
		for (int j=value+1; j<=14; j++) {
			sum += fullHouse(n,value);
		}
		return sum;
	}
		
}//end of class Probabilities

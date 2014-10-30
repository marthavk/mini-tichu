import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * @author Martha Vlachou-Konchylaki
 */

public class Game {

	/**
	 * represent the 4 players of the game
	 */
	public  Player[] players;
	
	/**
	 * represents the table where the game is played
	 */
	public  Table tichuTable;
	
	/**
	 * represents one round of the game
	 */
	public  Round currentRound;
	
	/**
	 * this is the array where the scores of each team are stored
	 * the first position of the array(scores[0]) stores the score for Players 0 & 2 (team 1)
	 * the second position of the array(scores[1]) stores the score for Players 1 & 3 (team 2)
	 */
	public  int[] scores;
	
	/**
	 * represents the deck that will be used in the game
	 */
	public  Deck tichuDeck;	
	
	/**
	 * represents the number of rounds for a game with a specific number of rounds.  
	 */
	public  int numberOfRounds;
	
	/**
	 * represents the final score of the game. 
	 * Normally this value is 1000 unless defined otherwise 
	 */
	public  int endScore;
	
	/**
	 * represents the game status.
	 * this must be one of the values Game.ONEROUND, Game.MULTIPLEROUNDS, Game.COMPLETEGAME
	 */
	public int gameStatus;	
	public static final int ONEROUND = 1;		
	public static final int MULTIPLEROUNDS = 2;
	public static final int COMPLETEGAME = 3;
	
	public  int[] totalWins;
	
	/**
	 * cTor of the Game
	 */
	public Game(boolean user){		
		players = new Player[4];
		players[0] = new Player(0, user);
		for (int i=1; i<4; i++) {
			players[i] = new Player(i, false);
			players[i].configPlayer();
		}		
		currentRound = new Round();
	    scores = new int[2];
	    scores[0] = 0;
	    scores[1] = 0;
	    tichuTable = new Table();
	    tichuDeck = new Deck();	
	    numberOfRounds=1000;
	    gameStatus = Game.ONEROUND;
	    endScore=1000;
	    totalWins = new int[3];
	    totalWins[0]=0;
	    totalWins[1]=0;
	    totalWins[2]=0;
	 }
	
	/**
	 * adds the round scores for each team
	 * @param roundScores
	 */
	public void addScores(int[] roundScores) {
		scores[0] += roundScores[0];
		scores[1] += roundScores[1];
	}
	
	/**
	 * Checks if the game is over
	 * @param endScore: the score limit
	 * @return true if the game is over, false otherwise
	 */
	public boolean isOver(int roundCtr) {
		switch(gameStatus) {
		case Game.MULTIPLEROUNDS:	return roundCtr>=numberOfRounds;
		case Game.COMPLETEGAME:		return ((scores[0]>=endScore)||(scores[1]>=endScore))&&(scores[0]!=scores[1]);
		default:					return true;
		}
					
	}
	
	public void addWins(int[] rscores) {
		if (rscores[0]>rscores[1])
			totalWins[0]++;
		else if (rscores[1]>rscores[0])
			totalWins[1]++;
		else
			totalWins[2]++;
			
	}
	
	/**
	 * @return a String representation of the winners
	 */
	public String printWinners() {
		if(scores[0]>scores[1])
			return "the winners are " + players[0].name + " and " + players[2].name;
		else if (scores[1]>scores[0])
			return "the winners are " + players[1].name + " and " + players[3].name;
		else
			return "there is no winner yet!!!";
	}
	
	/**
	 * The main menu of the program.
	 * The options are the 6 testing sets defined in class TestingHands, a manual deal of Cards, 
	 * a certain number of Rounds game, a complete game setting the final score, or one round of 
	 * random deal of cards  
	 * @return the choice made
	 */
	public int menu() {
		Scanner scanIn = new Scanner(System.in);
		GameDemo.message.append("Type: \n1: for testing set 1" +
		"\n2: for testing set 2" +
		"\n3: for testing set 3" +
		"\n4: for testing set 4" +
		"\n5: for testing set 5" +
		"\n6: for a mini test" +
		"\n7: for a manual deal of cards" +
		"\n8: for a specific number of rounds game" +
		"\n9: for a complete game" +
		"\nany other key: for a random deal of cards");
		try {
			int deal = scanIn.nextInt();
			return deal;
		}
		catch (InputMismatchException e){
			return 0;
		}
	}
	
	/**
	 * handler of the menu choice
	 * @param code
	 */
	public void deal(int code) {
			switch(code) {
			case 1:	TestingHands.test1(this);
					break;
//			case 2:	TestingHands.test2();
//					break;
//			case 3:	TestingHands.test3();
//					break;
//			case 4:	TestingHands.test4();
//					break;
//			case 5:	TestingHands.test5();
//					break;
//			case 6:	TestingHands.miniTest();
//					break;
			case 7:	currentRound.dealCardsManually();
					break;
			case 8:	currentRound.deal();
					gameStatus = Game.MULTIPLEROUNDS;
					this.setNumberOfRounds();
					break;
			case 9: currentRound.deal();
					gameStatus = Game.COMPLETEGAME;
					this.setEndScore();
					break;
			default:	currentRound.deal();
						break;
			}		
	}
	
	/**
	 * lets the user set the number of Rounds of the game
	 */
	public void setNumberOfRounds() {
		Scanner scanIn = new Scanner(System.in);
		GameDemo.message.append("Give number of rounds:");
		try {			
			numberOfRounds = scanIn.nextInt();
		}
		catch (InputMismatchException e) {
			numberOfRounds=1;
		}
	}
	
	/**
	 * lets the user set the end score of a complete game
	 */
	public void setEndScore() {
		Scanner scanIn = new Scanner(System.in);
		GameDemo.message.append("Give end score:");
		try {
			endScore = scanIn.nextInt();
		}
		catch(InputMismatchException e) {
			endScore = 1000;
		}
	}

}
	
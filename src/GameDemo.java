import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class GameDemo extends JFrame{
	public static Combination[] displayPanels;
	public static JLabel[] playerLabels;
	public static JList cardList;
	public static DefaultListModel cardModel;
	public static JButton play;
	public static JButton pass;
	public static Message message;
	private Container container;
	public static boolean playStatus;
	public static boolean playerPlayed;
	public static int idx;
	
	public static Game tichu; 
	
	public GameDemo  (){
		super ("Tichu Demo");
/*______________SETUP GAME__________*/
		tichu = new Game(yesOrNoDialog("Will you play?"));

		
/*_______________SETUP GUI_____________________*/	
		
		container = getContentPane();
		container.setLayout(new MigLayout("", "10[right]15[300!]15[]", "[100!]5[100!]5[100!]5[100!]20[]5[120!]5[100!]"));
		playStatus = false;
		playerPlayed = false;
		displayPanels = new Combination[4];
		playerLabels = new JLabel[4];
		message = new Message();
		
	    for (int i=0; i<4; i++) {	    	
	    	playerLabels[i] = new JLabel("Player " + i);
	    	displayPanels[i] = new Combination();
	    }	 
	    
	    //add buttons and button listeners
	    JButton play = new JButton("play");	    	    		    	  
		JButton pass = new JButton("pass");
		ButtonListener bl = new ButtonListener();
		play.addActionListener(bl);
		pass.addActionListener(bl);
		
		cardModel = new DefaultListModel();		
		cardList = new JList(cardModel);
		
		cardList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		cardList.setPreferredSize(new Dimension(1000, 110));
		
		//add components to the container
		for (int i=0; i<4; i++) {			
			container.add(playerLabels[i]);			
			container.add(tichu.players[i].getPlayerHand());
			container.add(displayPanels[i], "span, wrap");
		}
		
		container.add(play);
		container.add(pass, "wrap");		
		container.add(cardList, "span, growx");
		container.add(message, "span, growx");				
		cardList.setVisibleRowCount(1);
		
		JScrollPane scroll = new JScrollPane( cardList,  
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,  
               JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		JScrollPane scrollMessages = new JScrollPane(message, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		cardList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		cardList.setVisible(true);
		container.add(scroll, "span, growx");
		container.add(scrollMessages, "span, growx"); 
				
		setSize(1050,800);
		setVisible(true);		
		
	}

		@Override
		public void paint(Graphics g) {			
			super.paint(g); // fill with background color.
		     //paint panels
			for (int j=0; j<4; j++) {	
		        tichu.players[j].getPlayerHand().paintComponent(g);	
			}
		}
		
		public void refreshPanels(Combination comb) {
			if (comb!=null) {
				displayPanels[idx] = comb;
			}
			repaint();
		}
		
		public void refreshTable() {
			for (int i=0; i<4; i++) {
				displayPanels[i].clear();
			}
			repaint();
		}
		
		public void refreshRound() {
			repaint();
		}
		
		public static void refreshCardList() {
			cardModel = tichu.players[0].getPlayerHand().getListModel();
			cardList.setModel(cardModel);
		}
		
		public boolean yesOrNoDialog(String question) {
			int reply = JOptionPane.showConfirmDialog(
		            null,
		            question,
		            "Question",
		            JOptionPane.YES_NO_OPTION);
		       if(reply == JOptionPane.YES_OPTION) return true;		        
		       else return false;		        
		}
		
		class ButtonListener implements ActionListener{
			int[] selection ;
			@Override			
			public void actionPerformed(ActionEvent evt) {
				if (playStatus == true) {
					if (evt.getActionCommand() == "play" ) {
						selection = (int[]) cardList.getSelectedIndices();
						Combination temp = Rules.constructCombination(tichu.players[idx].indexToHand(selection));
						if (temp!=null) {
							if (tichu.players[idx].status==Player.WONTHETRICK) {
								tichu.players[idx].play(temp);
								refreshPanels(temp);
								refreshCardList();
								playerPlayed = true;
							}
							else if (Rules.canBePlayed(tichu.tichuTable.getTopCombination(), temp)) {
								tichu.players[idx].play(temp);
								refreshPanels(temp);
								refreshCardList();
								playerPlayed = true;
							}
							else
								JOptionPane.showMessageDialog(null, "Combination cannot be played", "Error", JOptionPane.WARNING_MESSAGE);							
						}
						else							
							JOptionPane.showMessageDialog(null, "Invalid Combination!", "Error", JOptionPane.WARNING_MESSAGE);
						
					}
					if (evt.getActionCommand() == "pass" ) {
						if(tichu.players[idx].status!=Player.WONTHETRICK) {
						tichu.players[idx].pass();
						playerPlayed = true;
						}
						else 
							JOptionPane.showMessageDialog(null, "You should play something!", "Error", JOptionPane.WARNING_MESSAGE);
					}
					
	 			}	 							
			}
		}
		
		public static void main(String args[]) {
			GameDemo application = new GameDemo();
			application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				
			int trickCounter=0; 			
			int roundCounter = 0;		  			
			tichu.tichuDeck.shuffle();
			tichu.deal(0);
			cardModel = tichu.players[0].getPlayerHand().getListModel();
			cardList.setModel(cardModel);
			
			do {
				if (roundCounter!=0) {				
					tichu.currentRound.reSetRound();
					tichu.currentRound.resetAllPlayers();
					tichu.tichuDeck.shuffle();
					tichu.currentRound.deal();
				}
				roundCounter ++;  
			}while(false);
		
			message.append("\nBefore Exchanges:");
			tichu.currentRound.removeHandFromPlayersCount();
			tichu.currentRound.printAllPlayersCurrentHands();	
			tichu.currentRound.setExchangeCardsForAllPlayers();
			tichu.currentRound.exchangeCards();
		
			message.append("CARDS EXCHANGED:");	
		
			tichu.currentRound.printExchangeCardsForAllPlayers();	
			tichu.currentRound.resetCardCountForAllPlayers();
			tichu.currentRound.removeHandFromPlayersCount();
			tichu.currentRound.setPlayerNodes();
			message.append("player nodes set");
			
			cardModel = tichu.players[0].getPlayerHand().getListModel();
			cardList.setModel(cardModel);
			application.repaint();
			
			trickCounter = 0;
		
			do { //round
				idx=Rules.setStartPlayer();
				trickCounter ++;
				message.append("\nROUND " + roundCounter + ", TRICK " + trickCounter); 
				do {	//trick		
				  if (!tichu.players[idx].hasNoCardsLeft()) {
					  if (!tichu.players[idx].isUser){
						  Combination temp = tichu.players[idx].selectCombinationToPlay();	
						  if (temp!=null) tichu.players[idx].play(temp);
						  else tichu.players[idx].pass();
						  application.refreshPanels(temp);
					  }
					  else {
						  while(!playerPlayed){
							  System.out.println();
							  playStatus = true;								  						  
						  }
					 playStatus = false;
					 playerPlayed = false;
					  }
				  	}
				  	tichu.tichuTable.setPlayersCondition(tichu.players[idx]);					  	
				  	idx++;					  
				  	idx = idx%4;	
				} while (!tichu.tichuTable.isOver(idx%4)&&(!tichu.currentRound.isOver()));
				if (tichu.currentRound.isOver()) {
				    idx=(idx+3)%4;
				}
				tichu.players[idx].status=Player.WONTHETRICK;
				message.append(tichu.players[idx].name + " won the trick");	
				tichu.tichuTable.setBeforeClear(tichu.players[idx], tichu.tichuTable.combinationsOnTable.peek());				 
				tichu.tichuTable.clear();
			}while (!tichu.currentRound.isOver());
			
		  // adds last player to rank
		  for (int i=0; i<4; i++) {
			  if (!tichu.players[i].hasNoCardsLeft())
				  tichu.currentRound.rank.add(i);
		  }
		  for (int s=0; s<4; s++) {
			  if (tichu.players[s].hasNoCardsLeft())
				  message.append(tichu.players[s].name + " has no cards left");
			  else
				  message.append(tichu.players[s].name + " has " + tichu.players[s].getPlayerHand().getCardCount() + " cards left");						  					  					    
		  }			  
		  
		  message.append("\nRound is over");
		  tichu.currentRound.setRoundScores();
		  tichu.addScores(tichu.currentRound.roundScores);
		  tichu.addWins(tichu.currentRound.roundScores);
				
	}

}	



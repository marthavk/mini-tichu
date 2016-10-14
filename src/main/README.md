# The Mini Tichu Project
## Abstract
Tichu is a popular trick-taking climbing card game that involves strong strategic play along with elements of chance. Apart from the typical search-based methods that employ domain-dependent  or  domain-independent  mechanisms  for selecting the most promising action at each step of the game,the complex dynamics that arise from the team-play feature of Tichu suggests the use of agent-based techniques for specifying a higher-level tactical strategy for artificial intelligent players.   In  this  work  we  focus  on  finite  state  machines,  a technique that has been traditionally used for specifying the behavior  of  non-player  characters  in  video-games,  and  investigate how they can be coupled with simple  search-based methods as a means for developing strong AI players in a simplified version of the game that we call **Mini-Tichu**.

### Introduction
In order to illustrate the different roles for the search methods and the finite state machines, we investigate a
slightly simplified version of the Chinese card game of Tichu, that we call Mini-Tichu. This game is played with a normal 52-card poker deck  by four people that form two teams such that teammates sit in alternating positions. The deck is entirely
dealt between the players and  the objective of the game for each player is to discard all their cards. The first who achieves
this gets a certain bonus, and one of the  most important strategical points of the game is to plan for being first in this
respect or help your teammate achieve this, while making it  difficult for opponents to do the same thing. Mini-Tichu is a trick
taking climbing game in the sense that each player can play by discarding  a higher combination of cards from his hand. The
player starting a trick defines the type of combination played, including a single card  and a pair of cards, as well as all poker
combinations, and a few more. Players may decide if they want to play a combination of the same  type and higher value or
pass. The last player that discarded a combination after three players have passed wins the trick and defines  a new one.

Mini-Tichu is an incomplete information game as each player starts by knowing only his cards (1/4 of the full deck).
Each round is fully observable as each action reveals hidden cards that are removed from the game. Also, the game is highly
dynamic since there is a strong team-play feature that requires players to constantly evaluate the position of all players and
 switch between defensive and aggressive play.

Due to the high degree of incomplete information in the beginning of the game and the number of players, the state space
describing possible evolutions of the game is vast. This essentially renders the traditional search techniques such as AB
trees or Minimax applicable only in the final stages of the game where a lot of cards have been discarded. On the other
hand, reactive strategies based on the current state and the last executed actions offer a different approach to decision
making for non-player characters in other game genres than card games that has proven useful. More specifically, **finite
state machines (FSMs)** offer a simple way to specify a reactive behavior. An FSM is defined by a finite number of states,
each of which intuitively characterizes a specific type of pre-defined behavior. Changes between states occur through 
triggered conditions corresponding to discrete actions or events during the game. 

This work intends to combine strategic play with deliberative search techniques in order to provide useful guidelines for an AI
 Mini-Tichu player, for the greater part of the game that the state space is not manageable solely by search.
 
### Note
A complete description of this work by Martha Vlachou-Konchylaki and Stavros Vassos can be found [here](http://www.fdg2013.org/program/posters/poster19_vlachoukonchylaki_vassos.pdf)

### The Code
```@Authors Martha Vlachou Konchylaki```

This project is an applet of one round of the game of **mini-tichu**. 
When running the applet 2 options are possible: 
- Observation of the game, in order to see how artificial players react
    -  press *No* in the *Will You Play* dialogue.
- Play as one of the 4 players. Then the user has one AI team-mate and one team of 2 AI players as opponents.
    - press *Yes* in the *Will You Play* dialogue.

By changing the ```player#config.properties``` file the parameters of the players can be changed 
(this results to different kind of behaviors)

This project is not for commercial purpose

Also: The file ```miglayout-3.5.5-swing``` must be added to the build path if not recognized

Instructions for the **Tichu** game can be found [here](http://www.fatamorgana.ch/tichu/tichu_english.asp)



# Idea #1

The flow of the game is:

```Agent (A)``` -> ```Opponent Right (OR)``` -> ```Teammate (T)``` -> ```Opponent Left (OL)```  -> ...


We define:
* The current state (```v(t)```)
* The possible actions (```a(v(t))```)
* The next state distribution(```v(t+1)```)
* The reward at each state (```r(v(t))```)

### Current State

We define the state as one vector ```v  = [v0, v1, v2, ….. , v51 ] ```

Each element between ```v0``` and ```v51``` represents the status of each of the **52** cards of the deck (special cards not supported yet in current implementation).

```vi``` can take values in ```{0, 1, 2, 3, 4, 5}``` and more specifically:
* ```vi=0``` when card i  is in the agent’s hand
* ```vi=1``` when card i  is in one of other players’ hand
* ```vi=2``` when card i  is out of the game
* ```vi=3``` when card i  is on the table played by ```OR```
* ```vi=4``` when card i  is on the table played by ```T```
* ```vi=5``` when card i  is on the table played by ```OL```

The **state space** is 6^56 (when special cards are added in the vector) ~ 10^43

By replacing ```vi=3```, ```vi=5``` with one state (card is on the table, played by opponent) then we can reduce the state space to 10^39

### Possible Actions = Next State Distribution

The actions are alterations of the current state and the space is the same as the state space.
We do not define the rules of the game in the actions, the idea is that the agent will learn how to play after taking "illegal" moves

### The Reward At Each State

The reward at one state is: 
* A **large negative** value when the move is illegal
* **Zero** when the move is legal but it is not the end of the round
* **The team's score** in the end of the round


### Pros & Cons
* (-)The state space is large
* (+)Clear representation of the state and the actions
* (-)No domain knowledge input in reward
* (+-)It doesn't assume any rules 

# Idea #2

### Current state
The state is represented by a vector of:
* 14 elements for the hand representation (taking values from 0 to 56. 56 represents an empty placeholder). The vector can also be of dynamic length.
* Followed by a vector of what is on the table in a historical order

### Pros & Cons
* (+-) ???







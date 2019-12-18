## Java Framework for two-player board games

This project was inspired by recent successes of [board game-playing AI](https://deepmind.com/research/case-studies/alphago-the-story-so-far) and also served to polish my OOP. It is a Java Framework for two-player board games; I have so far implemented tic-tac-toe, but the framework can accommodate any two-player board game (complete four, chess, go, …). 

This doc describes
* the game flow
* player types
* the class structure
* a class tree
* heuristics for approximating field scores

### Game Flow

Game flow is managed by a Controller, which operates on generic Board and Player Classes. 

1. The Controller passes a turn to the current Player
2. The current Player selects a move
3. The controller checks the validity of the selected move
4. The controller updates the Board 
5. The controller prints the current Board. 

If the Board is in a game-over configuration (either Player won or ‘draw’):

6. The Controller terminates the game and prints the result. 

### Player types

Computer Players differ by their implementation of tree search algorithm. The MiniMaxPlayer implements the [minimax algorithm](https://en.wikipedia.org/wiki/Minimax#Minimax_algorithm_with_alternate_moves); the AlphaBetaPlayer implements minimax using [alpha-beta pruning](https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning) to search the game tree up to a maximum depth maxDepth; the MCTSPlayer implements a [Monte Carlo tree search](https://en.wikipedia.org/wiki/Monte_Carlo_tree_search).

### Class Structure

Overview of Classes and their most important methods

```
Controller(Board, Player, Player):
    run(): while game is ongoing, passes turns between players, checks validity of selected moves and updates Board 
```
```
Test():
  main(): initialise Board, Player1, Player2, Controller and run()
```
```
Board(n_rows, n_cols):
  gameOver()
  generatePossibleMoves(currentPlayer): generate all possible moves for currentPlayer
  scoreHeatmap(scores): print board with scores for each empty field
    
	TicTacToeBoard
	  gameOver(): implements TicTacToe-specific termination condition
```
```
Player(int):
  selectMove(Board,currentPlayer)

	ComputerPlayer(int):
	treeSearchEval(Board, currentPlayer)
	selectMove(Board,currentPlayer): move selection, implemented by subclasses
	evaluateMoves(currentPlayer, moves): calculate scores for moves
	
		RandomPlayer(int):
		  treeSearchEval(Board, currentPlayer): select random move

		MCTSPlayer(maxRunTime):
		  treeSearchEval(Board, currentPlayer): select move by Monte Carlo tree search

		AlphaBetaPlayer(maxDepth):
		  treeSearchEval(Board, currentPlayer): select move by alpha-beta pruning

			MiniMaxPlayer(maxDepth):
			  treeSearchEval(Board, currentPlayer): select move by minimax algorithm
```
```
Filter():
  apply([][])

	SingleFilter():
	  apply([][]): apply individual filter to Board

	MultiFilter():
	  apply([][]): apply multiple filters to Board
```


### Class Tree

![tictactoe-class-tree](https://github.com/lgoetz/two-player-ai/blob/master/tictactoe_class_diagram.png)

### Heuristics for approximating scores

SingleFilter and MultiFilter estimate the score of a Board based on the number of adjacent equal fields (based on the rules for winning TicTacToe). They are used to evaluate a proposed move when maxDepth is reached during an alpha-beta pruning tree search.



### Ideas for additions

* a HumanPlayer; such that treeSearchEval() returns a keyboard input
* better heuristics, e.g. learn move scores from (self-play generated) training data
* ...


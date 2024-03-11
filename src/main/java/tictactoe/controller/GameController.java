package tictactoe.controller;

import java.util.List;

import tictactoe.exception.DuplicateSymbolException;
import tictactoe.exception.MoreThanOneBotException;
import tictactoe.exception.PlayerCountMissMatchException;
import tictactoe.models.Game;
import tictactoe.models.Player;
import tictactoe.winningstrategies.WinningStrategy;

public class GameController {
	//not creating Game object and using because in future 
	//if we need to support multiple games we can do it in below format
	
	public Game startGame(int dimension, List<Player> playerList, List<WinningStrategy> winningStrategies) throws DuplicateSymbolException, PlayerCountMissMatchException, MoreThanOneBotException{
		return Game.getBuilder().setDimension(dimension).setPlayers(playerList).setWinningStrategies(winningStrategies).build();
	}
	
	public void printBoard(Game game) {
		game.printBoard();
	}

	public void makeMove(Game game) {
		game.makeMove();
	}
	
	public void undo(Game game) {
		game.undo();
	}
}

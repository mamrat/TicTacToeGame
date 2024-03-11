package tictactoe.apprunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tictactoe.controller.GameController;
import tictactoe.exception.DuplicateSymbolException;
import tictactoe.exception.MoreThanOneBotException;
import tictactoe.exception.PlayerCountMissMatchException;
import tictactoe.models.Bot;
import tictactoe.models.BotDifficultyLevel;
import tictactoe.models.Game;
import tictactoe.models.GameState;
import tictactoe.models.Player;
import tictactoe.models.PlayerType;
import tictactoe.winningstrategies.ColumnWinningStrategy;
import tictactoe.winningstrategies.DiagonalWinningStrategy;
import tictactoe.winningstrategies.RowWinningStrategy;
import tictactoe.winningstrategies.WinningStrategy;

public class AppRunner {

	public static void main(String[] args) throws DuplicateSymbolException, MoreThanOneBotException, PlayerCountMissMatchException{
		GameController gameController = new GameController();
		Scanner scanner = new Scanner(System.in);
		
		Integer dimension = 3;
		List<Player> playerList = new ArrayList<>();
		List<WinningStrategy> winningStrategies = new ArrayList<>();
		
		playerList.add(new Player('x', "Mamtha", 1, PlayerType.HUMAN));
		playerList.add(new Bot('0', "GPT", 2, PlayerType.BOT, BotDifficultyLevel.EASY));
		//playerList.add(new Player('y', "Anu", 3, PlayerType.HUMAN));

		
		winningStrategies.add(new RowWinningStrategy());
		winningStrategies.add(new ColumnWinningStrategy());
		winningStrategies.add(new DiagonalWinningStrategy());
		
		Game game = gameController.startGame(dimension, playerList, winningStrategies);
		
		while(game.getGameState().equals(GameState.IN_PROGRESS)) {
			
			//1. print board 2. make move  3. make move
			game.printBoard();
			System.out.println("Do you want to undo? (Y/N)");
			String doUndo = scanner.next();
			
			if(doUndo.equalsIgnoreCase("Y")) {
				gameController.undo(game);
				continue;
			}
			
			gameController.makeMove(game);
		}
		
		//if im here it mean game state is not in progress
		if(GameState.SUCCESS.equals(game.getGameState())) {
			System.out.println(game.getWinner().getName()+" Congrats! You won the game :)");
		}
		if(GameState.DRAW.equals(game.getGameState())) {
			System.out.println("Match Tied");
		}
		
	}
	
}

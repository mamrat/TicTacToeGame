package tictactoe.models;

import tictactoe.botplayingstrategy.BotPlayingStrategy;
import tictactoe.botplayingstrategy.BotPlayingStrategyFactory;

public class Bot extends Player {

	private BotDifficultyLevel botDifficultyLevel;

	private BotPlayingStrategy botPlayingStrategy;
	
	public Bot(char symbol, String name, Integer id, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
		super(symbol, name, id, playerType);
		this.botDifficultyLevel = botDifficultyLevel;
		this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategyForDifficultyLevel();
	}
	
	public Cell makeMove(Board board) {
		System.out.println("Get ready for the GPTs move!");
		Cell cell = botPlayingStrategy.makeMove(board);
		cell.setCellState(CellState.FILLED);
		cell.setPlayer(this);
		return cell;
		
	}
	
}

package tictactoe.botplayingstrategy;

import java.util.List;

import tictactoe.models.Board;
import tictactoe.models.Cell;
import tictactoe.models.CellState;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{

	@Override
	public Cell makeMove(Board board) {
		
		for(List<Cell> row : board.getBoard()) {
			for(Cell cell : row) {
				if(CellState.EMPTY.equals(cell.getCellState())) {
					return cell;
				}
			}
		}
		
		return null;
	}

}

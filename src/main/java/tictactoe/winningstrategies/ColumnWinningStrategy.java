package tictactoe.winningstrategies;

import java.util.HashMap;
import java.util.Map;

import tictactoe.models.Board;
import tictactoe.models.Move;

public class ColumnWinningStrategy implements WinningStrategy{

Map<Integer , Map<Character, Integer>> map = new HashMap<>();
	
	@Override
	public boolean checkWinner(Board board, Move move) {
		
		int column = move.getCell().getColumn();
		char symbol = move.getPlayer().getSymbol();
		
		//check if current column is present in the map
		if(!map.containsKey(column)) {
			map.put(column, new HashMap<>());
		}
		
		Map<Character, Integer> columnMap = map.get(column);
		
		if(!columnMap.containsKey(symbol)) {
			columnMap.put(symbol, 0);
		}
		
		//update the symbol count for columnMap
		columnMap.put(symbol, columnMap.get(symbol)+1);
		
		//check if the symbol count has reached the size of the board
		if(board.getBoard().size()==(columnMap.get(symbol))) {
			System.out.println("Winning via column "+column);

			return true;
		}
		
		return false;
	}

	@Override
	public void undo(Board board, Move lastMove) {
		int column = lastMove.getCell().getColumn();
		char symbol = lastMove.getPlayer().getSymbol();
		
		Map<Character, Integer> columnMap = map.get(column);
		columnMap.put(symbol, columnMap.get(symbol)-1);
		
	}

}

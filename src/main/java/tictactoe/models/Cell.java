package tictactoe.models;

public class Cell {

	private Integer row;
	private Integer column;
	private CellState cellState;
	private Player player;

	public Cell(Integer row, Integer column) {
		super();
		this.row = row;
		this.column = column;
		this.cellState = CellState.EMPTY;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}

	public CellState getCellState() {
		return cellState;
	}

	public void setCellState(CellState cellState) {
		this.cellState = cellState;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void display() {
		if (CellState.FILLED.equals(cellState)) {
			System.out.print("| " + player.getSymbol() + " |");
		} else if (CellState.EMPTY.equals(cellState)) {
			System.out.print("| - |");
		}
	}

}

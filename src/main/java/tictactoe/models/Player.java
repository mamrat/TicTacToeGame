package tictactoe.models;

import java.util.Scanner;

public class Player {

	private char symbol;
	private String name;
	private Integer id;
	private PlayerType playerType;
	private Scanner scanner;
	
	public char getSymbol() {
		return symbol;
	}
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public PlayerType getPlayerType() {
		return playerType;
	}
	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}
	
	public Player(char symbol, String name, Integer id, PlayerType playerType) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.id = id;
		this.playerType = playerType;
		scanner = new Scanner(System.in);
	}
	
	public Cell makeMove(Board board) {
		
		System.out.println(this.name+", Its your turn to make the move, enter row and column");
		
		Integer row = Integer.valueOf(scanner.nextInt());
		Integer column = Integer.valueOf(scanner.nextInt());
		
		while(validateMove(row, column, board)==false) {
			System.out.println(this.name + ", Invalid move, plase try again");
			
			row = Integer.valueOf(scanner.nextInt());
			column = Integer.valueOf(scanner.nextInt());
		}
		
		//if we have valid move
		Cell cell = board.getBoard().get(row).get(column);
		cell.setCellState(CellState.FILLED);
		cell.setPlayer(this);
		
		return cell;
	}
	
	private boolean validateMove(Integer row, Integer column, Board board) {
		if(row>=board.getDimension()) {
			return false;
		}
		if(column >= board.getDimension()) {
			return false;
		}
		if(!CellState.EMPTY.equals(board.getBoard().get(row).get(column).getCellState())) {
			return false;
		}
		return true;
	}
	
}

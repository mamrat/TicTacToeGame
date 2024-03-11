package tictactoe.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tictactoe.exception.DuplicateSymbolException;
import tictactoe.exception.MoreThanOneBotException;
import tictactoe.exception.PlayerCountMissMatchException;
import tictactoe.winningstrategies.WinningStrategy;

public class Game {

	private List<Player> players;
	private Board board;
	private List<Move> moves;
	private Player winner;
	private GameState gameState;
	private Integer nextPlayerIndex;
	private List<WinningStrategy> winningStrategies;

	// create constructor in last
	private Game(Integer dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
		this.board = new Board(dimension);
		this.players = players;
		this.winningStrategies = winningStrategies;
		this.gameState = GameState.IN_PROGRESS;
		this.nextPlayerIndex = 0;
		this.moves = new ArrayList<>();
	}

	public static Builder getBuilder() {

		return new Builder();
	}

	public void printBoard() {
		board.printBoard();
	}

	public void makeMove() {
		Player player = players.get(nextPlayerIndex);
		Cell cell = player.makeMove(board);

		Move move = new Move(cell, player);
		moves.add(move);

		if (chechWinner(move, board)) {
			gameState = GameState.SUCCESS;
			winner = player;
			return;
		}

		if (moves.size()==board.getDimension()*board.getDimension()) {
			gameState = GameState.DRAW;
			return;
		}

		// update the next player accordingly
		nextPlayerIndex++;
		nextPlayerIndex = nextPlayerIndex % players.size();

	}

	private boolean chechWinner(Move move, Board board) {
		for (WinningStrategy winningStrategy : winningStrategies) {
			if (winningStrategy.checkWinner(board, move)) {
				return true;
			}
		}
		return false;
	}
	
	public void undo() {
		Move lastMove = removeLastMove();
		if (lastMove == null) {
			return;
		}

		updateTheCellandStrategy(lastMove);
		updateNextPlayer();

	}

	private void updateNextPlayer() {
		if (nextPlayerIndex != 0) {
			nextPlayerIndex--;
		} else {
			nextPlayerIndex = players.size() - 1;
		}
	}

	private void updateTheCellandStrategy(Move lastMove) {
		Cell cell = lastMove.getCell();
		cell.setCellState(CellState.EMPTY);
		cell.setPlayer(null);

		for (WinningStrategy winningStrategy : winningStrategies) {
			winningStrategy.undo(board, lastMove);

		}
	}

	private Move removeLastMove() {
		if(moves.size()==0) {
			System.out.println("No moves to undo");
			return null;
		}
		Move lastMove = moves.get(moves.size()-1);
		moves.remove(lastMove);
		return lastMove;
	}

	public static class Builder {
		private List<Player> players;
		private List<WinningStrategy> winningStrategies;
		private Integer dimension;

		public Builder() {
			super();
			this.players = new ArrayList<>();
			this.winningStrategies = new ArrayList<>();
			this.dimension = 0;
		}

		public Game build() throws MoreThanOneBotException, DuplicateSymbolException, PlayerCountMissMatchException {
			/*
			 * validations: 1. BotCountValidation: validate bot count <=1 2.
			 * ValidateUniqueSymbolForPlayers 3. ValidateDimensionAndPlayerCount
			 */

			validateBotCount();
			validateUniqueSymbolForPlayers();
			validateDimensionAndPlayerCount();

			return new Game(dimension, players, winningStrategies);

		}

		private void validateDimensionAndPlayerCount() throws PlayerCountMissMatchException {
			if (players.size() != (dimension - 1)) {
				throw new PlayerCountMissMatchException();
			}

		}

		private void validateUniqueSymbolForPlayers() throws DuplicateSymbolException {
			Set<Character> symbols = new HashSet<>();

			for (Player player : players) {
				if (symbols.contains(player.getSymbol())) {
					throw new DuplicateSymbolException();
				} else {
					symbols.add(player.getSymbol());
				}
			}

		}

		private void validateBotCount() throws MoreThanOneBotException {
			Integer botCount = 0;

			for (Player player : players) {
				if (player.getPlayerType().equals(PlayerType.BOT)) {
					botCount++;
				}
			}

			if (botCount > 1) {
				throw new MoreThanOneBotException();
			}
		}

		public Builder setPlayers(List<Player> players) {
			this.players = players;
			return this;
		}

		public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
			this.winningStrategies = winningStrategies;
			return this;
		}

		public Builder setDimension(Integer dimension) {
			this.dimension = dimension;
			return this;
		}

	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public List<Move> getMove() {
		return moves;
	}

	public void setMove(List<Move> moves) {
		this.moves = moves;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public Integer getNextPlayerIndex() {
		return nextPlayerIndex;
	}

	public void setNextPlayerIndex(Integer nextPlayerIndex) {
		this.nextPlayerIndex = nextPlayerIndex;
	}

}

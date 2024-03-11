package tictactoe.botplayingstrategy;

public class BotPlayingStrategyFactory {

	public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel() {
		
		return new EasyBotPlayingStrategy();
	}

}

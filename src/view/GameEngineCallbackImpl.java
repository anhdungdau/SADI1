package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class GameEngineCallbackImpl implements GameEngineCallback
{
	private Logger logger;
	private int total;
	private ArrayList<Integer> al = new ArrayList();

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public GameEngineCallbackImpl() {
		logger = Logger.getLogger(GameEngineCallback.class.getName());
		logger.setLevel(Level.FINE);
//		FileHandler filehandler = null;
//		try {
//			filehandler = new FileHandler("Output.log");
//			filehandler.setLevel(Level.FINE);
//			logger.addHandler(filehandler);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		filehandler.setFormatter(new SimpleFormatter());
	}

	//called as the dice are rolling for a Player, use this to update your display or log to console
	public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine) {
		int total = dicePair.getDice1() + dicePair.getDice2();
		displayRoll(player.getPlayerName(), dicePair, total);
	}

	//called when dice have stopped rolling with the final resting dice values
	public void result(Player player, DicePair result, GameEngine gameEngine) {
		int total = result.getDice1() + result.getDice2();
		displayFinalRoll(player.getPlayerName(), result, total);
	}

	//called as the HOUSE dice are rolling, use this to update your display or log to console
	public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine) {
		int total = dicePair.getDice1() + dicePair.getDice2();
		displayRoll("House", dicePair, total);
	}

	//called when HOUSE dice have stopped rolling with the final resting dice values
	public void houseResult(DicePair result, GameEngine gameEngine) {
		total = result.getDice1() + result.getDice2();
		displayFinalRoll("House", result, total);

		// Update bets & print out the result for all players
		for (Player player : gameEngine.getAllPlayers()) {
			if (player.getRollResult().getDice1()+player.getRollResult().getDice2()>al.get(al.size()-1))
				player.setPoints(player.getPoints()+player.getBet()*2);
			else if (player.getRollResult().getDice1()+player.getRollResult().getDice2()==al.get(al.size()-1))
				player.setPoints(player.getPoints()+player.getBet());
			else player.setPoints(player.getPoints());
			displayResult(player);
		}
	}
	
	private void displayRoll(String player, DicePair result, int total) {
		Formatter formatter = new Formatter(new StringBuilder(), Locale.US);
		formatter.format("%s: ROLLING Dice 1: %d, Dice 2: %d .. Total: %d",
				player, result.getDice1(), result.getDice2(), total);
		logger.log(Level.FINE, formatter.toString());
		formatter.close();
	}

	private void displayFinalRoll(String player, DicePair result, int total) {
		Formatter formatter = new Formatter(new StringBuilder(), Locale.US);
		formatter.format("%s: *RESULT* Dice 1: %d, Dice 2: %d .. Total: %d",
				player, result.getDice1(), result.getDice2(), total);
		logger.log(Level.INFO, formatter.toString());
		formatter.close();
		al.add(total);
	}

	public void displayResult(Player player) {
		Formatter formatter = new Formatter(new StringBuilder(), Locale.US);
		formatter.format("Player: id=%s, name=%s, points=%d", player.getPlayerId(), player.getPlayerName(), player.getPoints());
		logger.log(Level.INFO, formatter.toString());
		formatter.close();
		player.toString();
	}
}

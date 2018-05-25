package model;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import view.GameEngineCallbackImpl;

import java.util.ArrayList;
import java.util.Collection;

public class GameEngineImpl implements GameEngine {
    private Collection<GameEngineCallback> gameEngineCallbacks;
    private Collection<Player> players = new ArrayList<Player>();
    private Player house;
    private int houseTotal = 0;
    private GameEngineCallbackImpl a = new GameEngineCallbackImpl();
    
    public GameEngineImpl() {
        gameEngineCallbacks = new ArrayList<GameEngineCallback> ();
        players = new ArrayList<Player>();
        house = new SimplePlayer("HOUSE", 0);
    }

    //Returns: true if the player had sufficient points and the bet was placed
    public boolean placeBet(Player player, int bets) {
        if (bets > player.getPoints())
            return false;
        else
            return player.placeBet(bets);
    }

    //Roll the dice progressing from the initialDelay to the finalDelay in increments of delayIncrement, delays are in milliseconds (ms)
    public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement) {
        DicePair dicePair = null;
        for (int delay = initialDelay; delay < finalDelay; delay += delayIncrement) {
            dicePair = new DicePairImpl();
            for (GameEngineCallback callback : gameEngineCallbacks) {
                callback.intermediateResult(player, dicePair, this);
            }
        }
        dicePair = new DicePairImpl();
        for (GameEngineCallback callback : gameEngineCallbacks) {
            callback.result(player, dicePair,this);
        }
        player.setRollResult(dicePair);
    }

    //Same as rollPlayer() but rolls for the house and calls the house versions of the callback methods on GameEngineCallback,
    public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
        DicePair dicePair = null;
        for (int delay = initialDelay; delay < finalDelay; delay += delayIncrement) {
            dicePair = new DicePairImpl();
            for (GameEngineCallback callback : gameEngineCallbacks) {
                callback.intermediateHouseResult(dicePair, this);
            }
        }
        dicePair = new DicePairImpl();
        for (GameEngineCallback callback : gameEngineCallbacks) {
            callback.houseResult(dicePair, this);
        }        
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    //Returns: the corresponding Player instance or null if Player doesn't exist
    public Player getPlayer(String id) {
        for (Player player: players) {
            if (player.getPlayerId().equals(id)) {
                return player;
            }
        }
        return null;
    }
   
    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
        gameEngineCallbacks.add(gameEngineCallback);
    }

    //Returns: true if the gameEngineCallback existed
    public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
        return gameEngineCallbacks.remove(gameEngineCallback);
    }

    //Returns: an unmodifiable collection of all Players
    public Collection<Player> getAllPlayers() {
        Collection<Player> collection = new ArrayList<Player>();
        for (Player player: players) {
            collection.add(player);
        }
        return collection;
    }
}

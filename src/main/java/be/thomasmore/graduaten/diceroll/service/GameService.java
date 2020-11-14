package be.thomasmore.graduaten.diceroll.service;

import be.thomasmore.graduaten.diceroll.entity.Game;

import java.util.List;

public interface GameService {
    Game getGameById(Long GameID);
    List<Game> getGames();
}

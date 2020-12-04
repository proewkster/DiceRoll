package be.thomasmore.graduaten.diceroll.service;

import be.thomasmore.graduaten.diceroll.entity.Game;
import be.thomasmore.graduaten.diceroll.objects.GameDTO;

import java.util.List;

public interface GameService {
    Game getGameById(Long GameID);
    List<Game> getGames();
    List<Game> getGamesByTitle(String keyword);
    List<Game> getGamesById(Long id);
    List<Game> getHighestRated();
    public void adjustStockGame(Game game, int stock);
    public Game addGame(GameDTO gameDTO) throws Exception;
    public String deleteGame(Long id);
    public void  saveGame(Game game);
    List<Game> getFilterCategorie(Long Id);
}

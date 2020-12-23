package be.thomasmore.graduaten.diceroll.service;

import be.thomasmore.graduaten.diceroll.entity.Game;
import be.thomasmore.graduaten.diceroll.entity.PageInfo;
import be.thomasmore.graduaten.diceroll.objects.GameDTO;

import java.util.List;

public interface GameService {
    Game getGameById(Long GameID);
    PageInfo getGames(int pageNo, int pageSize);
    List<Game> getGamesByTitle(String keyword);
    List<Game> getGamesById(Long id);
    List<Game> getHighestRated(int pageNo, int pageSize);
    public void adjustStockGame(Game game, int stock);
    public Game addGame(GameDTO gameDTO) throws Exception;
    public String deleteGame(Long id);
    public void  saveGame(Game game);
    PageInfo getFilterCategorie(Long Id, int pageNo, int pageSize);
    List<Game> getFilter2Categories(Long Id, Long id2);
}

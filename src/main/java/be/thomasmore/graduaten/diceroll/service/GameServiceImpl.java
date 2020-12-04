package be.thomasmore.graduaten.diceroll.service;

import be.thomasmore.graduaten.diceroll.entity.Game;
import be.thomasmore.graduaten.diceroll.objects.GameDTO;
import be.thomasmore.graduaten.diceroll.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    GameRepository gameRepository;

    @Override
    public Game getGameById(Long GameID) {return gameRepository.getOne(GameID);}

    @Override
    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game addGame(GameDTO gameDTO) throws Exception {
        Game game = new Game();
        game.setTitle(gameDTO.getTitle());
        game.setAge(gameDTO.getAge());
        game.setAvgTime(gameDTO.getAvgtime());
        game.setCategory(gameDTO.getCategory());
        game.setDesigner(gameDTO.getDesigner());
        game.setImgURL(gameDTO.getImguRL());
        game.setDistributer(gameDTO.getDistributor());
        game.setMaxPlayers(gameDTO.getMaxplayers());
        game.setMinPlayers(gameDTO.getMinplayers());
        game.setMinTime(gameDTO.getMintime());
        game.setMaxtime(gameDTO.getMaxtime());
        game.setPrice_Rent(gameDTO.getPrice_Rent());
        game.setPrice_Sale(gameDTO.getPrice_Sale());
        game.setStock_Sale(gameDTO.getStock_sale());
        game.setStock_Rent(gameDTO.getStock_rent());
        game.setYear(gameDTO.getYear()) ;
        game.setRating(gameDTO.getRating());
        game.setNumVotes(gameDTO.getNumvotes());

        return gameRepository.save(game);
    }

    @Override
    public void saveGame(Game game) {
        gameRepository.save(game);
    }

    @Override
    public String deleteGame(Long id) {
        gameRepository.deleteById(id);
        return "Item is Deleted";
    }

    @Override
    public List<Game> getGamesByTitle(String keyword) {
        if (keyword != null){
        return gameRepository.findAll(keyword);
        }
        return gameRepository.findAll();
    }

    @Override
    public List<Game> getGamesById(Long id) {
        if (id != null){
            return gameRepository.findAll(id);
        }
        return gameRepository.findAll();
    }
    @Override
    public void adjustStockGame(Game game, int stock){
        game.setStock_Sale(game.getStock_Sale()-stock);
        gameRepository.save(game);
    }
@Override
    public List<Game> getFilterCategorie(Long id){
        if (id!= null){
            return gameRepository.getfilter1categorie(id);
        }
        return gameRepository.findAll();
}

}


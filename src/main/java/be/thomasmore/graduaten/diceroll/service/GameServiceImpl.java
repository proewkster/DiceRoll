package be.thomasmore.graduaten.diceroll.service;

import be.thomasmore.graduaten.diceroll.entity.Game;
import be.thomasmore.graduaten.diceroll.entity.PageInfo;
import be.thomasmore.graduaten.diceroll.objects.GameDTO;
import be.thomasmore.graduaten.diceroll.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    GameRepository gameRepository;

    @Override
    public Game getGameById(Long GameID) {return gameRepository.getOne(GameID);}

    @Override
    public PageInfo getGames(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo,pageSize);
        Page<Game> pagedResult = gameRepository.findAll(paging);
        return new PageInfo(pagedResult.toList(), pagedResult.getTotalPages(),pageNo, pageSize);
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
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public List<Game> getGamesByTitle(String keyword) {
        if (keyword != null){
        return gameRepository.findAll(keyword);
        }
        //return gameRepository.findAll();
        Pageable paging = PageRequest.of(0,1000);
        Page<Game> pagedResult = gameRepository.findAll(paging);
        return pagedResult.toList();
    }

    @Override
    public List<Game> getGamesById(Long id) {
        if (id != null){
            return gameRepository.findAll(id);
        }
        //return gameRepository.findAll();
        Pageable paging = PageRequest.of(0,1000);
        Page<Game> pagedResult = gameRepository.findAll(paging);
        return pagedResult.toList();
    }

    @Override
    public List<Game> getHighestRated(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(0,20);
        Page<Game> pagedResult = gameRepository.getTop20ByRating(paging);
        return pagedResult.toList();
        //return gameRepository.getTop20ByRating();
    }

    @Override
    public void adjustStockGame(Game game, int stock){
        game.setStock_Sale(game.getStock_Sale()-stock);
        gameRepository.save(game);
    }
@Override
    public PageInfo getFilterCategorie(Long id, int pageNo, int pageSize){
        if (id!= null){
            Pageable paging = PageRequest.of(pageNo,pageSize);
            Page<Game> pagedResult =gameRepository.getfilter1categorie(id, paging);
            return new PageInfo(pagedResult.toList(), pagedResult.getTotalPages(),pageNo, pageSize);
        }
        //return gameRepository.findAll();
    Pageable paging = PageRequest.of(0,20);
    Page<Game> pagedResult = gameRepository.findAll(paging);
    return new PageInfo(pagedResult.toList(), pagedResult.getTotalPages(),pageNo, pageSize);
}
    @Override
    public List<Game> getFilter2Categories(Long id, Long id2){
        if (id!= null && id2!=null){
            return gameRepository.getfilter2categorie(id, id2);
        }
        //return gameRepository.findAll();
        Pageable paging = PageRequest.of(0,20);
        Page<Game> pagedResult = gameRepository.findAll(paging);
        return pagedResult.toList();
    }

}

package be.thomasmore.graduaten.diceroll.repository;

import be.thomasmore.graduaten.diceroll.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
@Query("Select g from Game g where g.Title LIKE %?1%")
    public List<Game> findAll(String keyword);
@Query("select g from Game g where g.GameID=?1")
    public List<Game> findAll(Long id);
@Query("SELECT g FROM Game g ORDER BY g.Rating DESC")
    public  List<Game> getTop20ByRating();
@Query("SELECT g FROM Game g INNER JOIN GameCategorie gc ON g.GameID=gc.GameID WHERE gc.CategorieId=?1")
    public  List<Game> getfilter1categorie(Long id);
@Query("SELECT g FROM Game g  WHERE g.GameID IN (SELECT gc.GameID FROM GameCategorie  gc WHERE gc.CategorieId=?1) AND g.GameID IN (SELECT gc.GameID FROM GameCategorie  gc WHERE gc.CategorieId=?2)")
    public  List<Game> getfilter2categorie(Long id, long id2);
}

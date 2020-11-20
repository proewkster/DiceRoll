package be.thomasmore.graduaten.diceroll.repository;

import be.thomasmore.graduaten.diceroll.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
@Query("Select g from Game g where g.Title LIKE %?1%")
    public List<Game> findAll(String keyword);
@Query("select g from Game g where g.GameID=?1")
    public List<Game> findAll(Long id);
}

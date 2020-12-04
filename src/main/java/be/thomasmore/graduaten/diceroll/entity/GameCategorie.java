package be.thomasmore.graduaten.diceroll.entity;

import javax.persistence.*;

@Entity
@Table(name= "GameCategories")
public class GameCategorie {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long GameCategorieId;
        private long GameID;
        private long CategorieId;

    public GameCategorie() {
    }

    public GameCategorie(long gameCategorieId, long gameID, long categorieId) {
        GameCategorieId = gameCategorieId;
        GameID = gameID;
        CategorieId = categorieId;
    }

    public long getGameCategorieId() {
        return GameCategorieId;
    }

    public void setGameCategorieId(long gameCategorieId) {
        GameCategorieId = gameCategorieId;
    }

    public long getGameID() {
        return GameID;
    }

    public void setGameID(long gameID) {
        GameID = gameID;
    }

    public long getCategorieId() {
        return CategorieId;
    }

    public void setCategorieId(long categorieId) {
        CategorieId = categorieId;
    }
}

package be.thomasmore.graduaten.diceroll.entity;

import javax.persistence.*;

@Entity
@Table(name= "Categories")

public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long CategorieId;
    private String Genre;

    public Categorie() {
    }

    public Categorie(long categorieId, String genre) {
        CategorieId = categorieId;
        Genre = genre;
    }

    public long getCategorieId() {
        return CategorieId;
    }

    public void setCategorieId(long categorieId) {
        CategorieId = categorieId;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }
}

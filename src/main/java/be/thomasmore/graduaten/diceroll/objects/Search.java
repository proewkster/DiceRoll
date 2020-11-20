package be.thomasmore.graduaten.diceroll.objects;

public class Search {
    private String keyword;
    private Long id;

    public Search() {
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

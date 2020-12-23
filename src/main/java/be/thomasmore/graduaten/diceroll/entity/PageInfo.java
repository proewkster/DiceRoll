package be.thomasmore.graduaten.diceroll.entity;

import java.util.List;

public class PageInfo {
    public List<Game> gamePage;
    public int totalPages;
    public int currentPage;
    public  int pageSize;



    public PageInfo() {
    }

    public PageInfo(List<Game> gamePage, int totalPages, int currentPage, int pageSize) {
        this.gamePage = gamePage;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.pageSize=pageSize;
    }

    public List<Game> getGamePage() {
        return gamePage;
    }

    public void setGamePage(List<Game> gamePage) {
        this.gamePage = gamePage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}

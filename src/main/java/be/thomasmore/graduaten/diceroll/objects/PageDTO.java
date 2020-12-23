package be.thomasmore.graduaten.diceroll.objects;

public class PageDTO {
    public int currentPage;

    public PageDTO() {
    }

    public PageDTO(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}

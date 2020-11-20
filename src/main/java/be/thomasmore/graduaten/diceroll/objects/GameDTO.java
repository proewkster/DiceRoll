package be.thomasmore.graduaten.diceroll.objects;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class GameDTO {

    private Long gameid;
    @NotBlank(message = "No blank space in title")
    private String title;
    @NotNull(message = "Give value")
    private Long minplayers;
    @NotNull(message = "Value May not be null")
    private Long maxplayers;
    @NotNull(message = "Value May not be null")
    private Long mintime;
    @NotNull(message = "Value May not be null")
    private Long maxtime;
    @NotNull(message = "Value May not be null")
    private Long avgtime;
    @NotNull(message = "Value May not be null")
    private String year;
    @NotNull(message = "Value May not be null")
    private Long rating;
    @NotNull(message = "Value May not be null")
    private Long numvotes;
    @NotBlank(message = "No blank space in imgURL")
    private String imguRL;
    @NotNull(message = "Value May not be null")
    private Long age;
    @NotBlank(message = "No blank space allowed")
    private String category;
    @NotBlank(message = "No blank space allowed")
    private String designer;
    @NotBlank(message = "No blank space allowed")
    private String distributor;
    @NotNull(message = "Value May not be null")
    private double price_Sale;
    @NotNull(message = "Value May not be null")
    private double price_Rent;
    @NotNull(message = "Value May not be null")
    private Long stock_sale;
    @NotNull(message = "Value May not be null")
    private Long stock_rent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getMaxplayers() {
        return maxplayers;
    }

    public void setMaxplayers(Long maxplayers) {
        this.maxplayers = maxplayers;
    }

    public Long getMintime() {
        return mintime;
    }

    public void setMintime(Long mintime) {
        this.mintime = mintime;
    }

    public Long getMaxtime() {
        return maxtime;
    }

    public void setMaxtime(Long maxtime) {
        this.maxtime = maxtime;
    }

    public Long getAvgtime() {
        return avgtime;
    }

    public void setAvgtime(Long avgtime) {
        this.avgtime = avgtime;
    }

    public String getYear() {

        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public Long getNumvotes() {
        return numvotes;
    }

    public void setNumvotes(Long numvotes) {
        this.numvotes = numvotes;
    }

    public String getImguRL() {
        return imguRL;
    }

    public void setImguRL(String imguRL) {
        this.imguRL = imguRL;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public double getPrice_Sale() {
        return price_Sale;
    }

    public void setPrice_Sale(double price_Sale) {
        this.price_Sale = price_Sale;
    }

    public double getPrice_Rent() {
        return price_Rent;
    }

    public void setPrice_Rent(double price_Rent) {
        this.price_Rent = price_Rent;
    }

    public Long getStock_sale() {
        return stock_sale;
    }

    public void setStock_sale(Long stock_sale) {
        this.stock_sale = stock_sale;
    }

    public Long getStock_rent() {
        return stock_rent;
    }

    public void setStock_rent(Long stock_rent) {
        this.stock_rent = stock_rent;
    }



    public Long getMinplayers() {
        return minplayers;
    }

    public void setMinplayers(Long minplayers) {
        this.minplayers = minplayers;
    }

    public Long getGameid() {
        return gameid;
    }

    public void setGameid(Long gameid) {
        this.gameid = gameid;
    }

    public GameDTO() {
    }


}

package be.thomasmore.graduaten.diceroll.entity;

import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "Games")

public class Game {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long GameID;
    private String Title;
    private Long MinPlayers;
    private Long MaxPlayers;
    private Long MinTime;
    private Long Maxtime;
    private Long AvgTime;
    private Date Year;
    private Long Rating;
    private Long NumVotes;
    private String ImgURL;
    private Long Age;
    private String Category;
    private String Designer;
    private String Distributor;
    private double Price_Sale;
    private double Price_Rent;
    private Long Stock_Sale;
    private Long Stock_Rent;
    @OneToMany(mappedBy = "game")
    Set<SoldGame> soldGame = new HashSet<>();

    public Game() {
    }

    public Game(Long gameID, String title, Long minPlayers, Long maxPlayers, Long minTime, Long maxtime, Long avgTime, Date year, Long rating, Long numVotes, String imgURL, Long age, String category, String designer, String distributer, double price_Sale, double price_Rent, Long stock_Sale, Long stock_Rent) {
        GameID = gameID;
        Title = title;
        MinPlayers = minPlayers;
        MaxPlayers = maxPlayers;
        MinTime = minTime;
        Maxtime = maxtime;
        AvgTime = avgTime;
        Year = year;
        Rating = rating;
        NumVotes = numVotes;
        ImgURL = imgURL;
        Age = age;
        Category = category;
        Designer = designer;
        Distributor = distributer;
        Price_Sale = price_Sale;
        Price_Rent = price_Rent;
        Stock_Sale = stock_Sale;
        Stock_Rent = stock_Rent;
    }
    @Pattern(regexp = "[0-9][0-9][0-9][0-9]",message = "DateFormat = YYYY")
    public String getYear() {
        if (Year != null){
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        return dateFormat.format(Year);
        }
        return "2000";
    }
    public void setYear(String year) throws Exception{
        this.Year = new SimpleDateFormat("yyyy").parse(year);
    }

    public String getDistributor() {
        return Distributor;
    }

    public void setDistributor(String distributor) {
        Distributor = distributor;
    }

    public Long getGameID() {
        return GameID;
    }

    public void setGameID(Long gameID) {
        GameID = gameID;
    }
    @NotBlank(message = "Minplayer moet ingevuld zijn")
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
    @NotNull(message = "Value May not be null")
    public Long getMinPlayers() {
        return MinPlayers;
    }

    public void setMinPlayers(Long minPlayers) {
        MinPlayers = minPlayers;
    }
    @NotNull(message = "Value May not be null")
    public Long getMaxPlayers() {
        return MaxPlayers;
    }

    public void setMaxPlayers(Long maxPlayers) {
        MaxPlayers = maxPlayers;
    }
    @NotNull(message = "Value May not be null")
    public Long getMinTime() {
        return MinTime;
    }

    public void setMinTime(Long minTime) {
        MinTime = minTime;
    }
    @NotNull(message = "Value May not be null")
    public Long getMaxtime() {
        return Maxtime;
    }

    public void setMaxtime(Long maxtime) {
        Maxtime = maxtime;
    }
    @NotNull(message = "Value May not be null")
    public Long getAvgTime() {
        return AvgTime;
    }

    public void setAvgTime(Long avgTime) {
        AvgTime = avgTime;
    }
    @NotNull(message = "Value May not be null")
    public Long getRating() {
        return Rating;
    }

    public void setRating(Long rating) {
        Rating = rating;
    }
    @NotNull(message = "Value May not be null")
    public Long getNumVotes() {
        return NumVotes;
    }

    public void setNumVotes(Long numVotes) {
        NumVotes = numVotes;
    }
    @NotBlank(message = "No null String allowed")
    public String getImgURL() {
        return ImgURL;
    }

    public void setImgURL(String imgURL) {
        ImgURL = imgURL;
    }
    @NotNull(message = "Value May not be null")
    public Long getAge() {
        return Age;
    }

    public void setAge(Long age) {
        Age = age;
    }
    @NotBlank(message = "No null String allowed")
    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
    @NotBlank(message = "No null String allowed")
    public String getDesigner() {
        return Designer;
    }

    public void setDesigner(String designer) {
        Designer = designer;
    }
    @NotBlank(message = "No null String allowed")
    public String getDistributer() {
        return Distributor;
    }

    public void setDistributer(String distributer) {
        Distributor = distributer;
    }
    @NotNull(message = "Value May not be null")
    public double getPrice_Sale() {
        return Price_Sale;
    }

    public void setPrice_Sale(double price_Sale) {
        Price_Sale = price_Sale;
    }
    @NotNull(message = "Value May not be null")
    public double getPrice_Rent() {
        return Price_Rent;
    }

    public void setPrice_Rent(double price_Rent) {
        Price_Rent = price_Rent;
    }
    @NotNull(message = "Value May not be null")
    public Long getStock_Sale() {
        return Stock_Sale;
    }

    public void setStock_Sale(Long stock_Sale) {
        Stock_Sale = stock_Sale;
    }
    @NotNull(message = "Value May not be null")
    public Long getStock_Rent() {
        return Stock_Rent;
    }

    public void setStock_Rent(Long stock_Rent) {
        Stock_Rent = stock_Rent;
    }

    public Set<SoldGame> getSoldGame() {
        return soldGame;
    }

    public void setSoldGame(Set<SoldGame> soldGame) {
        this.soldGame = soldGame;
    }
}

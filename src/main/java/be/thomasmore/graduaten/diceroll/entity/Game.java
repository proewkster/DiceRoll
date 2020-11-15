package be.thomasmore.graduaten.diceroll.entity;

import javax.persistence.*;
import java.util.Date;

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

    public Long getGameID() {
        return GameID;
    }

    public void setGameID(Long gameID) {
        GameID = gameID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Long getMinPlayers() {
        return MinPlayers;
    }

    public void setMinPlayers(Long minPlayers) {
        MinPlayers = minPlayers;
    }

    public Long getMaxPlayers() {
        return MaxPlayers;
    }

    public void setMaxPlayers(Long maxPlayers) {
        MaxPlayers = maxPlayers;
    }

    public Long getMinTime() {
        return MinTime;
    }

    public void setMinTime(Long minTime) {
        MinTime = minTime;
    }

    public Long getMaxtime() {
        return Maxtime;
    }

    public void setMaxtime(Long maxtime) {
        Maxtime = maxtime;
    }

    public Long getAvgTime() {
        return AvgTime;
    }

    public void setAvgTime(Long avgTime) {
        AvgTime = avgTime;
    }

    public Date getYear() {
        return Year;
    }

    public void setYear(Date year) {
        Year = year;
    }

    public Long getRating() {
        return Rating;
    }

    public void setRating(Long rating) {
        Rating = rating;
    }

    public Long getNumVotes() {
        return NumVotes;
    }

    public void setNumVotes(Long numVotes) {
        NumVotes = numVotes;
    }

    public String getImgURL() {
        return ImgURL;
    }

    public void setImgURL(String imgURL) {
        ImgURL = imgURL;
    }

    public Long getAge() {
        return Age;
    }

    public void setAge(Long age) {
        Age = age;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDesigner() {
        return Designer;
    }

    public void setDesigner(String designer) {
        Designer = designer;
    }

    public String getDistributer() {
        return Distributor;
    }

    public void setDistributer(String distributer) {
        Distributor = distributer;
    }

    public double getPrice_Sale() {
        return Price_Sale;
    }

    public void setPrice_Sale(double price_Sale) {
        Price_Sale = price_Sale;
    }

    public double getPrice_Rent() {
        return Price_Rent;
    }

    public void setPrice_Rent(double price_Rent) {
        Price_Rent = price_Rent;
    }

    public Long getStock_Sale() {
        return Stock_Sale;
    }

    public void setStock_Sale(Long stock_Sale) {
        Stock_Sale = stock_Sale;
    }

    public Long getStock_Rent() {
        return Stock_Rent;
    }

    public void setStock_Rent(Long stock_Rent) {
        Stock_Rent = stock_Rent;
    }
}
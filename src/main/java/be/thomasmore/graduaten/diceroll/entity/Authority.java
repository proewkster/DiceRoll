package be.thomasmore.graduaten.diceroll.entity;

import javax.persistence.*;

@Entity
@Table(name="Authorities")
public class Authority {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="AuthorityID")
    private int authorityID;
    @Column(name="Name")
    private String name;

    //Constructors

    public Authority() {
    }

    //Getters and Setters

    public int getAuthorityID() {
        return authorityID;
    }

    public void setAuthorityID(int authorityID) {
        this.authorityID = authorityID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

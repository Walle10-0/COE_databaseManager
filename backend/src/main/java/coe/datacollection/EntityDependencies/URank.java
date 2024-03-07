package coe.datacollection.EntityDependencies;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "_rank", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class URank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "_rank")
    private String rank;

    public URank() {
    }

    public URank(String value) {
        if (value.equals("Instructor")) {
            setId(1);
        } else if (value.equals("Assistant Professor")) {
            setId(2);
        } else if (value.equals("Associate Professor")) {
            setId(3);
        } else if (value.equals("Professor")) {
            setId(4);
        } else if (value.equals("Distinguished Professor")) {
            setId(5);
        } else {
            throw new IllegalArgumentException("Invalid rank: " + value);
        }
        rank = value;
    }  

    // setters and getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String x) {
        if (x.equals("Instructor")) {
            setId(1);
        } else if (x.equals("Assistant Professor")) {
            setId(2);
        } else if (x.equals("Associate Professor")) {
            setId(3);
        } else if (x.equals("Professor")) {
            setId(4);
        } else if (x.equals("Distinguished Professor")) {
            setId(5);
        } else {
            throw new IllegalArgumentException("Invalid rank: " + x);
        }
        rank = x;
    }
}
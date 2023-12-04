package coe.datacollection.EntityDependencies;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "_level", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class SLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "_level")
    private String level;

    // setters and getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String lev) {
        this.level = lev;
    }
}
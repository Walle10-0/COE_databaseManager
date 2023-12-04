package coe.datacollection.EntityDependencies;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "_load", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class CLoad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "_load")
    private String load;

    // setters and getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoad() {
        return load;
    }

    public void setLoad(String x) {
        this.load = x;
    }
}
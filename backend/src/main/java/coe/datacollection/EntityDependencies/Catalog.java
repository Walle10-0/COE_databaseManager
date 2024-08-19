package coe.datacollection.EntityDependencies;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "_catalog", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "class_num")
    private int classNumber;

    @Column(name = "credit_hours")
    private int creditHours;

    @ManyToOne
    @JoinColumn(name = "_class_type")
    private CClassType classType;

    public String getClassName() {
        return prefix + " " + classNumber;
    }
}
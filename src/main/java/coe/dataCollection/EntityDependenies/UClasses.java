package coe.dataCollection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "classes")
public class UClasses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "_class_pre")
    private ClassPre prefix;

    @Column(name = "class_num")
    private int classNumber;
	
    @Column(name = "credit_hours")
    private int creditHours;

    @ManyToOne
    @JoinColumn(name = "_class_type")
    private CClassType classType;
    
    // Getters and setters
}
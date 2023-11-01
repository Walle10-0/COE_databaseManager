package coe.dataCollection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
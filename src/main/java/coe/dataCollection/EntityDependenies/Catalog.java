package coe.dataCollection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "_catalog", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Catalog {
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
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
	
	public ClassPre getPrefix() {
        return prefix;
    }

    public void setClassPre(ClassPre nPre) {
        this.prefix = nPre;
	}

    public String getClassName() {
        return prefix.getClassPre() + classNumber;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public CClassType getClassType() {
        return classType;
    }

    public void setClassType(CClassType classType) {
        this.classType = classType;
	}
}
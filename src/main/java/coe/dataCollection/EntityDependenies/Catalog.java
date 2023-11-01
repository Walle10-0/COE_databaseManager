package coe.dataCollection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
	}
}
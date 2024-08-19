package coe.datacollection.EntityDependencies;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
//import jakarta.persistence.Transient;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "_semester", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Semester {
    @Id
	@JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "_year")
    private int year;

    @Column(name = "semester_name")
    private String semesterName;
		
	@JsonIgnore
	public String getFullName()
	{
		return semesterName + " " + year;
	}
}

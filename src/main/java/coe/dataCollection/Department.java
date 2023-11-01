package coe.dataCollection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "_dept", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Department
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
 
	@Column(name = "_dept")
	private String name;
	
	@OneToMany(mappedBy="dept", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;

	// setters and getters
	
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return name;
    }

    public void setDepartment(String dept) {
        this.name = dept;
    }
}
package coe.dataCollection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "_user_role", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class UserRole
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
 
	@Column(name = "_user_role")
	private String role;

	// setters and getters
	
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String x) {
        this.role = x;
    }
}
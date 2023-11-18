package coe.datacollection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	// add custom query methods here

	public Department findById(int id);

	@Query("SELECT d.name FROM Department d WHERE d.id = :id")
	public String findNameFromId(@Param("id") Integer id);

	@Query("SELECT d.id FROM Department d WHERE d.name = :name")
	public String findIdFromName(@Param("name") Integer name);

	@Query("SELECT d.users FROM Department d WHERE d.id = :id")
	public List<User> findUsersByDepartmentId(@Param("id") Integer id);

	@Query("SELECT d FROM Department d WHERE d.name LIKE %:partialName%")
	public List<Department> findByNameContaining(@Param("partialName") String partialName);

}

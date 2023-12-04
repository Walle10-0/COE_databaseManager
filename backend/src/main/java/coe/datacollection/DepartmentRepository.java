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
	public Department findByDeptName(String deptName);
	
	/*
	@Query("SELECT d.department FROM Department")
	public List<String> getNames();*/

	@Query("SELECT d.deptName FROM Department d WHERE d.deptId = :id")
	public String findNameFromId(@Param("id") Integer id);

	@Query("SELECT d.deptId FROM Department d WHERE d.deptName = :name")
	public int findIdFromName(@Param("name") Integer name);

	@Query("SELECT d FROM Department d WHERE d.deptName LIKE %:partialName%")
	public List<Department> findByNameContaining(@Param("partialName") String partialName);

}

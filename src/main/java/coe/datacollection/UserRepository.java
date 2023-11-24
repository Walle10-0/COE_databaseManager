package coe.datacollection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u.department FROM User u WHERE u.userId = :userId")
	public Department findDepartmentByUserId(@Param("userId") Long userId);

	@Query("SELECT u FROM User u WHERE u.department = :fdep")
	public List<User> findUsersByDepartment(@Param("fdep") Department fdep);
	
	@Query("SELECT u FROM User u WHERE u.department.deptId = :id")
	public List<User> findUsersByDepartmentId(@Param("id") Integer id);

	//@Query("SELECT u FROM User u WHERE u.uid = :check")
	//public List<User> findByNameContaining(@Param("check") String check);
}

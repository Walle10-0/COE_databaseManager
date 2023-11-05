package coe.dataCollection;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import coe.dataCollection.User;
import coe.dataCollection.Department;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	
	@Query("SELECT u.dept FROM User u WHERE u.uid = :userId")
    public Department findDepartmentByUserId(@Param("userId") Long userId);
	
	@Query("SELECT u FROM User u WHERE u.dept = :fdep")
    public List<User> findUsersByDepartment(@Param("fdep") Department fdep);
	
	@Query("SELECT u FROM User u WHERE u.uid = :check")
	public List<User> findByNameContaining(@Param("check") String check);
	
	@Query("SELECT u FROM User u WHERE u.dept.id = :depNum")
	public List<User> findByDeptNum(@Param("depNum") int depNum);

}
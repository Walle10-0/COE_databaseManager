package coe.datacollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

@Repository
public class GenericRepository {

	@Autowired
	private EntityManager entityManager;

	public List<String> findStringVals(String entity, String feild) {
        Query query = entityManager.createQuery("SELECT e." + feild + " FROM " + entity + " e");
        return query.getResultList();
    }
	
	public List<String> test() {
        Query query = entityManager.createQuery("SELECT e.semesterName.semesterName FROM Semester e");
        return query.getResultList();
    }
	
	public <T> T findByString(String entity, String feild, String value) {
        Query query = entityManager.createQuery("SELECT e FROM " + entity + " e WHERE e." + feild + " = :value");
		query.setParameter("value", value);
		List result = query.getResultList();
        return (T) (result.isEmpty() ? null : result.get(0));
    }

}

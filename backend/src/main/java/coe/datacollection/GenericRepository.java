package coe.datacollection;

import coe.datacollection.EntityDependencies.Semester;

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

	public Semester findSemester(String name, int year) {
        	Query query = entityManager.createQuery("SELECT e FROM Semester e WHERE e.semesterName = :name");
		query.setParameter("name", name);
		List<Semester> mySearch = (List<Semester>)query.getResultList();

		Semester result = null;
		for(Semester s : mySearch)
		{
			if(s.getFullName().equalsIgnoreCase(name + " " + year))
			{
				result = s;
			}
		}
		if(result == null)
		{
			result = new Semester();
			result.setYear(year);
			result.setSemesterName(name);
		}
        	return result;
    	}

}

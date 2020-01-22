package repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import entities.Faculty;
import repository.FacultryRepository;

@Repository
public class FacultryRepositoryImpl implements FacultryRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Faculty> getAllFaculties() {
		List<Faculty> faculties = new ArrayList<>();
		try {
			TypedQuery<Faculty> facultiesQuery = entityManager.createQuery("Select faculty from Faculty faculty ",
					Faculty.class);
			faculties = facultiesQuery.getResultList();
			return faculties;
		} catch (Exception e) {
			return faculties;
		}
	}

	@Override
	public Faculty getFacultyById(Long id) {
		Faculty faculty = new Faculty();
		try {
			TypedQuery<Faculty> facultyQuery = entityManager
					.createQuery("Select faculty from Faculty faculty where faculty.id=:id", Faculty.class);
			facultyQuery.setParameter("id", id);
			faculty = facultyQuery.getSingleResult();
			return faculty;
		} catch (Exception e) {
			return faculty;
		}
	}

}

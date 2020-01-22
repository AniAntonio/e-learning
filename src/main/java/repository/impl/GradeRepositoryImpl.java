package repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import entities.Grade;
import repository.GradeRepository;

@Repository
public class GradeRepositoryImpl implements GradeRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean saveGrade(Grade grade) {
		try {
			entityManager.persist(grade);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public List<Grade> getAllGradesOFStudent(Long studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Grade> getAllGradesOfTest(Long testId) {
		List<Grade> grades = new ArrayList<>();
		try {
			TypedQuery<Grade> gradesQuery = entityManager
					.createQuery("Select grade from Grade grade where grade.test.id = :id", Grade.class);
			gradesQuery.setParameter("id", testId);
			grades = gradesQuery.getResultList();
			return grades;
		} catch (Exception e) {
			return grades;
		}

	}

}

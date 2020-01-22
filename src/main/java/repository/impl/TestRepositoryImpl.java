package repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import entities.Test;
import entities.UserAlternative;
import repository.TestRepository;

@Repository
public class TestRepositoryImpl implements TestRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Test getTestById(Long id) {
		Test test = new Test();
		try {
			TypedQuery<Test> testQuery = entityManager.createQuery("Select test from Test test where test.id=:id",
					Test.class);
			testQuery.setParameter("id", id);
			test = testQuery.getSingleResult();
			return test;
		} catch (Exception e) {
			return test;
		}
	}

	@Override
	public Test createTest(Test test) {
		entityManager.persist(test);
		return test;
	}

	@Override
	public List<Test> getTestByCourse(Long courseId) {
		List<Test> tests = new ArrayList<Test>();
		try {
			TypedQuery<Test> testQuery = entityManager
					.createQuery("Select test from Test test where test.course.id=:id", Test.class);
			testQuery.setParameter("id", courseId);
			tests = testQuery.getResultList();
			return tests;
		} catch (Exception e) {
			return tests;
		}
	}

	@Override
	public boolean saveUserAlternative(UserAlternative userAlternative) {
		try {
			entityManager.persist(userAlternative);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}

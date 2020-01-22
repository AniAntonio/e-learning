package repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import entities.Course;
import entities.User;
import repository.CourseRepository;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean saveCourse(Course course) {
		try {
			entityManager.merge(course);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean createCourse(Course course) {
		try {
			entityManager.persist(course);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Course getCourseById(Long id) {
		Course course = new Course();
		try {
			TypedQuery<Course> courseQuery = entityManager
					.createQuery("Select course from Course course where course.id=:id", Course.class);
			courseQuery.setParameter("id", id);
			course = courseQuery.getSingleResult();
			return course;
		} catch (Exception e) {
			return course;
		}
	}

	@Override
	public List<Course> getAllPedagogueCourses(Long idPedagogue) {
		List<Course> courses = new ArrayList<>();
		try {
			TypedQuery<Course> coursesQuery = entityManager
					.createQuery("Select course from Course course where course.pedagogue.id=:id", Course.class);
			coursesQuery.setParameter("id", idPedagogue);
			courses = coursesQuery.getResultList();
			return courses;
		} catch (Exception e) {
			return courses;
		}
	}

	@Override
	public List<Course> getAllStudentCourses(User student) {
		List<Course> courses = new ArrayList<>();
		try {

			TypedQuery<Course> coursesQuery = entityManager.createQuery(
					"Select course from Course course  where (:user) MEMBER OF course.users ", Course.class);
			coursesQuery.setParameter("user", student);
			courses = coursesQuery.getResultList();
			return courses;
		} catch (Exception e) {
			return courses;
		}
	}

}

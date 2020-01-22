package repository;

import java.util.List;

import entities.Course;
import entities.User;

public interface CourseRepository {

	boolean createCourse(Course course);

	Course getCourseById(Long id);

	List<Course> getAllPedagogueCourses(Long idPedagogue);

	boolean saveCourse(Course course);

	List<Course> getAllStudentCourses(User student);

}

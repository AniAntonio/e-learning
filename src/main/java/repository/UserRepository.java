package repository;

import java.util.List;

import entities.Course;
import entities.Role;
import entities.User;

public interface UserRepository {
	List<User> getAllStudents();

	List<User> getAllPedagogues();

	User getUserByUsernameAndPassword(String username, String password);

	User getUserByUsername(String username);

	boolean addUser(User user);

	boolean deleteUser(User user);

	boolean editUser(User user);

	User getUserById(Long id);

	Role getRoleById(Long id);

	List<User> getAllStudentsNotRegisteredInCourse(Course course, Long facultyId);

	List<User> getUsersByIds(List<Long> ids);
}

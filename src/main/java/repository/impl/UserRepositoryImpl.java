package repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import entities.Course;
import entities.Role;
import entities.User;
import repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<User> getAllStudents() {
		List<User> users = new ArrayList<>();
		try {
			TypedQuery<User> usersQuery = entityManager.createQuery(
					"Select user from User user where user.deleted is false and user.role.id=:id", User.class);
			usersQuery.setParameter("id", 3L);
			users = usersQuery.getResultList();
			return users;
		} catch (Exception e) {
			return users;
		}
	}

	public User getUserByUsernameAndPassword(String username, String password) {
		User user = new User();
		try {
			TypedQuery<User> userQuery = entityManager.createQuery(
					"Select u from User u where u.username=:username and u.password=:password and u.deleted is false",
					User.class);
			userQuery.setParameter("username", username);
			userQuery.setParameter("password", password);
			user = userQuery.getSingleResult();
			return user;
		} catch (Exception e) {
			return user;

		}
	}

	public User getUserByUsername(String username) {
		User user = new User();
		try {
			TypedQuery<User> userQuery = entityManager.createQuery(
					"Select user from User user where user.username=:username and user.deleted is false", User.class);
			userQuery.setParameter("username", username);
			user = userQuery.getSingleResult();
			return user;
		} catch (Exception e) {
			return user;
		}
	}

	public boolean addUser(User user) {
		try {
			entityManager.persist(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteUser(User user) {
		try {
			user.setDeleted(Boolean.TRUE);
			entityManager.merge(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean editUser(User user) {
		try {

			entityManager.merge(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public User getUserById(Long id) {
		User user = new User();
		try {
			TypedQuery<User> userQuery = entityManager
					.createQuery("Select user from User user where user.id=:id and user.deleted is false", User.class);
			userQuery.setParameter("id", id);
			user = userQuery.getSingleResult();
			return user;
		} catch (Exception e) {
			return user;
		}
	}

	@Override
	public Role getRoleById(Long id) {
		Role role = new Role();
		try {
			TypedQuery<Role> roleQuery = entityManager.createQuery("Select role from Role role where role.id=:id",
					Role.class);
			roleQuery.setParameter("id", id);
			role = roleQuery.getSingleResult();
			return role;
		} catch (Exception e) {
			return role;
		}
	}

	@Override
	public List<User> getAllPedagogues() {
		List<User> users = new ArrayList<>();
		try {
			TypedQuery<User> usersQuery = entityManager.createQuery(
					"Select user from User user where user.deleted is false and user.role.id=:id", User.class);
			usersQuery.setParameter("id", 2L);
			users = usersQuery.getResultList();
			return users;
		} catch (Exception e) {

			return users;
		}
	}

	@Override
	public List<User> getAllStudentsNotRegisteredInCourse(Course course, Long facultyId) {
		List<User> users = new ArrayList<>();
		List<Long> ids = new ArrayList<>();
		if (course.getUsers() != null) {
			ids = course.getUsers().stream().map(e -> e.getId()).collect(Collectors.toList());
		}
		if (CollectionUtils.isEmpty(ids)) {

			ids.add(0L);
		}
		try {
			TypedQuery<User> usersQuery = entityManager.createQuery(
					"select u from User u where u.id  not in  (?1) and u.faculty.id =?2 and u.role.id=3 ", User.class);
			usersQuery.setParameter(1, ids).setParameter(2, facultyId);
			users = usersQuery.getResultList();
			return users;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return users;
		}
	}

	@Override
	public List<User> getUsersByIds(List<Long> ids) {
		List<User> users = new ArrayList<>();
		try {
			TypedQuery<User> usersQuery = entityManager.createQuery(
					"Select user from User user where user.deleted is false and user.id in :ids", User.class);
			usersQuery.setParameter("ids", ids);
			users = usersQuery.getResultList();
			return users;
		} catch (Exception e) {

			return users;
		}
	}

}

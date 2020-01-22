package service;

import java.util.List;

import dto.UserDto;
import entities.Faculty;

public interface UserService {

	List<UserDto> getAllStudents();

	List<UserDto> getAllPedagogues();

	List<Faculty> getAllFaculties();

	UserDto getUserByUsernameAndPassword(String username, String password);

	boolean addUser(UserDto user);

	boolean editUser(UserDto user);

	boolean deleteUser(UserDto user);

	UserDto getUserById(Long id);

	Faculty getFacultyById(Long id);

}

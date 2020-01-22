package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import converter.UserConverter;
import dto.UserDto;
import entities.Faculty;
import entities.Role;
import entities.User;
import repository.FacultryRepository;
import repository.UserRepository;
import service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	FacultryRepository facultyRepository;

	@Override
	public List<UserDto> getAllStudents() {
		List<UserDto> students = new ArrayList<UserDto>();
		for (User student : userRepository.getAllStudents()) {
			students.add(UserConverter.toUserDto(student));
		}
		return students;
	}

	@Override
	public UserDto getUserByUsernameAndPassword(String username, String password) {
		return UserConverter.toUserDto(userRepository.getUserByUsernameAndPassword(username, password));
	}

	@Override
	public UserDto getUserById(Long id) {
		return UserConverter.toUserDto(userRepository.getUserById(id));
	}

	@Override
	public boolean editUser(UserDto userDto) {
		Long id = userRepository.getUserByUsername(userDto.getUsername()).getId();
		if (id != null && id != userDto.getId()) {
			return false;
		}
		User user = userRepository.getUserById(userDto.getId());
		User student = UserConverter.toUserUpdateEntity(userDto, user);
		if (userDto.getIdFaculty() != null)
			student.setFaculty(facultyRepository.getFacultyById(userDto.getIdFaculty()));
		return userRepository.editUser(student);
	}

	@Override
	public boolean deleteUser(UserDto userDto) {
		User user = userRepository.getUserById(userDto.getId());
		return userRepository.deleteUser(user);
	}

	@Override
	public List<Faculty> getAllFaculties() {
		return facultyRepository.getAllFaculties();
	}

	@Override
	public Faculty getFacultyById(Long id) {
		return facultyRepository.getFacultyById(id);
	}

	@Override
	public boolean addUser(UserDto userDto) {
		Long id = userRepository.getUserByUsername(userDto.getUsername()).getId();
		if (id != null) {
			return false;
		}
		userDto.setDeleted(false);
		Role role = userRepository.getRoleById(userDto.getIdRole());
		User student = UserConverter.toUserEntity(userDto);
		student.setRole(role);
		student.setFaculty(facultyRepository.getFacultyById(userDto.getIdFaculty()));
		return userRepository.addUser(student);

	}

	@Override
	public List<UserDto> getAllPedagogues() {
		List<UserDto> pedagogues = new ArrayList<UserDto>();
		for (User pedagogue : userRepository.getAllPedagogues()) {
			pedagogues.add(UserConverter.toUserDto(pedagogue));
		}
		return pedagogues;
	}

}

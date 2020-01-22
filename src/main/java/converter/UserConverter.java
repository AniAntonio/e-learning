package converter;

import java.util.ArrayList;
import java.util.List;

import dto.UserDto;
import entities.User;

public class UserConverter {

	public static User toUserEntity(UserDto userDto) {
		User user = new User();
		if (userDto.getId() != null) {
			user.setId(userDto.getId());
		}
		user.setFirstname(userDto.getFirstname());
		user.setLastname(userDto.getLastname());
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setDeleted(userDto.isDeleted());
		user.setRegDate(userDto.getRegDate());
		return user;
	}

	public static User toUserUpdateEntity(UserDto userDto, User user) {

		if (userDto.getFirstname() != null)
			user.setFirstname(userDto.getFirstname());
		if (userDto.getLastname() != null)
			user.setLastname(userDto.getLastname());
		if (userDto.getUsername() != null)
			user.setUsername(userDto.getUsername());
		if (userDto.getPassword() != null)
			user.setPassword(userDto.getPassword());
		if (userDto.getEmail() != null)
			user.setEmail(userDto.getEmail());
		if (userDto.getRegDate() != null)
			user.setRegDate(userDto.getRegDate());
		return user;
	}

	public static UserDto toUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setFirstname(user.getFirstname());
		userDto.setLastname(user.getLastname());
		userDto.setUsername(user.getUsername());
		userDto.setPassword(user.getPassword());
		userDto.setEmail(user.getEmail());
		userDto.setDeleted(user.isDeleted());
		userDto.setRegDate(user.getRegDate());
		if (user.getRole() != null)
			userDto.setRoleDto(RoleConverter.toRoleDto(user.getRole()));
		if (user.getFaculty() != null)
			userDto.setFacultyDto(FacultyConverter.toFacultyDto(user.getFaculty()));
		return userDto;
	}

	public static List<UserDto> toUserDtos(List<User> users) {
		List<UserDto> userDtos = new ArrayList<UserDto>();
		for (User user : users) {
			userDtos.add(toUserDto(user));
		}
		return userDtos;
	}

	public static List<User> toUserEntities(List<UserDto> userDtos) {
		List<User> users = new ArrayList<User>();
		for (UserDto userDto : userDtos) {
			users.add(toUserEntity(userDto));
		}
		return users;
	}
}

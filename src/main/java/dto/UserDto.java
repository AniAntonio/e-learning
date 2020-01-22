package dto;

import lombok.Data;

@Data
public class UserDto {

	protected Long id;

	private String username;

	private String password;

	private String firstname;

	private String lastname;

	private String email;

	private RoleDto roleDto;

	private boolean deleted;

	private FacultyDto facultyDto;

	private String regDate;

	private Long idFaculty;

	private Long idRole;

}

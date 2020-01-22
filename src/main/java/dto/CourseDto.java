package dto;

import java.util.List;

import lombok.Data;

@Data
public class CourseDto {

	protected Long id;

	private String name;

	private String description;

	private String book;

	private String startDate;

	private String finishDate;

	private UserDto pedagogueDto;

	private FacultyDto facultyDto;

	private List<UserDto> userDtos;

}

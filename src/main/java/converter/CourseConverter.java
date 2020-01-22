package converter;

import java.util.ArrayList;
import java.util.List;

import dto.CourseDto;
import entities.Course;

public class CourseConverter {

	public static CourseDto toDto(Course course) {
		CourseDto courseDto = new CourseDto();
		courseDto.setId(course.getId());
		courseDto.setDescription(course.getDescription());
		courseDto.setBook(course.getBook());
		courseDto.setFacultyDto(FacultyConverter.toFacultyDto(course.getFaculty()));
		courseDto.setStartDate(course.getStartDate());
		courseDto.setFinishDate(course.getFinishDate());
		courseDto.setName(course.getName());
		courseDto.setPedagogueDto(UserConverter.toUserDto(course.getPedagogue()));
		if (course.getUsers() != null)
			courseDto.setUserDtos(UserConverter.toUserDtos(course.getUsers()));
		return courseDto;
	}

	public static Course toEntity(CourseDto courseDto) {
		Course course = new Course();
		course.setId(courseDto.getId());
		course.setDescription(courseDto.getDescription());
		course.setBook(courseDto.getBook());
		course.setStartDate(courseDto.getStartDate());
		course.setFinishDate(courseDto.getFinishDate());
		course.setName(courseDto.getName());
		return course;
	}

	public static List<CourseDto> toDtos(List<Course> courses) {
		List<CourseDto> courseDtos = new ArrayList<CourseDto>();
		for (Course course : courses) {
			courseDtos.add(toDto(course));
		}
		return courseDtos;
	}

	public static List<Course> toEntities(List<CourseDto> courseDtos) {
		List<Course> courses = new ArrayList<Course>();
		for (CourseDto courseDto : courseDtos) {
			courses.add(toEntity(courseDto));
		}
		return courses;
	}

}

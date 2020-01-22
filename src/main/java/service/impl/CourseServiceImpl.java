package service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import converter.CourseConverter;
import converter.UserConverter;
import dto.CourseDto;
import dto.UserDto;
import dto.UtilDto;
import entities.Course;
import entities.User;
import repository.CourseRepository;
import repository.UserRepository;
import service.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserRepository userRepository;

	private DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public boolean createCourse(CourseDto courseDto, Long idPedagogue) {
		if (LocalDate.parse(courseDto.getStartDate(), FOMATTER)
				.isAfter(LocalDate.parse(courseDto.getFinishDate(), FOMATTER))) {
			return false;
		}
		Course course = CourseConverter.toEntity(courseDto);
		User pedagogue = userRepository.getUserById(idPedagogue);
		course.setPedagogue(pedagogue);
		course.setFaculty(pedagogue.getFaculty());
		return courseRepository.createCourse(course);
	}

	@Override
	public CourseDto getCourseById(Long id) {
		return CourseConverter.toDto(courseRepository.getCourseById(id));
	}

	@Override
	public List<CourseDto> getAllPedagogueCourses(Long idPedagogue) {
		return CourseConverter.toDtos(courseRepository.getAllPedagogueCourses(idPedagogue));
	}

	@Override
	public List<UserDto> getAllStudentsNotRegisteredInCourse(Long idCourse) {
		CourseDto courseDto = CourseConverter.toDto(courseRepository.getCourseById(idCourse));
		return UserConverter.toUserDtos(userRepository.getAllStudentsNotRegisteredInCourse(
				CourseConverter.toEntity(courseDto), courseDto.getFacultyDto().getId()));
	}

	@Override
	public boolean addStudentsInCourse(UtilDto utilDto) {
		Course course = courseRepository.getCourseById(utilDto.getIdCourse());
		List<User> students = course.getUsers();
		students.addAll(userRepository.getUsersByIds(utilDto.getStudentIds()));
		course.setUsers(students);
		if (courseRepository.saveCourse(course)) {
			return true;
		}
		return false;
	}

	@Override
	public List<CourseDto> getAllStudentCourses(Long idStudent) {
		User user = userRepository.getUserById(idStudent);
		return CourseConverter.toDtos(courseRepository.getAllStudentCourses(user));
	}

}

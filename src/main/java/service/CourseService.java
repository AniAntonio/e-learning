package service;

import java.util.List;

import dto.CourseDto;
import dto.UserDto;
import dto.UtilDto;

public interface CourseService {

	boolean createCourse(CourseDto courseDto, Long idPedagogue);

	CourseDto getCourseById(Long id);

	List<CourseDto> getAllPedagogueCourses(Long idPedagogue);

	List<UserDto> getAllStudentsNotRegisteredInCourse(Long idCourse);

	boolean addStudentsInCourse(UtilDto utilDto);

	List<CourseDto> getAllStudentCourses(Long idStudent);

}

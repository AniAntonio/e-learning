package service;

import java.util.List;

import dto.UserGradeDto;

public interface GradeService {

	List<UserGradeDto> getTestGrades(Long idTest);

}

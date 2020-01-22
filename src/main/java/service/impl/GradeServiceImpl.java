package service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.UserGradeDto;
import entities.Grade;
import repository.GradeRepository;
import service.GradeService;

@Service
@Transactional
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeRepository gradeRepository;

	@Override
	public List<UserGradeDto> getTestGrades(Long idTest) {
		List<UserGradeDto> gradeDtos = new ArrayList<>();
		List<Grade> grades = gradeRepository.getAllGradesOfTest(idTest);
		for (Grade grade : grades) {
			UserGradeDto userGradeDto = new UserGradeDto(grade.getUser().getFirstname(), grade.getUser().getLastname(),
					grade.getGrade());
			gradeDtos.add(userGradeDto);
		}
		return gradeDtos;
	}

}

package repository;

import java.util.List;

import entities.Grade;

public interface GradeRepository {

	boolean saveGrade(Grade grade);

	List<Grade> getAllGradesOFStudent(Long studentId);
	
	List<Grade> getAllGradesOfTest(Long testId);

}

package repository;

import java.util.List;

import entities.Test;
import entities.UserAlternative;

public interface TestRepository {

	Test getTestById(Long id);

	Test createTest(Test test);

	List<Test> getTestByCourse(Long courseId);

	boolean saveUserAlternative(UserAlternative userAlternative);

}

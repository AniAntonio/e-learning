package service;

import java.util.List;

import dto.QuestionDto;
import dto.TestDto;

public interface TestService {

	List<TestDto> getPedagogueTests(Long idPedagogue);

	TestDto createTest(TestDto testDto);

	TestDto getTestById(Long testId);

	List<TestDto> getTestsByCourseId(Long courseId);

	void submitExamAndCalculateGrade(Long testId, List<QuestionDto> questionDtos, Long loggedUserId);

}

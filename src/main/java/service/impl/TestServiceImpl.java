package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import converter.TestConverter;
import dto.AlternativeDto;
import dto.QuestionDto;
import dto.TestDto;
import entities.Alternative;
import entities.Course;
import entities.Grade;
import entities.Question;
import entities.Test;
import entities.User;
import entities.UserAlternative;
import repository.CourseRepository;
import repository.GradeRepository;
import repository.QuestionRepository;
import repository.TestRepository;
import repository.UserRepository;
import service.TestService;

@Service
@Transactional
public class TestServiceImpl implements TestService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private TestRepository testRepository;

	@Autowired
	private GradeRepository gradeRepository;

	@Override
	public TestDto createTest(TestDto testDto) {
		Test test = new Test();
		test.setCourse(courseRepository.getCourseById(testDto.getCourseId()));
		test.setQuestions(questionRepository.getQuestionListByIds(testDto.getQuestionIds()));
		test.setDescription(testDto.getDescription());
		test.setDuration(testDto.getDuration());
		test.setTestDate(testDto.getTestDate());

		return TestConverter.toDto(testRepository.createTest(test));
	}

	@Override
	public TestDto getTestById(Long testId) {
		return TestConverter.toDto(testRepository.getTestById(testId));
	}

	@Override
	public List<TestDto> getTestsByCourseId(Long courseId) {
		return TestConverter.toDtos(testRepository.getTestByCourse(courseId));
	}

	@Override
	public void submitExamAndCalculateGrade(Long testId, List<QuestionDto> questionDtos, Long loggedUserId) {
		Test test = testRepository.getTestById(testId);
		User user = userRepository.getUserById(loggedUserId);
		AtomicInteger counter = new AtomicInteger(0);
		questionDtos.forEach(questionDto -> {
			List<Long> alternativeAnswers = questionDto.getAlternatives().stream().map(AlternativeDto::getId)
					.collect(Collectors.toList());
			List<Alternative> alternatives = questionRepository.getAlternativeListByIds(alternativeAnswers);
			Question question = questionRepository.getQuestionById(questionDto.getId());
			if (checkAnswer(question, alternativeAnswers)) {
				counter.getAndIncrement();
			}
			alternatives.forEach(alternative -> {
				UserAlternative userAlternative = new UserAlternative();
				userAlternative.setUser(user);
				userAlternative.setQuestion(question);
				userAlternative.setTest(test);
				userAlternative.setAlternative(alternative);
				testRepository.saveUserAlternative(userAlternative);
			});

		});
		float grade = ((float) counter.get() / questionDtos.size()) * 100;

		Grade gradeEntity = new Grade();
		gradeEntity.setGrade((long) grade);
		gradeEntity.setTest(test);
		gradeEntity.setUser(user);
		gradeRepository.saveGrade(gradeEntity);

	}

	private boolean checkAnswer(Question question, List<Long> alternativeAnswers) {
		List<Long> correctAlternatives = question.getAlternatives().stream().filter(a -> a.isCorrect())
				.map(Alternative::getId).collect(Collectors.toList());
		for (Long id : alternativeAnswers) {
			if (!correctAlternatives.contains(id)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public List<TestDto> getPedagogueTests(Long idPedagogue) {
		List<Course> pedagogueCourses = courseRepository.getAllPedagogueCourses(idPedagogue);
		List<TestDto> testDtos = new ArrayList<>();
		for (Course course : pedagogueCourses) {
			testDtos.addAll(TestConverter.toDtos(testRepository.getTestByCourse(course.getId())));
		}
		return testDtos;
	}

}

package repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import entities.Alternative;
import entities.Question;
import entities.QuestionType;
import repository.QuestionRepository;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Question createQuestion(Question question) {
		entityManager.persist(question);
		return question;
	}

	@Override
	public QuestionType getQuestionTypeById(Long id) {
		QuestionType questionType = new QuestionType();
		try {
			TypedQuery<QuestionType> questionTypeQuery = entityManager.createQuery(
					"Select questionType from QuestionType questionType where questionType.id=:id", QuestionType.class);
			questionTypeQuery.setParameter("id", id);
			questionType = questionTypeQuery.getSingleResult();
			return questionType;
		} catch (Exception e) {
			return questionType;
		}
	}

	@Override
	public List<Question> getQuestionListByIds(List<Long> id) {
		List<Question> questions = new ArrayList<Question>();
		try {
			TypedQuery<Question> questionQuery = entityManager
					.createQuery("Select question from Question question where question.id in (:id)", Question.class);
			questionQuery.setParameter("id", id);
			questions = questionQuery.getResultList();
			return questions;
		} catch (Exception e) {
			return questions;
		}
	}

	@Override
	public Alternative getAlternativeById(Long id) {
		Alternative alternative = new Alternative();
		try {
			TypedQuery<Alternative> questionQuery = entityManager.createQuery(
					"Select alternative from Alternative alternative where alternative.id=:id", Alternative.class);
			questionQuery.setParameter("id", id);
			alternative = questionQuery.getSingleResult();
			return alternative;
		} catch (Exception e) {
			return alternative;
		}
	}

	@Override
	public List<QuestionType> getAllQuestionTypes() {
		return entityManager.createQuery("SELECT type FROM QuestionType type ", QuestionType.class).getResultList();
	}

	@Override
	public List<Question> getAllQuestionsOfPedagogue(Long id) {
		return entityManager.createQuery("Select question from Question question where question.pedagogue.id = :id ",
				Question.class).setParameter("id", id).getResultList();
	}

	@Override
	public Question getQuestionById(Long id) {
		Question question = new Question();
		try {
			TypedQuery<Question> questionQuery = entityManager
					.createQuery("Select question from Question question where question.id=:id", Question.class);
			questionQuery.setParameter("id", id);
			question = questionQuery.getSingleResult();
			return question;
		} catch (Exception e) {
			return question;
		}
	}

	@Override
	public List<Alternative> getAlternativeListByIds(List<Long> ids) {
		List<Alternative> alternatives = new ArrayList<>();
		try {
			TypedQuery<Alternative> questionQuery = entityManager.createQuery(
					"Select alternative from Alternative alternative where alternative.id in (:id)", Alternative.class);
			questionQuery.setParameter("id", ids);
			alternatives = questionQuery.getResultList();
			return alternatives;
		} catch (Exception e) {
			return alternatives;
		}
	}

}

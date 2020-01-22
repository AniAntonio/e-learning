package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "questions", schema = "public")
@Data
public class Question implements Serializable {
	private static final long serialVersionUID = 1268317671009653176L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "idtype")
	private QuestionType questionType;

	@ManyToMany(mappedBy = "questions")
	private List<Test> tests;

	@OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST)
	private List<Alternative> alternatives = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "userid")
	private User pedagogue;

	public Question addAlternativeInQuestion(Alternative alternative) {
		alternatives.add(alternative);
		alternative.setQuestion(this);
		return this;
	}

}

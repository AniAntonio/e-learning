package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tests", schema = "public")
@Data
public class Test implements Serializable {

	private static final long serialVersionUID = 1268317671009653176L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@ManyToOne
	@JoinColumn(name = "idcourse")
	private Course course;

	@Column(name = "testdate")
	private String testDate;

	@Column(name = "duration")
	private String duration;

	@Column(name = "description")
	private String description;

	@ManyToMany
	@JoinTable(name = "test_questions", joinColumns = { @JoinColumn(name = "idtest") }, inverseJoinColumns = {
			@JoinColumn(name = "idquestion") })
	private List<Question> questions;

}

package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user_alternatives", schema = "public")
@Data
public class UserAlternative {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	@ManyToOne
	@JoinColumn(name = "testid")
	private Test test;

	@ManyToOne
	@JoinColumn(name = "questionid")
	private Question question;

	@ManyToOne
	@JoinColumn(name = "alternativeid")
	private Alternative alternative;

}

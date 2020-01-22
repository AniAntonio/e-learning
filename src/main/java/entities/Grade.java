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
@Table(name = "grade", schema = "public")
@Data
public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(nullable = false, length = 22)
	private Long grade;

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	@ManyToOne
	@JoinColumn(name = "testid")
	private Test test;

}

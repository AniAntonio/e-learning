package entities;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "course", schema = "public")
@Data
public class Course implements Serializable {

	private static final long serialVersionUID = 1268317671009653176L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "book")
	private String book;

	@Column(name = "startdate")
	private String startDate;

	@Column(name = "finishdate")
	private String finishDate;

	@ManyToOne
	@JoinColumn(name = "idpedagogue")
	private User pedagogue;

	@ManyToOne
	@JoinColumn(name = "idfaculty")
	private Faculty faculty;

	@ManyToMany
	@JoinTable(name = "user_course", joinColumns = { @JoinColumn(name = "idcourse") }, inverseJoinColumns = {
			@JoinColumn(name = "iduser") })
	private List<User> users = new ArrayList<>();;

}

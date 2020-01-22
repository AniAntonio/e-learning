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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "users", schema = "public")
@Data
public class User implements Serializable {
	private static final long serialVersionUID = 1268317671009653176L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "username", length = 50)
	@Size(min = 3, max = 50, message = "Username must be from 3 to 50 letters!")
	private String username;

	@Column(name = "password", length = 100)
	@Size(min = 3, max = 50, message = "Password must be from 3 to 50 letters!")
	private String password;

	@Column(name = "firstname", length = 50)
	@Size(min = 3, max = 50, message = "Firstname must be from 3 to 50 letters!")
	private String firstname;

	@Column(name = "lastname", length = 50)
	@Size(min = 3, max = 50, message = "Lastname must be from 3 to 50 letters!")
	private String lastname;

	@Column(name = "email", length = 50)
	@NotNull
	@Email(message = "Please enter a valid email! example@gmail.com")
	private String email;

	@ManyToOne
	@JoinColumn(name = "idrole")
	private Role role;

	@ManyToOne
	@JoinColumn(name = "facultyid")
	private Faculty faculty;

	@Column(name = "regdate")
	private String regDate;

	@Column(name = "deleted")
	private boolean deleted;

	@ManyToMany(mappedBy = "users")
	private List<Course> courses = new ArrayList<>();;

	@OneToMany
	private List<Grade> grades = new ArrayList<>();;
}

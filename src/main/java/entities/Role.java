package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role implements Serializable {
	private static final long serialVersionUID = 1268317671009653174L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "rolename", length = 50)
	@Size(max = 50)
	private String rolename;

	@Column(name = "description", length = 256)
	@Size(max = 256)
	private String description;

}

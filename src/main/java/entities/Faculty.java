package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "faculty")
@Data
public class Faculty implements Serializable {

	private static final long serialVersionUID = 1268317671009653177L;

	@Id
	private Long id;

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "description", length = 1000)
	private String description;

}

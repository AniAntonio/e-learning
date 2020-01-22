package dto;

import lombok.Data;

@Data
public class UserGradeDto {

	private String firstName;

	private String lastName;

	private Long grade;

	public UserGradeDto() {

	}

	public UserGradeDto(String firstName, String lastName, Long grade) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.grade = grade;

	}
}

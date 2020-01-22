package dto;

import lombok.Data;

@Data
public class AlternativeDto {

	private Long id;

	private String name;

	private boolean isCorrect;

	public Boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
}

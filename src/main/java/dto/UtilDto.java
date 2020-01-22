package dto;

import java.util.List;

import lombok.Data;

@Data
public class UtilDto {

	private Long idCourse;

	private List<Long> studentIds;

}

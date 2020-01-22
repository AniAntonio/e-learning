package converter;

import java.util.ArrayList;
import java.util.List;

import dto.AlternativeDto;
import entities.Alternative;

public class AlternativeConverter {

	public static Alternative toEntity(AlternativeDto alternativeDto) {
		Alternative alternative = new Alternative();
		alternative.setId(alternativeDto.getId());
		alternative.setDescription(alternativeDto.getName());
		alternative.setCorrect(alternativeDto.getIsCorrect());
		return alternative;

	}

	public static AlternativeDto toDto(Alternative alternative) {
		AlternativeDto alternativeDto = new AlternativeDto();
		alternativeDto.setId(alternative.getId());
		alternativeDto.setName(alternative.getDescription());
		alternativeDto.setIsCorrect(alternative.isCorrect());
		return alternativeDto;

	}

	public static List<Alternative> toEntities(List<AlternativeDto> alternativeDtos) {
		List<Alternative> alternatives = new ArrayList<Alternative>();
		for (AlternativeDto alternativeDto : alternativeDtos) {
			alternatives.add(toEntity(alternativeDto));
		}
		return alternatives;
	}

	public static List<AlternativeDto> toDtos(List<Alternative> alternatives) {
		List<AlternativeDto> alternativeDtos = new ArrayList<AlternativeDto>();
		for (Alternative alternative : alternatives) {
			alternativeDtos.add(toDto(alternative));
		}
		return alternativeDtos;
	}
}

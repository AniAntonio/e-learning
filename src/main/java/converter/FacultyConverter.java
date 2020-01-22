package converter;

import dto.FacultyDto;
import entities.Faculty;

public class FacultyConverter {

	public static FacultyDto toFacultyDto(Faculty faculty) {
		FacultyDto facultyDto = new FacultyDto();
		facultyDto.setId(faculty.getId());
		facultyDto.setName(faculty.getName());
		facultyDto.setDescription(faculty.getDescription());
		return facultyDto;
	}

	public static Faculty toFacultyEntity(FacultyDto facultyDto) {
		Faculty faculty = new Faculty();
		faculty.setId(facultyDto.getId());
		faculty.setName(facultyDto.getName());
		faculty.setDescription(facultyDto.getDescription());
		return faculty;
	}
}

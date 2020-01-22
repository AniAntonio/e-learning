package repository;

import java.util.List;

import entities.Faculty;

public interface FacultryRepository {

	List<Faculty> getAllFaculties();

	Faculty getFacultyById(Long id);

}

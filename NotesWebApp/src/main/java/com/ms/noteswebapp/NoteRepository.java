package com.ms.noteswebapp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ms.noteswebapp.models.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer>{
	
	@Query(value = "SELECT n FROM Note n WHERE n.title LIKE '%' || :keyword || '%'")
	public List<Note> search(@Param("keyword") String keyword);

}

package com.ms.noteswebapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ms.noteswebapp.models.Note;

@Service
public class NoteService {
	
	@Autowired
	NoteRepository noteRepository;
	
	public List<Note> search(String keyword) {
		return noteRepository.search(keyword);
	}

}

package com.ms.noteswebapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.noteswebapp.models.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer>{

}

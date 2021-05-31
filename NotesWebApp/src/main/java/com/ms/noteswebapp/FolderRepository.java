package com.ms.noteswebapp;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.noteswebapp.models.Folder;

public interface FolderRepository extends JpaRepository<Folder, Integer>{

}

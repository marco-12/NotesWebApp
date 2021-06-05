package com.ms.noteswebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ms.noteswebapp.FolderRepository;
import com.ms.noteswebapp.NoteRepository;
import com.ms.noteswebapp.NoteService;
import com.ms.noteswebapp.models.Folder;
import com.ms.noteswebapp.models.Note;

@Controller
public class NoteController {
	
	@Autowired
	FolderRepository folderRepository;
	
	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
	NoteService noteService;
	
	@GetMapping("/notes/create")
	public String creareNoteform(Model model) {
		model.addAttribute("folders", folderRepository.findAll());
		return "createNote.html";
	}
	
	@PostMapping("/notes/create")
	public String saveNewNote(@RequestParam("title") String title, @RequestParam("body") String body, @RequestParam("folder") Integer folderId) {
		// aggiungo il .get() perchè mi ritporna un OPTIONAL
		Folder folder = folderRepository.findById(folderId).get();
		Note newNote = new Note(title, body, folder);
		noteRepository.save(newNote);
		return "redirect:/home";
	}
	
	@PostMapping("/edit/{noteId}")
	public ModelAndView update(@PathVariable Integer noteId){
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("note", noteRepository.findById(noteId));
		mav.setViewName("editNote");
		return mav;
	}
	
	@PostMapping("/edit")
	public ModelAndView doUpdate(@Validated Note n, BindingResult bindingResult){
		noteRepository.save(n);
		return new ModelAndView("redirect:/home");
		
	}

	
	@PostMapping("/notes/delete")
	public String deleteNote(@RequestParam(name = "noteId") Integer noteId, Model model) {
		noteRepository.deleteById(noteId);
		model.addAttribute("notes", noteRepository.findAll()); 
		return "redirect:/home";
	}
	
	@GetMapping("/search")
	public ModelAndView search(@RequestParam String keyword) {
		List<Note> searchedNotes = noteService.search(keyword);
		ModelAndView mav = new ModelAndView("searchNotes");
		mav.addObject("searchedNotes", searchedNotes);
		return mav;
	}

}

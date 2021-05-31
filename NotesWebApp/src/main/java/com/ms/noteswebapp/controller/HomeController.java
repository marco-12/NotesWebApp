package com.ms.noteswebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ms.noteswebapp.FolderRepository;
import com.ms.noteswebapp.NoteRepository;
import com.ms.noteswebapp.models.Folder;

@Controller
public class HomeController {
	
	@Autowired
	FolderRepository folderRepository;
	
	@Autowired
	NoteRepository noteRepository;
	
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("folders", folderRepository.findAll());
		model.addAttribute("notes", noteRepository.findAll());
		return "index";
	}
	
	@PostMapping("/home")
	public String createFolder(@RequestParam(name = "folderName") String folderName, Model model) {
		System.out.println("Folder = " + folderName);
		Folder newFolder = new Folder(folderName);
		folderRepository.save(newFolder);
		model.addAttribute("folders", folderRepository.findAll()); 
		return "redirect:/home";
	}
	
	@PostMapping("/folders/delete")
	public String deleteFolder(@RequestParam(name = "folderId") Integer folderId, Model model) {
		folderRepository.deleteById(folderId);
		model.addAttribute("folders", folderRepository.findAll()); 
		return "redirect:/home";
	}

}

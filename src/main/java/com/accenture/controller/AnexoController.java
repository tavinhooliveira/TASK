package com.accenture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.accenture.model.Anexo;
import com.accenture.model.Task;
import com.accenture.repository.Anexos;

@Controller
@RequestMapping("/tasks/anexo")
public class AnexoController {
	
	@Autowired
	private Anexos anexos;
	
	// Salvar
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Anexo anexo, Task task, Errors errors, RedirectAttributes attributes) {
		attributes.addAttribute(task);
		if (errors.hasErrors()) {
			return "redirect:/tasks/detalhes/" + anexo.getTasks().getCodigo();
		}
		try {
			anexos.save(anexo);
			attributes.addFlashAttribute("mensagem", "Anexo adicionado com sucesso!");
			return "redirect:/tasks/detalhes/" + anexo.getTasks().getCodigo();
		} catch (IllegalArgumentException e) {
			errors.rejectValue(null, e.getMessage());
			return "redirect:/tasks/detalhes/" + anexo.getTasks().getCodigo();
		}
	}

}

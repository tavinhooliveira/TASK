package com.accenture.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.accenture.model.Anexo;
import com.accenture.model.Hora;
import com.accenture.model.Lider;
import com.accenture.model.Modulo;
import com.accenture.model.Responsavel;
import com.accenture.model.StatusTask;
import com.accenture.model.Task;
import com.accenture.model.TipoTask;
import com.accenture.repository.Anexos;
import com.accenture.repository.Horas;
import com.accenture.repository.Lideres;
import com.accenture.repository.Modulos;
import com.accenture.repository.Responsaveis;
import com.accenture.repository.filter.TaskFilter;
import com.accenture.service.CadastroTaskService;

@Controller
@RequestMapping("/tasks")
public class TaskController {

	private static final String CADASTRO_VIEW = "/pages/CadastroTask";
	private static final String LIST_TASK_VIEW = "/pages/ListarTask";
	private static final String DETALHE_TASK_VIEW = "/pages/DetalheTask";

	@Autowired
	private CadastroTaskService cadastrotaskservice;

	@Autowired
	private Responsaveis responsaveis;

	@Autowired
	private Lideres lideres;

	@Autowired
	private Modulos modulos;

	@Autowired
	private Horas horas;
	
	@Autowired
	private Anexos anexos;

	
	// Cadastro Novo
	@RequestMapping("/novo")
	public ModelAndView novo(@ModelAttribute("filtro") TaskFilter filtro) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		List<Task> allTask = cadastrotaskservice.filtrar(filtro);
		mv.addObject("tasks", allTask);
		mv.addObject(new Task());
		return mv;
	}
	
	
	// Combo Responsaveis
	@ModelAttribute("tdresponsaveis")
	public List<Responsavel> tdresponsaveis() {
		return responsaveis.findAll();
	}
	
	// Combo Lideres
	@ModelAttribute("tdlideres")
	public List<Lider> tdlideres() {
		return lideres.findAll();
	}
	
	// Combo Modulos
	@ModelAttribute("tdModulos")
	public List<Modulo> tdModulos() {
		return modulos.findAll();
	}
	
		
	// Salvar
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Task task, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;

		}
		try {
			cadastrotaskservice.salvar(task);
			attributes.addFlashAttribute("mensagem", "Task salva com sucesso!");
			return "redirect:/tasks/detalhes/" + task.getCodigo().toString();
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}
	
	// Listar Task
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") TaskFilter filtro) {
		List<Task> allTask = cadastrotaskservice.filtrar(filtro);
		ModelAndView mv = new ModelAndView(LIST_TASK_VIEW);
		mv.addObject("tasks", allTask);
		return mv;
	}

	// PesquisaComboTipos
	@ModelAttribute("todasTasks")
	public List<TipoTask> todasTasks() {
		return Arrays.asList(TipoTask.values());
	}

	// PesquisaComboStauts
	@ModelAttribute("todasTasksStatus")
	public List<StatusTask> todasTasksStatus() {
		return Arrays.asList(StatusTask.values());
	}

	// Editar
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Task task) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(task);
		List<Responsavel> allResponsaveis = responsaveis.findAll();
		mv.addObject("tdresponsaveis", allResponsaveis);
		List<Lider> allLideres = lideres.findAll();
		mv.addObject("tdlideres", allLideres);
		List<Modulo> allModulos = modulos.findAll();
		mv.addObject("tdModulos", allModulos);
		return mv;
	}

	// Detalhes Task
	@RequestMapping("detalhes/{codigo}")
	public ModelAndView exibir(@PathVariable("codigo") Task task) {
		ModelAndView mv = new ModelAndView(DETALHE_TASK_VIEW);
		mv.addObject(task);
		List<Hora> allHoras = horas.findAll();
		mv.addObject("horas", allHoras);
		List<Responsavel> allResponsaveis = responsaveis.findAll();
		mv.addObject("tdresponsaveis", allResponsaveis);
		List<Anexo> allAnexos = anexos.findAll();
		mv.addObject("anexos", allAnexos);

		return mv;
	}

	// Excluir
	@RequestMapping("delete/{codigo}")
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastrotaskservice.excluir(codigo);
		attributes.addFlashAttribute("mensagem", "Task excluída com sucesso!");
		return "redirect:/tasks/";
	}

}

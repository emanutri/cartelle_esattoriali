package it.prova.gestionecartelle.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionecartelle.model.Contribuente;
import it.prova.gestionecartelle.service.ContribuenteService;

@Controller
@RequestMapping(value = "/contribuente")
public class ContribuenteController {

	@Autowired
	private ContribuenteService contribuenteService;

	@GetMapping
	public ModelAndView listAllContribuenti() {
		ModelAndView mv = new ModelAndView();
		List<Contribuente> contribuenti = contribuenteService.listAllElements();
		mv.addObject("contribuenti_list_attribute", contribuenti);
		mv.setViewName("contribuente/list");
		return mv;
	}
	
	@GetMapping("/insert")
	public String createContribuente(Model model) {
		model.addAttribute("insert_contribuenti_attr", new Contribuente());
		return "contribuente/insert";
	}
	
	@PostMapping("/save")
	public String saveContribuente(@Valid @ModelAttribute("insert_contribuenti_attr") Contribuente contribuente, BindingResult result,
			RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			return "contribuente/insert";
		}
		contribuenteService.inserisciNuovo(contribuente);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/contribuente";
	}
	
	@GetMapping("/search")
	public String searchContribuente() {
		return "contribuente/search";
	}
	
	//devo evidenziare i contribuenti con cartelle in contenzioso (jsp)
	@PostMapping("/list")
	public String listContribuenti(Contribuente contribuenteExample, ModelMap model) {
		List<Contribuente> contribuenti = contribuenteService.findByExample(contribuenteExample);
		model.addAttribute("contribuenti_list_attribute", contribuenti);
		return "contribuente/list";
	}
	
	//qui va il calcolo delle cartelle
	@GetMapping("/show/{idContribuente}")
	public String showContribuente(@PathVariable(required = true) Long idContribuente, Model model) {
		model.addAttribute("show_contribuente_attr", contribuenteService.caricaSingoloElemento(idContribuente));
		return "contribuente/show";
	}
	
	@GetMapping("/edit/{idContribuente}")
	public String editContribuente(@PathVariable(required = true) Long idContribuente, Model model) {
		model.addAttribute("contribuente_attribute", contribuenteService.caricaSingoloElemento(idContribuente));
		return "contribuente/edit";
	}

	@PostMapping("/edit/update")
	public String updateContribuente(@Valid @ModelAttribute("contribuente_attribute") Contribuente contribuente,
			@Valid @ModelAttribute("idContribuente") Long idContribuente, BindingResult result,
			RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "contribuente/edit";
		}

		contribuente.setId(idContribuente);
		contribuenteService.aggiorna(contribuente);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/contribuente";
	}
	
	@GetMapping("/delete/{idContribuente}")
	public String controllaDeleteContribuente(@PathVariable(required = true) Long idContribuente, Model model) {
		model.addAttribute("contribuente_delete", contribuenteService.caricaSingoloElemento(idContribuente));
		return "/contribuente/delete";
	}
	
	@PostMapping("/delete/execute")
	public String controllaDeleteRegista(@Valid @ModelAttribute("idContribuente") Long idContribuente, BindingResult result,
			RedirectAttributes redirectAttrs) {

		try {
			contribuenteService.rimuovi(contribuenteService.caricaSingoloElemento(idContribuente));
			redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
			
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("errorMessage", "Non puoi eliminare un contribuente se ha cartelle associate.");
		}
		return "redirect:/contribuente";
	}

}

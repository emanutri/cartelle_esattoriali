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

import it.prova.gestionecartelle.model.CartellaEsattoriale;
import it.prova.gestionecartelle.model.Stato;
import it.prova.gestionecartelle.service.CartellaEsattorialeService;
import it.prova.gestionecartelle.service.ContribuenteService;

@Controller
@RequestMapping(value = "/cartellaesattoriale")
public class CartellaEsattorialeController {

	@Autowired
	private CartellaEsattorialeService cartellaService;

	@Autowired
	private ContribuenteService contribuenteService;

	@GetMapping
	public ModelAndView listAllCartelle() {
		ModelAndView mv = new ModelAndView();
		List<CartellaEsattoriale> cartelle = cartellaService.listAllElements();
		mv.addObject("cartelle_list_attribute", cartelle);
		mv.setViewName("cartellaesattoriale/list");
		return mv;
	}

	@GetMapping("/insert")
	public String createCartella(Model model) {
		CartellaEsattoriale cartella = new CartellaEsattoriale();
		cartella.setStato(Stato.CREATA);
		model.addAttribute("insert_cartella_attr", cartella);
		return "cartellaesattoriale/insert";
	}

	@PostMapping("/save")
	public String saveCartella(@Valid @ModelAttribute("insert_cartella_attr") CartellaEsattoriale cartellaEsattoriale,
			BindingResult result, RedirectAttributes redirectAttrs) {

		if (cartellaEsattoriale.getContribuente() != null && cartellaEsattoriale.getContribuente().getId() != null) {
			cartellaEsattoriale.setContribuente(
					contribuenteService.caricaSingoloElemento(cartellaEsattoriale.getContribuente().getId()));
		} else {
			result.reject("contribuente");
		}

		if (result.hasErrors()) {
			return "cartellaesattoriale/insert";
		}
		cartellaService.inserisciNuovo(cartellaEsattoriale);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/cartellaesattoriale";
	}

	@GetMapping("/search")
	public String searchCartella(Model model) {
		model.addAttribute("contribuenti_list_attribute", contribuenteService.listAllElements());
		model.addAttribute("stato_cartella", Stato.values());
		return "cartellaesattoriale/search";
	}

	@PostMapping("/list")
	public String listCartelle(CartellaEsattoriale cartellaExample, ModelMap model) {
		List<CartellaEsattoriale> cartelle = cartellaService.findByExample(cartellaExample);
		model.addAttribute("cartelle_list_attribute", cartelle);
		return "cartellaesattoriale/list";
	}

	@GetMapping("/show/{idCartella}")
	public String showCartella(@PathVariable(required = true) Long idCartella, Model model) {
		model.addAttribute("show_cartella_attr", cartellaService.caricaSingoloElementoEager(idCartella));
		return "cartellaesattoriale/show";
	}

	@GetMapping("/edit/{idCartella}")
	public String editCartella(@PathVariable(required = true) Long idCartella, Model model) {
		model.addAttribute("contribuente_list_attribute", contribuenteService.listAllElements());
		model.addAttribute("cartella_attribute", cartellaService.caricaSingoloElementoEager(idCartella));
		model.addAttribute("stato_cartella", Stato.values());
		return "cartellaesattoriale/edit";
	}

	@PostMapping("/edit/update")
	public String updateCartella(@Valid @ModelAttribute("cartella_attribute") CartellaEsattoriale cartellaEsattoriale,
			@Valid @ModelAttribute("idCartella") Long idCartella, BindingResult result,
			RedirectAttributes redirectAttrs) {

		if (cartellaEsattoriale.getContribuente() != null && cartellaEsattoriale.getContribuente().getId() != null) {
			cartellaEsattoriale.setContribuente(
					contribuenteService.caricaSingoloElemento(cartellaEsattoriale.getContribuente().getId()));
		} else {
			result.rejectValue("contribuente", "contribuente.notnull");
		}

		if (result.hasErrors()) {
			return "cartellaesattoriale/edit";
		}

		cartellaEsattoriale.setId(idCartella);
		cartellaService.aggiorna(cartellaEsattoriale);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/cartellaesattoriale";
	}

	@GetMapping("/delete/{idCartella}")
	public String controllaDeleteCartella(@PathVariable(required = true) Long idCartella, Model model) {
		model.addAttribute("cartella_delete", cartellaService.caricaSingoloElementoEager(idCartella));
		return "/cartellaesattoriale/delete";
	}

	@PostMapping("/delete/execute")
	public String controllaDeleteCartella(@Valid @ModelAttribute("idCartella") Long idCartella, BindingResult result,
			RedirectAttributes redirectAttrs) {
		cartellaService.invalida(cartellaService.caricaSingoloElemento(idCartella));
		// cartellaService.aggiorna();

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/cartellaesattoriale";
	}
}

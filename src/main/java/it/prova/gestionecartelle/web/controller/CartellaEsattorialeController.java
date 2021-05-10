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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionecartelle.model.CartellaEsattoriale;
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
		model.addAttribute("insert_cartella_attr", new CartellaEsattoriale());
		return "cartellaesattoriale/insert";
	}

	@PostMapping("/save")
	public String saveCartella(@Valid @ModelAttribute("insert_cartella_attr") CartellaEsattoriale cartellaEsattoriale,
			BindingResult result, RedirectAttributes redirectAttrs) {

		// se il regista è valorizzato dobbiamo provare a caricarlo perché
		// ci aiuta in pagina
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
		return "cartellaesattoriale/search";
	}

	@PostMapping("/list")
	public String listCartelle(CartellaEsattoriale cartellaExample, ModelMap model) {
		List<CartellaEsattoriale> cartelle = cartellaService.findByExample(cartellaExample);
		model.addAttribute("cartelle_list_attribute", cartelle);
		return "cartellaesattoriale/list";
	}
}

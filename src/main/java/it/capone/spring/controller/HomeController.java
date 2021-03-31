package it.capone.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

//import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.ApiOperation;
import it.capone.spring.model.Player;
import it.capone.spring.service.IPlayerService;

@Controller
public class HomeController {

    /**
     * Si noti che Spring MVC si occupa di mappare le variabili delle view.jsp alle
     * variabili di classe del modello, per cui si ha lo stesso nome di variabile in
     * entrambi i posti.
     * 
     * Avviare Docker e l'immagine che contiene il database MySQL "players".
     * Digitare 'http://localhost:8080' per entrare da browser nel database players
     * oppure da MySqlWorkbench
     * 
     * 
     * Deployare l'app sotto un server Tomcat, far partire questi e dal browser
     * digitare => http://localhost:8081/spring/
     */

    @Autowired
    private IPlayerService playerService;

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping(value = "/")
    public String home(Locale locale, Model model) {
	logger.info("Benvenuto home! Il client locale e' {}.", locale);

	Date date = new Date();
	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

	String formattedDate = dateFormat.format(date);

	model.addAttribute("serverTime", formattedDate);

	return "home";
    }

    @GetMapping(value = "/test")
    public String test(Model model) {
	logger.info("Benvenuto test!");

	String message = "Pagina di Test con SpringMVC!";

	model.addAttribute("message", message);

	return "test";
    }

    /*
     * ************** START INTRODUZIONE WEB-FORM *********************
     */
    @GetMapping(value = "/simpleForm")
    public ModelAndView showForm() {
	
	// Si viene redirezionati alla view formPlayer.jsp prima instanziando un oggetto "player" e passandolo alla jsp
	return new ModelAndView("formPlayer", "player", new Player());
    }

    /**
     * - Un @ModelAttribute su un argomento del metodo indica che l'argomento verrà recuperato dal model. 
     * - Il suo valore corrisponde al valore della proprietà  "modelAttribute" inviata dal form. 
     * - Il @ModelAttribute "player" viene instanziato nella api /simpleForm ed inviato al form formPlayer.jsp
     *   in cui i valori inseriti vengono usati come dei set per il player e inviato in Post
     * - Se non è presente nel model, l'argomento verrà prima istanziato e poi aggiunto al model. 
     * - La property "params" fa match con il valore della property "name" definita nel tag html <input type="submit">   
     */
    @PostMapping(value = "/addPlayer", params = "submit")
    public String submitPlayer(@Valid @ModelAttribute("player") Player player, 
	    @RequestParam(name="submit", required=false) String submit,
	    BindingResult result, Model model) {
	if (result.hasErrors()) {
	    return "error"; // In caso di errori(non eccezioni) si ha la reidirect verso la error.jsp
	}
	
	model.addAttribute("name", player.getName());
	model.addAttribute("lastName", player.getLastName());
	model.addAttribute("team", player.getTeam());
	model.addAttribute("position", player.getPosition());
	
	return "playersView";  // reidirect verso la playersView.jsp
    }
    
    /**
     *  La property "params" fa match con il valore della property "name" definita nel tag html <input type="submit">  
     */
    @PostMapping(value = "/addPlayer", params = "cancel")
    public String cancel(@Valid @ModelAttribute("player") Player player, BindingResult result, Model model) {
	if (result.hasErrors()) {
	    return "error"; // In caso di errori(non eccezioni) si ha la reidirect verso la error.jsp
	}
	
	model.addAttribute("message", "Hai cliccato su CANCEL, reinserisci i valori nel form");
	
	return "formPlayer";  // reidirect verso la formPlayer.jsp
    }
    
    /**
     *  La property "params" fa match con il valore della property "name" definita nel tag html <input type="submit">  
     */
    @PostMapping(value = "/addPlayer", params = "edit")
    public String edit(@Valid @ModelAttribute("player") Player player, BindingResult result, Model model) {
	if (result.hasErrors()) {
	    return "error"; // In caso di errori(non eccezioni) si ha la reidirect verso la error.jsp
	}
	
	model.addAttribute("message", "Hai cliccato su EDIT, reinserisci i valori nel form");
	
	return "formPlayer";  // reidirect verso la formPlayer.jsp
    }

    /*
     * ************ END INTRODUZIONE WEB-FORM ************************
     */

    @GetMapping("/players")
    @ApiOperation(value = "Lista dei players")
    public String getAllPlayers(Model m) {

	List<Player> players = playerService.players();
	m.addAttribute("players", players);
	return "players"; // Redireziona verso la jsp che contiene la lista dei players
    }

    /*
     * La parola "command" è un request-attribute riservato usato per visualizzare
     * il data-object(in questo caso -> new Player()) nel Form
     */
    @GetMapping("/addform")
    public String showForm(Model model) {
	model.addAttribute("command", new Player());
	return "addform";
    }

    @PostMapping("/addplayer")
    @ApiOperation(value = "Aggiunta di un player")
    public String addPlayer(@ModelAttribute("player") Player player) {
	playerService.addPlayer(player);
	return "redirect:/players"; // redireziona verso la Request "getAllPlayers"
    }

    /*
     * La parola "command" è un request-attribute riservato usato per visualizzare
     * il data-object(in questo caso -> new Player()) nel Form
     */
    @RequestMapping(value = "/editplayer/{id}")
    @ApiOperation(value = "Modifica player")
    public String editPlayer(@PathVariable Long id, Model model) {

	Player p = playerService.getPlayer(id);
	model.addAttribute("command", p);
	return "editform"; // Redireziona verso la jsp che contiene la pagina che contiene il players
    }

    @PostMapping(value = "/editsave")
    @ApiOperation(value = "Salva modifiche al player")
    public String editSavePlayer(@ModelAttribute("player") Player player) {

	playerService.updatePlayer(player.getId(), player);
	return "redirect:/players"; // redireziona verso la Request "getAllPlayers"
    }

    @GetMapping(value = "/deleteplayer/{id}")
    @ApiOperation(value = "Elimina player")
    public String deletePlayer(@PathVariable Long id) {
	playerService.deletePlayer(id);
	return "redirect:/players"; // Redireziona verso la jsp che contiene la lista dei players
    }

    @RequestMapping("/search")
    @ApiOperation(value = "Cerca i Player in base ad una stringa passata come parametro")
    public String search(@RequestParam String keyword, Model model) {

	List<Player> players = playerService.searchPlayer(keyword);

	model.addAttribute("command", players);
	return "searchpage"; // Redireziona verso la jsp che contiene il risultato della ricerca tramite
			     // keyword
    }

}

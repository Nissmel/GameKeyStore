package game.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import game.GameKeyShopingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import game.GameKey;
import game.GameKey.Type;
import game.Order;
import game.User;
import game.data.KeysRepository;
import game.data.TacoRepository;
import game.data.UserRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class GameListController {
  
  private final KeysRepository gamesRepo;
  
  private TacoRepository tacoRepo;

  private UserRepository userRepo;

  @Autowired
  public GameListController(
        KeysRepository ingredientRepo,
        TacoRepository tacoRepo,
        UserRepository userRepo) {
    this.gamesRepo = ingredientRepo;
    this.tacoRepo = tacoRepo;
    this.userRepo = userRepo;
  }

  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }
  
  @ModelAttribute(name = "design")
  public GameKeyShopingForm design() {
    return new GameKeyShopingForm();
  }
  
  @GetMapping
  public String showDesignForm(Model model, Principal principal) {
    List<GameKey> games = new ArrayList<>();
    gamesRepo.findAll().forEach(i -> games.add(i));
    
    Type[] types = GameKey.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), 
          filterByType(games, type));
    }
    
    String username = principal.getName();
    User user = userRepo.findByUsername(username);
    model.addAttribute("user", user);

    return "design";
  }

  @PostMapping
  public String processDesign(
          @Valid GameKeyShopingForm GameKeyShopingForm, Errors errors,
          @ModelAttribute Order order) {
    
    if (errors.hasErrors()) {
      return "design";
    }

    GameKeyShopingForm saved = tacoRepo.save(GameKeyShopingForm);
    order.addDesign(saved);

    return "redirect:/orders/current";
  }

  private List<GameKey> filterByType(
          List<GameKey> ingredients, Type type) {
    return ingredients
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
  }
  
}

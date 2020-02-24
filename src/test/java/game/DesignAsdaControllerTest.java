package game;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import game.GameKey.Type;
import game.data.KeysRepository;
import game.data.OrderRepository;
import game.data.TacoRepository;
import game.data.UserRepository;
import game.web.GameListController;

@RunWith(SpringRunner.class)
@WebMvcTest(GameListController.class)
public class DesignAsdaControllerTest {

  @Autowired
  private MockMvc mockMvc;
  
  private List<GameKey> ingredients;

  private GameKeyShopingForm design;
  
  @MockBean
  private KeysRepository ingredientRepository;

  @MockBean
  private TacoRepository designRepository;

  @MockBean
  private OrderRepository orderRepository;

  @MockBean
  private UserRepository userRepository;

  @Before
  public void setup() {
    ingredients = Arrays.asList(
      new GameKey("FLTO", "Flour Tortilla", Type.SHOOTER),
      new GameKey("COTO", "Corn Tortilla", Type.SHOOTER),
      new GameKey("GRBF", "Ground Beef", Type.RPG),
      new GameKey("CARN", "Carnitas", Type.RPG),
      new GameKey("TMTO", "Diced Tomatoes", Type.RACE),
      new GameKey("LETC", "Lettuce", Type.RACE),
      new GameKey("CHED", "Cheddar", Type.STRATEGY),
      new GameKey("JACK", "Monterrey Jack", Type.STRATEGY),
      new GameKey("SLSA", "Salsa", Type.MULTIPLAYER),
      new GameKey("SRCR", "Sour Cream", Type.MULTIPLAYER)
    );
    
    when(ingredientRepository.findAll())
        .thenReturn(ingredients);
        
    when(ingredientRepository.findById("FLTO")).thenReturn(Optional.of(new GameKey("FLTO", "Flour Tortilla", Type.SHOOTER)));
    when(ingredientRepository.findById("GRBF")).thenReturn(Optional.of(new GameKey("GRBF", "Ground Beef", Type.RPG)));
    when(ingredientRepository.findById("CHED")).thenReturn(Optional.of(new GameKey("CHED", "Cheddar", Type.STRATEGY)));
    
    design = new GameKeyShopingForm();
    design.setName("Test GameKeyShopingForm");

    design.setIngredients(Arrays.asList(
        new GameKey("FLTO", "Flour Tortilla", Type.SHOOTER),
        new GameKey("GRBF", "Ground Beef", Type.RPG),
        new GameKey("CHED", "Cheddar", Type.STRATEGY)
    	));

    when(userRepository.findByUsername("testuser"))
    		.thenReturn(new User("testuser", "testpass", "Test User"));
  }

  @Test
  @WithMockUser(username="testuser", password="testpass")
  public void testShowDesignForm() throws Exception {
	mockMvc.perform(get("/design"))
        .andExpect(status().isOk())
        .andExpect(view().name("design"))
        .andExpect(model().attribute("wrap", ingredients.subList(0, 2)))
        .andExpect(model().attribute("protein", ingredients.subList(2, 4)))
        .andExpect(model().attribute("veggies", ingredients.subList(4, 6)))
        .andExpect(model().attribute("cheese", ingredients.subList(6, 8)))
        .andExpect(model().attribute("sauce", ingredients.subList(8, 10)));
  }

  @Test
  @WithMockUser(username="testuser", password="testpass", authorities="ROLE_USER")
  public void processDesign() throws Exception {
    when(designRepository.save(design))
        .thenReturn(design);
    
    when(userRepository.findByUsername("testuser"))
	.thenReturn(new User("testuser", "testpass", "Test User"));

    mockMvc.perform(post("/design").with(csrf())
        .content("name=Test+GameKeyShopingForm&ingredients=FLTO,GRBF,CHED")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
        .andExpect(status().is3xxRedirection())
        .andExpect(header().stringValues("Location", "/orders/current"));
  }

}

package game;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import game.data.TacoRepository;
import game.data.UserRepository;
import game.web.OrderProps;
import game.data.KeysRepository;
import game.data.OrderRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(secure=false)
public class HomeControllerTest {

  @Autowired
  private MockMvc mockMvc;
  
  // Note: Most of these mocks are here to avoid autowiring issues. They aren't
  //       actually used in the course of the home page test, so their behavior
  //       isn't important. They just need to exist so autowiring can take place.
  
  @MockBean
  private KeysRepository ingredientRepository;

  @MockBean
  private TacoRepository designRepository;

  @MockBean
  private OrderRepository orderRepository;

  @MockBean
  private UserRepository userRepository;
  
  @MockBean
  private PasswordEncoder passwordEncoder;
  
  @MockBean
  private DiscountCodeProps discountProps;

  @MockBean
  private OrderProps orderProps;

  @Test
  public void testHomePage() throws Exception {
    mockMvc.perform(get("/"))
      .andExpect(status().isOk())
      .andExpect(view().name("home"))
      .andExpect(content().string(
          containsString("Welcome to...")));  
  }

}

package game.data;

import game.GameKeyShoppingForm;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository 
         extends CrudRepository<GameKeyShoppingForm, Long> {

}

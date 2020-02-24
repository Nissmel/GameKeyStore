package game.data;

import game.GameKeyShopingForm;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository 
         extends CrudRepository<GameKeyShopingForm, Long> {

}

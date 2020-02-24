package game.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import game.GameKey;
import game.data.KeysRepository;

@Component
public class GameByIdConverter implements Converter<String, GameKey> {

  private KeysRepository ingredientRepo;

  @Autowired
  public GameByIdConverter(KeysRepository keysRepo) {
    this.ingredientRepo = keysRepo;
  }
  
  @Override
  public GameKey convert(String id) {
    Optional<GameKey> optionalIngredient = ingredientRepo.findById(id);
	return optionalIngredient.isPresent() ?
		   optionalIngredient.get() : null;
  }

}

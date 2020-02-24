package game.data;

import org.springframework.data.repository.CrudRepository;

import game.GameKey;

public interface KeysRepository
         extends CrudRepository<GameKey, String> {

}

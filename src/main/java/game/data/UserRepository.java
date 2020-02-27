package game.data;
import org.springframework.data.repository.CrudRepository;
import game.User;

public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
  
}

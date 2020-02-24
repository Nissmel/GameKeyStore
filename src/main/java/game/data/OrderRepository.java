package game.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import game.Order;
import game.User;

public interface OrderRepository 
         extends CrudRepository<Order, Long> {

  List<Order> findByUserOrderByPlacedAtDesc(
          User user, Pageable pageable);
}

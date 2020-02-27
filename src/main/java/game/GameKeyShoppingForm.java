package game;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class GameKeyShoppingForm {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  
  private Date createdAt;

  @ManyToMany(targetEntity= GameKey.class)
  @Size(min=1, message="You must choose at least 1 ingredient")
  private List<GameKey> ingredients;

  @PrePersist
  void createdAt() {
    this.createdAt = new Date();
  }
}

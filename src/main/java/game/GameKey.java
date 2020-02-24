package game;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
public class GameKey {
  
  @Id
  private final String id;
  private final String name;
  private final Type type;
  
  public static enum Type {
    SHOOTER, RPG, RACE, STRATEGY, MULTIPLAYER
  }

}

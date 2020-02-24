package game;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import game.GameKey.Type;
import game.data.KeysRepository;
import game.data.UserRepository;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

  @Bean
  public CommandLineRunner dataLoader(KeysRepository repo,
                                      UserRepository userRepo, PasswordEncoder encoder) { // user repo for ease of testing with a built-in user
    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {
        repo.save(new GameKey("BFV", "Battlefield V", Type.SHOOTER));
        repo.save(new GameKey("FC4", "Far Cry 4", Type.SHOOTER));
        repo.save(new GameKey("FEAR3", "F.E.A.R. 3", Type.SHOOTER));
        repo.save(new GameKey("CSGO", "Counter-Strike: Global Offensive", Type.SHOOTER));
        repo.save(new GameKey("W3WH", "The Witcher 3: Wild Hunt", Type.RPG));
        repo.save(new GameKey("G3", "Gothic 3", Type.RPG));
        repo.save(new GameKey("TESV", "The Elder Scrolls V: Skyrim", Type.RPG));
        repo.save(new GameKey("DTS", "Don’t Starve", Type.RPG));
        repo.save(new GameKey("G2", "Grid 2", Type.RACE));
        repo.save(new GameKey("NFSH", "Need for Speed Heat", Type.RACE));
        repo.save(new GameKey("BPTUB", "Burnout Paradise: The Ultimate Box", Type.RACE));
        repo.save(new GameKey("D3", "Dirt 3", Type.RACE));
        repo.save(new GameKey("CS", "Cities: Skylines", Type.STRATEGY));
        repo.save(new GameKey("A18", "Anno 1800", Type.STRATEGY));
        repo.save(new GameKey("CIV", "Civilization VI", Type.STRATEGY));
        repo.save(new GameKey("TS", "The Settlers", Type.STRATEGY));
        repo.save(new GameKey("MAG", "Magica", Type.MULTIPLAYER));
        repo.save(new GameKey("DS3", "DARK SOULS III", Type.MULTIPLAYER));
        repo.save(new GameKey("DS2", "DARK SOULS II", Type.MULTIPLAYER));
        repo.save(new GameKey("DS", "DARK SOULS ", Type.MULTIPLAYER));
        
        
        userRepo.save(new User("habuma", encoder.encode("password"), "Craig Walls"));
      }
    };
  }
  
}

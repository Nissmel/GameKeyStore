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
        repo.save(new GameKey("35C5271A-2061-4582-80CD-1038831113EE", "Battlefield V", Type.SHOOTER));
        repo.save(new GameKey("1D95D254-20BA-486B-A017-82DC4A797D2A", "Far Cry 4", Type.SHOOTER));
        repo.save(new GameKey("9E055621-FFFA-426D-8C08-DE572D4E9D03", "F.E.A.R. 3", Type.SHOOTER));
        repo.save(new GameKey("079D1300-6153-45BD-8F69-FE010D74FE35", "Counter-Strike: Global Offensive", Type.SHOOTER));
        repo.save(new GameKey("850346DE-4471-4FCD-A080-883AB576CB57", "The Witcher 3: Wild Hunt", Type.RPG));
        repo.save(new GameKey("11A9E443-6DDB-450C-96F7-00DAD8881879", "Gothic 3", Type.RPG));
        repo.save(new GameKey("7B064AA6-FA42-4865-99D7-0BD910654872", "The Elder Scrolls V: Skyrim", Type.RPG));
        repo.save(new GameKey("644538E3-C6CB-4678-9575-37F78B2954B7", "Don’t Starve", Type.RPG));
        repo.save(new GameKey("82B1EEAC-EDD2-4D4A-AD32-8F0BF3FE04B0", "Grid 2", Type.RACE));
        repo.save(new GameKey("53C527D6-555E-40A3-9569-CEEEFB6633D8", "Need for Speed Heat", Type.RACE));
        repo.save(new GameKey("99C08EC1-F8F1-4DEB-AF8F-E469B1E84F29", "Burnout Paradise: The Ultimate Box", Type.RACE));
        repo.save(new GameKey("3E9B6694-03B8-427D-B6D5-5A8E4045971C", "Dirt 3", Type.RACE));
        repo.save(new GameKey("2FE78BF9-B3FB-4A12-A93B-6C13ACC1E93C", "Cities: Skylines", Type.STRATEGY));
        repo.save(new GameKey("8F884191-8DC2-4895-9B93-CBA6C35F8BC7", "Anno 1800", Type.STRATEGY));
        repo.save(new GameKey("617D1A00-C943-4B54-A7F8-316AC4BDCBA9", "Civilization VI", Type.STRATEGY));
        repo.save(new GameKey("49E6FCF3-3EE7-4F43-ADDC-9D03CC4C6DF4", "The Settlers", Type.STRATEGY));
        repo.save(new GameKey("1528B0D1-228D-4F87-AA51-CA67168B0DA9", "Magica", Type.MULTIPLAYER));
        repo.save(new GameKey("A8C2E15E-D114-4C66-AB93-8B653DED441D", "DARK SOULS III", Type.MULTIPLAYER));
        repo.save(new GameKey("9BECFB9D-3087-4BE0-A43C-5ADC65DCED65", "DARK SOULS II", Type.MULTIPLAYER));
        repo.save(new GameKey("538C450D-EEFF-43DB-A6F5-14AAF32BDDAD", "DARK SOULS ", Type.MULTIPLAYER));
        
        
        userRepo.save(new User("admin", encoder.encode("admin")));
      }
    };
  }
  
}

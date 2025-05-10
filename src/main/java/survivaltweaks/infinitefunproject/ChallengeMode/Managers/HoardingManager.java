package survivaltweaks.infinitefunproject.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.ChallengeMode.Metadata.Hoarder;
import survivaltweaks.infinitefunproject.ChallengeMode.ModeManager;

public class HoardingManager implements ModeManager {

    @Override
    public void apply(Player player) {
        player.setMetadata("Hoarder", new Hoarder());
    }

    @Override
    public String description() {
        return "You cannot drop\nitems.";
    }
}

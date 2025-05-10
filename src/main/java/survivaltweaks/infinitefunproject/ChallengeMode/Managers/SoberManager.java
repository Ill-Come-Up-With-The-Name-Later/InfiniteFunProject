package survivaltweaks.infinitefunproject.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.ChallengeMode.Metadata.Sober;
import survivaltweaks.infinitefunproject.ChallengeMode.ModeManager;

public class SoberManager implements ModeManager {
    @Override
    public void apply(Player player) {
        player.setMetadata("Sober", new Sober());
    }

    @Override
    public String description() {
        return "You cannot use\npotion effects.";
    }
}

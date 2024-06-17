package survivaltweaks.infinitefunproject.Player.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata.Sober;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.ModeManager;

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

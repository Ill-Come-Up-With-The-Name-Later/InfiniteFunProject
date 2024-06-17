package survivaltweaks.infinitefunproject.Player.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata.Grounded;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.ModeManager;

public class GroundedManager implements ModeManager {

    @Override
    public void apply(Player player) {
        player.setMetadata("Grounded", new Grounded());
    }

    @Override
    public String description() {
        return "You cannot jump.";
    }
}

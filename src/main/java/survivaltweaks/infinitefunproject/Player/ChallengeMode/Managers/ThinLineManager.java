package survivaltweaks.infinitefunproject.Player.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata.ThinLine;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.ModeManager;

public class ThinLineManager implements ModeManager {
    @Override
    public void apply(Player player) {
        player.setMetadata("ThinLine", new ThinLine());
    }

    @Override
    public String description() {
        return "You must always be\nabove 10 health.";
    }
}

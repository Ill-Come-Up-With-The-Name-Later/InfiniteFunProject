package survivaltweaks.infinitefunproject.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.ChallengeMode.Metadata.ThinLine;
import survivaltweaks.infinitefunproject.ChallengeMode.ModeManager;

public class ThinLineManager implements ModeManager {
    @Override
    public void apply(Player player) {
        player.setMetadata("Thin Line", new ThinLine());
    }

    @Override
    public String description() {
        return "You must always be\nabove 10 health.";
    }
}

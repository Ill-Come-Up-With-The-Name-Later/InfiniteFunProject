package survivaltweaks.infinitefunproject.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.ChallengeMode.Metadata.MineFatigue;
import survivaltweaks.infinitefunproject.ChallengeMode.ModeManager;

public class MineFatigueManager implements ModeManager {

    @Override
    public void apply(Player player) {
        player.setMetadata("Mining Fatigue", new MineFatigue());
    }

    @Override
    public String description() {
        return "You cannot break\nblocks.";
    }
}

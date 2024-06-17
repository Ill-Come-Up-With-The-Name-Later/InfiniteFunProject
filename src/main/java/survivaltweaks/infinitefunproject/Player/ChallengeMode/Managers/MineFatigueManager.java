package survivaltweaks.infinitefunproject.Player.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata.MineFatigue;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.ModeManager;

public class MineFatigueManager implements ModeManager {

    @Override
    public void apply(Player player) {
        player.setMetadata("MineFatigue", new MineFatigue());
    }

    @Override
    public String description() {
        return "You cannot break\nblocks.";
    }
}

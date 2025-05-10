package survivaltweaks.infinitefunproject.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.ChallengeMode.Metadata.BuildBlocked;
import survivaltweaks.infinitefunproject.ChallengeMode.ModeManager;

public class BuildBlockManager implements ModeManager {

    @Override
    public void apply(Player player) {
        player.setMetadata("Building Block", new BuildBlocked());
    }

    @Override
    public String description() {
        return "You cannot place\nblocks.";
    }
}

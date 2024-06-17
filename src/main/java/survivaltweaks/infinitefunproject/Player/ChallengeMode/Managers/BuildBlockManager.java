package survivaltweaks.infinitefunproject.Player.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata.BuildBlocked;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.ModeManager;

public class BuildBlockManager implements ModeManager {

    @Override
    public void apply(Player player) {
        player.setMetadata("BuildBlock", new BuildBlocked());
    }

    @Override
    public String description() {
        return "You cannot place\nblocks.";
    }
}

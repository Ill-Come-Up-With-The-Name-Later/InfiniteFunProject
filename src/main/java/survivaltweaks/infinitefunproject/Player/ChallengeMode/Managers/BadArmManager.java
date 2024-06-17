package survivaltweaks.infinitefunproject.Player.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata.BadArm;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.ModeManager;

public class BadArmManager implements ModeManager {
    @Override
    public void apply(Player player) {
        player.setMetadata("BadArm", new BadArm());
    }

    @Override
    public String description() {
        return "You cannot shoot\nprojectiles.";
    }
}

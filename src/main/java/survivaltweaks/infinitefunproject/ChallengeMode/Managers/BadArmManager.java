package survivaltweaks.infinitefunproject.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.ChallengeMode.Metadata.BadArm;
import survivaltweaks.infinitefunproject.ChallengeMode.ModeManager;

public class BadArmManager implements ModeManager {
    @Override
    public void apply(Player player) {
        player.setMetadata("Bad Arm", new BadArm());
    }

    @Override
    public String description() {
        return "You cannot shoot\nprojectiles.";
    }
}

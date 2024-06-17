package survivaltweaks.infinitefunproject.Player.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata.NoArmor;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.ModeManager;

public class NoArmorManager implements ModeManager {

    @Override
    public void apply(Player player) {
        player.setMetadata("NoArmor", new NoArmor());
    }

    @Override
    public String description() {
        return "You cannot wear\narmor.";
    }
}

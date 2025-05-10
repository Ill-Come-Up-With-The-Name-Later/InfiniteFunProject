package survivaltweaks.infinitefunproject.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.ChallengeMode.Metadata.NoArmor;
import survivaltweaks.infinitefunproject.ChallengeMode.ModeManager;

public class NoArmorManager implements ModeManager {

    @Override
    public void apply(Player player) {
        player.setMetadata("No Armor", new NoArmor());
    }

    @Override
    public String description() {
        return "You cannot wear\narmor.";
    }
}

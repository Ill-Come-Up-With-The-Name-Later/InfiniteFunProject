package survivaltweaks.infinitefunproject.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.ChallengeMode.Metadata.BoundArmor;
import survivaltweaks.infinitefunproject.ChallengeMode.ModeManager;

public class BoundArmorManager implements ModeManager {

    @Override
    public void apply(Player player) {
        player.setMetadata("Stuck to You", new BoundArmor());
    }

    @Override
    public String description() {
        return "Any armor you equip is\nfinal.";
    }
}

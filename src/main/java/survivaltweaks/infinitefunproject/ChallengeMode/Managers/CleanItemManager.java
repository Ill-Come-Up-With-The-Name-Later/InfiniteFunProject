package survivaltweaks.infinitefunproject.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.ChallengeMode.Metadata.CleanItems;
import survivaltweaks.infinitefunproject.ChallengeMode.ModeManager;

public class CleanItemManager implements ModeManager {
    @Override
    public void apply(Player player) {
        player.setMetadata("Clean Items", new CleanItems());
    }

    @Override
    public String description() {
        return "You cannot use anvils\nor enchantment tables.";
    }
}

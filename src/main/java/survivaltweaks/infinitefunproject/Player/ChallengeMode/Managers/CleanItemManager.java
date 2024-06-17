package survivaltweaks.infinitefunproject.Player.ChallengeMode.Managers;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata.CleanItems;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.ModeManager;

public class CleanItemManager implements ModeManager {
    @Override
    public void apply(Player player) {
        player.setMetadata("CleanItems", new CleanItems());
    }

    @Override
    public String description() {
        return "You cannot use anvils\nor enchantment tables.";
    }
}

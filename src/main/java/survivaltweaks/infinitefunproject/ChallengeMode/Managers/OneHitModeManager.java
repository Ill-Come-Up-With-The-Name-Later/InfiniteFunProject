package survivaltweaks.infinitefunproject.ChallengeMode.Managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.ChallengeMode.Metadata.OneHitMode;
import survivaltweaks.infinitefunproject.ChallengeMode.ModeManager;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class OneHitModeManager implements ModeManager {

    @Override
    public void apply(Player player) {
        player.setMetadata("One Hit", new OneHitMode());

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1);
            player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        }, 3);
    }

    @Override
    public String description() {
        return "One health, but you\ninstantly kill anything.";
    }
}

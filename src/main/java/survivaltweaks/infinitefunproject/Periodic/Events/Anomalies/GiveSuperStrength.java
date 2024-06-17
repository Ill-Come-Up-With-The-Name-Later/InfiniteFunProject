package survivaltweaks.infinitefunproject.Periodic.Events.Anomalies;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents.RandomEvent;

public class GiveSuperStrength implements RandomEvent {
    @Override
    public void trigger() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(ChatColor.RED + "Enjoy the one-shotting frenzy!");
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 800, 99, true, false, true));
        }
    }
}

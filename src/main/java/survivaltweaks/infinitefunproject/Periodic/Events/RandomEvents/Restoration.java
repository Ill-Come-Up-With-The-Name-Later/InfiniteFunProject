package survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class Restoration implements RandomEvent {
    @Override
    public void trigger() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        for(Player player : players) {
            player.sendMessage(ChatColor.GREEN + "Your hunger has been restored");
            player.addPotionEffect(PotionEffectType.SATURATION.createEffect(20, 12));
        }
    }
}

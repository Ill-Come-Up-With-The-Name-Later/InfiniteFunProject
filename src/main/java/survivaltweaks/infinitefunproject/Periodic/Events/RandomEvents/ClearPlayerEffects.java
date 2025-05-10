package survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.Collection;

public class ClearPlayerEffects implements RandomEvent {

    @Override
    public void trigger() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        for(Player player : players) {
            player.sendMessage(ChatColor.YELLOW + "All potion effects have been cleared!");
            player.getActivePotionEffects().clear();


            if(player.hasMetadata("Infected")) {
                player.removeMetadata("Infected", InfiniteFunProject.plugin);
            }

            if(player.hasMetadata("Cancer")) {
                player.removeMetadata("Cancer", InfiniteFunProject.plugin);
            }
        }
    }
}

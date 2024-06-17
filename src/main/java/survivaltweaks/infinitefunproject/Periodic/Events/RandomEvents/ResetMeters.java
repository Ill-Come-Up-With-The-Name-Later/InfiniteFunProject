package survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static survivaltweaks.infinitefunproject.StatusMeters.Cold.ColdInit.setCold;
import static survivaltweaks.infinitefunproject.StatusMeters.Heat.HeatInit.setHeat;
import static survivaltweaks.infinitefunproject.StatusMeters.Thirst.ThirstInit.setThirst;

public class ResetMeters implements RandomEvent {
    @Override
    public void trigger() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            setHeat(player, 0);
            setThirst(player, 0);
            setCold(player, 0);
            player.sendMessage(ChatColor.GREEN + "Your heat, cold, and thirst have been replenished!");
        }
    }
}

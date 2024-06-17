package survivaltweaks.infinitefunproject.Periodic.Events.Anomalies;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents.RandomEvent;

import static survivaltweaks.infinitefunproject.StatusMeters.Cold.ColdInit.getCold;
import static survivaltweaks.infinitefunproject.StatusMeters.Cold.ColdInit.setCold;
import static survivaltweaks.infinitefunproject.StatusMeters.Heat.HeatInit.getHeat;
import static survivaltweaks.infinitefunproject.StatusMeters.Heat.HeatInit.setHeat;
import static survivaltweaks.infinitefunproject.StatusMeters.Thirst.ThirstInit.getThirst;
import static survivaltweaks.infinitefunproject.StatusMeters.Thirst.ThirstInit.setThirst;

public class AlterStatusMeters implements RandomEvent {
    @Override
    public void trigger() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            setHeat(player, getHeat(player) + 12);
            setThirst(player, getThirst(player) + 12);
            setCold(player, getCold(player) + 12);
        }
        Bukkit.spigot().broadcast(new TextComponent(ChatColor.BOLD + "Environmental disturbance!"));
    }
}

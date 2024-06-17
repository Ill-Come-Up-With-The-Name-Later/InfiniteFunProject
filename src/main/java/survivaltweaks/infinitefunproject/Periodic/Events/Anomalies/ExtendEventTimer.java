package survivaltweaks.infinitefunproject.Periodic.Events.Anomalies;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import survivaltweaks.infinitefunproject.Periodic.Events.EventInit;
import survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents.RandomEvent;

public class ExtendEventTimer implements RandomEvent {
    @Override
    public void trigger() {
        EventInit.getCountdown()[0] += 120;

        Bukkit.spigot().broadcast(new TextComponent(ChatColor.GREEN + "The next event is delayed!"));
    }
}

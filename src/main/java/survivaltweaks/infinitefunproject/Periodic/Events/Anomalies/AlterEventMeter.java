package survivaltweaks.infinitefunproject.Periodic.Events.Anomalies;

import org.bukkit.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import survivaltweaks.infinitefunproject.Periodic.Events.EventInit;
import survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents.RandomEvent;

public class AlterEventMeter implements RandomEvent {

    @Override
    public void trigger() {
        EventInit.getCountdown()[0] = Math.max(EventInit.getCountdown()[0] - 40, 1);
        Bukkit.spigot().broadcast(new TextComponent(ChatColor.YELLOW + "The next event is arriving early!"));
    }
}

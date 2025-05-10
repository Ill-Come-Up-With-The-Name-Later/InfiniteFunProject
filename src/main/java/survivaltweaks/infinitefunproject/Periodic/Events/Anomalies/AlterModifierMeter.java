package survivaltweaks.infinitefunproject.Periodic.Events.Anomalies;

import org.bukkit.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents.RandomEvent;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;

public class AlterModifierMeter implements RandomEvent {

    @Override
    public void trigger() {
        WorldModInit.getCountdown()[0] = Math.max(WorldModInit.getCountdown()[0] - 120, 1);
        Bukkit.spigot().broadcast(new TextComponent(ChatColor.LIGHT_PURPLE + "The next modifier is arriving early!"));
    }
}

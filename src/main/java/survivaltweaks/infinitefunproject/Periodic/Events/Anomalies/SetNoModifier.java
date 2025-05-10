package survivaltweaks.infinitefunproject.Periodic.Events.Anomalies;

import org.bukkit.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents.RandomEvent;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModifier;

import static survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit.activeModifier;

public class SetNoModifier implements RandomEvent {
    @Override
    public void trigger() {
        WorldModInit.setActiveModifier(WorldModifier.NONE);
        WorldModInit.getCountdown()[0] += 120;
        Bukkit.spigot().broadcast(new TextComponent(ChatColor.LIGHT_PURPLE + "The modifier is now None!"));
        Bukkit.spigot().broadcast(new TextComponent(ChatColor.GRAY + "- " + ChatColor.YELLOW + activeModifier.getDescription()));
    }
}

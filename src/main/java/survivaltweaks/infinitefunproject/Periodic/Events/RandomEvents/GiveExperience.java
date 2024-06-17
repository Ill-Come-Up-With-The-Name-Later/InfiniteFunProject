package survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public class GiveExperience implements RandomEvent {
    @Override
    public void trigger() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        for(Player player : players) {
            player.sendMessage(color("&aNever know when you'll need it..."));
            player.giveExp(1500);
        }
    }
}

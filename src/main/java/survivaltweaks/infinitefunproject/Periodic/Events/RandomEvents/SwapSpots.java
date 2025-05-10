package survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.Periodic.Events.EventInit;

import java.util.ArrayList;
import java.util.Collection;

public class SwapSpots implements RandomEvent {
    @Override
    public void trigger() {
        if(Bukkit.getOnlinePlayers().size() == 2) {
            Collection<? extends Player> players = Bukkit.getOnlinePlayers();
            ArrayList<Player> players1 = new ArrayList<>(players);

            Player player1 = players1.get(0);
            Player player2 = players1.get(1);

            Location player1Loc = player1.getLocation();

            player1.teleport(player2.getLocation());
            player2.teleport(player1Loc);

            for(Player player : players1) {
                player.sendMessage(ChatColor.GREEN + "Swap successful!");
            }
        } else {
            for(Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(ChatColor.RED + "You all are really lucky this didn't go anywhere...");
                player.sendMessage(ChatColor.GREEN + "Enjoy the " + EventInit.getCountdown()[0] + " second break!");
            }
        }
    }
}

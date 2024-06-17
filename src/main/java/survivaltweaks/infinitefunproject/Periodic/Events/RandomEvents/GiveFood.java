package survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public class GiveFood implements RandomEvent {
    @Override
    public void trigger() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        for(Player player : players) {
            player.sendMessage(color("&cHere's some food!"));
            player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, new Random().nextInt(2, 11)));
        }
    }
}

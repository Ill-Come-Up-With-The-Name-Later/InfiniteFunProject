package survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public class GiveLoot implements RandomEvent {

    public void trigger() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        for(Player player : players) {
            player.sendMessage(color("&6You got lucky this time."));
            player.getInventory().addItem(new ItemStack(Material.DIAMOND, new Random().nextInt(0, 4)));
            player.getInventory().addItem(new ItemStack(Material.OBSIDIAN, new Random().nextInt(0, 3)));
            player.getInventory().addItem(new ItemStack(Material.IRON_INGOT, new Random().nextInt(0, 5)));
            player.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, new Random().nextInt(0, 4)));
            player.getInventory().addItem(new ItemStack(Material.EMERALD, new Random().nextInt(0, 4)));
        }
    }
}

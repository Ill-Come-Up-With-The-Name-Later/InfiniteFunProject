package survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public class GiveArmor implements RandomEvent {
    @Override
    public void trigger() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        for(Player player : players) {
            player.sendMessage(color("&7Suit up!"));
            int random = new Random().nextInt(0, 4);

            switch (random) {
                case 0:
                    player.getInventory().addItem(new ItemStack(Material.IRON_HELMET));
                    break;
                case 1:
                    player.getInventory().addItem(new ItemStack(Material.IRON_CHESTPLATE));
                    break;
                case 2:
                    player.getInventory().addItem(new ItemStack(Material.IRON_LEGGINGS));
                    break;
                case 3:
                    player.getInventory().addItem(new ItemStack(Material.IRON_BOOTS));
                    break;
            }
        }
    }
}

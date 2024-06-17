package survivaltweaks.infinitefunproject.Mobs.FarmAnimals;

import org.bukkit.Material;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class Cows implements Listener {

    @EventHandler
    public void onMilk(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();

        if(entity instanceof Cow) {
            Cow cow = (Cow) entity;

            if(player.getInventory().getItemInMainHand().getType() == Material.BUCKET) {
                player.damage(5, cow);
                event.setCancelled(true);
            }
        }
    }
}

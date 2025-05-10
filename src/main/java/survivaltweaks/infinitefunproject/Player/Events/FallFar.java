package survivaltweaks.infinitefunproject.Player.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

public class FallFar implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity e = event.getEntity();

        if(e instanceof Player) {
            Player player = (Player) e;
            float fallDist = player.getFallDistance();

            if(fallDist > 8 && !player.getWorld().getBlockAt(player.getLocation().subtract(0, 1, 0)).isEmpty()) {
                player.addPotionEffect(PotionEffectType.SLOWNESS.createEffect(400, 4));
                player.addPotionEffect(PotionEffectType.JUMP_BOOST.createEffect(400, 240));
                player.sendMessage(ChatColor.RED + "You broke your leg!");
            }

            player.setFallDistance(0);
        }
    }
}

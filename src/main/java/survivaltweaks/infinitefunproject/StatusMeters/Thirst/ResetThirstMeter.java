package survivaltweaks.infinitefunproject.StatusMeters.Thirst;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.StatusMeters.Thirst.ThirstInit.*;

public class ResetThirstMeter implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        setupThirst(player);
    }

    @EventHandler
    public void onWaterItemConsume(PlayerItemConsumeEvent event) {
        ArrayList<Material> waterItems = new ArrayList<>();
        waterItems.add(Material.POTION);
        waterItems.add(Material.MELON_SLICE);
        waterItems.add(Material.HONEY_BOTTLE);

        Player player = event.getPlayer();

        if(waterItems.contains(event.getItem().getType())) {
            setThirst(player, Math.max(getThirst(player) - 5, 0));
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Player) {
            Player player = (Player) entity;
            setThirst(player, 0);
        }
    }
}

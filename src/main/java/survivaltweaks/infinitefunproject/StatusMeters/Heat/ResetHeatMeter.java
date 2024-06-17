package survivaltweaks.infinitefunproject.StatusMeters.Heat;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.StatusMeters.Heat.HeatInit.*;

public class ResetHeatMeter implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        setupHeat(player);
    }

    @EventHandler
    public void onWaterItemConsume(PlayerItemConsumeEvent event) {
        ArrayList<Material> waterItems = new ArrayList<>();
        waterItems.add(Material.POTION);
        waterItems.add(Material.MELON_SLICE);
        waterItems.add(Material.HONEY_BOTTLE);

        Player player = event.getPlayer();

        if(waterItems.contains(event.getItem().getType())) {
            setHeat(player, Math.max(getHeat(player) - 5, 0));
        }
    }

    @EventHandler
    public void onCatchFire(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        EntityDamageEvent.DamageCause cause = event.getCause();

        if(entity instanceof Player && (cause == EntityDamageEvent.DamageCause.FIRE || cause == EntityDamageEvent.DamageCause.FIRE_TICK)) {
            Player player = (Player) entity;

            setHeat(player, Math.min(getHeat(player) + 2, 100));
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Player) {
            Player player = (Player) entity;
            setHeat(player, 0);
        }
    }
}

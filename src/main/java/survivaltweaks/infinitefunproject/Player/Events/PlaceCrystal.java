package survivaltweaks.infinitefunproject.Player.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class PlaceCrystal implements Listener {
    @EventHandler
    public void onPlace(EntityPlaceEvent event) {
        Entity placed = event.getEntity();
        Player player = event.getPlayer();

        if(placed instanceof EnderCrystal) {
            EnderCrystal crystal = (EnderCrystal) placed;

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> crystal.setMetadata("PlayerPlace", new PlayerPlacedCrystal()), 1);
            Bukkit.getScheduler().runTaskLater(plugin, crystal::remove, 690);

            // Heals player when they have less than or equal to 18 health
            new BukkitRunnable() {
                @Override
                public void run() {
                    if(crystal.isDead() || player.isDead()) {
                        cancel();
                        return;
                    }
                    if(distanceBetween(player.getLocation().toVector(), crystal.getLocation().toVector()) <= 30) {
                        if(player.getHealth() <= 18) {
                            crystal.setBeamTarget(player.getLocation().subtract(new Vector(0, 1.4, 0)));
                            player.setHealth(player.getHealth() + 2);
                            Bukkit.getScheduler().runTaskLater(plugin, () -> crystal.setBeamTarget(null), 8);
                        }
                    }
                }
            }.runTaskTimer(InfiniteFunProject.plugin, 4, 55);

            // Destroys crystals
            new BukkitRunnable() {
                @Override
                public void run() {

                    if(crystal.isDead()) {
                        cancel();
                        return;
                    }

                    ArrayList<Entity> entities = (ArrayList<Entity>) crystal.getNearbyEntities(45, 45, 45);
                    ArrayList<EnderCrystal> crystals = new ArrayList<>();
                    for(Entity entity : entities) {
                        if (entity instanceof EnderCrystal && !entity.hasMetadata("PlayerPlace") && !entity.isInvulnerable() &&
                        distanceBetween(entity.getLocation().toVector(), crystal.getLocation().toVector()) > 15) {
                            crystals.add((EnderCrystal) entity);
                        }
                    }

                    // Destroy crystals
                    for(int i = 0; i < crystals.size(); i++) {
                        int finalI = i;

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if(crystal.isDead() || crystals.get(finalI).isDead()) {
                                    cancel();
                                }
                                crystal.setBeamTarget(crystals.get(finalI).getLocation().subtract(new Vector(0, 1.8, 0)));
                                crystals.get(finalI).remove();
                                Bukkit.getScheduler().runTaskLater(plugin, () -> crystal.setBeamTarget(null), 5);
                            }
                        }.runTaskLater(plugin, i * 30L);
                    }
                }
            }.runTaskLater(plugin, 3);

            // Destroy self if dragon is near
            new BukkitRunnable() {
                @Override
                public void run() {
                    ArrayList<Entity> nearby = (ArrayList<Entity>) crystal.getNearbyEntities(5, 5, 5);

                    for(Entity e : nearby) {
                        if(e instanceof EnderDragon) {
                            crystal.remove();
                        }
                    }
                }
            }.runTaskTimer(plugin, 10, 10);

            // Damages dragon
            new BukkitRunnable() {
                @Override
                public void run() {

                    if(crystal.isDead()) {
                        cancel();
                        return;
                    }

                    ArrayList<Entity> entities = (ArrayList<Entity>) crystal.getNearbyEntities(45, 45, 45);
                    for(Entity entity : entities) {
                        if (entity instanceof EnderDragon) {
                            crystal.setBeamTarget(entity.getLocation());
                            ((EnderDragon) entity).damage(30, player);
                            Bukkit.getScheduler().runTaskLater(plugin, () -> crystal.setBeamTarget(null), 8);
                        }
                    }
                }
            }.runTaskTimer(plugin, 20, 47);
        }
    }
}


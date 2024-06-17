package survivaltweaks.infinitefunproject.Bosses.EnderDragon.Crystals;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.plugin;

public class CrystalCallHelp implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof EnderCrystal) {
            EnderCrystal crystal = (EnderCrystal) entity;

            if(!(crystal.hasMetadata("PlayerPlace") || crystal.hasMetadata("Attacker"))) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if(crystal.isDead()) {
                            cancel();
                            return;
                        }

                        ArrayList<Entity> entities = (ArrayList<Entity>) crystal.getNearbyEntities(9, 9, 9);
                        if(new Random().nextInt(0, 12) == 1) {
                            for(Entity possiblePlayer : entities) {
                                if(possiblePlayer instanceof Player) {
                                    Player player = (Player) possiblePlayer;
                                    ArrayList<Entity> dragonSearch = (ArrayList<Entity>) crystal.getNearbyEntities(100, 50, 100);

                                    for(Entity drag : dragonSearch) {
                                        if(drag instanceof EnderDragon) {
                                            EnderDragon dragon = (EnderDragon) drag;
                                            if(dragon.getBossBar().getColor().equals(BarColor.PINK)) {
                                                crystal.setBeamTarget(dragon.getLocation());

                                                dragon.getBossBar().setColor(BarColor.PURPLE);

                                                dragon.setPhase(EnderDragon.Phase.CIRCLING);
                                                dragon.teleport(player.getLocation().add(0, 35, 0));
                                                dragon.setVelocity(new Vector(0, -4.35, 0));

                                                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                                    crystal.setBeamTarget(null);
                                                    dragon.getBossBar().setColor(BarColor.PINK);
                                                }, 30);
                                            }
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }.runTaskTimer(InfiniteFunProject.plugin, 35, 35);
            }
        }
    }
}

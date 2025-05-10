package survivaltweaks.infinitefunproject.World.TrialChamber.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.AbstractSkeleton;
import org.bukkit.entity.Enemy;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.TrialSpawnerSpawnEvent;
import org.bukkit.inventory.ItemStack;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class EquipTrialSpawned implements Listener {

    @EventHandler
    public void onSpawn(TrialSpawnerSpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Enemy) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                Enemy enemy = (Enemy) entity;

                if(!(enemy.getEquipment() == null)) {
                    enemy.getEquipment().setBootsDropChance(0);
                    enemy.getEquipment().setLeggingsDropChance(0);
                    enemy.getEquipment().setChestplateDropChance(0);
                    enemy.getEquipment().setHelmetDropChance(0);
                    enemy.getEquipment().setItemInMainHandDropChance(0);

                    if(enemy instanceof Zombie || enemy instanceof AbstractSkeleton) {
                        enemy.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
                        enemy.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
                        enemy.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                        enemy.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                        enemy.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD));
                    }
                }
            }, 1);
        }
    }
}

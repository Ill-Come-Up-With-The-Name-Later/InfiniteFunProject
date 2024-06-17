package survivaltweaks.infinitefunproject.Mobs.Pillager;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.SeekingMetadata;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AutoAimCrossbow implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Pillager) {
            Pillager pillager = (Pillager) entity;
            ItemStack crossbow = new ItemStack(Material.CROSSBOW);

            ItemMeta crossbowMeta = crossbow.getItemMeta();

            crossbowMeta.addEnchant(Enchantment.MULTISHOT, 1, true);
            crossbowMeta.addEnchant(Enchantment.QUICK_CHARGE, 3, true);

            crossbow.setItemMeta(crossbowMeta);
            pillager.getEquipment().setItem(EquipmentSlot.HAND, crossbow);
            pillager.getEquipment().setItemInMainHandDropChance(0);
        }
    }

    @EventHandler
    public void onShoot(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();
        Entity shooter = (Entity) projectile.getShooter();

        if(projectile instanceof Arrow && shooter instanceof Pillager) {
            long day = shooter.getWorld().getFullTime() / 24000;
            int level = (int) Math.min(Math.floor((double) day / 10), 50);

            if(level >= 7) {
                ((Arrow) projectile).setCritical(true);
            }
            if(level >= 8) {
                projectile.setFireTicks(-1);
            }

            projectile.setMetadata("Seeking", new SeekingMetadata(4, 200, InfiniteFunProject.players()));
        }
    }
}

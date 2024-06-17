package survivaltweaks.infinitefunproject.Mobs.Skeleton;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class EquipSkeletonItems implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Skeleton) {
            Skeleton skeleton = (Skeleton) entity;
            skeleton.getEquipment().setItem(EquipmentSlot.HEAD, new ItemStack(Material.DIAMOND_HELMET));
            skeleton.getEquipment().setItem(EquipmentSlot.CHEST, new ItemStack(Material.IRON_CHESTPLATE));
            skeleton.getEquipment().setItem(EquipmentSlot.FEET, new ItemStack(Material.IRON_BOOTS));

            skeleton.getEquipment().setHelmetDropChance(0);
            skeleton.getEquipment().setChestplateDropChance(0);
            skeleton.getEquipment().setBootsDropChance(0);
        }
    }
}

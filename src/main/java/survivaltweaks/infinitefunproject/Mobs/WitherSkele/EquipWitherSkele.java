package survivaltweaks.infinitefunproject.Mobs.WitherSkele;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class EquipWitherSkele implements Listener {
    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity spawn = event.getEntity();

        if(spawn instanceof WitherSkeleton) {
            WitherSkeleton skeleton = (WitherSkeleton) spawn;

            skeleton.getEquipment().setItem(EquipmentSlot.HEAD, new ItemStack(Material.DIAMOND_HELMET));
            skeleton.getEquipment().setItem(EquipmentSlot.CHEST, new ItemStack(Material.IRON_CHESTPLATE));
            skeleton.getEquipment().setItem(EquipmentSlot.HAND, new ItemStack(Material.DIAMOND_SWORD));
            skeleton.getEquipment().setItem(EquipmentSlot.FEET, new ItemStack(Material.IRON_BOOTS));

            skeleton.getEquipment().setHelmetDropChance(0);
            skeleton.getEquipment().setChestplateDropChance(0);
            skeleton.getEquipment().setItemInMainHandDropChance(0);
            skeleton.getEquipment().setBootsDropChance(0);
        }
    }
}

package survivaltweaks.infinitefunproject.Mobs.Stray;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Stray;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class EquipStrays implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Stray) {
            Stray stray = (Stray) entity;
            stray.getEquipment().setItem(EquipmentSlot.HEAD, new ItemStack(Material.DIAMOND_HELMET));
            stray.getEquipment().setItem(EquipmentSlot.CHEST, new ItemStack(Material.IRON_CHESTPLATE));
            stray.getEquipment().setItem(EquipmentSlot.FEET, new ItemStack(Material.IRON_BOOTS));

            stray.getEquipment().setHelmetDropChance(0);
            stray.getEquipment().setChestplateDropChance(0);
            stray.getEquipment().setBootsDropChance(0);
        }
    }
}

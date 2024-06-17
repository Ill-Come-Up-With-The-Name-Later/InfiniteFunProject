package survivaltweaks.infinitefunproject.Mobs.Piglin;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Piglin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class EquipPiglins implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity spawn  = event.getEntity();

        if(spawn instanceof Piglin) {
            Piglin piglin = (Piglin) spawn;

            piglin.setImmuneToZombification(true);

            piglin.getEquipment().setItem(EquipmentSlot.HAND, new ItemStack(Material.GOLDEN_SWORD));
            piglin.getEquipment().setItem(EquipmentSlot.HEAD, new ItemStack(Material.GOLDEN_HELMET));
            piglin.getEquipment().setItem(EquipmentSlot.CHEST, new ItemStack(Material.GOLDEN_CHESTPLATE));
            piglin.getEquipment().setItem(EquipmentSlot.LEGS, new ItemStack(Material.GOLDEN_LEGGINGS));
            piglin.getEquipment().setItem(EquipmentSlot.FEET, new ItemStack(Material.GOLDEN_BOOTS));

            piglin.getEquipment().setItemInMainHandDropChance(0);
            piglin.getEquipment().setHelmetDropChance(0);
            piglin.getEquipment().setChestplateDropChance(0);
            piglin.getEquipment().setLeggingsDropChance(0);
            piglin.getEquipment().setBootsDropChance(0);
        }
    }
}

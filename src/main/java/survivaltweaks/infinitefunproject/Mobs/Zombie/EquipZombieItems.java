package survivaltweaks.infinitefunproject.Mobs.Zombie;

import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class EquipZombieItems implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Zombie) {
            Zombie zombie = (Zombie) entity;

            zombie.setCanBreakDoors(true);
            zombie.getEquipment().setItem(EquipmentSlot.HEAD, new ItemStack(Material.DIAMOND_HELMET));
            zombie.getEquipment().setItem(EquipmentSlot.CHEST, new ItemStack(Material.IRON_CHESTPLATE));
            zombie.getEquipment().setItem(EquipmentSlot.HAND, new ItemStack(Material.DIAMOND_SWORD));
            zombie.getEquipment().setItem(EquipmentSlot.FEET, new ItemStack(Material.IRON_BOOTS));

            zombie.getEquipment().setHelmetDropChance(0);
            zombie.getEquipment().setChestplateDropChance(0);
            zombie.getEquipment().setItemInMainHandDropChance(0);
            zombie.getEquipment().setBootsDropChance(0);
        }
        if(entity instanceof Drowned) {
            Drowned drowned = (Drowned) entity;
            drowned.getEquipment().setItem(EquipmentSlot.HEAD, new ItemStack(Material.DIAMOND_HELMET));
            drowned.getEquipment().setItem(EquipmentSlot.CHEST, new ItemStack(Material.IRON_CHESTPLATE));
            drowned.getEquipment().setItem(EquipmentSlot.HAND, new ItemStack(Material.TRIDENT));
            drowned.getEquipment().setItem(EquipmentSlot.FEET, new ItemStack(Material.IRON_BOOTS));

            drowned.getEquipment().setHelmetDropChance(0);
            drowned.getEquipment().setChestplateDropChance(0);
            drowned.getEquipment().setItemInMainHandDropChance(0);
            drowned.getEquipment().setBootsDropChance(0);
        }
    }
}

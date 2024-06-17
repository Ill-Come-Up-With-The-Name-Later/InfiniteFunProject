package survivaltweaks.infinitefunproject.Mobs.Vex;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Vex;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class EquipVex implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Vex) {
            Vex vex = (Vex) entity;

            vex.getEquipment().setItem(EquipmentSlot.HAND, new ItemStack(Material.DIAMOND_SWORD));
            vex.getEquipment().setItemInMainHandDropChance(0);
        }
    }
}

package survivaltweaks.infinitefunproject.World.Events;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

import java.util.Random;

public class ObtainSpecialDirt implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        if(block.getType() == Material.DIRT) {
            if(new Random().nextInt(0, 2000) == 1) {
                event.setCancelled(true);
                block.getWorld().setBlockData(block.getLocation(), Material.AIR.createBlockData());

                block.getWorld().spawnParticle(Particle.WITCH, block.getLocation(), 8, 0.3, 0.2, 0.3, 0.04);

                Item item = block.getWorld().spawn(block.getLocation(), Item.class);
                item.setItemStack(ItemManager.dirt);
                item.setGlowing(true);
            }
        }
    }
}

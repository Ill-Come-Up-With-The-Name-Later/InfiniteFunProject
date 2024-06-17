package survivaltweaks.infinitefunproject.Player.Torches;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

public class BurnoutTorch implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        ArrayList<Material> torches = new ArrayList<>();
        torches.add(Material.TORCH);
        torches.add(Material.WALL_TORCH);
        torches.add(Material.SOUL_TORCH);
        torches.add(Material.SOUL_WALL_TORCH);

        if(torches.contains(block.getType())) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () ->
                    block.getWorld().setBlockData(block.getLocation(), Material.AIR.createBlockData()), 2400);
        }
    }
}

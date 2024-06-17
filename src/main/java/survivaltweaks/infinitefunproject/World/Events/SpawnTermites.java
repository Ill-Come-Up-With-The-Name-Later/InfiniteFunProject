package survivaltweaks.infinitefunproject.World.Events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Silverfish;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;
import static survivaltweaks.infinitefunproject.Mobs.MobInit.setNoLevel;


public class SpawnTermites implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        ArrayList<Material> logs = new ArrayList<>();
        logs.add(Material.ACACIA_LOG);
        logs.add(Material.BIRCH_LOG);
        logs.add(Material.JUNGLE_LOG);
        logs.add(Material.MANGROVE_LOG);
        logs.add(Material.OAK_LOG);
        logs.add(Material.DARK_OAK_LOG);
        logs.add(Material.SPRUCE_LOG);

        if(new Random().nextInt(0, 9) == 1 && logs.contains(block.getType())) {
            Silverfish silverfish = block.getWorld().spawn(block.getLocation(), Silverfish.class);
            setNoLevel(silverfish);
            silverfish.setCustomName(color("&6Termite"));
        }
    }
}

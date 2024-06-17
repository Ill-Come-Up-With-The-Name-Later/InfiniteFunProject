package survivaltweaks.infinitefunproject.Player.Events;

import net.kyori.adventure.identity.Identity;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import java.util.ArrayList;

public class AttemptSleep implements Listener {

    @EventHandler
    public void onSleep(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        Player player = event.getPlayer();
        Action action = event.getAction();

        if(block == null) {
            return;
        }

        ArrayList<Material> bed = new ArrayList<>();
        bed.add(Material.BLACK_BED);
        bed.add(Material.BLUE_BED);
        bed.add(Material.BROWN_BED);
        bed.add(Material.CYAN_BED);
        bed.add(Material.GRAY_BED);
        bed.add(Material.GREEN_BED);
        bed.add(Material.LIGHT_BLUE_BED);
        bed.add(Material.LIGHT_GRAY_BED);
        bed.add(Material.MAGENTA_BED);
        bed.add(Material.ORANGE_BED);
        bed.add(Material.PINK_BED);
        bed.add(Material.PURPLE_BED);
        bed.add(Material.RED_BED);
        bed.add(Material.LIME_BED);
        bed.add(Material.YELLOW_BED);
        bed.add(Material.WHITE_BED);

        if(action.equals(Action.RIGHT_CLICK_BLOCK) && bed.contains(block.getType())) {
            if(block.getLocation().getWorld().getEnvironment().equals(World.Environment.NORMAL)) {
                player.setRespawnLocation(block.getLocation(), true);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Bed spawn location set!"));
            }
            event.setCancelled(true);
        }
    }
}

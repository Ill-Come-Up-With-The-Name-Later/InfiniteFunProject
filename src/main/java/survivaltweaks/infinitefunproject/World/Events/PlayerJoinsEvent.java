package survivaltweaks.infinitefunproject.World.Events;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.WorldLoadEvent;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.List;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public class PlayerJoinsEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.setGlowing(true);

        event.setJoinMessage(color("&aWelcome, " + player.getName() + "... to Project: Infinite Fun!"));
        List<World> worlds = Bukkit.getWorlds();

        for (World world : worlds) {
            world.setDifficulty(Difficulty.HARD);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
            world.setGameRule(GameRule.KEEP_INVENTORY, true);
            if(Bukkit.getOnlinePlayers().size() == 1) {
                world.setTime(15000);
            }
            world.getWorldBorder().setSize(20000);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if(player.hasMetadata("Cancer")) {
            player.removeMetadata("Cancer", InfiniteFunProject.plugin);
        }
    }
}

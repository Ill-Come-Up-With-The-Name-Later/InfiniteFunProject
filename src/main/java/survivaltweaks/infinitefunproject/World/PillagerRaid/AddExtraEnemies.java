package survivaltweaks.infinitefunproject.World.PillagerRaid;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Raid;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class AddExtraEnemies implements Listener {

    @EventHandler
    public void onWaveStart(RaidSpawnWaveEvent event) {
        Raid raid = event.getRaid();

        if(raid.isStarted()) {
            Location spawnLoc = raid.getLocation();

            if(new Random().nextInt(0, 3) == 1) {
                spawnLoc.add(new Random().nextInt(10, 40), 0, new Random().nextInt(10, 40));
            } else {
                spawnLoc.subtract(new Random().nextInt(10, 40), 0, new Random().nextInt(10, 40));
            }

            if(!(spawnLoc.getWorld().getBlockAt(spawnLoc).isPassable() || spawnLoc.getWorld().getBlockAt(spawnLoc).isEmpty())) {
                findAir(spawnLoc);
            } else {
                findGround(spawnLoc);
            }

            Bukkit.spigot().broadcast(new TextComponent(color("&c&lThe wave is stronger than expected...")));

            Illusioner illusioner = event.getWorld().spawn(spawnLoc, Illusioner.class);
            illusioner.setCanJoinRaid(true);
            illusioner.setGlowing(true);

            if(new Random().nextInt(0, 3) == 1) {
                Illusioner illusioner2 = event.getWorld().spawn(spawnLoc, Illusioner.class);
                illusioner2.setCanJoinRaid(true);
                illusioner2.setGlowing(true);
            }

            for(int i = 0; i < 3; i++) {
                Pillager pillager = event.getWorld().spawn(spawnLoc, Pillager.class);
                pillager.setCanJoinRaid(true);
                pillager.setGlowing(true);

                Vindicator vindicator = event.getWorld().spawn(spawnLoc, Vindicator.class);
                vindicator.setCanJoinRaid(true);
                vindicator.setGlowing(true);

                if(new Random().nextInt(0, 6) == 1) {
                    Evoker evoker = event.getWorld().spawn(spawnLoc, Evoker.class);
                    evoker.setCanJoinRaid(true);
                    evoker.setGlowing(true);
                }
                if(new Random().nextInt(0, 9) == 1) {
                    Ravager ravager = event.getWorld().spawn(spawnLoc, Ravager.class);
                    ravager.setCanJoinRaid(true);
                    ravager.setGlowing(true);
                }
            }
        }
    }
}

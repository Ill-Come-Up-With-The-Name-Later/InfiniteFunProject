package survivaltweaks.infinitefunproject.World.PillagerRaid;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Raid;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidSpawnWaveEvent;
import survivaltweaks.infinitefunproject.ItemSelector;

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

            ItemSelector<EntityType> entities = new ItemSelector<>();

            entities.addEntry(EntityType.ILLUSIONER, 6);
            entities.addEntry(EntityType.PILLAGER, 7);
            entities.addEntry(EntityType.VINDICATOR, 5);
            entities.addEntry(EntityType.EVOKER, 4);
            entities.addEntry(EntityType.RAVAGER, 2);

            int rolls = new Random().nextInt(6, 13);

            for(EntityType type : entities.rollItems(rolls)) {
                Raider raider = (Raider) spawnLoc.getWorld().spawnEntity(spawnLoc, type);
                raider.setGlowing(true);
                raider.setRaid(raid);
            }
        }
    }
}

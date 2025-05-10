package survivaltweaks.infinitefunproject.Bosses.EnderDragon.Crystals.SpecialCrystal;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class SpawnCrystal implements Listener {

    /**
     * Spawn the special crystal
     * periodically and set
     * it to attack.
     *
     * @param event: Crystal spawning
     */
    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity spawned = event.getEntity();

        if(spawned instanceof EnderDragon && spawned.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
            EnderDragon dragon = (EnderDragon) spawned;

            new BukkitRunnable() {
                @Override
                public void run() {
                    if(dragon.isDead()) {
                        cancel();
                        return;
                    }

                    if(dragon.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
                        double x = dragon.getWorld().getEnderDragonBattle().getEndPortalLocation().getX() + 0.5;
                        double y = dragon.getWorld().getEnderDragonBattle().getEndPortalLocation().getY() + 5;
                        double z = dragon.getWorld().getEnderDragonBattle().getEndPortalLocation().getZ() + 0.5;

                        EnderCrystal crystal = (EnderCrystal) dragon.getWorld().spawnEntity(new Location(dragon.getWorld(), x, y, z), EntityType.END_CRYSTAL);
                        crystal.setShowingBottom(false);

                        for(Player player : Bukkit.getOnlinePlayers()) {
                            if(player.getWorld().equals(dragon.getWorld())) {
                                player.sendMessage(ChatColor.LIGHT_PURPLE + "The center crystal has spawned!");
                            }
                        }

                        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> crystal.setMetadata("Attacker", new AttackValue()), 1);
                    }
                }
            }.runTaskTimer(InfiniteFunProject.plugin, 2, 6000);
        }
    }
}

/**
 * Sets an ender crystal
 * to attack the player
 */
class AttackValue implements MetadataValue {

    @Override
    public Object value() {
        return "Attacker";
    }

    @Override
    public int asInt() {
        return -4;
    }

    @Override
    public float asFloat() {
        return -4;
    }

    @Override
    public double asDouble() {
        return -4;
    }

    @Override
    public long asLong() {
        return -4;
    }

    @Override
    public short asShort() {
        return -4;
    }

    @Override
    public byte asByte() {
        return -4;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @Override
    public String asString() {
        return "Attacker";
    }

    @Override
    public Plugin getOwningPlugin() {
        return InfiniteFunProject.plugin;
    }

    @Override
    public void invalidate() {

    }
}

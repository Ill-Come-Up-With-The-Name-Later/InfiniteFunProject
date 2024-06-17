package survivaltweaks.infinitefunproject.Mobs.Turtle;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Turtle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;

public class LandmineTurtle implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Turtle) {
            Turtle turtle = (Turtle) entity;

            if(new Random().nextInt(0, 50) == 1) {
                turtle.setMetadata("Landmine", new LandmineMeta());
                turtle.setCustomName(ChatColor.RED + "Landmine Turtle");
                turtle.setCustomNameVisible(true);
                turtle.setGlowing(true);

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(turtle, 2.8);
                        entities.removeIf(x -> !(x instanceof LivingEntity));

                        if(!entities.isEmpty()) {
                            turtle.getWorld().createExplosion(turtle.getLocation(), 4.5f, false, false, turtle);
                            turtle.remove();
                            cancel();
                        }
                    }
                }.runTaskTimer(InfiniteFunProject.plugin, 2, 2);
            }
        }
    }
}

class LandmineMeta implements MetadataValue {

    @Nullable
    @Override
    public Object value() {
        return "Landmine";
    }

    @Override
    public int asInt() {
        return 0;
    }

    @Override
    public float asFloat() {
        return 0;
    }

    @Override
    public double asDouble() {
        return 0;
    }

    @Override
    public long asLong() {
        return 0;
    }

    @Override
    public short asShort() {
        return 0;
    }

    @Override
    public byte asByte() {
        return 0;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @NotNull
    @Override
    public String asString() {
        return "Landmine";
    }

    @Nullable
    @Override
    public Plugin getOwningPlugin() {
        return InfiniteFunProject.plugin;
    }

    @Override
    public void invalidate() {

    }
}

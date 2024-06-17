package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;

public class FallenStar implements ActivatedAbility, Listener {
    @Override
    public void activate(Player player) {
        Snowball snowball = player.launchProjectile(Snowball.class);
        snowball.setVisibleByDefault(false);
        snowball.setMetadata("FallenStar", new FallenStarMeta());

        new BukkitRunnable() {

            @Override
            public void run() {
                if(snowball.isDead()) {
                    cancel();
                    return;
                }

                snowball.getLocation().getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, snowball.getLocation(), 7, 0.1, 0.1, 0.1, 0.02);
                snowball.getLocation().getWorld().spawnParticle(Particle.FIREWORKS_SPARK, snowball.getLocation(), 7, 0.1, 0.1, 0.1, 0.02);
                snowball.getLocation().getWorld().spawnParticle(Particle.CRIT, snowball.getLocation(), 5, 0.1, 0.1, 0.1, 0.02);
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 0, 1);
    }

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();

        if(projectile.hasMetadata("FallenStar")) {
            if(event.getHitEntity() != null) {
                Entity hit = event.getHitEntity();

                ArrayList<Entity> nearbyEntities = (ArrayList<Entity>) circularNearbyEntities(hit.getLocation(), 6);
                hit.setVelocity(new Vector(0, 1.8, 0));

                for(Entity entity : nearbyEntities) {
                    if(entity instanceof LivingEntity) {
                        if(!entity.equals(projectile.getShooter())) {
                            entity.setVelocity(new Vector(0, 1.8, 0));
                        }
                    }
                }

                return;
            }

            Location location = projectile.getLocation();
            ArrayList<Entity> nearby = (ArrayList<Entity>) circularNearbyEntities(location, 6);

            for(Entity entity : nearby) {
                if(entity instanceof LivingEntity) {
                    if(!entity.equals(projectile.getShooter())) {
                        entity.setVelocity(new Vector(0, 1.8, 0));
                    }
                }
            }
        }
    }

    @Override
    public int getCooldown() {
        return 110;
    }

    @Override
    public String getDescription() {
        return "Launches a star that throws enemies\nupward when it lands.";
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }

    @Override
    public boolean cooldownModifiable() {
        return true;
    }
}

class FallenStarMeta implements MetadataValue {

    @Nullable
    @Override
    public Object value() {
        return "FallenStar";
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
        return false;
    }

    @NotNull
    @Override
    public String asString() {
        return "FallenStar";
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

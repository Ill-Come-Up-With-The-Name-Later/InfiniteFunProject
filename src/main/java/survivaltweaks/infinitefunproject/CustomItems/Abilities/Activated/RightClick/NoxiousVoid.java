package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.PierceShieldMeta;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;

public class NoxiousVoid implements ActivatedAbility, Listener {
    int radius = 8;

    @Override
    public void activate(Player player) {
        Arrow arrow = player.launchProjectile(Arrow.class);
        arrow.setVisibleByDefault(false);
        arrow.setMetadata("Blackhole", new BlackHoleMetadata());
        arrow.setMetadata("PierceShield", new PierceShieldMeta());
        arrow.setPierceLevel(100);

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            arrow.setGravity(false);
            arrow.teleport(player.getLocation().add(0, 2.5, 0));
            arrow.setVelocity(new Vector(0, 0, 0));

            new BukkitRunnable() {

                @Override
                public void run() {
                    if(arrow.isDead()) {
                        cancel();
                        return;
                    }
                    ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(arrow.getLocation(), radius);

                    for(Entity entity : entities) {
                        if(entity instanceof LivingEntity) {
                            if(entity.isDead() || ((LivingEntity) entity).getHealth() < 0) {
                                entity.remove();
                                continue;
                            }
                            if(!entity.equals(arrow.getShooter()) && !entity.isDead()) {
                                entity.teleport(arrow.getLocation());
                                ((LivingEntity) entity).damage(20, player);
                            }
                        }
                    }
                }
            }.runTaskTimer(InfiniteFunProject.plugin, 10, 10);
        }, 1);

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            arrow.getWorld().createExplosion(arrow.getLocation(), 6f, false, false, (Entity) arrow.getShooter());
            arrow.remove();
        }, 320);

        new BukkitRunnable() {

            @Override
            public void run() {
                if(arrow.isDead()) {
                    cancel();
                    return;
                }

                arrow.getLocation().getWorld().spawnParticle(Particle.SMOKE_NORMAL, arrow.getLocation(), 5, 0.1, 0.1, 0.1, 0.02);
                arrow.getLocation().getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, arrow.getLocation(), 5, 0.1, 0.1, 0.1, 0.02);
                arrow.getLocation().getWorld().spawnParticle(Particle.FIREWORKS_SPARK, arrow.getLocation(), 5, 0.1, 0.1, 0.1, 0.02);
                arrow.getLocation().getWorld().spawnParticle(Particle.CRIT_MAGIC, arrow.getLocation(), 5, 0.1, 0.1, 0.1, 0.02);
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 0, 1);
    }

    @Override
    public int getCooldown() {
        return 1600;
    }

    @Override
    public String getDescription() {
        return "Creates a vacuum, pulling enemies in\nand damaging them.\n\nThe vacuum explodes when\nit expires.";
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

class BlackHoleMetadata implements MetadataValue {

    @Nullable
    @Override
    public Object value() {
        return "Blackhole";
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
        return "Blackhole";
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
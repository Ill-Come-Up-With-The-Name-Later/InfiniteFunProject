package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class Flashbang implements ActivatedAbility, Listener {
    int duration = 100;

    @Override
    public void activate(Player player) {
        Snowball flashbang = player.launchProjectile(Snowball.class);
        flashbang.setMetadata("Flashbang", new FlashbangMetadata());

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if(!flashbang.isDead()) {
                ArrayList<Entity> nearby = (ArrayList<Entity>) circularNearbyEntities(flashbang, 6);
                flashbang.getWorld().spawnParticle(Particle.FLASH, flashbang.getLocation().add(0, 0.2, 0), 8, 0.01, 0.01, 0.01, 0.5);
                flashbang.getWorld().spawnParticle(Particle.EXPLOSION, flashbang.getLocation().add(0, 0.2, 0), 5, 0.01, 0.01, 0.01, 0.05);

                for(Entity entity : nearby) {
                    if(entity instanceof LivingEntity && ((LivingEntity) entity).hasLineOfSight(flashbang)) {
                        if(entity instanceof Mob) {
                            Mob monster = (Mob) entity;
                            monster.setTarget(null);
                        }

                        stun((LivingEntity) entity, duration, false);
                    }
                }

                flashbang.remove();
            }
        }, 30);
    }

    @Override
    public int getCooldown() {
        return 120;
    }

    @Override
    public String getDescription() {
        return "Throws a flashbang that stuns\nentities for " + String.format("%.2f", (float)duration / 20) + " seconds." +
                "\n\nWill explode after 1.5 seconds\nif it hits nothing.\n\n" + ChatColor.RED + "Can stun the thrower.";
    }

    @Override
    public boolean oneTimeUse() {
        return true;
    }

    @Override
    public boolean cooldownModifiable() {
        return true;
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();

        if(projectile.hasMetadata("Flashbang")) {
            ArrayList<Entity> nearby = (ArrayList<Entity>) circularNearbyEntities(projectile, 6);
            projectile.getWorld().spawnParticle(Particle.FLASH, projectile.getLocation(), 5, 0.01, 0.01, 0.01, 0.05);
            projectile.getWorld().spawnParticle(Particle.EXPLOSION, projectile.getLocation(), 5, 0.01, 0.01, 0.01, 0.05);

            for(Entity entity : nearby) {
                if(entity instanceof LivingEntity && ((LivingEntity) entity).hasLineOfSight(projectile)) {
                    stun((LivingEntity) entity, duration, false);
                }
            }
        }
    }
}

class FlashbangMetadata implements MetadataValue {

    @Nullable
    @Override
    public Object value() {
        return "Flashbang";
    }

    @Override
    public int asInt() {
        return 17;
    }

    @Override
    public float asFloat() {
        return 17;
    }

    @Override
    public double asDouble() {
        return 17;
    }

    @Override
    public long asLong() {
        return 17;
    }

    @Override
    public short asShort() {
        return 17;
    }

    @Override
    public byte asByte() {
        return 17;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @NotNull
    @Override
    public String asString() {
        return "Flashbang";
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

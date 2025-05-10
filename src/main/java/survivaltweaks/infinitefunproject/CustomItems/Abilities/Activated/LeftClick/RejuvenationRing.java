package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Effect;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.RemoveOnGroundMeta;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class RejuvenationRing implements ActivatedAbility, Listener {
    int radius = 6;

    @Override
    public void activate(Player player) {
        Arrow arrow = player.launchProjectile(Arrow.class);
        arrow.setDamage(5);
        arrow.setVisibleByDefault(false);
        arrow.setMetadata("RemoveOnGround", new RemoveOnGroundMeta());
        arrow.setMetadata("RejuvRing", new RejuvenatingRingMeta());

        new BukkitRunnable() {

            @Override
            public void run() {
                if(arrow.isDead()) {
                    cancel();
                    return;
                }

                arrow.getLocation().getWorld().spawnParticle(Particle.HAPPY_VILLAGER, arrow.getLocation(), 7, 0.1, 0.1, 0.1, 0.02);
                arrow.getLocation().getWorld().spawnParticle(Particle.ELECTRIC_SPARK, arrow.getLocation(), 5, 0.1, 0.1, 0.1, 0.02);
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 0, 1);
    }

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        ArrayList<PotionEffectType> effects = new ArrayList<>() {
            {
                add(PotionEffectType.WITHER);
                add(PotionEffectType.POISON);
                add(PotionEffectType.SLOWNESS);
                add(PotionEffectType.MINING_FATIGUE);
                add(PotionEffectType.NAUSEA);
                add(PotionEffectType.BLINDNESS);
                add(PotionEffectType.DARKNESS);
                add(PotionEffectType.HUNGER);
                add(PotionEffectType.WEAKNESS);
            }
        };

        Projectile projectile = event.getEntity();

        if(projectile.hasMetadata("RejuvRing")) {
            drawCircle(projectile.getLocation(), radius, Particle.HAPPY_VILLAGER, 90);
            drawCircle(projectile.getLocation(), radius, Particle.HEART, 90);
            ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(projectile.getLocation(), radius);

            for(Entity entity : entities) {
                if(entity instanceof Player) {
                    Player player = (Player) entity;
                    player.setHealth(player.getHealth() + 10);

                    for(PotionEffect effect : player.getActivePotionEffects()) {
                        if(effects.contains(effect.getType())) {
                            player.removePotionEffect(effect.getType());
                        }
                    }

                } else if(entity instanceof LivingEntity) {
                    LivingEntity living = (LivingEntity) entity;

                    living.damage(10, (Entity) projectile.getShooter());
                }
            }
        }
    }

    @Override
    public int getCooldown() {
        return 200;
    }

    @Override
    public String getDescription() {
        return "Throws a ring that heals nearby\nplayers. The ring also clears some\nnegative effects.";
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

class RejuvenatingRingMeta implements MetadataValue {

    @Nullable
    @Override
    public Object value() {
        return "RejuvRing";
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
        return "RejuvRing";
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

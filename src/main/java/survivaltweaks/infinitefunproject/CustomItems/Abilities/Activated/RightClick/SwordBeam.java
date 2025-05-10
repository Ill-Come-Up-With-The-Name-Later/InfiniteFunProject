package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.PierceShieldMeta;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.RemoveOnGroundMeta;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.addProjectileTrail;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;

public class SwordBeam implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        ArrayList<Particle> swordParticles = new ArrayList<>() {
            {
                add(Particle.SWEEP_ATTACK);
                add(Particle.ENCHANTED_HIT);
            }
        };

        SpectralArrow arrow = player.launchProjectile(SpectralArrow.class);
        arrow.setVisibleByDefault(false);
        arrow.setVelocity(arrow.getVelocity().normalize().multiply(8));
        arrow.setDamage(3);
        arrow.setGravity(false);
        arrow.setPierceLevel(99);
        arrow.setCritical(true);

        arrow.setMetadata("RemoveOnGround", new RemoveOnGroundMeta());
        arrow.setMetadata("PierceShield", new PierceShieldMeta());

        addProjectileTrail(arrow, swordParticles);

        new BukkitRunnable() {

            @Override
            public void run() {
                if(arrow.getTicksLived() > 119 || arrow.isOnGround()) {
                    arrow.remove();
                }

                if(arrow.isDead()) {
                    cancel();
                    return;
                }

                ArrayList<Entity> nearby = (ArrayList<Entity>) circularNearbyEntities(arrow, 2);

                for(Entity entity : nearby) {
                    if(entity instanceof LivingEntity && !entity.equals(arrow.getShooter())) {
                        LivingEntity living = (LivingEntity) entity;

                        living.damage(30, (Entity) arrow.getShooter());
                    }
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 0, 1);
    }

    @Override
    public int getCooldown() {
        return 100;
    }

    @Override
    public String getDescription() {
        return "Send out a large beam from your\nsword.\n\nThe beam pierces shields.";
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

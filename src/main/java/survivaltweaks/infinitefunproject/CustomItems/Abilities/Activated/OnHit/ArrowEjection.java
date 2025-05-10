package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHit;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.AttackAbility;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.RemoveOnGroundMeta;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.SeekingMetadata;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.nearbyLivingEntities;

public class ArrowEjection implements AttackAbility {

    @Override
    public void activate(Player player, LivingEntity entity) {
        ArrayList<LivingEntity> nearby = nearbyLivingEntities(entity, 22);

        for(LivingEntity living : nearby) {
            if(living.isDead() || living.getTicksLived() < 15) {
                continue;
            }

            if(!living.hasMetadata("BeenHit")) {
                if(entity.hasLineOfSight(living) && !living.equals(player)) {
                    Arrow arrow = player.getWorld().spawn(entity.getLocation(), Arrow.class);
                    arrow.setShooter(player);
                    arrow.setVelocity(living.getBoundingBox().getCenter().subtract(entity.getBoundingBox().getCenter()));
                    arrow.setDamage(20);
                    arrow.setMetadata("RemoveOnGround", new RemoveOnGroundMeta());
                    arrow.setMetadata("Seeking", new SeekingMetadata(8, 200, InfiniteFunProject.allExceptPlayers()));
                    arrow.setGlowing(true);

                    arrow.setVelocity(arrow.getVelocity().multiply(0.7));

                    //setHasBeenHit(living, 40);
                    living.setNoDamageTicks(3);
                }
            }
        }
    }

    @Override
    public void activate(Player player, EntityDamageByEntityEvent event) {
    }

    @Override
    public void activate(Player player) {

    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Shoots arrows at entities\nnear the original target.";
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}

package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnKill;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.KillAbility;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.ExplosiveMeta;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.RemoveOnGroundMeta;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.SeekingMetadata;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.nearbyLivingEntities;

public class ExplosiveArrowFling implements KillAbility {

    @Override
    public void activate(Player player, LivingEntity entity) {
        ArrayList<LivingEntity> nearby = nearbyLivingEntities(entity, 20);

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
                    arrow.setMetadata("Explosive", new ExplosiveMeta(4.4f, false, false));
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
    public void activate(Player player) {

    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Launches explosive arrows\nfrom enemies' corpses.";
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

package survivaltweaks.infinitefunproject.Mobs.Skeleton;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Random;

public class RandomTippedArrows implements Listener {

    @EventHandler
    public void onShoot(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();
        Entity shooter = (Entity) projectile.getShooter();

        if(!(shooter instanceof LivingEntity)) return;

        if(projectile instanceof Arrow && shooter instanceof AbstractSkeleton) {
            Arrow arrow = (Arrow) projectile;

            ArrayList<PotionEffectType> negativeEffects = new ArrayList<>();
            negativeEffects.add(PotionEffectType.BLINDNESS);
            negativeEffects.add(PotionEffectType.NAUSEA);
            negativeEffects.add(PotionEffectType.WEAKNESS);
            negativeEffects.add(PotionEffectType.POISON);
            negativeEffects.add(PotionEffectType.DARKNESS);
            negativeEffects.add(PotionEffectType.HUNGER);
            negativeEffects.add(PotionEffectType.MINING_FATIGUE);

            if(new Random().nextInt(0, 20) == 3) {
                arrow.addCustomEffect(negativeEffects.get(new Random().nextInt(0, negativeEffects.size()))
                        .createEffect(300, 1), true);
            }
        }
    }
}

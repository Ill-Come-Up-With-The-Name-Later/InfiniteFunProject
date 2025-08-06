package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.ControlledEntity;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import static survivaltweaks.infinitefunproject.Combat.RandomCrits.DealRandomCrit.giveCrits;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;
import static survivaltweaks.infinitefunproject.Periodic.WorldModifiers.AddModifiers.etherealAugmentation;

public class ControlEnemies implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(player, 10);

        for(Entity entity : entities) {
            if(!(entity instanceof Enemy)) {
                continue;
            }

            Mob enemy = (Mob) entity;
            enemy.setMetadata("Slave", new ControlledEntity());
            enemy.setCustomName(color("&a&l" + player.getName() + "'s " + fixCaps(enemy.getType().toString())));
            giveCrits(enemy, 800);

            enemy.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1200, 1, false, false, true));
            enemy.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 1200, 1, false, false, true));
            enemy.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 1, false, false, true));
            enemy.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 1200, 3, false, false, true));

            enemy.setHealth(enemy.getAttribute(Attribute.MAX_HEALTH).getBaseValue());

            etherealAugmentation(enemy);

            if(enemy.getTarget() != null) {
                if(enemy.getTarget().equals(player)) {
                    enemy.setTarget(null);
                }
            }

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                if(!enemy.isDead()) {
                    drawCircle(enemy, 1.2, Particle.TRIAL_SPAWNER_DETECTION_OMINOUS, 45);
                    enemy.removeMetadata("Slave", InfiniteFunProject.plugin);
                    enemy.remove();
                }
            }, 1200);

            new BukkitRunnable() {

                @Override
                public void run() {
                    if(enemy.isDead()) {
                        cancel();
                        return;
                    }

                    if(enemy.getTarget() == null || enemy.getTarget().isDead()) {
                        ArrayList<Entity> targets = (ArrayList<Entity>)
                                circularNearbyEntities(enemy, enemy.getAttribute(Attribute.FOLLOW_RANGE).getBaseValue());

                        Optional<Entity> target = targets.stream().filter(x -> x instanceof Enemy && !x.equals(player)
                        && !x.equals(enemy) && !x.hasMetadata("Slave"))
                                .min(Comparator.comparing(x -> x.getLocation().distanceSquared(enemy.getLocation())));

                        target.ifPresent(value -> enemy.setTarget((LivingEntity) value));
                        drawCircle(entity, 1.2, Particle.ENCHANT, 90);

                        if(!target.isPresent()) {
                            enemy.setTarget(null);
                        }
                    }
                }
            }.runTaskTimer(InfiniteFunProject.plugin, 10, 10);
        }
    }

    @Override
    public int getCooldown() {
        return 4800;
    }

    @Override
    public String getDescription() {
        return "Take control of nearby enemies\nand use them to attack enemies.\n\nMinions expire after one minute.";
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

package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.createRay;

public class Etherwarp implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        createRay(player, 10, true, new ArrayList<>());
        ArrayList<Entity> hitEntities = (ArrayList<Entity>) circularNearbyEntities(player, 6);

        for(Entity entity : hitEntities) {
            if(entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;

                if(entity.getTicksLived() > 10) {
                    livingEntity.damage(60, player);
                }
            }
        }

        player.getWorld().spawnParticle(Particle.EXPLOSION, player.getLocation(), 10, 0, 0, 0, 6);

        if(!player.hasPotionEffect(PotionEffectType.ABSORPTION)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100, 3, false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 100, 0, false, false, false));

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                player.setHealth(Math.min(player.getHealth() + (player.getAbsorptionAmount() / 2),
                        player.getAttribute(Attribute.MAX_HEALTH).getBaseValue()));
                player.getWorld().spawnParticle(Particle.HEART, player.getLocation(), 8, 0.2, 0.2, 0.2, 0.03);
            }, 99);
        }
    }

    @Override
    public int getCooldown() {
        return 3;
    }

    @Override
    public String getDescription() {
        return "Teleport forward 10 blocks,\ndamaging enemies in a nearby\nradius and shielding yourself.\n" +
                "\nAfter five seconds, recover\nhealth and lose the shield.";
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

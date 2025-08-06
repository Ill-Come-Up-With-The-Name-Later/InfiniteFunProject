package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHitAbility;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.PassiveAbility;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.TriggeredAbility;

import java.util.ArrayList;
import java.util.HashMap;

import static survivaltweaks.infinitefunproject.CustomItems.Abilities.InitAbilities.hasCooldown;
import static survivaltweaks.infinitefunproject.CustomItems.Abilities.InitAbilities.setCooldown;
import static survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.TriggeredAbility.triggerAbility;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.createDamageRay;

public class MedkitRevolver implements ActivatedAbility {
    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.ENCHANTED_HIT);
                add(Particle.FIREWORK);
                add(Particle.HAPPY_VILLAGER);
            }
        };

        HashMap<LivingEntity, Boolean> entities = createDamageRay(player, 60, 0, 0,
                false, false, false, particles, true);

        for(LivingEntity entity : entities.keySet()) {
            if(entity instanceof Player) {
                Player other = (Player) entity;

                if(entities.get(other)) {
                    other.setHealth(Math.min(other.getAttribute(Attribute.MAX_HEALTH).getValue(), other.getHealth() + 12));
                } else {
                    other.setHealth(Math.min(other.getAttribute(Attribute.MAX_HEALTH).getValue(), other.getHealth() + 6));
                }
            } else {
                if(entities.get(entity) && !hasCooldown(player, TriggeredAbility.CRYSTALLIZE)) {
                    entity.damage(12, player);
                    triggerAbility(player, TriggeredAbility.CRYSTALLIZE);
                } else {
                    entity.damage(6, player);
                }
            }
        }
    }

    @Override
    public int getCooldown() {
        return 12;
    }

    @Override
    public String getDescription() {
        return "Fires off a beam that hurts enemies\nand heals players.";
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

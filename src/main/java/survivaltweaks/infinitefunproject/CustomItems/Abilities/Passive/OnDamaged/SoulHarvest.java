package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.OnDamaged;

import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.OnAttackedAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class SoulHarvest implements OnAttackedAbility {

    @Override
    public void activate(Player player, LivingEntity attacker) {
        attacker.damage(666_666_666, player);
        player.setHealth(Math.min(player.getHealth() + attacker.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * 0.04,
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()));

        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.TRIAL_OMEN);
                add(Particle.TOTEM_OF_UNDYING);
            }
        };

        drawCircle(player, 1.5, particles, 90);
    }

    @Override
    public void activate(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getEntity();

        if(event.getDamage() > player.getHealth()) {
            event.setDamage(player.getHealth() - 1);
        }
    }

    @Override
    public void activate(Player player) {

    }

    @Override
    public int getCooldown() {
        return 100;
    }

    @Override
    public String getDescription() {
        return "Instantly kill the attacker\nand gain 4% of the attacker's\nhealth.";
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }
}

package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.OnDamaged;

import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.OnAttackedAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.createDamageRay;

public class RetaliationRush implements OnAttackedAbility {

    @Override
    public void activate(Player player, LivingEntity attacker) {}

    @Override
    public void activate(EntityDamageByEntityEvent event) {

    }

    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.SONIC_BOOM);
                add(Particle.ENCHANTED_HIT);
            }
        };

        createDamageRay(player, 10, 55, 400, true, false, true, particles, false);

        player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 40, 1,
                false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 0,
                false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 40, 0,
                false, false, false));
    }

    @Override
    public int getCooldown() {
        return 50;
    }

    @Override
    public String getDescription() {
        return "Shoots a sonic blast and\ngrants a small buff.";
    }

    @Override
    public boolean cooldownModifiable() {
        return true;
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }
}

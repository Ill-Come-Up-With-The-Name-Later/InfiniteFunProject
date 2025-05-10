package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class HealUser implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        drawCircle(player, 1.2, Particle.HAPPY_VILLAGER, 90);

        player.setHealth(Math.min(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue(), player.getHealth() + 10));
        player.getWorld().spawnParticle(Particle.HEART, player.getLocation().add(new Vector(0, 1, 0)),
                8, 0.2, 0.2, 0.2, 0.03);

        player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 100, 0, false, false, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1, false, false, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 0, false, false, true));
    }

    @Override
    public int getCooldown() {
        return 160;
    }

    @Override
    public String getDescription() {
        return "Heals the user for 10\nhealth and grants buffs.";
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

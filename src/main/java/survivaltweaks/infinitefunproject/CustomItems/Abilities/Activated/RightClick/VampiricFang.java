package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.Combat.RandomCrits.DealRandomCrit.giveMiniCrits;

public class VampiricFang implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        double hpDrain = player.getHealth() / 2;
        int duration = (int) (hpDrain * 20);
        player.setHealth(hpDrain);

        double baseDamage = player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue();

        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(baseDamage * 5);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration, 1, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, duration, 1, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, duration, 1, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, duration, 0, false, false, false));
        giveMiniCrits(player, duration);

        player.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, player.getLocation().add(0, 1, 0), 40, 0.3, 0.2, 0.3, 0.01);

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(baseDamage);
            player.sendMessage(ChatColor.RED + "Your vampiric buff has run out.");
        }, duration);
    }

    @Override
    public int getCooldown() {
        return 1000;
    }

    @Override
    public String getDescription() {
        return "Drain half of your health\nin exchange for a buff that\nwill last as many seconds\nas the amount of health\ndrained.";
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

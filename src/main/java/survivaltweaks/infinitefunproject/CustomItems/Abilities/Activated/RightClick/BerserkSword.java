package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.Combat.RandomCrits.DealRandomCrit.giveCrits;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class BerserkSword implements ActivatedAbility {
    @Override
    public void activate(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 300, 1, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 0, false, false, false));
        giveCrits(player, 300);

        drawCircle(player.getLocation(), 2.2, Particle.SMALL_FLAME, 90);

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            drawCircle(player.getLocation(), 2.2, Particle.SOUL_FIRE_FLAME, 90);
        }, 300);
    }

    @Override
    public int getCooldown() {
        return 900;
    }

    @Override
    public String getDescription() { return "Gives Strength II, Speed I,\nand Crits for 15 seconds."; }

    @Override
    public boolean oneTimeUse() {
        return false;
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}

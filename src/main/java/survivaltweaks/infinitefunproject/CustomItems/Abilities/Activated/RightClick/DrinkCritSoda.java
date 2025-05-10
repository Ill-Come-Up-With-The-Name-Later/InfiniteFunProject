package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import static survivaltweaks.infinitefunproject.Combat.RandomCrits.DealRandomCrit.giveMiniCrits;

public class DrinkCritSoda implements ActivatedAbility {
    int duration = 160;

    @Override
    public void activate(Player player) {
        giveMiniCrits(player, duration);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, duration, 0, false, false, false));
    }

    @Override
    public int getCooldown() {
        return 480;
    }

    @Override
    public String getDescription() {
        return "Gain Mini Crits and Slowness I\nfor " + String.format("%.2f", (float) duration / 20) + " seconds.";
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

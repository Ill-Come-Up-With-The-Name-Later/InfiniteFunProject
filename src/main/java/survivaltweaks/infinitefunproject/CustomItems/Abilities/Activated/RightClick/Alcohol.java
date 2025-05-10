package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import static survivaltweaks.infinitefunproject.Combat.RandomCrits.DealRandomCrit.giveCrits;

public class Alcohol implements ActivatedAbility {
    @Override
    public void activate(Player player) {
        giveCrits(player, 600);

        player.addPotionEffect(PotionEffectType.NAUSEA.createEffect(600, 1));
        player.addPotionEffect(PotionEffectType.SPEED.createEffect(600, 1));
        player.addPotionEffect(PotionEffectType.DARKNESS.createEffect(600, 1));

        player.damage(2);
    }

    @Override
    public int getCooldown() {
        return 720;
    }

    @Override
    public String getDescription() {
        return "Gets you really drunk.";
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

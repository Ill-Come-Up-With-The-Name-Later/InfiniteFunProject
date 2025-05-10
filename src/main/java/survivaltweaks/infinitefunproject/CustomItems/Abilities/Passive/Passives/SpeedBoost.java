package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passive;

public class SpeedBoost implements Passive {

    @Override
    public void activate(Player player) {
        if(!player.hasPotionEffect(PotionEffectType.SPEED)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 2, false, false, true));
        }
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Grants permanent Speed III.";
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}

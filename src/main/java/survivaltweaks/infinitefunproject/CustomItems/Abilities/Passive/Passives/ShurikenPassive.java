package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passive;

public class ShurikenPassive implements Passive {
    @Override
    public void activate(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 0, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20, 0, false, false, false));
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Grants you Speed and Jump Boost I.";
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}

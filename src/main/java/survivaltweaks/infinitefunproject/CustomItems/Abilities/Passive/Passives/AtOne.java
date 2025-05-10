package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passive;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class AtOne implements Passive {

    @Override
    public void activate(Player player) {
        if(player.hasPotionEffect(PotionEffectType.WITHER)) {
            player.removePotionEffect(PotionEffectType.WITHER);
        }

        drawCircle(player, 1.2, Particle.SOUL, 30);

        for(Tameable tameable : player.getWorld().getEntitiesByClass(Tameable.class)) {
            if(tameable.isTamed()) {
                if(tameable.getOwner().equals(player)) {
                    tameable.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 30, 1,
                            false, false, false));
                    tameable.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30, 2,
                            false, false, false));
                    drawCircle(tameable, 1.2, Particle.SOUL, 45);

                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 30, 0,
                            false, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30, 1,
                            false, false, false));
                }
            }
        }
    }

    @Override
    public int getCooldown() {
        return 20;
    }

    @Override
    public String getDescription() {
        return "Grants the wearer\na connection to souls.";
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}

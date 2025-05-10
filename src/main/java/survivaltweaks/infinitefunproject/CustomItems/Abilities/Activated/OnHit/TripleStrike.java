package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHit;

import org.bukkit.Bukkit;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.AttackAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class TripleStrike implements AttackAbility {

    @Override
    public void activate(Player player, LivingEntity entity) {
        for(int i = 1; i < 3; i++) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                LightningStrike strike = player.getWorld().strikeLightning(entity.getLocation());
                strike.setCausingPlayer(player);
                strike.setFlashCount(3);
            }, i * 15);
        }
    }

    @Override
    public void activate(Player player, EntityDamageByEntityEvent event) {

    }

    @Override
    public void activate(Player player) {

    }

    @Override
    public int getCooldown() {
        return 40;
    }

    @Override
    public String getDescription() {
        return "Strikes the enemy with\nlightning three times.";
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }

    @Override
    public boolean cooldownModifiable() {
        return true;
    }
}

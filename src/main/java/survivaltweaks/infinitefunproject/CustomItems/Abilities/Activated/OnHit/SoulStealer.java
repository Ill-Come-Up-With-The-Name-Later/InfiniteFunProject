package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHit;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.AttackAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class SoulStealer implements AttackAbility {

    @Override
    public void activate(Player player) {}

    @Override
    public void activate(Player player, LivingEntity entity) {
        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            if(entity.isDead()) {
                player.setHealth(player.getHealth() + 5);
            }
        }, 8);
    }

    @Override
    public void activate(Player player, EntityDamageByEntityEvent event) {

    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Gain back health for every kill.";
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

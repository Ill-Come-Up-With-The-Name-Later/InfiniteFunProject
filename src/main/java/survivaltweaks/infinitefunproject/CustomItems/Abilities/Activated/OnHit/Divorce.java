package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHit;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.AttackAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;
import static survivaltweaks.infinitefunproject.Player.Marriage.MarriageManager.divorce;
import static survivaltweaks.infinitefunproject.Player.Marriage.MarriageManager.getSpouse;

public class Divorce implements AttackAbility {
    @Override
    public void activate(Player player) {}

    @Override
    public boolean cooldownModifiable() {
        return false;
    }

    @Override
    public void activate(Player player, LivingEntity entity) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.CRIT);
                add(Particle.DAMAGE_INDICATOR);
            }
        };

        drawCircle(player.getLocation(), 1.1, particles, 90);

        if(getSpouse(player) == null) {
            player.sendMessage(ChatColor.RED + "You are already single.");
            return;
        }

        if(!getSpouse(entity).equals(player)) {
            player.sendMessage(ChatColor.RED + "You cannot break another marriage.");
            return;
        }

        if(getSpouse(player).equals(entity) || getSpouse(entity).equals(player)) {
            divorce(player);
        }
    }

    @Override
    public void activate(Player player, EntityDamageByEntityEvent event) {
        event.setDamage(0);
        event.setCancelled(true);
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Divorces your spouse.";
    }

    @Override
    public boolean oneTimeUse() {
        return true;
    }
}

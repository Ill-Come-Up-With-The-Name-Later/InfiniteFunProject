package survivaltweaks.infinitefunproject.MonsterAbilities.OnAttackedAbility;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.MonsterAbilities.MonsterAbility;

public class WebAttacker implements MonsterAbility {
    @Override
    public void activate(Monster monster) {

    }

    @Override
    public void activate(Monster monster, LivingEntity entity) {
        Location loc = entity.getLocation();
        monster.getWorld().setBlockData(loc, Material.COBWEB.createBlockData());

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                () -> loc.getWorld().setBlockData(loc, Material.AIR.createBlockData()), 100);
    }

    @Override
    public void activate(EntityDamageByEntityEvent event) {

    }

    @Override
    public String abilityName() {
        return "WebAttacker";
    }
}

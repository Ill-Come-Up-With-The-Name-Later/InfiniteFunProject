package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHit;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.AttackAbility;

public class Cripple implements AttackAbility {

    @Override
    public void activate(Player player, LivingEntity entity) {
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 200, 2, false, false, false));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 2, false, false, false));

        if(!entity.getPassengers().isEmpty()) {
            for(Entity passenger : entity.getPassengers()) {
                entity.removePassenger(passenger);
                passenger.setVelocity(new Vector(0, 2, 0));
            }
        }

        if(entity.getVehicle() != null) {
            Entity vehicle = entity.getVehicle();
            vehicle.removePassenger(entity);

            entity.setVelocity(new Vector(0, 2, 0));
        }

        entity.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, entity.getLocation(), 13, 0.2, 0.2, 0.2, 0.1);
    }

    @Override
    public void activate(Player player, EntityDamageByEntityEvent event) {

    }

    @Override
    public void activate(Player player) {

    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "BANG! And you're dead.";
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

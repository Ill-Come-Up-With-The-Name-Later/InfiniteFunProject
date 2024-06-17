package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHit;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.AttackAbility;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.DamageStackMeta;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class LoomingMisfortune implements AttackAbility {

    @Override
    public void activate(Player player, LivingEntity entity) {
        if(!entity.hasMetadata("DamageStack")) {
            entity.setMetadata("DamageStack", new DamageStackMeta(1, 5));
            return;
        }

        DamageStackMeta meta = (DamageStackMeta) entity.getMetadata("DamageStack").get(0);
        meta.setStacks(meta.getStacks() + 1);

        entity.getWorld().spawnParticle(Particle.SOUL, entity.getLocation().add(0, 0.7, 0), 8, 0.2, 0.2, 0.2, 0.03);

        if(meta.getStacks() >= meta.getMaxStacks()) {
            entity.removeMetadata("DamageStack", InfiniteFunProject.plugin);
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                if(!entity.isDead()) {
                    drawCircle(entity.getLocation(), 3, Particle.SOUL_FIRE_FLAME, 90);
                    entity.damage(25, player);
                }
            }, 10);

            player.setHealth(player.getHealth() + 3);
        }
    }

    @Override
    public void activate(Player player, EntityDamageByEntityEvent event) {

    }

    @Override
    public void activate(Player player) {}

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Applies a mark on anything you hit.\nIf you hit an entity with five marks,\ndeal damage, clear marks, and recover\nhealth.";
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

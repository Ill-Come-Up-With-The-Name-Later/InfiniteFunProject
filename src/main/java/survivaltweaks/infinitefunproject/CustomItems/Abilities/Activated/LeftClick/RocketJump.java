package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class RocketJump implements ActivatedAbility, Listener {

    double blastRadius = 4.5;

    @Override
    public void activate(Player player) {
        Arrow rocket = player.launchProjectile(Arrow.class);
        rocket.setMetadata("Rocket", new RocketMeta());
        rocket.setDamage(0);
    }

    @Override
    public int getCooldown() {
        return 8;
    }

    @Override
    public String getDescription() {
        return "Fires a rocket that launches you.\nDoes zero damage.";
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }

    @EventHandler
    public void onHitGround(ProjectileHitEvent event) {
        Block block = event.getHitBlock();
        Projectile projectile = event.getEntity();

        if(!projectile.hasMetadata("Rocket")) {
            return;
        }

        projectile.remove();

        if(!(projectile.getShooter() instanceof Player)) {
            return;
        }

        if(block == null) {
            return;
        }

        Player player = (Player) projectile.getShooter();
        Location hit = block.getLocation();

        hit.getWorld().spawnParticle(Particle.EXPLOSION, hit, 6, 0.3, 0.1, 0.3, 0.03);

        double dist = hit.distance(player.getLocation());

        if(dist > blastRadius) {
            return;
        }

        double force = (blastRadius - (dist / blastRadius)) * 0.33;
        Vector launch = player.getEyeLocation().getDirection().multiply(-1).multiply(force);

        launch.setY(launch.getY() * 0.25);
        launch.setX(launch.getX() * force);
        launch.setZ(launch.getZ() * force);

        if(player.getPitch() > 70) {
            launch.setY(launch.getY() * 2.4);
        }

        if(!player.isOnGround()) {
            launch.multiply(1.1);
        }

        player.setVelocity(player.getVelocity().multiply(5).add(launch));
    }
}

class RocketMeta implements MetadataValue {

    @Override
    public @Nullable Object value() {
        return "Rocket";
    }

    @Override
    public int asInt() {
        return 0;
    }

    @Override
    public float asFloat() {
        return 0;
    }

    @Override
    public double asDouble() {
        return 0;
    }

    @Override
    public long asLong() {
        return 0;
    }

    @Override
    public short asShort() {
        return 0;
    }

    @Override
    public byte asByte() {
        return 0;
    }

    @Override
    public boolean asBoolean() {
        return false;
    }

    @Override
    public @NotNull String asString() {
        return "Rocket";
    }

    @Override
    public @Nullable Plugin getOwningPlugin() {
        return InfiniteFunProject.plugin;
    }

    @Override
    public void invalidate() {

    }
}

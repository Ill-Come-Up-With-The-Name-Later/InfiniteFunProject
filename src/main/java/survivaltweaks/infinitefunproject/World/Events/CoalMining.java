package survivaltweaks.infinitefunproject.World.Events;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.List;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class CoalMining implements Listener {

    @EventHandler
    public void onMine(BlockBreakEvent event) {
        Block block = event.getBlock();

        if(!(block.getType() == Material.COAL_ORE || block.getType() == Material.DEEPSLATE_COAL_ORE
                || block.getType() == Material.COAL_BLOCK)) {
            return;
        }

        block.getWorld().spawnParticle(Particle.LARGE_SMOKE, block.getLocation().add(0, 0.4, 0), 20,
                0.7, 0.4, 0.7, 0.01);

        if(new Random().nextInt(0, 100) == 1) {
            @NotNull List<Entity> entities = circularNearbyEntities(block.getLocation(), 5);

            for(Entity entity : entities) {
                if(entity instanceof LivingEntity) {
                    LivingEntity living = (LivingEntity) entity;

                    drawCircle(living.getLocation(), 1.4, Particle.SMOKE, 90);
                    drawCircle(living.getLocation(), 1.4, Particle.DAMAGE_INDICATOR, 90);

                    int timeUntilDeath = new Random().nextInt(300, 1200);

                    living.sendMessage(ChatColor.RED + "You have developed super lung cancer!\nYou will begin to die in " +
                            String.format("%.2f", (float) timeUntilDeath / 20) + " seconds.");

                    living.setMetadata("Cancer", new CancerMeta());

                    living.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,
                            timeUntilDeath, 1, false, false, false));
                    living.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE,
                            timeUntilDeath, 0, false, false, false));

                    Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                        if(living.hasMetadata("Cancer")) {
                            living.setHealth(5);
                            living.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,
                                    1200, 5, false, false, false));
                            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                                    () -> living.removeMetadata("Cancer", InfiniteFunProject.plugin), 2);
                        }
                    }, timeUntilDeath);
                }
            }
        }
    }
}

class CancerMeta implements MetadataValue {

    @Nullable
    @Override
    public Object value() {
        return "Cancer";
    }

    @Override
    public int asInt() {
        return 37;
    }

    @Override
    public float asFloat() {
        return 37;
    }

    @Override
    public double asDouble() {
        return 37;
    }

    @Override
    public long asLong() {
        return 37;
    }

    @Override
    public short asShort() {
        return 37;
    }

    @Override
    public byte asByte() {
        return 37;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @NotNull
    @Override
    public String asString() {
        return "Cancer";
    }

    @Nullable
    @Override
    public Plugin getOwningPlugin() {
        return InfiniteFunProject.plugin;
    }

    @Override
    public void invalidate() {

    }
}

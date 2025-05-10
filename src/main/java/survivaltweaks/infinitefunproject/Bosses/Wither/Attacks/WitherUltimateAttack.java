package survivaltweaks.infinitefunproject.Bosses.Wither.Attacks;

import org.bukkit.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.Bosses.Wither.InitWither.setWitherSpawn;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawExpandingCircle;

public class WitherUltimateAttack implements Listener {

    /**
     * Wither ultimate attack loop
     *
     * @param event: Entity spawn event
     */
    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Wither) {
            Wither wither = (Wither) entity;

            new BukkitRunnable() {
                @Override
                public void run() {
                    if(wither.isDead()) {
                        cancel();
                        return;
                    }
                    heartStopper(wither);
                }
            }.runTaskTimer(InfiniteFunProject.plugin, 2750, 2750);
        }
    }

    /**
     * Wither ultimate attack
     *
     * @param wither: The attacking Wither
     */
    public void heartStopper(Wither wither) {
        Bukkit.spigot().broadcast(new TextComponent(ChatColor.DARK_RED + "The beating of all hearts ceases..."));

        for(World world : Bukkit.getWorlds()) {
            world.setGameRule(GameRule.NATURAL_REGENERATION, false);
        }

        drawExpandingCircle(wither, 2, 19, 1, 15, Particle.DAMAGE_INDICATOR);

        for(int i = 1; i < 20; i++) {
            int finalI = i;
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                for(Entity entity : InfiniteFunProject.circularNearbyEntities(wither, finalI + 1)) {
                    if(entity instanceof LivingEntity) {
                        LivingEntity livingEntity = (LivingEntity) entity;
                        livingEntity.damage(7, wither);
                    }
                }
            }, i * 15);
        }

        for(Player player : Bukkit.getOnlinePlayers()) {
            player.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, player.getLocation().add(new Vector(0, 1, 0)),
                    6, 0.2, 0.2, 0.2, 0.1);
            player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 3, false, false, true));

            for(int i = 0; i < 2; i++) {
                WitherSkeleton witherSkeleton = player.getWorld().spawn(player.getLocation().add(0, 4, 0), WitherSkeleton.class);
                setWitherSpawn(witherSkeleton);
            }
        }

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            for(World world : Bukkit.getWorlds()) {
                world.setGameRule(GameRule.NATURAL_REGENERATION, true);
            }
        }, 600);
    }
}

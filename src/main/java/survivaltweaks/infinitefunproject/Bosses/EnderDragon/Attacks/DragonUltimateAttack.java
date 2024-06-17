package survivaltweaks.infinitefunproject.Bosses.EnderDragon.Attacks;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class DragonUltimateAttack implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof EnderDragon) {
            EnderDragon dragon = (EnderDragon) entity;

            new BukkitRunnable() {
                @Override
                public void run() {
                    if(dragon.isDead() || dragon.getPhase().equals(EnderDragon.Phase.DYING)) {
                        cancel();
                        return;
                    }
                    voidCall(dragon);
                }
            }.runTaskTimer(InfiniteFunProject.plugin, 3800, 3800);
        }
    }

    public void voidCall(EnderDragon dragon) {
        Bukkit.spigot().broadcast(new TextComponent(ChatColor.LIGHT_PURPLE + "The dragon's call echoes into the void beyond..."));

        dragon.setInvulnerable(true);
        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> dragon.setInvulnerable(false), 155);

        for(int i = 1; i <= 20; i++) {
            int finalI = i;
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                drawCircle(dragon.getLocation(), finalI + 10, Particle.DRAGON_BREATH, 180);
                drawCircle(dragon.getLocation(), finalI + 10, Particle.SPELL_WITCH, 180);
                drawCircle(dragon.getLocation(), finalI + 10, Particle.CRIT_MAGIC, 180);

                for(Entity entity : InfiniteFunProject.circularNearbyEntities(dragon, finalI + 10)) {
                    if(entity instanceof LivingEntity) {
                        LivingEntity livingEntity = (LivingEntity) entity;
                        livingEntity.damage(7, dragon);
                    }
                }
            }, i * 15);
        }

        for(Player player : Bukkit.getOnlinePlayers()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 120, 0, false, false, true));
            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 180, 1, false, false, true));

            Location playerLoc = player.getLocation();

            player.getWorld().spawnParticle(Particle.DRAGON_BREATH, playerLoc.add(0, 0.3, 0), 30, 0.3, 0.1, 0.3, 0.04);
            player.getWorld().spawnParticle(Particle.SPELL_WITCH, playerLoc.add(0, 0.3, 0), 30, 0.3, 0.1, 0.3, 0.04);
            player.getWorld().spawnParticle(Particle.SMOKE_NORMAL, playerLoc.add(0, 0.3, 0), 20, 0.3, 0.1, 0.3, 0.04);

            for(int i = 0; i < 2; i++) {
                Enderman enderman = player.getWorld().spawn(player.getLocation(), Enderman.class);
                enderman.setTarget(player);
            }
        }

        int i = 1;
        for(Player target : dragon.getWorld().getPlayers()) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                DragonFireball fireball = dragon.getWorld().spawn(dragon.getEyeLocation().subtract(0, 15, 0), DragonFireball.class);

                fireball.setShooter(dragon);
                fireball.setIsIncendiary(true);
                fireball.setYield(3);

                Vector velocity = target.getLocation().toVector().subtract(fireball.getLocation().toVector());
                fireball.setDirection(velocity);
                fireball.setVelocity(velocity.normalize());
            }, i * 6L);
            i++;
        }
    }
}

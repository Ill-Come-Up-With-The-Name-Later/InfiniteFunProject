package survivaltweaks.infinitefunproject.Combat.Shield;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class ShieldRework implements Listener {

    private static final ArrayList<Player> shielded = new ArrayList<>();

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        ItemStack offHand =  player.getInventory().getItemInOffHand();
        Action action = event.getAction();

        boolean rightClick = action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK);

        if(player.hasCooldown(Material.SHIELD) &&
                (mainHand.getType().equals(Material.SHIELD) || offHand.getType().equals(Material.SHIELD)) && rightClick) {
            event.setCancelled(true);
            return;
        }

        if(mainHand.getType().equals(Material.SHIELD) || offHand.getType().equals(Material.SHIELD) && rightClick) {
            player.setCooldown(Material.SHIELD, 140);
            player.sendMessage(ChatColor.GREEN + "You are now shielded.");
            shielded.add(player);

            giveProjectileShield(player, 110, new ArrayList<>(), new ArrayList<>(), 5);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 2, true, false, true));

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                shielded.remove(player);
                player.sendMessage(ChatColor.RED + "You are no longer shielded.");
            }, 110);
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();

        if(damaged instanceof Player) {
            Player player = (Player) damaged;

            if(isShielding(player)) {
                event.setDamage(event.getDamage() / 3);
                player.setVelocity(new Vector(0, 0 , 0));

                drawExpandingCircle(player.getLocation(), 1, 4, 2, 2, Particle.ENCHANTED_HIT);
                drawExpandingCircle(player.getLocation().add(new Vector(0, 1, 0)), 1,
                        4, 2, 2, Particle.ENCHANTED_HIT);
                drawExpandingCircle(player.getLocation().add(new Vector(0, 2, 0)), 1,
                        4, 2, 2, Particle.ENCHANTED_HIT);

                if(distanceBetween(player.getLocation().toVector(), damager.getLocation().toVector()) <= 5
                        && damager instanceof LivingEntity) {
                    ((LivingEntity) damager).damage(event.getDamage() / 4, player);
                }
            }
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Player) {
            Player player = (Player) entity;

            shielded.remove(player);
            player.setCooldown(Material.SHIELD, 0);
        }
    }

    public static boolean isShielding(Player player) {
        return shielded.contains(player);
    }
}

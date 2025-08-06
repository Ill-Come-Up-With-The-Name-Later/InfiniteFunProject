package survivaltweaks.infinitefunproject.EventListeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.fixCaps;

public class DisplayHP implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            Entity entity = event.getEntity();

            if(entity instanceof Player) {
                return;
            }

            if(!(entity instanceof LivingEntity)) {
                return;
            }

            LivingEntity livingEntity = (LivingEntity) entity;

            int health = (int) livingEntity.getHealth();
            int maxHealth = (int) livingEntity.getAttribute(Attribute.MAX_HEALTH).getBaseValue();
            String name = livingEntity.getCustomName();

            if(name == null) {
                name = "";
            }

            String[] splitName = name.split("\\|");
            if(splitName.length > 1) {
                name = splitName[0].substring(0, splitName[0].length() - 1);
            }

            if(name.isEmpty()) {
                livingEntity.setCustomName(ChatColor.YELLOW  + fixCaps(livingEntity.getType().toString()) +
                        ChatColor.WHITE + " | " + healthDisplay(health, maxHealth));
            } else {
                livingEntity.setCustomName(name + ChatColor.WHITE + " | " + healthDisplay(health, maxHealth));
            }

            livingEntity.setCustomNameVisible(true);

            String finalName = name;
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                if(!livingEntity.isDead() && livingEntity.getNoDamageTicks() <= 3) {
                    if(finalName.isEmpty()) {
                        livingEntity.setCustomName("");
                    } else {
                        livingEntity.setCustomName(finalName);
                    }

                    livingEntity.setCustomNameVisible(false);
                }
            }, 50);
        }, 1);
    }

    private String healthDisplay(int health, int maxHealth) {
        if(health >= maxHealth / 2) {
            return "" + ChatColor.GREEN + health + ChatColor.WHITE + "/" + ChatColor.GREEN + maxHealth;
        } else if(health <= maxHealth / 2 && health >= maxHealth / 4) {
            return "" + ChatColor.YELLOW + health + ChatColor.WHITE + "/" + ChatColor.GREEN + maxHealth;
        } else {
            return "" + ChatColor.RED + health + ChatColor.WHITE + "/" + ChatColor.GREEN + maxHealth;
        }
    }
}

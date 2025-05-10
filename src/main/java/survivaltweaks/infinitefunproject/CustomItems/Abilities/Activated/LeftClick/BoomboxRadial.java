package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class BoomboxRadial implements ActivatedAbility {
    int radius = 4;

    @Override
    public void activate(Player player) {
        drawCircle(player.getLocation(), radius, Particle.NOTE, 90);
        drawCircle(player.getLocation(), radius, Particle.ENCHANTED_HIT, 90);
        drawCircle(player.getLocation(), radius, Particle.CRIT, 90);

        ArrayList<Entity> nearby = (ArrayList<Entity>) circularNearbyEntities(player, radius);

        for(Entity entity : nearby) {
            if(entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entity;
                living.damage(28 + (getCharges(player) * 3), player);
            }
        }

        setCharges(player, 0);
    }

    public static int getCharges(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();

        if(item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if(meta.hasLore()) {
                ArrayList<String> lore = (ArrayList<String>) meta.getLore();

                for(String str : lore) {
                    if(str.contains(ChatColor.GRAY + "Charges: ")) {
                        int charges = Integer.parseInt(str.substring(str.length() - 1));

                        return charges;
                    }
                }
            }
        }

        return 0;
    }

    public static void setCharges(Player player, int charges) {
        ItemStack item = player.getInventory().getItemInMainHand();
        charges = Math.max(Math.min(charges, 4), 0);

        if(item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if(meta.hasLore()) {
                ArrayList<String> lore = (ArrayList<String>) meta.getLore();

                int chargeIndex = 0;

                for(String str : lore) {
                    if(str.contains(ChatColor.GRAY + "Charges: ")) {
                        chargeIndex = lore.indexOf(str);
                        break;
                    }
                }

                lore.set(chargeIndex, ChatColor.GRAY + "Charges: " + ChatColor.YELLOW + charges);
                meta.setLore(lore);
                item.setItemMeta(meta);
            }
        }
    }

    @Override
    public int getCooldown() {
        return 50;
    }

    @Override
    public String getDescription() {
        return "Emits a sound so loud, it can\ndamage enemies.";
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }

    @Override
    public boolean cooldownModifiable() {
        return true;
    }
}

package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.fixCaps;

public class Bleach implements ActivatedAbility, Listener {
    @Override
    public void activate(Player player) {
        ArrayList<PotionEffectType> positiveEffects = new ArrayList<>();
        positiveEffects.add(PotionEffectType.FIRE_RESISTANCE);
        positiveEffects.add(PotionEffectType.RESISTANCE);
        positiveEffects.add(PotionEffectType.DOLPHINS_GRACE);
        positiveEffects.add(PotionEffectType.HERO_OF_THE_VILLAGE);
        positiveEffects.add(PotionEffectType.STRENGTH);
        positiveEffects.add(PotionEffectType.INVISIBILITY);
        positiveEffects.add(PotionEffectType.LUCK);
        positiveEffects.add(PotionEffectType.SPEED);
        positiveEffects.add(PotionEffectType.JUMP_BOOST);
        positiveEffects.add(PotionEffectType.REGENERATION);
        positiveEffects.add(PotionEffectType.ABSORPTION);
        positiveEffects.add(PotionEffectType.HEALTH_BOOST);
        positiveEffects.add(PotionEffectType.CONDUIT_POWER);
        positiveEffects.add(PotionEffectType.NIGHT_VISION);
        positiveEffects.add(PotionEffectType.WATER_BREATHING);

        for(PotionEffect effect : player.getActivePotionEffects()) {
            if(positiveEffects.contains(effect.getType())) {
                player.removePotionEffect(effect.getType());
            }
        }

        player.damage(8);
    }

    @Override
    public int getCooldown() {
        return 600;
    }

    @Override
    public String getDescription() {
        return "Clears all positive effects.";
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
    public void applyToItem(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        ItemStack selected = player.getItemOnCursor();

        if(selected.equals(ItemManager.bleach)) {
            if(item.getType().isEdible()) {
                ItemMeta modified = item.getItemMeta();
                modified.setDisplayName(ChatColor.GRAY + fixCaps(item.getType().toString()));

                item.setItemMeta(modified);
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onConsumption(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if(item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();

            if(meta.getDisplayName().equalsIgnoreCase(ChatColor.GRAY + fixCaps(item.getType().toString()))) {
                player.setHealth(player.getHealth() / 4);
                player.setAbsorptionAmount(player.getAbsorptionAmount() / 4);
            }
        }
    }
}

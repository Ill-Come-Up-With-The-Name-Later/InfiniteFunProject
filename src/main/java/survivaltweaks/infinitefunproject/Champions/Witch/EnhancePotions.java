package survivaltweaks.infinitefunproject.Champions.Witch;

import org.bukkit.entity.Projectile;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.Witch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

public class EnhancePotions implements Listener {

    @EventHandler
    public void onPotionThrow(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();

        if(projectile.getShooter() instanceof Witch) {
            Witch witch = (Witch) projectile.getShooter();

            if(witch.hasMetadata("Champion")) {
                ThrownPotion potion = (ThrownPotion) projectile;
                potion.setItem(enhancePotion(potion.getItem().clone()));
            }
        }
    }

    public static ItemStack enhancePotion(ItemStack potion) {
        PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();

        for(PotionEffect effect : potionMeta.getCustomEffects()) {
            potionMeta.removeCustomEffect(effect.getType());
            potionMeta.addCustomEffect(effect.getType().createEffect(effect.getDuration(), 2), false);
        }

        potion.setItemMeta(potionMeta);
        return potion;
    }
}

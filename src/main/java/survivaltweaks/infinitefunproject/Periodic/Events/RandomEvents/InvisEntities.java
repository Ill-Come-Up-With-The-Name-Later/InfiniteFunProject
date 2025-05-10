package survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class InvisEntities implements RandomEvent {
    @Override
    public void trigger() {
        List<World> worlds = Bukkit.getWorlds();

        for(World w : worlds) {
            for(Entity e : w.getEntities()) {
                if(e instanceof LivingEntity && !(e instanceof Player)) {
                    ((LivingEntity) e).addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(200, 0));
                }
                if(e instanceof Player) {
                    Player player = ((Player) e).getPlayer();
                    player.sendMessage(ChatColor.GRAY + "Huh, where did they go?");
                }
            }
        }
    }
}

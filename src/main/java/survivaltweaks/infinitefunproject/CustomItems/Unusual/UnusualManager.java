package survivaltweaks.infinitefunproject.CustomItems.Unusual;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.Optional;

import static survivaltweaks.infinitefunproject.CustomItems.Unusual.Unusual.hasUnusual;

public class UnusualManager {

    public static ArrayList<Material> allowedItems = new ArrayList<>();

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new Unusualifier(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new WeaponUnusual(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new ProjectileUnusuals(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new SpecialUnusualifier(), InfiniteFunProject.plugin);

        allowedItems.add(Material.LEATHER_HELMET);
        allowedItems.add(Material.GOLDEN_HELMET);
        allowedItems.add(Material.CHAINMAIL_HELMET);
        allowedItems.add(Material.IRON_HELMET);
        allowedItems.add(Material.DIAMOND_HELMET);
        allowedItems.add(Material.NETHERITE_HELMET);
        allowedItems.add(Material.TURTLE_HELMET);
        allowedItems.add(Material.CARVED_PUMPKIN);
        allowedItems.add(Material.SKELETON_SKULL);
        allowedItems.add(Material.WITHER_SKELETON_SKULL);
        allowedItems.add(Material.ZOMBIE_HEAD);
        allowedItems.add(Material.PLAYER_HEAD);
        allowedItems.add(Material.PIGLIN_HEAD);

        allowedItems.add(Material.LEATHER_BOOTS);
        allowedItems.add(Material.GOLDEN_BOOTS);
        allowedItems.add(Material.CHAINMAIL_BOOTS);
        allowedItems.add(Material.IRON_BOOTS);
        allowedItems.add(Material.DIAMOND_BOOTS);
        allowedItems.add(Material.NETHERITE_BOOTS);

        allowedItems.add(Material.WOODEN_SWORD);
        allowedItems.add(Material.STONE_SWORD);
        allowedItems.add(Material.GOLDEN_SWORD);
        allowedItems.add(Material.IRON_SWORD);
        allowedItems.add(Material.DIAMOND_SWORD);
        allowedItems.add(Material.NETHERITE_SWORD);

        allowedItems.add(Material.BOW);
        allowedItems.add(Material.TRIDENT);
        allowedItems.add(Material.CROSSBOW);
        allowedItems.add(Material.MACE);

        allowedItems.add(Material.WOODEN_AXE);
        allowedItems.add(Material.STONE_AXE);
        allowedItems.add(Material.GOLDEN_AXE);
        allowedItems.add(Material.IRON_AXE);
        allowedItems.add(Material.DIAMOND_AXE);
        allowedItems.add(Material.NETHERITE_AXE);

        new BukkitRunnable() {

            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    showEffect(player);
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 2, 2);
    }

    public static void showEffect(Player player) {
        ItemStack[] armor = player.getInventory().getArmorContents();
        int unusualLine = -1;

        for (int i = 0; i < armor.length; i++) {
            ItemStack item = armor[i];

            if (item == null || item.getItemMeta().getLore() == null) {
                continue;
            }

            if (!hasUnusual(item)) {
                continue;
            }

            unusualLine = unusualLine(item);

            if (unusualLine == -1) {
                continue;
            }

            String effect = item.getItemMeta().getLore().get(unusualLine).substring(24);
            Optional<Unusual> unusualOptional = Optional.ofNullable(Unusual.getByName(effect));

            if (unusualOptional.isPresent()) {
                Unusual unusual = unusualOptional.get();

                for(Particle particle : unusual.getParticles()) {
                    Location spawnLoc = player.getEyeLocation();
                    if(player.isGliding() || player.isSleeping()) {
                        spawnLoc.setY(spawnLoc.getY() + 0.15);
                    } else {
                        spawnLoc.setY(spawnLoc.getY() + 0.75);
                    }

                    if(i == 3) {
                        if((player.getLocation().getPitch() >= -40) && !player.getGameMode().equals(GameMode.SPECTATOR)) {
                            if(particle == Particle.DUST) {
                                if(unusual.getDustOptions() != null) {
                                    player.getWorld().spawnParticle(particle, spawnLoc, 12, 0.3, 0.3, 0.3, 0.03,
                                            unusual.getDustOptions(), true);
                                    continue;
                                }
                            }

                            player.getWorld().spawnParticle(particle, spawnLoc, 12, 0.3, 0.3, 0.3, 0.03, null, true);
                        }
                    }

                    if(i == 0) {
                        if(particle == Particle.DUST) {
                            if(unusual.getDustOptions() != null) {
                                player.getWorld().spawnParticle(particle, player.getLocation(), 12, 0.3, 0.3, 0.3, 0.03,
                                        unusual.getDustOptions(), true);
                                continue;
                            }
                        }

                        player.getWorld().spawnParticle(particle, player.getLocation(), 12, 0.3, 0.3, 0.3, 0.03, null, true);
                    }
                }
            }
        }
    }

    public static int unusualLine(ItemStack item) {
        if(hasUnusual(item)) {
            for (String str : item.getItemMeta().getLore()) {
                if (str.contains("* Unusual Effect")) {
                    return item.getItemMeta().getLore().indexOf(str);
                }
            }
        }

        return -1;
    }
}

package survivaltweaks.infinitefunproject.World.Village;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.createTrade;

public class BlackMarketTrader implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity spawned = event.getEntity();

        if(spawned instanceof Villager) {
            if(new Random().nextInt(0, 150) == 1) {
                Villager villager = (Villager) spawned;

                if(villager.hasMetadata("SuperTrader")) {
                    return;
                }

                villager.setAdult();

                villager.setCustomName(ChatColor.GOLD + "Black Market Salesman");
                villager.setCustomNameVisible(true);

                villager.setProfession(Villager.Profession.CLERIC);
                villager.setVillagerLevel(5);
                villager.setVillagerType(Villager.Type.SWAMP);

                villager.setMetadata("BlackMarket", new BlackMarketMeta());
            }
        }
    }

    @EventHandler
    public void onOpen(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        if(entity instanceof Villager) {
            Villager villager = (Villager) entity;

            if(villager.hasMetadata("BlackMarket")) {
                event.setCancelled(true);

                player.openMerchant(getSpecialDeals(), true);
            }
        }
    }

    public Merchant getSpecialDeals() {
        Merchant merchant = Bukkit.createMerchant(ChatColor.GOLD + "Black Market Salesman");

        ArrayList<MerchantRecipe> trades = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            trades.add(createTrade(
                    ItemManager.customItems.get(new Random().nextInt(0, ItemManager.customItems.size())), 12,
                    new ArrayList<>() {{
                        add(new ItemStack(Material.EMERALD_BLOCK, new Random().nextInt(10, 50)));
                    }}
            ));
        }

        merchant.setRecipes(trades);
        return merchant;
    }
}

class BlackMarketMeta implements MetadataValue {

    @Override
    public @Nullable Object value() {
        return "BlackMarket";
    }

    @Override
    public int asInt() {
        return 0;
    }

    @Override
    public float asFloat() {
        return 0;
    }

    @Override
    public double asDouble() {
        return 0;
    }

    @Override
    public long asLong() {
        return 0;
    }

    @Override
    public short asShort() {
        return 0;
    }

    @Override
    public byte asByte() {
        return 0;
    }

    @Override
    public boolean asBoolean() {
        return false;
    }

    @Override
    public @NotNull String asString() {
        return "BlackMarket";
    }

    @Override
    public @Nullable Plugin getOwningPlugin() {
        return InfiniteFunProject.plugin;
    }

    @Override
    public void invalidate() {

    }
}
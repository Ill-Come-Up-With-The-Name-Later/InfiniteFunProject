package survivaltweaks.infinitefunproject.World.Village;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.fixCaps;

public class SuperTrader implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity spawned = event.getEntity();

        if(spawned instanceof Villager) {
            if(new Random().nextInt(0, 75) == 1) {
                Villager villager = (Villager) spawned;

                if(villager.hasMetadata("BlackMarket")) {
                    return;
                }

                villager.setAdult();
                villager.setProfession(Villager.Profession.values()[new Random().nextInt(1, Villager.Profession.values().length)]);

                if(villager.getProfession() == Villager.Profession.NONE || villager.getProfession() == Villager.Profession.NITWIT) {
                    return;
                }

                villager.setCustomName(ChatColor.GREEN + "Super " + fixCaps(villager.getProfession().toString()));
                villager.setCustomNameVisible(true);

                villager.increaseLevel(4);
                villager.addTrades(8);

                villager.setMetadata("SuperTrader", new SuperTraderMeta());
            }
        }
    }
}

class SuperTraderMeta implements MetadataValue {

    @Override
    public @Nullable Object value() {
        return "SuperTrader";
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
        return "SuperTrader";
    }

    @Override
    public @Nullable Plugin getOwningPlugin() {
        return InfiniteFunProject.plugin;
    }

    @Override
    public void invalidate() {

    }
}
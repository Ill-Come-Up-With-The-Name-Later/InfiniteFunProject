package survivaltweaks.infinitefunproject.CustomItems.Metadata;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

public class SeekingMetadata implements MetadataValue {
    int strength;
    ArrayList<EntityType> targets;
    int projLifespan;

    public SeekingMetadata(int strength, int lifespan, ArrayList<EntityType> targets) {
        this.strength = strength;
        this.projLifespan = lifespan;
        this.targets = targets;
    }

    @Nullable
    @Override
    public Object value() {
        return "Seeking";
    }

    @Override
    public int asInt() {
        return 109;
    }

    @Override
    public float asFloat() {
        return 109;
    }

    @Override
    public double asDouble() {
        return 109;
    }

    @Override
    public long asLong() {
        return 109;
    }

    @Override
    public short asShort() {
        return 109;
    }

    @Override
    public byte asByte() {
        return 109;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @NotNull
    @Override
    public String asString() {
        return "Seeking";
    }

    @Nullable
    @Override
    public Plugin getOwningPlugin() {
        return InfiniteFunProject.plugin;
    }

    @Override
    public void invalidate() {

    }

    public ArrayList<EntityType> getTargets() {
        return targets;
    }

    public int getStrength() {
        return strength;
    }

    public int getProjLifespan() {
        return projLifespan;
    }
}

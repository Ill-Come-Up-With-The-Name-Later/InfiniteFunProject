package survivaltweaks.infinitefunproject.CustomItems.Metadata;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class BounceProjectiles implements MetadataValue {

    private int bounces;
    private int maxBounces;
    private int lifeSpan;

    public BounceProjectiles(int maxBounces, int maxLifeSpan) {
        this.maxBounces = maxBounces;
        this.lifeSpan = maxLifeSpan;
        this.bounces = 0;
    }

    public int getBounces() {
        return bounces;
    }

    public int getMaxBounces() {
        return maxBounces;
    }

    public int getLifeSpan() {
        return lifeSpan;
    }

    public void setBounces(int bounces) {
        this.bounces = bounces;
    }

    @Override
    public @Nullable Object value() {
        return "Bouncy";
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
        return "Bouncy";
    }

    @Override
    public @Nullable Plugin getOwningPlugin() {
        return InfiniteFunProject.plugin;
    }

    @Override
    public void invalidate() {

    }
}

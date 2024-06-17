package survivaltweaks.infinitefunproject.CustomItems.Metadata;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class ExplosiveMeta implements MetadataValue {
    public float explosionPower;
    public boolean destroyBlocks;
    public boolean causeFire;

    public ExplosiveMeta(float explosionPower, boolean destroyBlocks, boolean causeFire) {
        this.explosionPower = explosionPower;
        this.destroyBlocks = destroyBlocks;
        this.causeFire = causeFire;
    }

    @Nullable
    @Override
    public Object value() {
        return "Explosive";
    }

    @Override
    public int asInt() {
        return 555;
    }

    @Override
    public float asFloat() {
        return 555;
    }

    @Override
    public double asDouble() {
        return 555;
    }

    @Override
    public long asLong() {
        return 555;
    }

    @Override
    public short asShort() {
        return 555;
    }

    @Override
    public byte asByte() {
        return 55;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @NotNull
    @Override
    public String asString() {
        return "Explosive";
    }

    @Nullable
    @Override
    public Plugin getOwningPlugin() {
        return InfiniteFunProject.plugin;
    }

    @Override
    public void invalidate() {

    }

    public float getExplosionPower() {
        return explosionPower;
    }

    public boolean isCauseFire() { return causeFire; }

    public boolean isDestroyBlocks() { return destroyBlocks; }
}

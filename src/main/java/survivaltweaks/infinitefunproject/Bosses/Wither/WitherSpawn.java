package survivaltweaks.infinitefunproject.Bosses.Wither;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

/**
 * Sets an enemy as the
 * wither's to control
 */
public class WitherSpawn implements MetadataValue {

    @Override
    public Object value() {
        return "WitherSpawn";
    }

    @Override
    public int asInt() {
        return 11;
    }

    @Override
    public float asFloat() {
        return 11;
    }

    @Override
    public double asDouble() {
        return 11;
    }

    @Override
    public long asLong() {
        return 11;
    }

    @Override
    public short asShort() {
        return 11;
    }

    @Override
    public byte asByte() {
        return 11;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @Override
    public String asString() {
        return "WitherSpawn";
    }

    @Override
    public Plugin getOwningPlugin() {
        return InfiniteFunProject.plugin;
    }

    @Override
    public void invalidate() {

    }
}

package survivaltweaks.infinitefunproject.Player.Events;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class PlayerPlacedCrystal implements MetadataValue {

    @Override
    public Object value() {
        return "PlayerPlace";
    }

    @Override
    public int asInt() {
        return 23;
    }

    @Override
    public float asFloat() {
        return 23;
    }

    @Override
    public double asDouble() {
        return 23;
    }

    @Override
    public long asLong() {
        return 23;
    }

    @Override
    public short asShort() {
        return 23;
    }

    @Override
    public byte asByte() {
        return 23;
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @Override
    public String asString() {
        return "PlayerPlace";
    }

    @Override
    public Plugin getOwningPlugin() {
        return InfiniteFunProject.plugin;
    }

    @Override
    public void invalidate() {

    }
}

package survivaltweaks.infinitefunproject.Player.ChallengeMode;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.Managers.*;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata.Listeners.*;

public enum ChallengeMode {

    ONE_HIT("one_hit", "OneHitMode", new OneHitModeManager(), new OneHitModeListener(), Material.IRON_SWORD,
            new OneHitModeManager().description()),
    NO_ARMOR("no_armor", "NoArmor", new NoArmorManager(), new NoArmorListener(), Material.IRON_CHESTPLATE,
            new NoArmorManager().description()),
    HYDROPHOBIC("hydrophobic", "Hydrophobic", new HydrophobicManager(), new HydrophobicListener(), Material.SPONGE,
            new HydrophobicManager().description()),
    BUILDING_BLOCK("build_block", "BuildBlock", new BuildBlockManager(), new BuildBlockListener(), Material.BARRIER,
            new BuildBlockManager().description()),
    HOARDER("hoarder", "Hoarder", new HoardingManager(), new HoardingListener(), Material.RAW_GOLD_BLOCK,
            new HoardingManager().description()),
    GROUNDED("grounded", "Grounded", new GroundedManager(), new GroundedListener(), Material.GRASS_BLOCK,
            new GroundedManager().description()),
    MINER_FATIGUE("mining_fatigue", "MineFatigue", new MineFatigueManager(), new MineFatigueListener(), Material.GOLDEN_PICKAXE,
            new MineFatigueManager().description()),
    COME_TOGETHER("come_together", "ComeTogether", new TogetherManager(), new TogetherListener(), Material.AXOLOTL_BUCKET,
            new TogetherManager().description()),
    SUN_KING("sun_king", "SunKing", new SunKingManager(), new SunKingListener(), Material.SHROOMLIGHT,
            new SunKingManager().description()),
    MR_MOONLIGHT("mr_moonlight", "MrMoonlight", new MoonlightManager(), new MoonlightListener(), Material.END_STONE,
            new MoonlightManager().description()),
    I_ME_MINE("i_me_mine", "IMeMine", new IMeMineManager(), new IMeMineListener(), Material.ENDER_EYE,
            new IMeMineManager().description()),
    CLEAN_ITEM("clean_items", "CleanItems", new CleanItemManager(), new CleanItemListener(), Material.BOOK,
            new CleanItemManager().description()),
    THIN_LINE("thin_line", "ThinLine", new ThinLineManager(), new ThinLineListener(), Material.GOLDEN_APPLE,
            new ThinLineManager().description()),
    BAD_ARM("bad_arm", "BadArm", new BadArmManager(), new BadArmListener(), Material.SNOWBALL,
            new BadArmManager().description()),
    SOBER("sober", "Sober", new SoberManager(), new SoberListener(), Material.GLASS_BOTTLE,
            new SoberManager().description()),
    ;

    private final String name;
    private final String dataString;
    private final ModeManager manager;
    private final Listener modeListener;
    private final Material icon;
    private final String description;

    ChallengeMode(String name, String dataString, ModeManager manager, Listener modeListener, Material icon, String description) {
        this.name = name;
        this.dataString = dataString;
        this.manager = manager;
        this.modeListener = modeListener;
        this.icon = icon;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDataString() {
        return dataString;
    }

    public ModeManager getManager() {
        return manager;
    }

    public Material getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    public Listener getModeListener() {
        return modeListener;
    }
}

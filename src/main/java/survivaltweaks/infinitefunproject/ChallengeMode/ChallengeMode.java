package survivaltweaks.infinitefunproject.ChallengeMode;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import survivaltweaks.infinitefunproject.ChallengeMode.Managers.*;
import survivaltweaks.infinitefunproject.ChallengeMode.Listeners.*;

public enum ChallengeMode {

    ONE_HIT("one_hit", "One Hit", new OneHitModeManager(), new OneHitModeListener(), Material.IRON_SWORD,
            new OneHitModeManager().description()),
    NO_ARMOR("no_armor", "No Armor", new NoArmorManager(), new NoArmorListener(), Material.IRON_CHESTPLATE,
            new NoArmorManager().description()),
    HYDROPHOBIC("hydrophobic", "Hydrophobic", new HydrophobicManager(), new HydrophobicListener(), Material.SPONGE,
            new HydrophobicManager().description()),
    BUILDING_BLOCK("build_block", "Building Block", new BuildBlockManager(), new BuildBlockListener(), Material.BARRIER,
            new BuildBlockManager().description()),
    HOARDER("hoarder", "Hoarder", new HoardingManager(), new HoardingListener(), Material.RAW_GOLD_BLOCK,
            new HoardingManager().description()),
    GROUNDED("grounded", "Grounded", new GroundedManager(), new GroundedListener(), Material.GRASS_BLOCK,
            new GroundedManager().description()),
    MINE_FATIGUE("mining_fatigue", "Mining Fatigue", new MineFatigueManager(), new MineFatigueListener(), Material.GOLDEN_PICKAXE,
            new MineFatigueManager().description()),
    COME_TOGETHER("come_together", "Come Together", new TogetherManager(), new TogetherListener(), Material.AXOLOTL_BUCKET,
            new TogetherManager().description()),
    SUN_KING("sun_king", "Sun King", new SunKingManager(), new SunKingListener(), Material.SHROOMLIGHT,
            new SunKingManager().description()),
    MR_MOONLIGHT("mr_moonlight", "Mr. Moonlight", new MoonlightManager(), new MoonlightListener(), Material.END_STONE,
            new MoonlightManager().description()),
    I_ME_MINE("i_me_mine", "I Me Mine", new IMeMineManager(), new IMeMineListener(), Material.ENDER_EYE,
            new IMeMineManager().description()),
    CLEAN_ITEM("clean_items", "Clean Items", new CleanItemManager(), new CleanItemListener(), Material.BOOK,
            new CleanItemManager().description()),
    THIN_LINE("thin_line", "Thin Line", new ThinLineManager(), new ThinLineListener(), Material.GOLDEN_APPLE,
            new ThinLineManager().description()),
    BAD_ARM("bad_arm", "Bad Arm", new BadArmManager(), new BadArmListener(), Material.SNOWBALL,
            new BadArmManager().description()),
    SOBER("sober", "Sober", new SoberManager(), new SoberListener(), Material.GLASS_BOTTLE,
            new SoberManager().description()),
    LIGHT_DEPENDENT("light_dependent","Light Dependent", new LightDependentManager(), new LightDependentListener(), Material.LIGHT,
            new LightDependentManager().description()),
    BOUND_ARMOR("stuck_to_you", "Stuck to You", new BoundArmorManager(), new BoundArmorListener(), Material.DIAMOND_CHESTPLATE,
            new BoundArmorManager().description()),
    RANDOM_DAMAGE("lucky_numbers", "Lucky Numbers", new RandomDamageManager(), new RandomDamageListener(), Material.TARGET,
            new RandomDamageManager().description()),
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

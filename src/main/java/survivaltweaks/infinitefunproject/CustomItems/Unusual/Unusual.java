package survivaltweaks.infinitefunproject.CustomItems.Unusual;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import survivaltweaks.infinitefunproject.ItemSelector;

import java.util.*;

import static survivaltweaks.infinitefunproject.CustomItems.Unusual.UnusualManager.unusualLine;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.contains;

public enum Unusual {

    BURNING_FLAMES("Burning Flames", Particle.FLAME, 1),
    OPULENCE("Opulence", Particle.HAPPY_VILLAGER, 2),
    CREMATORIUM("Crematorium", Particle.SMOKE, 5),
    ROMANTIC_RADIANCE("Romantic Radiance", Particle.HEART, 17),
    DAZZLING("Dazzling", Particle.END_ROD, 8),
    ETHEREAL_ESSENCE("Ethereal Essence", Particle.WITCH, 3),
    FROSTBITTEN_FLAMES("Frostbitten Flames", Particle.SOUL_FIRE_FLAME, 1),
    HYDRATION("Hydration", Particle.SPLASH, 5),
    BLIGHTED_HEART("Blighted Heart", Particle.DAMAGE_INDICATOR, 7),
    REJUVENATION("Rejuvenation", Particle.TOTEM_OF_UNDYING, 4),
    HEAVY_HITTING("Heavy Hitting", Particle.CRIT, 5),
    FULL_STEAM_AHEAD("Full Steam Ahead", Particle.EXPLOSION, 15),
    BLISTERING_BOLT("Blistering Bolt", Particle.EFFECT, 4),
    SPELL_CASTING("Spell Casting", Particle.ENCHANT, 2),
    INFERNAL_IRRITATION("Infernal Irritation", Particle.ANGRY_VILLAGER, 4),
    SWIRLING_SOULS("Swirling Souls", Particle.SOUL, 2),
    SPARKLING_SPECTACLE("Sparkling Spectacle", Particle.FIREWORK, 8),
    SUSPICIOUS_SKULLS("Suspicious Skulls", Particle.TRIAL_OMEN, 1),
    CHERRY_BLOSSOMS("Cherry Blossoms", Particle.CHERRY_LEAVES, 1),
    CLOUDS("Clouds", Particle.CAMPFIRE_COSY_SMOKE, 14),
    SWARMING_SPARKS("Swarming Sparks", Particle.TRIAL_SPAWNER_DETECTION, 2),
    SPECTRAL_SPARKS("Spectral Sparks", Particle.TRIAL_SPAWNER_DETECTION_OMINOUS, 2),
    SPORES("Spores", Particle.CRIMSON_SPORE, 8),
    GOLDEN_SHOWER("Golden Shower", Particle.FALLING_HONEY, 6),
    ELECTRICAL("Electrical Energy", Particle.ELECTRIC_SPARK, 8),
    FALLING_FLAMES("Falling Flames", Particle.FALLING_LAVA, 3),
    WINDY("Windy", Particle.SMALL_GUST, 0),
    SWEAT("Sweat", Particle.FALLING_WATER, 3),
    ALLURING_AURA("Alluring Aura", Particle.GLOW, 2),
    WEBBED("Webbed", Particle.ITEM_COBWEB, 5),
    STICKY_SLIME("Sticky Slime", Particle.ITEM_SLIME, 6),
    SNOWED_IN("Snowed In", new Particle[] { Particle.SNOWFLAKE, Particle.ITEM_SNOWBALL } , 3),
    SLASH_N_SWIPE("Slash n' Swipe", Particle.SWEEP_ATTACK, 6),
    LOOK_AT_ME("Look at Me!", Particle.FLASH, 5),
    ULTRASONIC("Ultrasonic", Particle.SONIC_BOOM, 3),
    STORMY_SPIRIT("Stormy Spirit", new Particle[] { Particle.WHITE_SMOKE, Particle.FALLING_WATER }, 2),
    FROSTBURNED_FLAMES("Forstburned Flames", new Particle[] { Particle.FLAME, Particle.SOUL_FIRE_FLAME }, 1),
    MYSTICS("Mystics", new Particle[] { Particle.WITCH, Particle.DRAGON_BREATH, Particle.SMOKE }, 1),
    RIPTIDE("Riptide", new Particle[] { Particle.FALLING_WATER, Particle.BUBBLE, Particle.UNDERWATER,
            Particle.SPLASH }, 0),
    MALEVOLENT_MAGIC("Malevolent Magic", new Particle[] { Particle.WITCH, Particle.ENCHANTED_HIT,
            Particle.SMOKE }, 0),
    OBNOXIOUS("Obnoxious", new Particle[] { Particle.ELECTRIC_SPARK, Particle.GLOW, Particle.FIREWORK,
            Particle.FLASH }, 3),
    ELDRITCH_ENCHANTMENT("Eldritch Enchantment", new Particle[] { Particle.ENCHANT, Particle.WITCH,
            Particle.ENCHANTED_HIT }, 2),
    LOVE_IN_THE_AIR("Love in the Air", new Particle[] { Particle.HEART, Particle.HAPPY_VILLAGER,
            Particle.CHERRY_LEAVES }, 5),
    SMOKE_SHOW("Smoke Show", new Particle[] { Particle.SMOKE, Particle.LARGE_SMOKE, Particle.WHITE_SMOKE,
            Particle.CAMPFIRE_COSY_SMOKE }, 4),
    RED_DUST("Red Cloud", new Particle.DustOptions(Color.RED, 1), 2),
    BLUE_DUST("Blue Cloud", new Particle.DustOptions(Color.BLUE, 1), 2),
    BLACK_DUST("Black Cloud", new Particle.DustOptions(Color.BLACK, 1), 2),
    GREEN_DUST("Green Cloud", new Particle.DustOptions(Color.GREEN, 1), 2),
    PURPLE_DUST("Purple Cloud", new Particle.DustOptions(Color.PURPLE, 1), 2),
    AQUA_DUST("Aqua Cloud", new Particle.DustOptions(Color.AQUA, 1), 2),
    LIME_DUST("Lime Cloud", new Particle.DustOptions(Color.LIME, 1), 2),
    YELLOW_DUST("Yellow Cloud", new Particle.DustOptions(Color.YELLOW, 1), 2),
    ORANGE_DUST("Orange Cloud", new Particle.DustOptions(Color.ORANGE, 1), 2),
    SILVER_DUST("Silver Cloud", new Particle.DustOptions(Color.SILVER, 1), 2),
    TEAL_DUST("Teal Cloud", new Particle.DustOptions(Color.TEAL, 1), 2),
    PINK_DUST("Pink Cloud", new Particle.DustOptions(Color.fromRGB(255, 176, 243), 1), 2),
    CHERRY_CLUSTER("Cherry Cluster", new Particle[] { Particle.CHERRY_LEAVES, Particle.HAPPY_VILLAGER },
            new Particle.DustOptions(Color.fromRGB(255, 176, 243), 1), 1),
    SING_SONG("Sing-Song", new Particle[] { Particle.NOTE }, 4),
    GREEN_MACHINE("Green Machine", new Particle[] { Particle.HAPPY_VILLAGER, Particle.NOTE },
            new Particle.DustOptions(Color.LIME, 1), 0),
    FRIGID_FIGHTER("Frigid Fighter", new Particle[] { Particle.SNOWFLAKE, Particle.ITEM_SNOWBALL },
            new Particle.DustOptions(Color.fromRGB(165, 236, 240), 1), 3),
    HEATED("Heated", new Particle[] { Particle.FLAME, Particle.LAVA, Particle.SMOKE },
            new Particle.DustOptions(Color.YELLOW, 1), 3),
    IRRADIATED("Irradiated", new Particle[] { Particle.TOTEM_OF_UNDYING, Particle.HAPPY_VILLAGER, Particle.ELECTRIC_SPARK,
            Particle.FIREWORK }, new Particle.DustOptions(Color.LIME, 1), 5),
    DEATH_RAY("Death Ray", new Particle[] { Particle.WITCH, Particle.ENCHANTED_HIT,
            Particle.SMOKE }, new Particle.DustOptions(Color.RED, 1), 0),
    STARS("Stars", new Particle[] { Particle.ELECTRIC_SPARK, Particle.INSTANT_EFFECT }, 14),
    PWNED("Pwned", new Particle[] { Particle.WITCH, Particle.CRIT, Particle.ENCHANTED_HIT, Particle.DAMAGE_INDICATOR, Particle.SOUL }, 2),
    QUITE_A_FRIGHT("Quite a Fright", new Particle[] { Particle.SMOKE, Particle.SOUL, Particle.TRIAL_SPAWNER_DETECTION,
            Particle.TRIAL_SPAWNER_DETECTION_OMINOUS, Particle.TRIAL_OMEN }, 3),
    TOXIC_CLOUD("Toxic Cloud", new Particle[] { Particle.FALLING_WATER, Particle.FALLING_HONEY },
            new Particle.DustOptions(Color.YELLOW, 1), 4),
    FURIOUS_FLAMES("Furious Flames", new Particle[] { Particle.FLAME, Particle.LAVA, Particle.FALLING_LAVA, Particle.LANDING_LAVA },
            new Particle.DustOptions(Color.ORANGE, 1), 0),
    BOOMBOX("Boombox", new Particle[] { Particle.NOTE, Particle.SONIC_BOOM }, new Particle.DustOptions(Color.AQUA, 1), 3),
    ROCKET_TRAIL("Rocket Trail", new Particle[] { Particle.FLAME, Particle.WHITE_SMOKE, Particle.SMOKE, Particle.CRIT }, 0),
    NONE("None", new Particle[] {}, 0),
    ;

    private final String name;
    private ArrayList<Particle> particles = new ArrayList<>();
    private final int weight;
    private final Particle.DustOptions dustOptions;

    Unusual(String name, Particle particle, int weight) {
        this.name = name;
        this.particles.add(particle);
        this.weight = weight;

        dustOptions = null;
    }

    Unusual(String name, ArrayList<Particle> particles, int weight) {
        this.name = name;
        this.particles = particles;
        this.weight = weight;

        dustOptions = null;
    }

    Unusual(String name, Particle[] particles, int weight) {
        this.name = name;
        this.particles = new ArrayList<>(List.of(particles));
        this.weight = weight;

        dustOptions = null;
    }

    Unusual(String name, Particle.DustOptions options, int weight) {
        this.name = name;
        this.particles.add(Particle.DUST);
        this.weight = weight;

        this.dustOptions = options;
    }

    Unusual(String name, Particle[] particles, Particle.DustOptions options, int weight) {
        this.name = name;
        this.particles = new ArrayList<>(List.of(particles));
        this.weight = weight;

        if(!contains(particles, Particle.DUST)) {
            this.particles.add(Particle.DUST);
        }

        this.dustOptions = options;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Particle> getParticles() {
        return particles;
    }

    public int getWeight() {
        return weight;
    }

    public Particle.DustOptions getDustOptions() {
        return dustOptions;
    }

    public static Unusual getByName(String string) {
        Optional<Unusual> unusualOptional = Arrays.stream(Unusual.values()).filter(x -> x.getName().equalsIgnoreCase(string)).findFirst();

        return unusualOptional.orElse(null);
    }

    public static Unusual getByInternalName(String string) {
        Optional<Unusual> unusualOptional = Arrays.stream(Unusual.values()).filter(x -> x.toString().equalsIgnoreCase(string)).findFirst();

        return unusualOptional.orElse(null);
    }

    public static Unusual rollRandomEffect() {
        ItemSelector<Unusual> effects = new ItemSelector<>();

        for(Unusual unusual : Unusual.values()) {
            for(int i = 0; i < unusual.getWeight(); i++) {
                effects.addEntry(unusual, unusual.getWeight());
            }
        }

        return effects.rollItem();
    }

    public static boolean hasUnusual(ItemStack item) {
        ItemMeta meta = item.getItemMeta();

        if(!meta.hasLore()) {
            return false;
        }

        for(String str : item.getItemMeta().getLore()) {
            if(str.contains("* Unusual Effect")) {
                return true;
            }
        }

        return false;
    }

    public static ItemStack addRandomUnusual(ItemStack item) {
        if(hasUnusual(item)) {
            return replaceUnusual(item, rollRandomEffect());
        }

        return addUnusual(item, rollRandomEffect());
    }

    public static ItemStack addUnusual(ItemStack item, Unusual unusual) {
        if(hasUnusual(item)) {
            return replaceUnusual(item, unusual);
        }

        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore;

        try {
            lore = (ArrayList<String>) meta.getLore();

            lore.add("");
            String effect = unusual.getName();
            lore.add(ChatColor.DARK_PURPLE + "* Unusual Effect" + ChatColor.GRAY + ": " + ChatColor.YELLOW + effect);
        } catch(NullPointerException ignored) {
            lore = new ArrayList<>();
            lore.add("");
            String effect = unusual.getName();
            lore.add(ChatColor.DARK_PURPLE + "* Unusual Effect" + ChatColor.GRAY + ": " + ChatColor.YELLOW + effect);
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack replaceUnusual(ItemStack item, Unusual newEffect) {
        if(!hasUnusual(item)) {
            return addUnusual(item, newEffect);
        }

        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore;

        lore = (ArrayList<String>) meta.getLore();

        int unusualLine = unusualLine(item);

        if (unusualLine == -1) {
            return item;
        }

        String effect = newEffect.getName();
        lore.set(unusualLine, ChatColor.DARK_PURPLE + "* Unusual Effect" + ChatColor.GRAY + ": " + ChatColor.YELLOW + effect);

        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}

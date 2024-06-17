package survivaltweaks.infinitefunproject.CustomItems;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LCAbility;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHitAbility;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RCAbility;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.OnDamagedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.PassiveAbility;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.TriggeredAbility;
import survivaltweaks.infinitefunproject.Player.Upgrades.InitUpgrades;

import java.util.ArrayList;

import static org.bukkit.Material.*;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public class ItemManager {

    public static ArrayList<ItemStack> customItems;

    private static final String phightingSeries = color("&bPHIGHTING!");
    private static final String lethalCompanySeries = color("&4Lethal Company");
    private static final String tf2Series = color("&6Team Fortress 2");
    private static final String csSeries = color("&aCounter-Strike");
    private static final String returningSeries = color("&eReturning");

    public static ItemStack bat;
    public static ItemStack zombieSword;
    public static ItemStack spiderSword;
    public static ItemStack wardenChestplate;
    public static ItemStack superHammer;
    public static ItemStack dragonSword;
    public static ItemStack berserkSword;
    public static ItemStack endlessPearl;
    public static ItemStack enchantedPearl;
    public static ItemStack weaponHandle;
    public static ItemStack decrepitScroll;
    public static ItemStack culmination;
    public static ItemStack fang;
    public static ItemStack vaccine;
    public static ItemStack medication;
    public static ItemStack specialSugar;
    public static ItemStack tea;
    public static ItemStack alcohol;
    public static ItemStack bleach;
    public static ItemStack easterEgg;
    public static ItemStack flashbang;
    public static ItemStack radar;
    public static ItemStack scythe;
    public static ItemStack gun;
    public static ItemStack beamSword;
    public static ItemStack boombox;
    public static ItemStack banHammer;
    public static ItemStack rocketLauncher;
    public static ItemStack grenade;
    public static ItemStack hyperLaser;
    public static ItemStack spaceTripmine;
    public static ItemStack katana;
    public static ItemStack slingshot;
    public static ItemStack medkit;
    public static ItemStack biograftSword;
    public static ItemStack vineStaff;
    public static ItemStack shuriken;
    public static ItemStack critSoda;
    public static ItemStack playbook;
    public static ItemStack inphernalBase;
    public static ItemStack comingSoon;
    public static ItemStack doubleBarrel;
    public static ItemStack upgradeCatalyst;
    public static ItemStack placeholder;
    public static ItemStack butterflyKnife;
    public static ItemStack executionThread;
    public static ItemStack revolver;
    public static ItemStack medkitRevolver;
    public static ItemStack engagementRing;
    public static ItemStack flamethrower;
    public static ItemStack laserMachineGun;
    public static ItemStack kingTrident;
    public static ItemStack divorcePapers;
    public static ItemStack callisto;
    public static ItemStack triton;
    public static ItemStack ascensionGaze;
    public static ItemStack dirt;
    public static ItemStack railgun;
    public static ItemStack scannerHelmet;
    public static ItemStack oneHitMode;
    public static ItemStack challengeModeBrowser;

    public static void init() {
        customItems = new ArrayList<>();

        bat = setSeries(createBat(), tf2Series);
        customItems.add(bat);

        zombieSword = createZombieSword();
        customItems.add(zombieSword);

        spiderSword = createSpiderSword();
        customItems.add(spiderSword);

        wardenChestplate = createWardenChestplate();
        customItems.add(wardenChestplate);

        endlessPearl = createEndlessPearl();
        customItems.add(endlessPearl);

        enchantedPearl = createEnchantedPearl();
        customItems.add(enchantedPearl);

        weaponHandle = createWeaponHandle();
        customItems.add(weaponHandle);

        decrepitScroll = setMiniCrits(createDecrepitScroll());
        customItems.add(decrepitScroll);

        culmination = setMiniCrits(setAlwaysCrits(createCulmination()));
        customItems.add(culmination);

        berserkSword = setMiniCrits(createBerserkSword());
        customItems.add(berserkSword);

        superHammer = setNoCrits(createHammer());
        customItems.add(superHammer);

        dragonSword = setMiniCrits(createDragonSword());
        customItems.add(dragonSword);

        fang = createFang();
        customItems.add(fang);

        vaccine = createVaccine();
        customItems.add(vaccine);

        medication = createMedication();
        customItems.add(medication);

        specialSugar = createSpecialSugar();
        customItems.add(specialSugar);

        tea = createTea();
        customItems.add(tea);

        alcohol = createAlcohol();
        customItems.add(alcohol);

        bleach = createBleach();
        customItems.add(bleach);

        easterEgg = setSeries(setNoCrits(createEasterEgg()), lethalCompanySeries);
        customItems.add(easterEgg);

        flashbang = setSeries(createFlashbang(), csSeries);
        customItems.add(flashbang);

        radar = createRadar();
        customItems.add(radar);

        scythe = setSeries(createScythe(), phightingSeries);
        customItems.add(scythe);

        beamSword = setSeries(createBeamSword(), phightingSeries);
        customItems.add(beamSword);

        gun = setSeries(createGun(), phightingSeries);

        boombox = setSeries(createBoombox(), phightingSeries);
        customItems.add(boombox);

        banHammer = setSeries(createBanHammer(), phightingSeries);
        customItems.add(banHammer);

        rocketLauncher = setSeries(createRocketLauncher(), phightingSeries);
        customItems.add(rocketLauncher);

        grenade = createGrenade();
        customItems.add(grenade);

        hyperLaser = setSeries(createHyperLaser(), phightingSeries);
        customItems.add(hyperLaser);

        spaceTripmine = setSeries(createSubspace(), phightingSeries);
        customItems.add(spaceTripmine);

        katana = setSeries(createKatana(), phightingSeries);
        customItems.add(katana);

        slingshot = setSeries(createSlingshot(), phightingSeries);
        customItems.add(slingshot);

        medkit = setSeries(createMedkit(), phightingSeries);
        customItems.add(medkit);

        biograftSword = setSeries(createBioSword(), phightingSeries);
        customItems.add(biograftSword);

        vineStaff = setSeries(createVineStaff(), phightingSeries);
        customItems.add(vineStaff);

        shuriken = setSeries(createShuriken(), phightingSeries);
        customItems.add(shuriken);

        critSoda = setSeries(createCritSoda(), tf2Series);
        customItems.add(critSoda);

        playbook = createPlaybook();
        customItems.add(playbook);

        inphernalBase = setSeries(createInphernalBase(), phightingSeries);
        customItems.add(inphernalBase);

        doubleBarrel = setSeries(setMiniCrits(createDoubleBarrel()), lethalCompanySeries);
        customItems.add(doubleBarrel);

        comingSoon = createComingSoon();

        placeholder = createPlaceholder();

        upgradeCatalyst = createUpgradeCatalyst();
        customItems.add(upgradeCatalyst);

        butterflyKnife = setSeries(createButterflyKnife(), tf2Series);
        customItems.add(butterflyKnife);

        executionThread = setSeries(createExecutionThread(), phightingSeries);
        customItems.add(executionThread);

        revolver = createRevolver();
        customItems.add(revolver);

        medkitRevolver = setSeries(createMedkitRevolver(), phightingSeries);
        customItems.add(medkitRevolver);

        engagementRing = createEngagementRing();
        customItems.add(engagementRing);

        flamethrower = createFlamethrower();
        customItems.add(flamethrower);

        laserMachineGun = createLaserMachineGun();
        customItems.add(laserMachineGun);

        kingTrident = createKingTrident();
        customItems.add(kingTrident);

        divorcePapers = createDivorcePapers();
        customItems.add(divorcePapers);

        callisto = setSeries(createCallisto(), returningSeries);
        customItems.add(callisto);

        triton = setSeries(createTriton(), returningSeries);
        customItems.add(triton);

        ascensionGaze = setSeries(createAscensionGaze(), returningSeries);
        customItems.add(ascensionGaze);

        dirt = setSeries(createDirt(), returningSeries);
        customItems.add(dirt);

        railgun = setSeries(createRailgun(), returningSeries);
        customItems.add(railgun);

        scannerHelmet = setSeries(createScannerHelmet(), lethalCompanySeries);
        customItems.add(scannerHelmet);

        oneHitMode = createOneHitMode();
        customItems.add(oneHitMode);

        challengeModeBrowser = createChallengeModeBrowser();
        customItems.add(challengeModeBrowser);

        postLore();
    }

    private static ItemStack createChallengeModeBrowser() {
        ItemStack browser = new ItemStack(RECOVERY_COMPASS);

        ItemMeta browserMeta = browser.getItemMeta();
        browserMeta.setDisplayName(ChatColor.DARK_AQUA + "Challenge Modes");

        browserMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        browserMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Allows you to choose to");
        lore.add(ChatColor.GRAY + "enable challenges.");

        browserMeta.setLore(lore);
        browser.setItemMeta(browserMeta);

        RCAbility.addAbility(browser, RCAbility.BROWSE_CHALLENGES);
        return browser;
    }

    private static ItemStack createOneHitMode() {
        ItemStack mode = new ItemStack(SKELETON_SKULL);

        ItemMeta modeMeta = mode.getItemMeta();
        modeMeta.setDisplayName(ChatColor.RED + "Armageddon Mode");

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "In this mode, you will");
        lore.add(ChatColor.GRAY + "have one health, but you");
        lore.add(ChatColor.GRAY + "will one hit anything.");
        lore.add("");
        lore.add(ChatColor.RED + "No going back...");

        modeMeta.setLore(lore);
        mode.setItemMeta(modeMeta);

        RCAbility.addAbility(mode, RCAbility.ENABLE_ONE_HIT_MODE);
        return mode;
    }

    private static ItemStack createScannerHelmet() {
        ItemStack helmet = new ItemStack(IRON_HELMET);

        ItemMeta helmetMeta = helmet.getItemMeta();
        helmetMeta.setDisplayName(ChatColor.BLUE + "Scanner Helmet");

        helmet.setItemMeta(helmetMeta);

        PassiveAbility.addAbility(helmet, PassiveAbility.SCAN);
        return helmet;
    }

    private static ItemStack createRailgun() {
        ItemStack railgun = new ItemStack(IRON_HOE);

        ItemMeta railgunMeta = railgun.getItemMeta();
        railgunMeta.setDisplayName(ChatColor.GOLD + "Railgun");

        railgunMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        railgunMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        railgun.setItemMeta(railgunMeta);

        LCAbility.addAbility(railgun, LCAbility.RAILGUN_SHOT);
        return railgun;
    }

    private static ItemStack createDirt() {
        ItemStack superDirt = new ItemStack(Material.DIRT);
        ItemMeta dirtMeta = superDirt.getItemMeta();

        dirtMeta.setDisplayName(ChatColor.DARK_PURPLE + "Transcended Dirt");

        dirtMeta.addEnchant(Enchantment.BINDING_CURSE, 10, true);
        dirtMeta.addEnchant(Enchantment.VANISHING_CURSE, 10, true);

        dirtMeta.setUnbreakable(true);
        dirtMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.YELLOW + "(Right-Click) Ability" + ChatColor.GRAY + ": " + ChatColor.GREEN + "Place");
        lore.add(ChatColor.GRAY + "Place the item on the ground,");
        lore.add(ChatColor.GRAY + "wasting it.");
        lore.add(color("&7- &cOne Time Use"));

        dirtMeta.setLore(lore);
        superDirt.setItemMeta(dirtMeta);

        return superDirt;
    }

    private static ItemStack createAscensionGaze() {
        ItemStack sword = new ItemStack(Material.IRON_SWORD);

        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.setDisplayName(color("&6&l&kZ &b&lAscension's Gaze &6&l&kZ"));

        sword.setItemMeta(swordMeta);

        RCAbility.addAbility(sword, RCAbility.ETHERWARP);
        return sword;
    }

    private static ItemStack createTriton() {
        ItemStack triton = new ItemStack(Material.CROSSBOW);

        ItemMeta tritonMeta = triton.getItemMeta();
        tritonMeta.setDisplayName(ChatColor.RED + "Triton");

        tritonMeta.addEnchant(Enchantment.PIERCING, 10, true);
        tritonMeta.addEnchant(Enchantment.QUICK_CHARGE, 5, true);
        tritonMeta.addEnchant(Enchantment.MULTISHOT, 1, true);
        tritonMeta.addEnchant(Enchantment.DURABILITY, 5, true);
        tritonMeta.addEnchant(Enchantment.MENDING, 1, true);

        triton.setItemMeta(tritonMeta);

        LCAbility.addAbility(triton, LCAbility.TRITON_BURST);
        return triton;
    }

    private static ItemStack createCallisto() {
        ItemStack callisto = new ItemStack(Material.BOW);

        ItemMeta bowMeta = callisto.getItemMeta();
        bowMeta.setDisplayName(ChatColor.GOLD + "Callisto");

        bowMeta.addEnchant(Enchantment.ARROW_DAMAGE, 10, true);
        bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);

        bowMeta.setUnbreakable(true);
        bowMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        callisto.setItemMeta(bowMeta);

        LCAbility.addAbility(callisto, LCAbility.CALLISTO_EXPLOSIVE_SHOT);
        PassiveAbility.addAbility(callisto, PassiveAbility.CALLISTO_ARROWS);
        return callisto;
    }

    private static ItemStack createDivorcePapers() {
        ItemStack papers = new ItemStack(Material.PAPER);

        ItemMeta paperMeta = papers.getItemMeta();
        paperMeta.setDisplayName(ChatColor.RED + "Divorce Papers");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(color("&7&o\"Try to see it my way."));
        lore.add(color("&7&oDo I have to keep on talking"));
        lore.add(color("&7&otill I can't go on?"));
        lore.add(color("&7&oWhile you see it your way,"));
        lore.add(color("&7&oRun the risk of knowing that"));
        lore.add(color("&7&oour love may soon be gone."));
        lore.add("");
        lore.add(color("&7&oWe can work it out."));
        lore.add(color("&7&oWe can work it out.\""));

        paperMeta.setLore(lore);
        papers.setItemMeta(paperMeta);

        OnHitAbility.addAbility(papers, OnHitAbility.DIVORCE);
        return papers;
    }

    private static ItemStack createKingTrident() {
        ItemStack kingTrident = new ItemStack(Material.TRIDENT);

        ItemMeta tridentMeta = kingTrident.getItemMeta();
        tridentMeta.setDisplayName(ChatColor.DARK_AQUA + "Drowned King's Trident");

        tridentMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
        tridentMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 3, true);
        tridentMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 3, true);
        tridentMeta.addEnchant(Enchantment.CHANNELING, 3, true);
        tridentMeta.addEnchant(Enchantment.IMPALING, 3, true);
        tridentMeta.addEnchant(Enchantment.DURABILITY, 3, true);

        tridentMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "The trident of a drowned");
        lore.add(ChatColor.GRAY + "king.");
        lore.add("");
        lore.add(color("&7&o\"I'd like to be, under the"));
        lore.add(color("&7&osea..."));
        lore.add(color("&7&oIn an octopus's garden in the"));
        lore.add(color("&7&oshade...\""));

        tridentMeta.setLore(lore);
        kingTrident.setItemMeta(tridentMeta);

        PassiveAbility.addAbility(kingTrident, PassiveAbility.AQUATIC_ADAPTATION);
        OnHitAbility.addAbility(kingTrident, OnHitAbility.SPLASH);
        OnHitAbility.addAbility(kingTrident, OnHitAbility.IMPALE_ABILITY);
        return kingTrident;
    }

    private static ItemStack createLaserMachineGun() {
        ItemStack laserGun = new ItemStack(Material.SPECTRAL_ARROW);

        ItemMeta laserMeta = laserGun.getItemMeta();
        laserMeta.setDisplayName(ChatColor.AQUA + "Laser Machine Gun");

        laserMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        laserMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        laserGun.setItemMeta(laserMeta);

        LCAbility.addAbility(laserGun, LCAbility.SHOOT_LASER_GUN);
        return laserGun;
    }

    private static ItemStack createFlamethrower() {
        ItemStack flamethrower = new ItemStack(Material.IRON_SHOVEL);

        ItemMeta flamethrowerMeta = flamethrower.getItemMeta();
        flamethrowerMeta.setDisplayName(ChatColor.GOLD + "Flamethrower");

        flamethrowerMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        flamethrowerMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        flamethrower.setItemMeta(flamethrowerMeta);

        LCAbility.addAbility(flamethrower, LCAbility.SHOOT_FLAME);
        return flamethrower;
    }

    private static ItemStack createEngagementRing() {
        ItemStack ring = new ItemStack(Material.GOLD_INGOT);

        ItemMeta ringMeta = ring.getItemMeta();
        ringMeta.setDisplayName(ChatColor.GOLD + "Engagement Ring");

        ringMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        ringMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(color("&7&o\"You're asking me:"));
        lore.add(color("&7&oWill my love grow?"));
        lore.add(color("&7&oI don't know, I don't know."));
        lore.add("");
        lore.add(color("&7&oYou stick around,"));
        lore.add(color("&7&onow it may show."));
        lore.add(color("&7&oI don't know, I don't know.\""));

        ringMeta.setLore(lore);
        ring.setItemMeta(ringMeta);

        RCAbility.addAbility(ring, RCAbility.PROPOSE);
        return ring;
    }

    private static ItemStack createMedkitRevolver() {
        ItemStack revolver = new ItemStack(Material.DIAMOND_HOE);

        ItemMeta revolverMeta = revolver.getItemMeta();
        revolverMeta.setDisplayName(ChatColor.GREEN + "Medkit's Revolver");

        revolverMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        revolverMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        revolver.setItemMeta(revolverMeta);

        LCAbility.addAbility(revolver, LCAbility.SHOOT_MEDKIT_REVOLVER);
        RCAbility.addAbility(revolver, RCAbility.FAN_THE_HAMMER);
        TriggeredAbility.addAbility(revolver, TriggeredAbility.CRYSTALLIZE);
        return revolver;
    }

    private static ItemStack createRevolver() {
        ItemStack revolver = new ItemStack(IRON_HOE);

        ItemMeta revolverMeta = revolver.getItemMeta();
        revolverMeta.setDisplayName(ChatColor.RED + "Revolver");

        revolverMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        revolverMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        revolverMeta.setUnbreakable(true);
        revolverMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Now Featuring:");
        lore.add("");
        lore.add(ChatColor.GRAY + "- A-Side:");
        lore.add(ChatColor.GRAY + "  - Taxman");
        lore.add(ChatColor.GRAY + "  - Elanor Rigby");
        lore.add(ChatColor.GRAY + "  - I'm Only Sleeping");
        lore.add(ChatColor.GRAY + "  - Love You To");
        lore.add(ChatColor.GRAY + "  - Here, There and");
        lore.add(ChatColor.GRAY + "    Everywhere");
        lore.add(ChatColor.GRAY + "  - Yellow Submarine");
        lore.add(ChatColor.GRAY + "  - She Said She Said");
        lore.add("");
        lore.add(ChatColor.GRAY + "- B-Side:");
        lore.add(ChatColor.GRAY + "  - Good Day Sunshine");
        lore.add(ChatColor.GRAY + "  - And Your Bird Can");
        lore.add(ChatColor.GRAY + "    Sing");
        lore.add(ChatColor.GRAY + "  - For No One");
        lore.add(ChatColor.GRAY + "  - Doctor Robert");
        lore.add(ChatColor.GRAY + "  - I Want to Tell You");
        lore.add(ChatColor.GRAY + "  - Got to Get You into My");
        lore.add(ChatColor.GRAY + "    Life");
        lore.add(ChatColor.GRAY + "  - Tomorrow Never Knows");
        lore.add("");
        lore.add(ChatColor.GRAY + "Wait, this isn't a vinyl...");

        revolverMeta.setLore(lore);
        revolver.setItemMeta(revolverMeta);

        LCAbility.addAbility(revolver, LCAbility.SHOOT_REVOLVER);
        return revolver;
    }

    private static ItemStack createExecutionThread() {
        ItemStack thread = new ItemStack(Material.STRING);

        ItemMeta threadMeta = thread.getItemMeta();
        threadMeta.setDisplayName(ChatColor.GRAY + "Thread");

        threadMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        threadMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        thread.setItemMeta(threadMeta);

        LCAbility.addAbility(thread, LCAbility.EXECUTION_THREAD);
        return thread;
    }

    private static ItemStack createButterflyKnife() {
        ItemStack knife = new ItemStack(Material.IRON_SWORD);

        ItemMeta knifeMeta = knife.getItemMeta();
        knifeMeta.setDisplayName(ChatColor.GRAY + "Butterfly Knife");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Right behind you...");

        knifeMeta.setLore(lore);
        knife.setItemMeta(knifeMeta);

        RCAbility.addAbility(knife, RCAbility.CLOAK);
        OnHitAbility.addAbility(knife, OnHitAbility.BACKSTAB);
        return knife;
    }

    public static ItemStack createDamageUpgradeIcon() {
        ItemStack icon = new ItemStack(Material.IRON_SWORD);

        ItemMeta iconMeta = icon.getItemMeta();
        iconMeta.setDisplayName(ChatColor.GREEN + "Upgrade Damage");

        iconMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "+ 0.5 Damage/Level.");
        lore.add("");
        lore.add(ChatColor.GRAY + "XP Level Cost: " + ChatColor.YELLOW + InitUpgrades.damageUpgradeCost);

        iconMeta.setLore(lore);
        icon.setItemMeta(iconMeta);

        return icon;
    }

    public static ItemStack createArmorUpgradeIcon() {
        ItemStack icon = new ItemStack(Material.IRON_CHESTPLATE);

        ItemMeta iconMeta = icon.getItemMeta();
        iconMeta.setDisplayName(ChatColor.GREEN + "Upgrade Armor");

        iconMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "+ 0.35 Armor/Level.");
        lore.add("");
        lore.add(ChatColor.GRAY + "XP Level Cost: " + ChatColor.YELLOW + InitUpgrades.armorUpgradeCost);

        iconMeta.setLore(lore);
        icon.setItemMeta(iconMeta);

        return icon;
    }

    public static ItemStack createAttackSpeedUpgradeIcon() {
        ItemStack icon = new ItemStack(Material.GOLDEN_SWORD);

        ItemMeta iconMeta = icon.getItemMeta();
        iconMeta.setDisplayName(ChatColor.GREEN + "Upgrade Attack Speed");

        iconMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "+ 0.32 Attack Speed/Level.");
        lore.add("");
        lore.add(ChatColor.GRAY + "XP Level Cost: " + ChatColor.YELLOW + InitUpgrades.attackSpeedUpgradeCost);

        iconMeta.setLore(lore);
        icon.setItemMeta(iconMeta);

        return icon;
    }

    public static ItemStack createKBResUpgradeIcon() {
        ItemStack icon = new ItemStack(Material.TURTLE_HELMET);

        ItemMeta iconMeta = icon.getItemMeta();
        iconMeta.setDisplayName(ChatColor.GREEN + "Upgrade Knockback Resistance");

        iconMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "+ 2% Knockback Resistance/Level.");
        lore.add("");
        lore.add(ChatColor.GRAY + "XP Level Cost: " + ChatColor.YELLOW + InitUpgrades.knockbackResistanceUpgradeCost);

        iconMeta.setLore(lore);
        icon.setItemMeta(iconMeta);

        return icon;
    }

    public static ItemStack createHealthUpgradeIcon() {
        ItemStack icon = new ItemStack(Material.GOLDEN_APPLE);

        ItemMeta iconMeta = icon.getItemMeta();
        iconMeta.setDisplayName(ChatColor.GREEN + "Upgrade Health");

        iconMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "+ 0.5 Health/Level.");
        lore.add("");
        lore.add(ChatColor.GRAY + "XP Level Cost: " + ChatColor.YELLOW + InitUpgrades.healthUpgradeCost);

        iconMeta.setLore(lore);
        icon.setItemMeta(iconMeta);

        return icon;
    }

    public static ItemStack createDoubleJumpUpgradeIcon() {
        ItemStack icon = new ItemStack(Material.RABBIT_FOOT);

        ItemMeta iconMeta = icon.getItemMeta();
        iconMeta.setDisplayName(ChatColor.GREEN + "Upgrade Double Jump");

        iconMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "+ Ability to Double Jump.");
        lore.add("");
        lore.add(ChatColor.GRAY + "XP Level Cost: " + ChatColor.YELLOW + InitUpgrades.doubleJumpCost);

        iconMeta.setLore(lore);
        icon.setItemMeta(iconMeta);

        return icon;
    }

    private static ItemStack createPlaceholder() {
        ItemStack placeholder = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);

        ItemMeta placeholderMeta = placeholder.getItemMeta();
        placeholderMeta.setDisplayName(" ");

        placeholder.setItemMeta(placeholderMeta);
        return placeholder;
    }

    private static ItemStack createUpgradeCatalyst() {
        ItemStack catalyst = new ItemStack(Material.NETHER_STAR);

        ItemMeta catalystMeta = catalyst.getItemMeta();
        catalystMeta.setDisplayName(ChatColor.GOLD + "Upgrade Catalyst");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "A mysterious energy source that");
        lore.add(ChatColor.GRAY + "emanates special powers...");

        catalystMeta.setLore(lore);
        catalyst.setItemMeta(catalystMeta);

        RCAbility.addAbility(catalyst, RCAbility.OPEN_UPGRADE_UI);
        return catalyst;
    }

    private static ItemStack createDoubleBarrel() {
        ItemStack shotgun = new ItemStack(IRON_HOE);

        ItemMeta shotgunMeta = shotgun.getItemMeta();
        shotgunMeta.setDisplayName(ChatColor.GRAY + "Double Barrel");

        shotgunMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        shotgunMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        shotgun.setItemMeta(shotgunMeta);

        LCAbility.addAbility(shotgun, LCAbility.SHOTGUN_FIRE);
        return shotgun;
    }

    private static ItemStack createComingSoon() {
        ItemStack coming = new ItemStack(Material.STRUCTURE_VOID);

        ItemMeta comingMeta = coming.getItemMeta();
        comingMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Coming Soon!");

        comingMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        comingMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "This item has yet to be");
        lore.add(ChatColor.GRAY + "released.");

        comingMeta.setLore(lore);
        coming.setItemMeta(comingMeta);

        PassiveAbility.addAbility(coming, PassiveAbility.WAIT);
        return coming;
    }

    private static ItemStack createInphernalBase() {
        ItemStack base = new ItemStack(Material.FIRE_CHARGE);

        ItemMeta baseMeta = base.getItemMeta();
        baseMeta.setDisplayName(ChatColor.GOLD + "Inpherno Base Item");

        baseMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        baseMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.DARK_GRAY + "The base item for the entire");
        lore.add(ChatColor.AQUA + "PHIGHTING!" + ChatColor.DARK_GRAY + " series.");

        baseMeta.setLore(lore);
        base.setItemMeta(baseMeta);

        return base;
    }

    private static ItemStack createPlaybook() {
        ItemStack book = new ItemStack(Material.BOOK);

        ItemMeta bookMeta = book.getItemMeta();
        bookMeta.setDisplayName(ChatColor.GOLD + "Billionaire's Playbook");

        bookMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        bookMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Only featuring legal practices!");

        bookMeta.setLore(lore);
        book.setItemMeta(bookMeta);

        RCAbility.addAbility(book, RCAbility.DAMAGE_TAX_EVASION);
        return book;
    }

    private static ItemStack createCritSoda() {
        ItemStack soda = new ItemStack(Material.POTION);

        PotionMeta sodaMeta = (PotionMeta) soda.getItemMeta();
        sodaMeta.setDisplayName(ChatColor.YELLOW + "Crit-a-Cola");

        sodaMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        sodaMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        sodaMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        sodaMeta.setColor(Color.YELLOW);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Trademark battlefield drink from");
        lore.add(ChatColor.GRAY + "the Bonk! Company.");
        lore.add("");
        lore.add(ChatColor.GREEN + "Irradiated to perfection!");

        sodaMeta.setLore(lore);
        soda.setItemMeta(sodaMeta);

        RCAbility.addAbility(soda, RCAbility.DRINK_CRIT_SODA);
        return soda;
    }

    private static ItemStack createShuriken() {
        ItemStack shuriken = new ItemStack(Material.FLINT);

        ItemMeta shurikenMeta = shuriken.getItemMeta();
        shurikenMeta.setDisplayName(ChatColor.GRAY + "Shuriken");

        shurikenMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        shurikenMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Hitting enemies' backs with");
        lore.add(ChatColor.GRAY + "abilities deals extra damage.");

        shurikenMeta.setLore(lore);
        shuriken.setItemMeta(shurikenMeta);

        LCAbility.addAbility(shuriken, LCAbility.SHURIKEN_THROW);
        RCAbility.addAbility(shuriken, RCAbility.SHURIKEN_STAB);
        PassiveAbility.addAbility(shuriken, PassiveAbility.SHURIKEN_BUFF);
        PassiveAbility.addAbility(shuriken, PassiveAbility.SIBLING_BOND_SHURIKEN);
        return shuriken;
    }

    private static ItemStack createVineStaff() {
        ItemStack vineStaff = new ItemStack(Material.STICK);

        ItemMeta staffMeta = vineStaff.getItemMeta();
        staffMeta.setDisplayName(ChatColor.DARK_GREEN + "Vine Staff");

        staffMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        staffMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        vineStaff.setItemMeta(staffMeta);

        LCAbility.addAbility(vineStaff, LCAbility.SHOOT_PETALS);
        RCAbility.addAbility(vineStaff, RCAbility.LOTUS_ABILITY);
        PassiveAbility.addAbility(vineStaff, PassiveAbility.SIBLING_BOND_VINE);
        return vineStaff;
    }

    private static ItemStack createBioSword() {
        ItemStack biograftSword = new ItemStack(Material.GOLDEN_SWORD);

        ItemMeta swordMeta = biograftSword.getItemMeta();
        swordMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Biograft Energy Sword");

        swordMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        swordMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        swordMeta.setUnbreakable(true);
        swordMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "You must have two swords");
        lore.add(ChatColor.GRAY + "to use abilities.");

        swordMeta.setLore(lore);
        biograftSword.setItemMeta(swordMeta);

        LCAbility.addAbility(biograftSword, LCAbility.DUAL_SWING);
        RCAbility.addAbility(biograftSword, RCAbility.BLINK);
        return biograftSword;
    }

    private static ItemStack createMedkit() {
        ItemStack medkit = new ItemStack(Material.WARPED_HANGING_SIGN);

        ItemMeta medkitMeta = medkit.getItemMeta();
        medkitMeta.setDisplayName(ChatColor.GREEN + "Medkit");

        medkitMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        medkitMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        medkit.setItemMeta(medkitMeta);

        LCAbility.addAbility(medkit, LCAbility.REJUVENATING_RING);
        RCAbility.addAbility(medkit, RCAbility.DIVINE_RESURRECTION);
        return medkit;
    }

    private static ItemStack createSlingshot() {
        ItemStack slingshot = new ItemStack(Material.LEAD);

        ItemMeta slingMeta = slingshot.getItemMeta();
        slingMeta.setDisplayName(ChatColor.GREEN + "Slingshot");

        slingMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        slingMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        slingshot.setItemMeta(slingMeta);

        LCAbility.addAbility(slingshot, LCAbility.SLING_SHOT);
        RCAbility.addAbility(slingshot, RCAbility.HEAVY_HURLER);
        return slingshot;
    }

    private static ItemStack createKatana() {
        ItemStack katana = new ItemStack(Material.IRON_SWORD);

        ItemMeta katanaMeta = katana.getItemMeta();
        katanaMeta.setDisplayName(ChatColor.RED + "Katana");

        katanaMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        katanaMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        katana.setItemMeta(katanaMeta);

        LCAbility.addAbility(katana, LCAbility.CALAMITY_SLICE);
        OnHitAbility.addAbility(katana, OnHitAbility.LOOMING_MISFORTUNE);
        RCAbility.addAbility(katana, RCAbility.SOUL_SPLICER);
        return katana;
    }

    private static ItemStack createSubspace() {
        ItemStack star = new ItemStack(Material.NETHER_STAR);

        ItemMeta starMeta = star.getItemMeta();
        starMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Subspace Tripmine");

        starMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        starMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        star.setItemMeta(starMeta);

        LCAbility.addAbility(star, LCAbility.FALLEN_STAR);
        OnHitAbility.addAbility(star, OnHitAbility.SOUL_STEALER);
        RCAbility.addAbility(star, RCAbility.NOXIOUS_VOID);
        return star;
    }

    private static ItemStack createHyperLaser() {
        ItemStack laser = new ItemStack(Material.CROSSBOW);

        ItemMeta laserMeta = laser.getItemMeta();
        laserMeta.setDisplayName(ChatColor.DARK_PURPLE + "Hyperlaser");

        laserMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        laserMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        laser.setItemMeta(laserMeta);

        LCAbility.addAbility(laser, LCAbility.SHOOT_HYPER_LASER);
        OnHitAbility.addAbility(laser, OnHitAbility.LOCKED_LOADED);
        RCAbility.addAbility(laser, RCAbility.EVISCERATION_BEAM);
        return laser;
    }

    private static ItemStack createGrenade() {
        ItemStack grenade = new ItemStack(Material.SNOWBALL);

        ItemMeta grenadeMeta = grenade.getItemMeta();
        grenadeMeta.setDisplayName(ChatColor.GREEN + "Grenade");

        grenadeMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        grenadeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "\"Grenade goin' out!\"");
        lore.add("");
        lore.add(ChatColor.GRAY + "For " + ChatColor.GREEN + "hatmp4");

        grenadeMeta.setLore(lore);
        grenade.setItemMeta(grenadeMeta);

        RCAbility.addAbility(grenade, RCAbility.GRENADE_THROW);
        return grenade;
    }

    private static ItemStack createRocketLauncher() {
        ItemStack rocketLauncher = new ItemStack(Material.CROSSBOW);

        ItemMeta launcherMeta = rocketLauncher.getItemMeta();
        launcherMeta.setDisplayName(ChatColor.RED + "Rocket Launcher");

        launcherMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        launcherMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        rocketLauncher.setItemMeta(launcherMeta);

        LCAbility.addAbility(rocketLauncher, LCAbility.SHOOT_ROCKET);
        RCAbility.addAbility(rocketLauncher, RCAbility.ROCKET_BARRAGE);
        return rocketLauncher;
    }

    private static ItemStack createBanHammer() {
        ItemStack banHammer = new ItemStack(Material.NETHERITE_AXE);

        ItemMeta hammerMeta = banHammer.getItemMeta();
        hammerMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Ban Hammer");

        hammerMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        hammerMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        banHammer.setItemMeta(hammerMeta);

        LCAbility.addAbility(banHammer, LCAbility.BAN_HAMMER_SWING);
        RCAbility.addAbility(banHammer, RCAbility.LITIGATION);
        return banHammer;
    }

    private static ItemStack createBoombox() {
        ItemStack boombox = new ItemStack(Material.JUKEBOX);

        ItemMeta boomboxMeta = boombox.getItemMeta();
        boomboxMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Boombox");

        boomboxMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        boomboxMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Charges: " + ChatColor.YELLOW + "0");
        lore.add("");
        lore.add(ChatColor.RED + "Charges only increase while");
        lore.add(ChatColor.RED + "holding the boombox.");

        boomboxMeta.setLore(lore);
        boombox.setItemMeta(boomboxMeta);

        LCAbility.addAbility(boombox, LCAbility.BOOMBOX_RADIAL);
        RCAbility.addAbility(boombox, RCAbility.BOOMBOX_SHIELD);
        PassiveAbility.addAbility(boombox, PassiveAbility.BOOMBOX_BUFF);
        return boombox;
    }

    private static ItemStack createBeamSword() {
        ItemStack sword = new ItemStack(Material.IRON_SWORD);

        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.setDisplayName(ChatColor.GRAY + "Lost Temple Sword");

        swordMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        swordMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        swordMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        swordMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier("generic.attack_damage", 15, AttributeModifier.Operation.ADD_NUMBER));

        sword.setItemMeta(swordMeta);

        LCAbility.addAbility(sword, LCAbility.SWORD_SLASH);
        OnHitAbility.addAbility(sword, OnHitAbility.SWORD_SPIRIT);
        RCAbility.addAbility(sword, RCAbility.SWORD_BEAM);
        return sword;
    }

    private static ItemStack createGun() {
        ItemStack gun = new ItemStack(Material.SPECTRAL_ARROW);

        ItemMeta gunMeta = gun.getItemMeta();
        gunMeta.setDisplayName(ChatColor.AQUA + "Rifle");

        gunMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        gunMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        gunMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        gun.setItemMeta(gunMeta);

        LCAbility.addAbility(gun, LCAbility.SCYTHE_GUN_SHOOT);
        RCAbility.addAbility(gun, RCAbility.SWAP_GUN_TO_SCYTHE);
        return gun;
    }

    private static ItemStack createScythe() {
        ItemStack scythe = new ItemStack(Material.DIAMOND_HOE);

        ItemMeta scytheMeta = scythe.getItemMeta();
        scytheMeta.setDisplayName(ChatColor.AQUA + "Scythe");

        scytheMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        scytheMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        scythe.setItemMeta(scytheMeta);

        LCAbility.addAbility(scythe, LCAbility.SCYTHE_SWING);
        RCAbility.addAbility(scythe, RCAbility.SWAP_SCYTHE_TO_GUN);
        return scythe;
    }

    private static ItemStack createRadar() {
        ItemStack radar = new ItemStack(Material.RECOVERY_COMPASS);

        ItemMeta radarMeta = radar.getItemMeta();
        radarMeta.setDisplayName(ChatColor.GREEN + "Radar");

        radarMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        radarMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "State of the art enemy");
        lore.add(ChatColor.GRAY + "detection system.");

        radarMeta.setLore(lore);
        radar.setItemMeta(radarMeta);

        PassiveAbility.addAbility(radar, PassiveAbility.RADAR);
        return radar;
    }

    private static ItemStack createFlashbang() {
        ItemStack flashbang = new ItemStack(Material.SNOWBALL);

        ItemMeta flashbangMeta = flashbang.getItemMeta();
        flashbangMeta.setDisplayName(ChatColor.YELLOW + "Flashbang");

        flashbangMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        flashbangMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Probably not legal.");

        flashbangMeta.setLore(lore);
        flashbang.setItemMeta(flashbangMeta);

        RCAbility.addAbility(flashbang, RCAbility.FLASHBANG);
        return flashbang;
    }

    private static ItemStack createEasterEgg() {
        ItemStack egg = new ItemStack(Material.EGG);

        ItemMeta eggMeta = egg.getItemMeta();
        eggMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Easter Egg");

        eggMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        eggMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        egg.setItemMeta(eggMeta);

        RCAbility.addAbility(egg, RCAbility.EXPLODING_EGG);
        return egg;
    }

    private static ItemStack createTea() {
        ItemStack tea = new ItemStack(Material.POTION);
        PotionMeta teaMeta = (PotionMeta) tea.getItemMeta();

        teaMeta.setDisplayName(ChatColor.GOLD + "Infinite Tea");
        teaMeta.setColor(Color.BLACK);

        teaMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        tea.setItemMeta(teaMeta);

        RCAbility.addAbility(tea, RCAbility.CLEAR_BAD_EFFECTS);
        return tea;
    }

    private static ItemStack createAlcohol() {
        ItemStack alcohol = new ItemStack(Material.POTION);
        PotionMeta alcoholMeta = (PotionMeta) alcohol.getItemMeta();

        alcoholMeta.setDisplayName(ChatColor.RED + "Infinite Alcohol");
        alcoholMeta.setColor(Color.fromARGB(1, 173, 76, 7));

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "It's like real alcohol, but for kids!");
        lore.add("");
        lore.add(ChatColor.DARK_GRAY + "The finest moonshine on this");
        lore.add(ChatColor.DARK_GRAY + "side of the Mississippi!");

        alcoholMeta.setLore(lore);
        alcoholMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        alcohol.setItemMeta(alcoholMeta);

        RCAbility.addAbility(alcohol, RCAbility.INTOXICATED);
        return alcohol;
    }

    private static ItemStack createBleach() {
        ItemStack bleach = new ItemStack(Material.POTION);
        PotionMeta bleachMeta = (PotionMeta) bleach.getItemMeta();

        bleachMeta.setDisplayName(ChatColor.AQUA + "Infinite Bleach");
        bleachMeta.setColor(Color.WHITE);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "The popular cleaning product.");
        lore.add("");
        lore.add(ChatColor.GRAY + "Allows you to anoint food by clicking");
        lore.add(ChatColor.GRAY + "on it in your inventory with this bleach");
        lore.add(ChatColor.GRAY + "selected.");
        lore.add("");
        lore.add(ChatColor.RED + "Does not work in Creative Mode");

        bleachMeta.setLore(lore);
        bleachMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        bleach.setItemMeta(bleachMeta);

        RCAbility.addAbility(bleach, RCAbility.CLEANER);
        return bleach;
    }

    private static ItemStack createSpecialSugar() {
        ItemStack sugar = new ItemStack(Material.SUGAR, 4);

        ItemMeta sugarMeta = sugar.getItemMeta();
        sugarMeta.setDisplayName(color("&f\"Sugar\""));

        sugarMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        sugarMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.DARK_GRAY + "Totally legal!");

        sugarMeta.setLore(lore);
        sugar.setItemMeta(sugarMeta);

        return sugar;
    }

    private static ItemStack createMedication() {
        ItemStack meds = new ItemStack(Material.GHAST_TEAR);

        ItemMeta medsMeta = meds.getItemMeta();
        medsMeta.setDisplayName(ChatColor.RED + "\"Medication\"");

        medsMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        medsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Medication is the latest");
        lore.add(ChatColor.GRAY + "FDA approved, doctor");
        lore.add(ChatColor.GRAY + "recommended treatment option");
        lore.add(ChatColor.GRAY + "for people struggling with");
        lore.add(ChatColor.GRAY + "super lung cancer.");
        lore.add("");
        lore.add(ChatColor.GRAY + "Medication is a once daily");
        lore.add(ChatColor.GRAY + "pill that of course you won't");
        lore.add(ChatColor.GRAY + "be able to swallow.");
        lore.add("");
        lore.add(ChatColor.GRAY + "If taken as prescribed by");
        lore.add(ChatColor.GRAY + "a doctor, super lung cancer");
        lore.add(ChatColor.GRAY + "will be in remission within");
        lore.add(ChatColor.GRAY + "a few weeks of treatment.");
        lore.add("");
        lore.add(ChatColor.GRAY + "Side effects include but");
        lore.add(ChatColor.GRAY + "are not limited to: agility");
        lore.add(ChatColor.GRAY + "nausea, and enhanced strength.");
        lore.add("");
        lore.add(ChatColor.GRAY + "Alert your doctor if you are");
        lore.add(ChatColor.GRAY + "experiencing side effects,");
        lore.add(ChatColor.GRAY + "pregnant, or plan on becoming");
        lore.add(ChatColor.GRAY + "pregnant.");
        lore.add("");
        lore.add(ChatColor.GRAY + "Do not take Medication if");
        lore.add(ChatColor.GRAY + "you are allergic to it or");
        lore.add(ChatColor.GRAY + "any of its ingredients. Do not");
        lore.add(ChatColor.GRAY + "take it if your family has any");
        lore.add(ChatColor.GRAY + "history of addiction.");
        lore.add("");
        lore.add(ChatColor.GRAY + "Do not stop taking Medication");
        lore.add(ChatColor.GRAY + "unless your doctor tells you");
        lore.add(ChatColor.GRAY + "to or you are experiencing");
        lore.add(ChatColor.GRAY + "side effects.");
        lore.add("");
        lore.add(ChatColor.GRAY + "Ask your doctor today if");
        lore.add(ChatColor.GRAY + "Medication is the right option");
        lore.add(ChatColor.GRAY + "for you.");
        lore.add("");
        lore.add(ChatColor.GRAY + "If you cannot afford Medication,");
        lore.add(ChatColor.GRAY + "Medicare probably won't help.");

        medsMeta.setLore(lore);
        meds.setItemMeta(medsMeta);

        RCAbility.addAbility(meds, RCAbility.CANCER_CURE);
        return meds;
    }

    private static ItemStack createVaccine() {
        ItemStack vaccine = new ItemStack(Material.ARROW);

        ItemMeta vaccineMeta = vaccine.getItemMeta();
        vaccineMeta.setDisplayName(ChatColor.GOLD + "Reusable Coronavirus Vaccine");

        vaccineMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        vaccineMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "The newest innovation in");
        lore.add(ChatColor.GRAY + "medicine...");

        vaccineMeta.setLore(lore);
        vaccine.setItemMeta(vaccineMeta);

        LCAbility.addAbility(vaccine, LCAbility.VACCINE);
        return vaccine;
    }

    private static ItemStack createFang() {
        ItemStack fang = new ItemStack(Material.GHAST_TEAR);

        ItemMeta fangMeta = fang.getItemMeta();
        fangMeta.setDisplayName(ChatColor.RED + "Vampiric Fang");

        fangMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.DARK_RED + "It's Morbin' time...");

        fangMeta.setLore(lore);
        fang.setItemMeta(fangMeta);

        RCAbility.addAbility(fang, RCAbility.LIFE_DRAIN_BUFF);
        return fang;
    }

    private static ItemStack createCulmination() {
        ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);

        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.setDisplayName(color("&6&l&kZ &d&lCulmination &6&l&kZ"));

        swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 6, true);
        swordMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 6, true);
        swordMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 6, true);
        swordMeta.addEnchant(Enchantment.DURABILITY, 5, true);
        swordMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 5, true);
        swordMeta.addEnchant(Enchantment.MENDING, 1, true);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "The true most powerful sword...");

        swordMeta.setLore(lore);
        sword.setItemMeta(swordMeta);

        RCAbility.addAbility(sword, RCAbility.CALL_OF_THE_VOID);
        OnHitAbility.addAbility(sword, OnHitAbility.DRACONIAN_RAGE);
        return sword;
    }

    private static ItemStack createDecrepitScroll() {
        ItemStack scroll = new ItemStack(SKULL_BANNER_PATTERN);

        ItemMeta scrollMeta = scroll.getItemMeta();
        scrollMeta.setDisplayName(ChatColor.DARK_PURPLE + "Decrepit Scroll");
        scrollMeta.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);

        scrollMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        scrollMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "A mysterious mythic scroll...");

        scrollMeta.setLore(lore);
        scroll.setItemMeta(scrollMeta);

        RCAbility.addAbility(scroll, RCAbility.SCROLL_POWER);
        return scroll;
    }

    private static ItemStack createWeaponHandle() {
        ItemStack handle = new ItemStack(Material.BLAZE_ROD);

        ItemMeta handleMeta = handle.getItemMeta();
        handleMeta.setDisplayName(ChatColor.GOLD + "Mythic Handle");

        handleMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        handleMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "A handle that looks to");
        lore.add(ChatColor.GRAY + "be used for many weapons...");

        handleMeta.setLore(lore);
        handle.setItemMeta(handleMeta);

        return handle;
    }

    private static ItemStack createEnchantedPearl() {
        ItemStack pearl = new ItemStack(Material.ENDER_PEARL);

        ItemMeta pearlMeta = pearl.getItemMeta();
        pearlMeta.setDisplayName(ChatColor.AQUA + "Enchanted Ender Pearl");

        pearlMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        pearlMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        pearl.setItemMeta(pearlMeta);
        return pearl;
    }

    private static ItemStack createEndlessPearl() {
        ItemStack pearl = new ItemStack(Material.ENDER_PEARL);

        ItemMeta pearlMeta = pearl.getItemMeta();
        pearlMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Endless Ender Pearl");

        pearlMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        pearlMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "An infinite Ender Pearl");

        pearlMeta.setLore(lore);
        pearl.setItemMeta(pearlMeta);

        RCAbility.addAbility(pearl, RCAbility.THROW_PEARL);
        return pearl;
    }

    private static ItemStack createBerserkSword() {
        ItemStack sword = new ItemStack(Material.IRON_SWORD);

        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.setDisplayName(color("&7Berserk Sword"));

        swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
        swordMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 3, true);
        swordMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 3, true);

        sword.setItemMeta(swordMeta);

        RCAbility.addAbility(sword, RCAbility.RAGE);
        OnHitAbility.addAbility(sword, OnHitAbility.BLOODLUST);
        return sword;
    }

    private static ItemStack createDragonSword() {
        ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);

        ItemMeta dragonSwordMeta = sword.getItemMeta();
        dragonSwordMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Dragon Sword");

        dragonSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
        dragonSwordMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 3, true);
        dragonSwordMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 3, true);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "The most powerful sword in existence...");

        dragonSwordMeta.setLore(lore);

        dragonSwordMeta.setUnbreakable(true);
        dragonSwordMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        sword.setItemMeta(dragonSwordMeta);

        RCAbility.addAbility(sword, RCAbility.VOID_ENERGY);
        return sword;
    }

    private static ItemStack createHammer() {
        ItemStack hammer = new ItemStack(Material.IRON_AXE);

        ItemMeta hammerMeta = hammer.getItemMeta();
        hammerMeta.setDisplayName(ChatColor.DARK_PURPLE + "Super Hammer");

        hammerMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
        hammerMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 2, true);
        hammerMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 2, true);

        hammerMeta.setUnbreakable(true);
        hammerMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        hammerMeta.setCustomModelData(69);

        hammer.setItemMeta(hammerMeta);

        RCAbility.addAbility(hammer, RCAbility.HAMMER_THROW);
        return hammer;
    }

    private static ItemStack createWardenChestplate() {
        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);

        ArmorMeta chestplateMeta = (ArmorMeta) chestplate.getItemMeta();
        chestplateMeta.setDisplayName(ChatColor.DARK_AQUA + "Warden Chestplate");

        chestplateMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
        chestplateMeta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 6, true);
        chestplateMeta.addEnchant(Enchantment.PROTECTION_FIRE, 6, true);
        chestplateMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 6, true);
        chestplateMeta.addEnchant(Enchantment.DURABILITY, 5, true);

        chestplateMeta.setTrim(new ArmorTrim(TrimMaterial.DIAMOND, TrimPattern.SILENCE));

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "This chestplate must have belonged");
        lore.add(ChatColor.GRAY + "to the " + ChatColor.DARK_AQUA + "Warden" + ChatColor.GRAY + "...");

        chestplateMeta.setLore(lore);
        chestplate.setItemMeta(chestplateMeta);

        OnDamagedAbility.addAbility(chestplate, OnDamagedAbility.RETALIATION_RUSH);
        return chestplate;
    }

    private static ItemStack createBat() {
        ItemStack bat = new ItemStack(Material.STICK);

        ItemMeta batMeta = bat.getItemMeta();
        batMeta.setDisplayName(color("&6Wooden Bat"));

        batMeta.addEnchant(Enchantment.KNOCKBACK, 3, true);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "\"Think fast, Chucklenutts!\" - TF2 Scout");

        batMeta.setLore(lore);
        bat.setItemMeta(batMeta);

        return bat;
    }

    private static ItemStack createZombieSword() {
        ItemStack sword = new ItemStack(Material.IRON_SWORD);

        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.setDisplayName(color("&2Undead Sword"));

        swordMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 2, true);

        sword.setItemMeta(swordMeta);
        return sword;
    }

    private static ItemStack createSpiderSword() {
        ItemStack sword = new ItemStack(Material.IRON_SWORD);

        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.setDisplayName(color("&cSpider Sword"));

        swordMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 2, true);

        sword.setItemMeta(swordMeta);
        return sword;
    }

    private static ItemStack setMiniCrits(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        if(meta.hasLore()) {
            lore = (ArrayList<String>) meta.getLore();
        }
        lore.add("");
        lore.add(color("&cCritical Hits become Mini Crits"));

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack setNoCrits(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        if(meta.hasLore()) {
            lore = (ArrayList<String>) meta.getLore();
        }
        lore.add("");
        lore.add(color("&cNo Critical Hits or Mini Crits"));

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack setAlwaysCrits(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        if(meta.hasLore()) {
            lore = (ArrayList<String>) meta.getLore();
        }
        lore.add("");
        lore.add(color("&aGuaranteed Critical Hits"));

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    private static void postLore() {
        for(ItemStack item : customItems) {
            ItemMeta meta = item.getItemMeta();

            if(meta.hasLore()) {
                ArrayList<String> lore = (ArrayList<String>) meta.getLore();
                lore.add("");
                lore.add(color("&bCustom Item"));
                if(meta.isUnbreakable()) {
                    lore.add("");
                    lore.add(color("&6&lUnbreakable"));
                }
                lore.add(ChatColor.DARK_GRAY + "Special ID: " + customItems.indexOf(item));
                meta.setLore(lore);
            } else {
                ArrayList<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(color("&bCustom Item"));
                if(meta.isUnbreakable()) {
                    lore.add("");
                    lore.add(color("&6&lUnbreakable"));
                }
                lore.add(ChatColor.DARK_GRAY + "Special ID: " + customItems.indexOf(item));
                meta.setLore(lore);
            }

            item.setItemMeta(meta);
        }
    }

    private static ItemStack setSeries(ItemStack item, String series) {
        ItemMeta meta = item.getItemMeta();

        if(meta.hasLore()) {
            ArrayList<String> lore = (ArrayList<String>) meta.getLore();
            lore.add("");
            lore.add(series + ChatColor.GRAY + " Series");
            meta.setLore(lore);
        } else {
            ArrayList<String> lore = new ArrayList<>();
            lore.add("");
            lore.add(series + ChatColor.GRAY + " Series");
            meta.setLore(lore);
        }

        item.setItemMeta(meta);
        return item;
    }
}

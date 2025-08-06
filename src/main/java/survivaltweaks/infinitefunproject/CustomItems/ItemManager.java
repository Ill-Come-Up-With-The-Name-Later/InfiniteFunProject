package survivaltweaks.infinitefunproject.CustomItems;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.*;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.OnDamagedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.PassiveAbility;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.TriggeredAbility;
import survivaltweaks.infinitefunproject.CustomItems.Unusual.Unusual;
import survivaltweaks.infinitefunproject.Player.Upgrades.InitUpgrades;

import java.util.ArrayList;

import static org.bukkit.Material.*;
import static survivaltweaks.infinitefunproject.CustomItems.Unusual.Unusual.addUnusual;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public class ItemManager {

    /**
     * Item series
     */
    enum Series {

        PHIGHTING(color("&bPHIGHTING!")),
        LETHAL_COMPANY(color("&4Lethal Company")),
        TEAM_FORTRESS_2(color("&6Team Fortress 2")),
        COUNTER_STRIKE(color("&aCounter-Strike")),
        RETURNING(color("&eReturning")),
        DEVELOPER(color("&d&lDeveloper"))
        ;

        private final String name;

        Series(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static ArrayList<ItemStack> customItems;

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
    public static ItemStack smasher;
    public static ItemStack deflectorHelmet;
    public static ItemStack shinyKnife;
    public static ItemStack remedy;
    public static ItemStack impregnationCannon;
    public static ItemStack rocketJumper;
    public static ItemStack chainBow;
    public static ItemStack thunderAxe;
    public static ItemStack star;
    public static ItemStack bounceBow;
    public static ItemStack directHit;
    public static ItemStack superCritPotion;
    public static ItemStack soulBoots;
    public static ItemStack giantPants;
    public static ItemStack controlCrystal;
    public static ItemStack magicWand;
    public static ItemStack guidedMissileLauncher;
    public static ItemStack critPotion;
    public static ItemStack ramAxe;
    public static ItemStack meteorFragment;
    public static ItemStack unusualifier;
    public static ItemStack unusualDictionary;
    public static ItemStack writtenDictionary;
    public static ItemStack specialUnusualifier;
    public static ItemStack cosmeticCrate;
    public static ItemStack weaponCrate;
    public static ItemStack forwardArrow;
    public static ItemStack backArrow;
    public static ItemStack specialCrate;
    public static ItemStack obsidianDome;
    public static ItemStack hathat;
    public static ItemStack terminator;
    public static ItemStack burningFlame;
    public static ItemStack nukeLauncher;

    /**
     * Create all custom items
     */
    public static void init() {
        customItems = new ArrayList<>();

        bat = setSeries(setSeries(createBat(), Series.TEAM_FORTRESS_2), Series.RETURNING);
        customItems.add(bat);

        zombieSword = createZombieSword();
        customItems.add(zombieSword);

        spiderSword = createSpiderSword();
        customItems.add(spiderSword);

        wardenChestplate = setSeries(createWardenChestplate(), Series.RETURNING);
        customItems.add(wardenChestplate);

        endlessPearl = createEndlessPearl();
        customItems.add(endlessPearl);

        enchantedPearl = createEnchantedPearl();
        customItems.add(enchantedPearl);

        weaponHandle = createWeaponHandle();
        customItems.add(weaponHandle);

        decrepitScroll = setMiniCrits(createDecrepitScroll());
        customItems.add(decrepitScroll);

        culmination = setMiniCrits(createCulmination());
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

        easterEgg = setSeries(setNoCrits(createEasterEgg()), Series.LETHAL_COMPANY);
        customItems.add(easterEgg);

        flashbang = setSeries(createFlashbang(), Series.COUNTER_STRIKE);
        customItems.add(flashbang);

        radar = createRadar();
        customItems.add(radar);

        scythe = setSeries(createScythe(), Series.PHIGHTING);
        customItems.add(scythe);

        beamSword = setSeries(createBeamSword(), Series.PHIGHTING);
        customItems.add(beamSword);

        gun = setSeries(createGun(), Series.PHIGHTING);
        customItems.add(gun);

        boombox = setSeries(createBoombox(), Series.PHIGHTING);
        customItems.add(boombox);

        banHammer = setSeries(createBanHammer(), Series.PHIGHTING);
        customItems.add(banHammer);

        rocketLauncher = setSeries(createRocketLauncher(), Series.PHIGHTING);
        customItems.add(rocketLauncher);

        grenade = createGrenade();
        customItems.add(grenade);

        hyperLaser = setSeries(createHyperLaser(), Series.PHIGHTING);
        customItems.add(hyperLaser);

        spaceTripmine = setSeries(createSubspace(), Series.PHIGHTING);
        customItems.add(spaceTripmine);

        katana = setSeries(createKatana(), Series.PHIGHTING);
        customItems.add(katana);

        slingshot = setSeries(createSlingshot(), Series.PHIGHTING);
        customItems.add(slingshot);

        medkit = setSeries(createMedkit(), Series.PHIGHTING);
        customItems.add(medkit);

        biograftSword = setSeries(createBioSword(), Series.PHIGHTING);
        customItems.add(biograftSword);

        vineStaff = setSeries(createVineStaff(), Series.PHIGHTING);
        customItems.add(vineStaff);

        shuriken = setSeries(createShuriken(), Series.PHIGHTING);
        customItems.add(shuriken);

        critSoda = setSeries(createCritSoda(), Series.TEAM_FORTRESS_2);
        customItems.add(critSoda);

        playbook = createPlaybook();
        customItems.add(playbook);

        inphernalBase = setSeries(createInphernalBase(), Series.PHIGHTING);
        customItems.add(inphernalBase);

        doubleBarrel = setSeries(setMiniCrits(createDoubleBarrel()), Series.LETHAL_COMPANY);
        customItems.add(doubleBarrel);

        comingSoon = createComingSoon();

        placeholder = createPlaceholder();

        upgradeCatalyst = createUpgradeCatalyst();
        customItems.add(upgradeCatalyst);

        butterflyKnife = setSeries(createButterflyKnife(), Series.TEAM_FORTRESS_2);
        customItems.add(butterflyKnife);

        executionThread = setSeries(createExecutionThread(), Series.PHIGHTING);
        customItems.add(executionThread);

        revolver = createRevolver();
        customItems.add(revolver);

        medkitRevolver = setSeries(createMedkitRevolver(), Series.PHIGHTING);
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

        callisto = setSeries(createCallisto(), Series.RETURNING);
        customItems.add(callisto);

        triton = setSeries(createTriton(), Series.RETURNING);
        customItems.add(triton);

        ascensionGaze = setSeries(createAscensionGaze(), Series.RETURNING);
        customItems.add(ascensionGaze);

        dirt = setSeries(createDirt(), Series.RETURNING);
        customItems.add(dirt);

        railgun = setSeries(createRailgun(), Series.RETURNING);
        customItems.add(railgun);

        scannerHelmet = setSeries(createScannerHelmet(), Series.LETHAL_COMPANY);
        customItems.add(scannerHelmet);

        oneHitMode = createOneHitMode();
        customItems.add(oneHitMode);

        challengeModeBrowser = createChallengeModeBrowser();
        customItems.add(challengeModeBrowser);

        smasher = createSmasher();
        customItems.add(smasher);

        deflectorHelmet = createDeflectorHelm();
        customItems.add(deflectorHelmet);

        shinyKnife = createShinyKnife();
        customItems.add(shinyKnife);

        remedy = setSeries(createRemedy(), Series.RETURNING);
        customItems.add(remedy);

        impregnationCannon = createImpregnationCannon();
        customItems.add(impregnationCannon);

        rocketJumper = setSeries(createRocketJumper(), Series.TEAM_FORTRESS_2);
        customItems.add(rocketJumper);

        chainBow = createChainBow();
        customItems.add(chainBow);

        thunderAxe = createThunderAxe();
        customItems.add(thunderAxe);

        star = setSuperCrits(createStar());
        customItems.add(star);

        bounceBow = createBounceBow();
        customItems.add(bounceBow);

        directHit = setSeries(createDirectHit(), Series.TEAM_FORTRESS_2);
        customItems.add(directHit);

        superCritPotion = createSuperCritPotion();
        customItems.add(superCritPotion);

        soulBoots = createSoulBoots();
        customItems.add(soulBoots);

        giantPants = createGiantPants();
        customItems.add(giantPants);

        controlCrystal = createControlCrystal();
        customItems.add(controlCrystal);

        magicWand = createMagicWand();
        customItems.add(magicWand);

        guidedMissileLauncher = createGuidedMissileLauncher();
        customItems.add(guidedMissileLauncher);

        critPotion = createCritPotion();
        customItems.add(critPotion);

        ramAxe = setNoCrits(createRamAxe());
        customItems.add(ramAxe);

        meteorFragment = createMeteorFragment();
        customItems.add(meteorFragment);

        unusualifier = createUnusualifier();
        customItems.add(unusualifier);

        unusualDictionary = createUnusualDictionary();
        customItems.add(unusualDictionary);

        writtenDictionary = createSpecialDictionary();
        customItems.add(writtenDictionary);

        specialUnusualifier = createSpecialUnusualifier();
        customItems.add(specialUnusualifier);

        cosmeticCrate = setSeries(createCosmeticCrate(), Series.TEAM_FORTRESS_2);
        customItems.add(cosmeticCrate);

        weaponCrate = setSeries(createWeaponCrate(), Series.TEAM_FORTRESS_2);
        customItems.add(weaponCrate);

        specialCrate = setSeries(createSpecialCrate(), Series.TEAM_FORTRESS_2);
        customItems.add(specialCrate);

        forwardArrow = createForwardArrow();

        backArrow = createBackArrow();

        obsidianDome = createObsidianDome();
        customItems.add(obsidianDome);

        hathat = setSeries(setSeries(createHatHat(), Series.RETURNING), Series.DEVELOPER);
        customItems.add(hathat);

        terminator = createTerminator();
        customItems.add(terminator);

        burningFlame = setSeries(createBurningFlame(), Series.DEVELOPER);
        customItems.add(burningFlame);

        nukeLauncher = createNukeLauncher();
        customItems.add(nukeLauncher);

        postLore();
    }

    private static ItemStack createNukeLauncher() {
        ItemStack launcher = new ItemStack(CROSSBOW);
        ItemMeta launcherMeta = launcher.getItemMeta();

        launcherMeta.setDisplayName(ChatColor.GREEN + "Nuclear Bomb Launcher");

        launcher.setItemMeta(launcherMeta);

        RCAbility.addAbility(launcher, RCAbility.LAUNCH_NUKE);
        return launcher;
    }

    private static ItemStack createBurningFlame() {
        ItemStack burningFlame = new ItemStack(NETHERITE_HELMET);
        ItemMeta flameMeta = burningFlame.getItemMeta();

        flameMeta.setDisplayName(ChatColor.GOLD + "Burning Flame");

        flameMeta.addEnchant(Enchantment.PROTECTION, 5, true);
        flameMeta.addEnchant(Enchantment.FIRE_PROTECTION, 5, true);
        flameMeta.addEnchant(Enchantment.BLAST_PROTECTION, 5, true);
        flameMeta.addEnchant(Enchantment.PROJECTILE_PROTECTION, 5, true);
        flameMeta.addEnchant(Enchantment.UNBREAKING, 7, true);
        flameMeta.addEnchant(Enchantment.MENDING, 2, true);

        burningFlame.setItemMeta(flameMeta);

        SneakAbility.addAbility(burningFlame, SneakAbility.SUPER_JUMP);
        PassiveAbility.addAbility(burningFlame, PassiveAbility.VIGOR);
        OnDamagedAbility.addAbility(burningFlame, OnDamagedAbility.FLAMING_THORN);

        addUnusual(burningFlame, Unusual.FURIOUS_FLAMES);
        return burningFlame;
    }

    private static ItemStack createTerminator() {
        ItemStack terminator = new ItemStack(BOW);
        ItemMeta terminatorMeta = terminator.getItemMeta();

        terminatorMeta.setDisplayName(ChatColor.GOLD + "Terminator");

        terminator.setItemMeta(terminatorMeta);

        LCAbility.addAbility(terminator, LCAbility.SALVATION);
        RCAbility.addAbility(terminator, RCAbility.TRIPLE_SHOT);

        addUnusual(terminator, Unusual.DEATH_RAY);
        return terminator;
    }

    private static ItemStack createHatHat() {
        ItemStack hat = new ItemStack(LEATHER_HELMET);
        LeatherArmorMeta meta = (LeatherArmorMeta) hat.getItemMeta();

        meta.setDisplayName(ChatColor.GREEN + "Hat's Hat");
        meta.setColor(Color.LIME);
        meta.addItemFlags(ItemFlag.HIDE_DYE);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "The legendary hat of");
        lore.add(ChatColor.GREEN + "Hat" + ChatColor.GRAY + ".");

        meta.setLore(lore);
        hat.setItemMeta(meta);

        SneakAbility.addAbility(hat, SneakAbility.SONIC_BLAST);

        addUnusual(hat, Unusual.GREEN_MACHINE);
        return hat;
    }

    private static ItemStack createObsidianDome() {
        ItemStack dome = new ItemStack(REPEATER);
        ItemMeta domeMeta = dome.getItemMeta();

        domeMeta.setDisplayName(ChatColor.YELLOW + "Meteor Defense System");

        domeMeta.addEnchant(Enchantment.UNBREAKING, 1, false);
        domeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.LIGHT_PURPLE + "Obsidian Dome Missile Defense");
        lore.add(ChatColor.LIGHT_PURPLE + "System");
        lore.add("");
        lore.add(ChatColor.GRAY + "Intercepts incoming meteors.");

        domeMeta.setLore(lore);
        dome.setItemMeta(domeMeta);

        LCAbility.addAbility(dome, LCAbility.INTERCEPT_METEORS);
        return dome;
    }

    private static ItemStack createSpecialCrate() {
        ItemStack crate = new ItemStack(CHEST);
        ItemMeta crateMeta = crate.getItemMeta();

        crateMeta.setDisplayName(ChatColor.GOLD + "Unlocked Mann Co. Supply Crate");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.AQUA + "Crate Series: #3");
        lore.add("");
        lore.add(ChatColor.GRAY + "Contains a random custom");
        lore.add(ChatColor.GRAY + "item.");

        crateMeta.setLore(lore);
        crate.setItemMeta(crateMeta);

        RCAbility.addAbility(crate, RCAbility.UNBOX_CUSTOM_ITEM);
        return crate;
    }

    private static ItemStack createForwardArrow() {
        ItemStack arrow = new ItemStack(ARROW);
        ItemMeta arrowMeta = arrow.getItemMeta();

        arrowMeta.setDisplayName(ChatColor.GREEN + "Next Page");

        arrow.setItemMeta(arrowMeta);
        return arrow;
    }

    private static ItemStack createBackArrow() {
        ItemStack arrow = new ItemStack(ARROW);
        ItemMeta arrowMeta = arrow.getItemMeta();

        arrowMeta.setDisplayName(ChatColor.GREEN + "Previous Page");

        arrow.setItemMeta(arrowMeta);
        return arrow;
    }

    private static ItemStack createCosmeticCrate() {
        ItemStack crate = new ItemStack(CHEST);
        ItemMeta crateMeta = crate.getItemMeta();

        crateMeta.setDisplayName(ChatColor.GOLD + "Unlocked Mann Co. Supply Crate");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.AQUA + "Crate Series: #1");
        lore.add("");
        lore.add(ChatColor.GRAY + "Contains a random helmet");
        lore.add(ChatColor.GRAY + "or boots.");
        lore.add("");
        lore.add(ChatColor.GRAY + "Contents have a low chance");
        lore.add(ChatColor.GRAY + "of being Unusual.");

        crateMeta.setLore(lore);
        crate.setItemMeta(crateMeta);

        RCAbility.addAbility(crate, RCAbility.UNBOX_WEARABLE);
        return crate;
    }

    private static ItemStack createWeaponCrate() {
        ItemStack crate = new ItemStack(CHEST);
        ItemMeta crateMeta = crate.getItemMeta();

        crateMeta.setDisplayName(ChatColor.GOLD + "Unlocked Mann Co. Supply Crate");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.AQUA + "Crate Series: #2");
        lore.add("");
        lore.add(ChatColor.GRAY + "Contains a random weapon.");
        lore.add("");
        lore.add(ChatColor.GRAY + "Contents have a low chance");
        lore.add(ChatColor.GRAY + "of being Unusual.");

        crateMeta.setLore(lore);
        crate.setItemMeta(crateMeta);

        RCAbility.addAbility(crate, RCAbility.UNBOX_WEAPON);
        return crate;
    }

    private static ItemStack createSpecialUnusualifier() {
        ItemStack specialUnusualifier = new ItemStack(NETHER_STAR);
        ItemMeta unusualifierMeta = specialUnusualifier.getItemMeta();

        unusualifierMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Special Unusualifier");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Used to set a desired");
        lore.add(ChatColor.GRAY + "Unusual effect to apply");
        lore.add(ChatColor.GRAY + "to an item.");
        lore.add("");
        lore.add(ChatColor.LIGHT_PURPLE + "Use this on a helmet, boots,");
        lore.add(ChatColor.LIGHT_PURPLE + "or a weapon to apply the");
        lore.add(ChatColor.LIGHT_PURPLE + "chosen Unusual effect to it.");
        lore.add("");
        lore.add(ChatColor.RED + "ONE TIME USE!");
        lore.add(ChatColor.RED + "CAN ONLY APPLY IN SURVIVAL MODE!");

        unusualifierMeta.setLore(lore);
        specialUnusualifier.setItemMeta(unusualifierMeta);

        addUnusual(specialUnusualifier, Unusual.NONE);

        RCAbility.addAbility(specialUnusualifier, RCAbility.SELECT_UNUSUAL);
        return specialUnusualifier;
    }

    private static ItemStack createSpecialDictionary() {
        ItemStack book = new ItemStack(WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();

        bookMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Unusual Guide");
        bookMeta.setTitle(ChatColor.LIGHT_PURPLE + "Unusual Guide");

        bookMeta.setAuthor(ChatColor.GREEN + "Project: Infinite Fun");

        int sumWeights = 0;

        for(Unusual unusual : Unusual.values()) {
            sumWeights += unusual.getWeight();
        }

        ArrayList<String> pages = new ArrayList<>();
        StringBuilder page = new StringBuilder();

        for(int i = 0; i < Unusual.values().length; i++) {
            if(i % 8 == 0 && i != 0) {
                pages.add(page.toString());
                page = new StringBuilder();
            }

            if(Unusual.values()[i].getWeight() == 0) {
                continue;
            }

            page.append(ChatColor.GRAY + "- " + ChatColor.DARK_PURPLE + Unusual.values()[i].getName() + ChatColor.GRAY + ": "
                    + ChatColor.GOLD + String.format("%.2f", ((float) Unusual.values()[i].getWeight() / (float) sumWeights) * 100f)
                    + ChatColor.GRAY + "%\n");
        }

        pages.add(page.toString());

        bookMeta.setPages(pages);
        book.setItemMeta(bookMeta);

        return book;
    }

    private static ItemStack createUnusualDictionary() {
        ItemStack dictionary = new ItemStack(BOOK);
        ItemMeta dictionaryMeta = dictionary.getItemMeta();

        dictionaryMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Unusual Dictionary");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Shows the chance to get");
        lore.add(ChatColor.GRAY + "any unusual effect from an");
        lore.add(ChatColor.GRAY + "Unusualifier.");
        lore.add("");

        int sumWeights = 0;

        for(Unusual unusual : Unusual.values()) {
            sumWeights += unusual.getWeight();
        }

        for(Unusual unusual : Unusual.values()) {
            if(unusual.getWeight() == 0) {
                continue;
            }

            lore.add(ChatColor.GRAY + "- " + ChatColor.DARK_PURPLE + unusual.getName() + ChatColor.GRAY + ": "
                    + ChatColor.GOLD + String.format("%.2f", ((float) unusual.getWeight() / (float) sumWeights) * 100f) +
                    ChatColor.GRAY + "%");
        }

        dictionaryMeta.setLore(lore);
        dictionary.setItemMeta(dictionaryMeta);

        return dictionary;
    }

    private static ItemStack createUnusualifier() {
        ItemStack converter = new ItemStack(Material.RECOVERY_COMPASS);
        ItemMeta converterMeta = converter.getItemMeta();

        converterMeta.setDisplayName(ChatColor.DARK_PURPLE + "Unusualifier");
        converterMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
        converterMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Add this to an item by clicking");
        lore.add(ChatColor.GRAY + "on it while this is selected in");
        lore.add(ChatColor.GRAY + "your inventory.");
        lore.add("");
        lore.add(ChatColor.LIGHT_PURPLE + "Use this on a helmet, boots,");
        lore.add(ChatColor.LIGHT_PURPLE + "or a weapon to apply a");
        lore.add(ChatColor.LIGHT_PURPLE + "random Unusual effect to it.");
        lore.add("");
        lore.add(ChatColor.RED + "ONE TIME USE!");
        lore.add(ChatColor.RED + "CAN ONLY APPLY IN SURVIVAL MODE!");

        converterMeta.setLore(lore);
        converter.setItemMeta(converterMeta);

        RCAbility.addAbility(converter, RCAbility.SHOW_UNUSUALS);
        return converter;
    }

    private static ItemStack createMeteorFragment() {
        ItemStack fragment = new ItemStack(MAGMA_BLOCK);

        ItemMeta fragmentMeta = fragment.getItemMeta();
        fragmentMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Meteor Fragment");

        fragmentMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
        fragmentMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "A chunk of an interstellar");
        lore.add(ChatColor.GRAY + "meteor that was intercepted.");

        fragmentMeta.setLore(lore);
        fragment.setItemMeta(fragmentMeta);

        return fragment;
    }

    private static ItemStack createRamAxe() {
        ItemStack ramAxe = new ItemStack(DIAMOND_AXE);

        ItemMeta axeMeta = ramAxe.getItemMeta();
        axeMeta.setDisplayName(ChatColor.RED + "Ram Axe");

        axeMeta.addEnchant(Enchantment.SHARPNESS, 6, true);
        axeMeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
        axeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ramAxe.setItemMeta(axeMeta);

        PassiveAbility.addAbility(ramAxe, PassiveAbility.SPEED_BOOST);
        OnHitAbility.addAbility(ramAxe, OnHitAbility.VELOCITY_DAMAGE_BOOST);
        OnKillAbility.addAbility(ramAxe, OnKillAbility.KILL_SPEED_BOOST);
        return ramAxe;
    }

    private static ItemStack createGuidedMissileLauncher() {
        ItemStack launcher = new ItemStack(COMPARATOR);

        ItemMeta launcherMeta = launcher.getItemMeta();
        launcherMeta.setDisplayName(ChatColor.RED + "Guided Missile Launcher");

        launcher.setItemMeta(launcherMeta);

        LCAbility.addAbility(launcher, LCAbility.GUIDED_MISSILE);
        return launcher;
    }

    private static ItemStack createMagicWand() {
        ItemStack wand = new ItemStack(BLAZE_ROD);

        ItemMeta wandMeta = wand.getItemMeta();
        wandMeta.setDisplayName(ChatColor.DARK_PURPLE + "Magical Wand");

        wandMeta.addEnchant(Enchantment.UNBREAKING, 1, false);
        wandMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "A magical wand capable");
        lore.add(ChatColor.GRAY + "of casting powerful");
        lore.add(ChatColor.GRAY + "spells.");

        wandMeta.setLore(lore);
        wand.setItemMeta(wandMeta);

        LCAbility.addAbility(wand, LCAbility.BLAST_PULSE);
        RCAbility.addAbility(wand, RCAbility.CURSE_ENEMY);
        PassiveAbility.addAbility(wand, PassiveAbility.DETARGET_PLAYER);
        OnHitAbility.addAbility(wand, OnHitAbility.MAGIC_LIFESTEAL);
        OnKillAbility.addAbility(wand, OnKillAbility.EXPLOSIVE_KILL);
        SneakAbility.addAbility(wand, SneakAbility.RADIAL_BLASTS);
        return wand;
    }

    private static ItemStack createControlCrystal() {
        ItemStack crystal = new ItemStack(AMETHYST_CLUSTER);

        ItemMeta crystalMeta = crystal.getItemMeta();
        crystalMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Crystal of Control");

        crystalMeta.addEnchant(Enchantment.UNBREAKING, 1, false);
        crystalMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "A magical crystal capable");
        lore.add(ChatColor.GRAY + "of manipulating enemies to");
        lore.add(ChatColor.GRAY + "sow internal chaos.");

        crystalMeta.setLore(lore);
        crystal.setItemMeta(crystalMeta);

        RCAbility.addAbility(crystal, RCAbility.CONTROL_ENEMIES);
        PassiveAbility.addAbility(crystal, PassiveAbility.DETARGET_PLAYER);
        return crystal;
    }

    private static ItemStack createGiantPants() {
        ItemStack pants = new ItemStack(DIAMOND_LEGGINGS);

        ArmorMeta pantsMeta = (ArmorMeta) pants.getItemMeta();
        pantsMeta.setDisplayName(ChatColor.GREEN + "Giant's Pants");

        pantsMeta.addEnchant(Enchantment.PROTECTION, 5, true);
        pantsMeta.addEnchant(Enchantment.BLAST_PROTECTION, 5, true);
        pantsMeta.addEnchant(Enchantment.FIRE_PROTECTION, 5, true);
        pantsMeta.addEnchant(Enchantment.PROJECTILE_PROTECTION, 5, true);
        pantsMeta.addEnchant(Enchantment.UNBREAKING, 20, true);
        pantsMeta.addEnchant(Enchantment.MENDING, 1, true);

        pantsMeta.setTrim(new ArmorTrim(TrimMaterial.EMERALD, TrimPattern.BOLT));

        pantsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        pantsMeta.addItemFlags(ItemFlag.HIDE_ARMOR_TRIM);

        pants.setItemMeta(pantsMeta);

        SneakAbility.addAbility(pants, SneakAbility.GROW_GIANT);
        PassiveAbility.addAbility(pants, PassiveAbility.STOMP);
        return pants;
    }

    private static ItemStack createSoulBoots() {
        ItemStack boots = new ItemStack(NETHERITE_BOOTS);

        ArmorMeta bootMeta = (ArmorMeta) boots.getItemMeta();
        bootMeta.setDisplayName(ChatColor.DARK_GRAY + "Soul Boots");

        bootMeta.addEnchant(Enchantment.SOUL_SPEED, 3, true);
        bootMeta.addEnchant(Enchantment.PROTECTION, 5, true);
        bootMeta.addEnchant(Enchantment.BLAST_PROTECTION, 5, true);
        bootMeta.addEnchant(Enchantment.FIRE_PROTECTION, 5, true);
        bootMeta.addEnchant(Enchantment.PROJECTILE_PROTECTION, 5, true);
        bootMeta.addEnchant(Enchantment.UNBREAKING, 20, true);
        bootMeta.addEnchant(Enchantment.MENDING, 1, true);

        bootMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        bootMeta.addItemFlags(ItemFlag.HIDE_ARMOR_TRIM);

        bootMeta.setTrim(new ArmorTrim(TrimMaterial.DIAMOND, TrimPattern.RIB));

        boots.setItemMeta(bootMeta);

        PassiveAbility.addAbility(boots, PassiveAbility.AT_ONE_SOULS);
        PassiveAbility.addAbility(boots, PassiveAbility.SOUL_DRAIN);
        OnDamagedAbility.addAbility(boots, OnDamagedAbility.SOUL_HARVEST);
        return boots;
    }

    private static ItemStack createCritPotion() {
        ItemStack potion = new ItemStack(POTION);

        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Crit Potion");

        meta.addEnchant(Enchantment.UNBREAKING, 1, true);

        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setColor(Color.fromRGB(0, 255, 0));

        potion.setItemMeta(meta);

        RCAbility.addAbility(potion, RCAbility.CRIT_POTION);
        return potion;
    }

    private static ItemStack createSuperCritPotion() {
        ItemStack potion = new ItemStack(POTION);

        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Super Crit-a-Cola");

        meta.addEnchant(Enchantment.UNBREAKING, 1, true);

        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setColor(Color.fromRGB(255, 0, 255));

        potion.setItemMeta(meta);

        RCAbility.addAbility(potion, RCAbility.SUPER_CRIT_POTION);
        return potion;
    }

    private static ItemStack createDirectHit() {
        ItemStack directHit = new ItemStack(CROSSBOW);
        ItemMeta hitMeta = directHit.getItemMeta();

        hitMeta.setDisplayName(ChatColor.GRAY + "Direct Hit");

        directHit.setItemMeta(hitMeta);

        LCAbility.addAbility(directHit, LCAbility.DIRECT_HIT_ROCKET);
        OnHitAbility.addAbility(directHit, OnHitAbility.DIRECT_HIT);
        return directHit;
    }

    private static ItemStack createBounceBow() {
        ItemStack bow = new ItemStack(BOW);
        ItemMeta bowMeta = bow.getItemMeta();

        bowMeta.setDisplayName(ChatColor.GREEN + "Bouncy Blast Bow");

        bow.setItemMeta(bowMeta);

        PassiveAbility.addAbility(bow, PassiveAbility.BOUNCING_ARROWS);
        PassiveAbility.addAbility(bow, PassiveAbility.EXPLOSIVE_ARROW);
        return bow;
    }

    private static ItemStack createStar() {
        ItemStack star = new ItemStack(SHROOMLIGHT);

        ItemMeta starMeta = star.getItemMeta();
        starMeta.setDisplayName(color("&6&lPocket Star"));

        starMeta.addEnchant(Enchantment.SHARPNESS, 7, true);
        starMeta.addEnchant(Enchantment.FIRE_ASPECT, 10, true);
        starMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(color("&7&o\"Here comes the sun."));
        lore.add(color("&7&oHere comes the sun,"));
        lore.add(color("&7&oand I say, it's alright.\""));
        lore.add("");
        lore.add(color("&7- George Harrison,"));
        lore.add(color("&7&o\"Here Comes the Sun\""));

        starMeta.setLore(lore);
        star.setItemMeta(starMeta);

        LCAbility.addAbility(star, LCAbility.SOLAR_FLARE);
        RCAbility.addAbility(star, RCAbility.SUPERNOVA);
        OnHitAbility.addAbility(star, OnHitAbility.STELLAR_RADIATION);
        OnKillAbility.addAbility(star, OnKillAbility.EXPLOSIVE_KILL);
        PassiveAbility.addAbility(star, PassiveAbility.SCORCH_SURROUNDING);
        PassiveAbility.addAbility(star, PassiveAbility.FIRE_MEMBRANE);
        return star;
    }

    private static ItemStack createThunderAxe() {
        ItemStack axe = new ItemStack(DIAMOND_AXE);

        ItemMeta axeMeta = axe.getItemMeta();
        axeMeta.setDisplayName(ChatColor.YELLOW + "Thunder Axe");

        axeMeta.addEnchant(Enchantment.SHARPNESS, 6, true);
        axeMeta.addEnchant(Enchantment.FIRE_ASPECT, 3, true);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "A powerful axe infused");
        lore.add(ChatColor.GRAY + "with lightning.");

        axeMeta.setLore(lore);
        axe.setItemMeta(axeMeta);

        RCAbility.addAbility(axe, RCAbility.ELECTRIC_PULSE);
        OnHitAbility.addAbility(axe, OnHitAbility.TRIPLE_STRIKE);
        OnKillAbility.addAbility(axe, OnKillAbility.GROUNDED);
        return axe;
    }

    private static ItemStack createChainBow() {
        ItemStack bow = new ItemStack(BOW);

        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setDisplayName(ChatColor.GREEN + "Chain Reaction Bow");

        bowMeta.addEnchant(Enchantment.POWER, 3, false);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Firing arrows leads to");
        lore.add(ChatColor.GRAY + "large chain reactions.");

        bowMeta.setLore(lore);
        bow.setItemMeta(bowMeta);

        LCAbility.addAbility(bow, LCAbility.ARROW_FLURRY);
        PassiveAbility.addAbility(bow, PassiveAbility.SEEKING_ARROWS);
        OnHitAbility.addAbility(bow, OnHitAbility.ARROW_EJECTION);
        OnKillAbility.addAbility(bow, OnKillAbility.CORPSE_SHOT);
        return bow;
    }

    private static ItemStack createRocketJumper() {
        ItemStack jumper = new ItemStack(FIREWORK_ROCKET);

        ItemMeta jumperMeta = jumper.getItemMeta();
        jumperMeta.setDisplayName(ChatColor.RED + "Rocket Jumper");

        jumperMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);

        jumper.setItemMeta(jumperMeta);

        LCAbility.addAbility(jumper, LCAbility.ROCKET_JUMP);
        return jumper;
    }

    private static ItemStack createImpregnationCannon() {
        ItemStack cannon = new ItemStack(CROSSBOW);

        ItemMeta cannonMeta = cannon.getItemMeta();
        cannonMeta.setDisplayName(ChatColor.WHITE + "Impregnation Cannon");

        cannonMeta.addEnchant(Enchantment.UNBREAKING, 1, false);
        cannonMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Why did I make this?");

        cannonMeta.setLore(lore);
        cannon.setItemMeta(cannonMeta);

        LCAbility.addAbility(cannon, LCAbility.IMPREGNATION_RAY);
        return cannon;
    }

    private static ItemStack createRemedy() {
        ItemStack remedy = new ItemStack(BOOK);

        ItemMeta remedyMeta = remedy.getItemMeta();
        remedyMeta.setDisplayName(ChatColor.GREEN + "Remedy");

        remedyMeta.addEnchant(Enchantment.UNBREAKING, 1, false);
        remedyMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        remedy.setItemMeta(remedyMeta);

        LCAbility.addAbility(remedy, LCAbility.HEAL_USER);
        RCAbility.addAbility(remedy, RCAbility.GRANT_INVULNERABILITY);
        OnHitAbility.addAbility(remedy, OnHitAbility.TARGETED_HEAL);
        return remedy;
    }

    private static ItemStack createShinyKnife() {
        ItemStack knife = new ItemStack(IRON_SWORD);

        ItemMeta knifeMeta = knife.getItemMeta();
        knifeMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Shiny Knife");

        knife.setItemMeta(knifeMeta);

        OnKillAbility.addAbility(knife, OnKillAbility.BREAD_SLICE);
        return knife;
    }

    private static ItemStack createDeflectorHelm() {
        ItemStack helm = new ItemStack(NETHERITE_HELMET);

        ArmorMeta helmMeta = (ArmorMeta) helm.getItemMeta();
        helmMeta.setDisplayName(ChatColor.GRAY + "Deflector");

        helmMeta.addEnchant(Enchantment.PROTECTION, 6, true);
        helmMeta.addEnchant(Enchantment.PROJECTILE_PROTECTION, 6, true);
        helmMeta.addEnchant(Enchantment.UNBREAKING, 20, true);
        helmMeta.addEnchant(Enchantment.MENDING, 1, true);

        helmMeta.setTrim(new ArmorTrim(TrimMaterial.AMETHYST, TrimPattern.FLOW));
        helmMeta.addItemFlags(ItemFlag.HIDE_ARMOR_TRIM);
        helmMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        helm.setItemMeta(helmMeta);

        PassiveAbility.addAbility(helm, PassiveAbility.WIND_DEFLECT);

        addUnusual(helm, Unusual.WINDY);
        return helm;
    }

    private static ItemStack createSmasher() {
        ItemStack smasher = new ItemStack(MACE);

        ItemMeta smasherMeta = smasher.getItemMeta();
        smasherMeta.setDisplayName(ChatColor.RED + "Super Smasher");

        smasherMeta.addEnchant(Enchantment.BREACH, 5, true);
        smasherMeta.addEnchant(Enchantment.WIND_BURST, 4, true);
        smasherMeta.addEnchant(Enchantment.DENSITY, 5, true);
        smasherMeta.addEnchant(Enchantment.UNBREAKING, 3, true);
        smasherMeta.addEnchant(Enchantment.SHARPNESS, 3, true);
        smasherMeta.addEnchant(Enchantment.SMITE, 3, true);
        smasherMeta.addEnchant(Enchantment.BANE_OF_ARTHROPODS, 3, true);

        smasher.setItemMeta(smasherMeta);

        OnHitAbility.addAbility(smasher, OnHitAbility.CRIPPLE);
        return smasher;
    }

    private static ItemStack createChallengeModeBrowser() {
        ItemStack browser = new ItemStack(RECOVERY_COMPASS);

        ItemMeta browserMeta = browser.getItemMeta();
        browserMeta.setDisplayName(ChatColor.DARK_AQUA + "Challenge Modes");

        browserMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
        browserMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Additional modifiers for the");
        lore.add(ChatColor.GRAY + "game's difficulty.");

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

        railgunMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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
        lore.add(ChatColor.YELLOW + "(Right Click) Ability" + ChatColor.GRAY + ": " + ChatColor.GREEN + "Place");
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
        tritonMeta.addEnchant(Enchantment.UNBREAKING, 5, true);
        tritonMeta.addEnchant(Enchantment.MENDING, 1, true);

        triton.setItemMeta(tritonMeta);

        LCAbility.addAbility(triton, LCAbility.TRITON_BURST);
        return triton;
    }

    private static ItemStack createCallisto() {
        ItemStack callisto = new ItemStack(Material.BOW);

        ItemMeta bowMeta = callisto.getItemMeta();
        bowMeta.setDisplayName(ChatColor.GOLD + "Callisto");

        bowMeta.addEnchant(Enchantment.POWER, 10, true);
        bowMeta.addEnchant(Enchantment.INFINITY, 1, true);

        bowMeta.setUnbreakable(true);
        bowMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        callisto.setItemMeta(bowMeta);

        LCAbility.addAbility(callisto, LCAbility.CALLISTO_EXPLOSIVE_SHOT);
        PassiveAbility.addAbility(callisto, PassiveAbility.CALLISTO_ARROWS);

        addUnusual(callisto, Unusual.MALEVOLENT_MAGIC);
        return callisto;
    }

    private static ItemStack createDivorcePapers() {
        ItemStack papers = new ItemStack(Material.PAPER);

        ItemMeta paperMeta = papers.getItemMeta();
        paperMeta.setDisplayName(ChatColor.RED + "Divorce Papers");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(color("&7&o\"Why, tell me why did"));
        lore.add(color("&7&oyou not treat me right?"));
        lore.add(color("&7&oLove has a nasty habit"));
        lore.add(color("&7&oof disappearing overnight.\""));
        lore.add("");
        lore.add(color("&7- Paul McCartney,"));
        lore.add(color("&7&o\"I'm Looking Through You\""));

        paperMeta.setLore(lore);
        papers.setItemMeta(paperMeta);

        OnHitAbility.addAbility(papers, OnHitAbility.DIVORCE);
        return papers;
    }

    private static ItemStack createKingTrident() {
        ItemStack kingTrident = new ItemStack(Material.TRIDENT);

        ItemMeta tridentMeta = kingTrident.getItemMeta();
        tridentMeta.setDisplayName(ChatColor.DARK_AQUA + "Drowned King's Trident");

        tridentMeta.addEnchant(Enchantment.SHARPNESS, 3, true);
        tridentMeta.addEnchant(Enchantment.SMITE, 3, true);
        tridentMeta.addEnchant(Enchantment.BANE_OF_ARTHROPODS, 3, true);
        tridentMeta.addEnchant(Enchantment.CHANNELING, 3, true);
        tridentMeta.addEnchant(Enchantment.IMPALING, 3, true);
        tridentMeta.addEnchant(Enchantment.UNBREAKING, 3, true);

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
        lore.add("");
        lore.add(color("&7- Ringo Starr,"));
        lore.add(color("&7&o\"Octopus's Garden\""));

        tridentMeta.setLore(lore);
        kingTrident.setItemMeta(tridentMeta);

        PassiveAbility.addAbility(kingTrident, PassiveAbility.AQUATIC_ADAPTATION);
        OnHitAbility.addAbility(kingTrident, OnHitAbility.SPLASH);
        OnHitAbility.addAbility(kingTrident, OnHitAbility.IMPALE_ABILITY);

        addUnusual(kingTrident, Unusual.RIPTIDE);
        return kingTrident;
    }

    private static ItemStack createLaserMachineGun() {
        ItemStack laserGun = new ItemStack(Material.SPECTRAL_ARROW);

        ItemMeta laserMeta = laserGun.getItemMeta();
        laserMeta.setDisplayName(ChatColor.AQUA + "Laser Machine Gun");

        laserMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
        laserMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        laserGun.setItemMeta(laserMeta);

        LCAbility.addAbility(laserGun, LCAbility.SHOOT_LASER_GUN);
        return laserGun;
    }

    private static ItemStack createFlamethrower() {
        ItemStack flamethrower = new ItemStack(Material.IRON_SHOVEL);

        ItemMeta flamethrowerMeta = flamethrower.getItemMeta();
        flamethrowerMeta.setDisplayName(ChatColor.GOLD + "Flamethrower");

        flamethrowerMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
        flamethrowerMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        flamethrower.setItemMeta(flamethrowerMeta);

        LCAbility.addAbility(flamethrower, LCAbility.SHOOT_FLAME);
        return flamethrower;
    }

    private static ItemStack createEngagementRing() {
        ItemStack ring = new ItemStack(Material.GOLD_INGOT);

        ItemMeta ringMeta = ring.getItemMeta();
        ringMeta.setDisplayName(ChatColor.GOLD + "Engagement Ring");

        ringMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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
        lore.add("");
        lore.add(color("&7- George Harrison,"));
        lore.add(color("&7&o\"Something\""));

        ringMeta.setLore(lore);
        ring.setItemMeta(ringMeta);

        RCAbility.addAbility(ring, RCAbility.PROPOSE);
        return ring;
    }

    private static ItemStack createMedkitRevolver() {
        ItemStack revolver = new ItemStack(Material.DIAMOND_HOE);

        ItemMeta revolverMeta = revolver.getItemMeta();
        revolverMeta.setDisplayName(ChatColor.GREEN + "Medkit's Revolver");

        revolverMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        revolverMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
        revolverMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        revolverMeta.setUnbreakable(true);
        revolverMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Now Featuring:");
        lore.add("");
        lore.add(ChatColor.GRAY + "- Side A:");
        lore.add(ChatColor.GRAY + "  - Taxman");
        lore.add(ChatColor.GRAY + "  - Elanor Rigby");
        lore.add(ChatColor.GRAY + "  - I'm Only Sleeping");
        lore.add(ChatColor.GRAY + "  - Love You To");
        lore.add(ChatColor.GRAY + "  - Here, There and");
        lore.add(ChatColor.GRAY + "    Everywhere");
        lore.add(ChatColor.GRAY + "  - Yellow Submarine");
        lore.add(ChatColor.GRAY + "  - She Said She Said");
        lore.add("");
        lore.add(ChatColor.GRAY + "- Side B:");
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

        threadMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

    public static ItemStack createDamageReductionUpgradeIcon() {
        ItemStack icon = new ItemStack(DIAMOND_CHESTPLATE);

        ItemMeta iconMeta = icon.getItemMeta();
        iconMeta.setDisplayName(ChatColor.GREEN + "Upgrade Damage Reduction");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "+ 2.5% Damage Reduction/Level.");
        lore.add("");
        lore.add(ChatColor.GRAY + "XP Level Cost: " + ChatColor.YELLOW + InitUpgrades.damageReductionCost);

        iconMeta.setLore(lore);
        icon.setItemMeta(iconMeta);

        return icon;
    }

    public static ItemStack createCooldownUpgradeIcon() {
        ItemStack icon = new ItemStack(POTION);

        PotionMeta iconMeta = (PotionMeta) icon.getItemMeta();
        iconMeta.setDisplayName(ChatColor.GREEN + "Upgrade Ability Cooldown Reduction");

        iconMeta.addEnchant(Enchantment.UNBREAKING, 1, true);

        iconMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        iconMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        iconMeta.setColor(Color.fromRGB(153, 255, 153));

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "+ 2.5% Ability Cooldown Reduction/Level.");
        lore.add("");
        lore.add(ChatColor.GRAY + "XP Level Cost: " + ChatColor.YELLOW + InitUpgrades.cooldownCost);

        iconMeta.setLore(lore);
        icon.setItemMeta(iconMeta);

        return icon;
    }

    public static ItemStack createMultiHitUpgradeIcon() {
        ItemStack icon = new ItemStack(ECHO_SHARD);

        ItemMeta iconMeta = icon.getItemMeta();
        iconMeta.setDisplayName(ChatColor.GREEN + "Upgrade Multi-Hit Chance");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "+ 10% Multi-Hit Chance/Level.");
        lore.add("");
        lore.add(ChatColor.GRAY + "XP Level Cost: " + ChatColor.YELLOW + InitUpgrades.multiHitCost);

        iconMeta.setLore(lore);
        icon.setItemMeta(iconMeta);

        return icon;
    }

    public static ItemStack createReachDistanceUpgradeIcon() {
        ItemStack icon = new ItemStack(Material.BRUSH);

        ItemMeta iconMeta = icon.getItemMeta();
        iconMeta.setDisplayName(ChatColor.GREEN + "Upgrade Reach Distance");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "+ 0.2 Blocks of Reach/Level.");
        lore.add("");
        lore.add(ChatColor.GRAY + "XP Level Cost: " + ChatColor.YELLOW + InitUpgrades.reachDistCost);

        iconMeta.setLore(lore);
        icon.setItemMeta(iconMeta);

        return icon;
    }

    public static ItemStack createDamageUpgradeIcon() {
        ItemStack icon = new ItemStack(Material.IRON_SWORD);

        ItemMeta iconMeta = icon.getItemMeta();
        iconMeta.setDisplayName(ChatColor.GREEN + "Upgrade Damage");

        iconMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "+ 0.7 Damage/Level.");
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
        ItemStack catalyst = new ItemStack(ENDER_EYE);

        ItemMeta catalystMeta = catalyst.getItemMeta();
        catalystMeta.setDisplayName(ChatColor.GOLD + "Upgrade Catalyst");

        catalystMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
        catalystMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

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

        shotgunMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
        shotgunMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        shotgun.setItemMeta(shotgunMeta);

        LCAbility.addAbility(shotgun, LCAbility.SHOTGUN_FIRE);
        return shotgun;
    }

    private static ItemStack createComingSoon() {
        ItemStack coming = new ItemStack(Material.STRUCTURE_VOID);

        ItemMeta comingMeta = coming.getItemMeta();
        comingMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Coming Soon!");

        comingMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        baseMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        bookMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        sodaMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
        sodaMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        sodaMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);

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

        shurikenMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        staffMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        swordMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        medkitMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        slingMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        katanaMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        starMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        laserMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        grenadeMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        launcherMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        hammerMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        boomboxMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        swordMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
        swordMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        swordMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

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

        gunMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
        gunMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        gunMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);

        gun.setItemMeta(gunMeta);

        LCAbility.addAbility(gun, LCAbility.SCYTHE_GUN_SHOOT);
        RCAbility.addAbility(gun, RCAbility.SWAP_GUN_TO_SCYTHE);
        return gun;
    }

    private static ItemStack createScythe() {
        ItemStack scythe = new ItemStack(Material.DIAMOND_HOE);

        ItemMeta scytheMeta = scythe.getItemMeta();
        scytheMeta.setDisplayName(ChatColor.AQUA + "Scythe");

        scytheMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        radarMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        flashbangMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        eggMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        teaMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
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
        alcoholMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
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
        bleachMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        bleach.setItemMeta(bleachMeta);

        RCAbility.addAbility(bleach, RCAbility.CLEANER);
        return bleach;
    }

    private static ItemStack createSpecialSugar() {
        ItemStack sugar = new ItemStack(Material.SUGAR, 4);

        ItemMeta sugarMeta = sugar.getItemMeta();
        sugarMeta.setDisplayName(color("&f\"Sugar\""));

        sugarMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        medsMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        vaccineMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        fangMeta.addEnchant(Enchantment.SHARPNESS, 3, true);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.DARK_RED + "It's Morbin' time...");

        fangMeta.setLore(lore);
        fang.setItemMeta(fangMeta);

        RCAbility.addAbility(fang, RCAbility.LIFE_DRAIN_BUFF);
        OnKillAbility.addAbility(fang, OnKillAbility.HEALTH_DRAIN);
        return fang;
    }

    private static ItemStack createCulmination() {
        ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);

        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.setDisplayName(color("&6&l&kZ &d&lCulmination &6&l&kZ"));

        swordMeta.addEnchant(Enchantment.SHARPNESS, 6, true);
        swordMeta.addEnchant(Enchantment.BANE_OF_ARTHROPODS, 6, true);
        swordMeta.addEnchant(Enchantment.SMITE, 6, true);
        swordMeta.addEnchant(Enchantment.BREACH, 5, true);
        swordMeta.addEnchant(Enchantment.SWEEPING_EDGE, 4, true);
        swordMeta.addEnchant(Enchantment.UNBREAKING, 5, true);
        swordMeta.addEnchant(Enchantment.LOOTING, 5, true);
        swordMeta.addEnchant(Enchantment.MENDING, 1, true);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "The true most powerful sword...");

        swordMeta.setLore(lore);
        sword.setItemMeta(swordMeta);

        RCAbility.addAbility(sword, RCAbility.CALL_OF_THE_VOID);
        OnHitAbility.addAbility(sword, OnHitAbility.DRACONIAN_RAGE);
        OnHitAbility.addAbility(sword, OnHitAbility.DESPERATE_RUSH);
        return sword;
    }

    private static ItemStack createDecrepitScroll() {
        ItemStack scroll = new ItemStack(SKULL_BANNER_PATTERN);

        ItemMeta scrollMeta = scroll.getItemMeta();
        scrollMeta.setDisplayName(ChatColor.DARK_PURPLE + "Decrepit Scroll");
        scrollMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        scrollMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        handleMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
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

        pearlMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
        pearlMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        pearl.setItemMeta(pearlMeta);
        return pearl;
    }

    private static ItemStack createEndlessPearl() {
        ItemStack pearl = new ItemStack(Material.ENDER_PEARL);

        ItemMeta pearlMeta = pearl.getItemMeta();
        pearlMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Endless Ender Pearl");

        pearlMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
        pearlMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "An infinite Ender Pearl.");

        pearlMeta.setLore(lore);
        pearl.setItemMeta(pearlMeta);

        RCAbility.addAbility(pearl, RCAbility.THROW_PEARL);
        return pearl;
    }

    private static ItemStack createBerserkSword() {
        ItemStack sword = new ItemStack(Material.IRON_SWORD);

        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.setDisplayName(color("&7Berserk Sword"));

        swordMeta.addEnchant(Enchantment.SHARPNESS, 3, true);
        swordMeta.addEnchant(Enchantment.SMITE, 3, true);
        swordMeta.addEnchant(Enchantment.BANE_OF_ARTHROPODS, 3, true);

        sword.setItemMeta(swordMeta);

        RCAbility.addAbility(sword, RCAbility.RAGE);
        OnHitAbility.addAbility(sword, OnHitAbility.BLOODLUST);
        OnHitAbility.addAbility(sword, OnHitAbility.DESPERATE_RUSH);
        return sword;
    }

    private static ItemStack createDragonSword() {
        ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);

        ItemMeta dragonSwordMeta = sword.getItemMeta();
        dragonSwordMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Dragon Sword");

        dragonSwordMeta.addEnchant(Enchantment.SHARPNESS, 4, true);
        dragonSwordMeta.addEnchant(Enchantment.BANE_OF_ARTHROPODS, 3, true);
        dragonSwordMeta.addEnchant(Enchantment.SMITE, 3, true);

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

        hammerMeta.addEnchant(Enchantment.SHARPNESS, 2, true);
        hammerMeta.addEnchant(Enchantment.SMITE, 2, true);
        hammerMeta.addEnchant(Enchantment.BANE_OF_ARTHROPODS, 2, true);
        hammerMeta.addEnchant(Enchantment.BREACH, 3, true);

        hammerMeta.setUnbreakable(true);
        hammerMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        hammerMeta.setCustomModelData(69);

        hammer.setItemMeta(hammerMeta);

        RCAbility.addAbility(hammer, RCAbility.HAMMER_THROW);
        return hammer;
    }

    private static ItemStack createWardenChestplate() {
        ItemStack chestplate = new ItemStack(NETHERITE_CHESTPLATE);

        ArmorMeta chestplateMeta = (ArmorMeta) chestplate.getItemMeta();
        chestplateMeta.setDisplayName(ChatColor.DARK_AQUA + "Warden Chestplate");

        chestplateMeta.addEnchant(Enchantment.PROTECTION, 6, true);
        chestplateMeta.addEnchant(Enchantment.BLAST_PROTECTION, 6, true);
        chestplateMeta.addEnchant(Enchantment.FIRE_PROTECTION, 6, true);
        chestplateMeta.addEnchant(Enchantment.PROJECTILE_PROTECTION, 6, true);
        chestplateMeta.addEnchant(Enchantment.UNBREAKING, 5, true);

        chestplateMeta.setTrim(new ArmorTrim(TrimMaterial.DIAMOND, TrimPattern.SILENCE));
        chestplateMeta.addItemFlags(ItemFlag.HIDE_ARMOR_TRIM);
        chestplateMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

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

        swordMeta.addEnchant(Enchantment.SMITE, 2, true);

        sword.setItemMeta(swordMeta);

        OnHitAbility.addAbility(sword, OnHitAbility.UNDEAD_DAMAGE_BONUS);
        return sword;
    }

    private static ItemStack createSpiderSword() {
        ItemStack sword = new ItemStack(Material.IRON_SWORD);

        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.setDisplayName(color("&cSpider Sword"));

        swordMeta.addEnchant(Enchantment.BANE_OF_ARTHROPODS, 2, true);

        sword.setItemMeta(swordMeta);

        OnHitAbility.addAbility(sword, OnHitAbility.ARTHROPOD_DAMAGE_BONUS);
        return sword;
    }

    /**
     * Set an item to only be
     * able to deal Mini Crits and
     * not full Crits
     *
     * @param item: An item
     * @return item
     */
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

    /**
     * Set an item as unable to deal
     * any kind of Crit
     *
     * @param item: An item
     * @return item
     */
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

    /**
     * Set an item to only be
     * able to deal critical hits
     *
     * @param item: An item
     * @return item
     */
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

    /**
     * Set an item to only be
     * able to deal Super Critical
     * Hits
     *
     * @param item: An item
     * @return item
     */
    private static ItemStack setSuperCrits(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        if(meta.hasLore()) {
            lore = (ArrayList<String>) meta.getLore();
        }
        lore.add("");
        lore.add(color("&5Critical Hits are Enhanced"));

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    /**
     * Modify items' lore after
     * they are created to add final
     * touches
     */
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

    /**
     * Set an item's series
     *
     * @param item: An item
     * @param series: The series of the item
     * @return item
     */
    private static ItemStack setSeries(ItemStack item, Series series) {
        ItemMeta meta = item.getItemMeta();

        if(meta.hasLore()) {
            ArrayList<String> lore = (ArrayList<String>) meta.getLore();
            lore.add("");
            lore.add(series.getName() + ChatColor.GRAY + " Series");
            meta.setLore(lore);
        } else {
            ArrayList<String> lore = new ArrayList<>();
            lore.add("");
            lore.add(series.getName() + ChatColor.GRAY + " Series");
            meta.setLore(lore);
        }

        item.setItemMeta(meta);
        return item;
    }
}

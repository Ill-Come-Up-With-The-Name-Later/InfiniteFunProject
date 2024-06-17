package survivaltweaks.infinitefunproject.Mobs;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Mobs.Animals.DropBleachedFood;
import survivaltweaks.infinitefunproject.Mobs.Bat.BatDisease;
import survivaltweaks.infinitefunproject.Mobs.Blaze.AutoAimFireballs;
import survivaltweaks.infinitefunproject.Champions.ChampionInit;
import survivaltweaks.infinitefunproject.Mobs.Creeper.AutoCharge;
import survivaltweaks.infinitefunproject.Mobs.ElderGuardian.DropKingTrident;
import survivaltweaks.infinitefunproject.Mobs.ElderGuardian.GiveMiningFatigue;
import survivaltweaks.infinitefunproject.Mobs.EnderDragon.DropSword;
import survivaltweaks.infinitefunproject.Mobs.EnderDragon.GiveDragonHealth;
import survivaltweaks.infinitefunproject.Mobs.FarmAnimals.InitFarmAnimals;
import survivaltweaks.infinitefunproject.Mobs.Ghast.MakeGhastInvis;
import survivaltweaks.infinitefunproject.Mobs.IronGolem.SetAggro;
import survivaltweaks.infinitefunproject.Mobs.Phantom.SetSize;
import survivaltweaks.infinitefunproject.Mobs.Piglin.EquipPiglins;
import survivaltweaks.infinitefunproject.Mobs.Pillager.AutoAimCrossbow;
import survivaltweaks.infinitefunproject.Mobs.Rabbit.TurnLethal;
import survivaltweaks.infinitefunproject.Mobs.Skeleton.AutoAimArrows;
import survivaltweaks.infinitefunproject.Mobs.Skeleton.EquipSkeletonItems;
import survivaltweaks.infinitefunproject.Mobs.Skeleton.RandomTippedArrows;
import survivaltweaks.infinitefunproject.Mobs.Spider.GiveSpiderEffects;
import survivaltweaks.infinitefunproject.Mobs.Stray.AutoAimStrayArrows;
import survivaltweaks.infinitefunproject.Mobs.Stray.EquipStrays;
import survivaltweaks.infinitefunproject.Mobs.Turtle.LandmineTurtle;
import survivaltweaks.infinitefunproject.Mobs.Vex.EquipVex;
import survivaltweaks.infinitefunproject.Mobs.Warden.DropWardenArmor;
import survivaltweaks.infinitefunproject.Mobs.Warden.MakeInvisible;
import survivaltweaks.infinitefunproject.Mobs.Wither.AutoAimSkulls;
import survivaltweaks.infinitefunproject.Mobs.Wither.DropScroll;
import survivaltweaks.infinitefunproject.Mobs.Wither.GiveWitherHealth;
import survivaltweaks.infinitefunproject.Mobs.WitherSkele.EquipWitherSkele;
import survivaltweaks.infinitefunproject.Mobs.Zombie.EquipZombieItems;

public class MobInit {

    public static void init() {
        OnSpawn.createList();

        Bukkit.getServer().getPluginManager().registerEvents(new OnSpawn(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new EquipZombieItems(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new AutoCharge(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new AutoAimArrows(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new AutoAimSkulls(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new GiveWitherHealth(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new GiveDragonHealth(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new SetAggro(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new EquipSkeletonItems(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new TurnLethal(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new AutoAimCrossbow(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new MakeInvisible(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new MakeGhastInvis(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new EquipWitherSkele(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new GiveMiningFatigue(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new EquipPiglins(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new AutoAimFireballs(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DropWardenArmor(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new GiveSpiderEffects(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new EquipVex(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DropSword(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new EquipStrays(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new AutoAimStrayArrows(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new RandomTippedArrows(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DropScroll(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new SetSize(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new BatDisease(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new ChampionInit(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new MonsterSwarm(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new LandmineTurtle(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DropKingTrident(), InfiniteFunProject.plugin);

        InitFarmAnimals.init();
    }

    public static void setNoLevel(LivingEntity entity) {
        entity.setMetadata("NoLevel", new NoLvl());
    }
}

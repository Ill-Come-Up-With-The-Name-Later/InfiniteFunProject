package survivaltweaks.infinitefunproject.Commands;

import org.bukkit.Bukkit;
import survivaltweaks.infinitefunproject.Commands.CustomItemsUI.CustomItemsCommand;
import survivaltweaks.infinitefunproject.Commands.CustomItemsUI.GivePlayerItems;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class InitCommands {

    public static void init() {
        InfiniteFunProject.getPlugin(InfiniteFunProject.class).getCommand("setday").setExecutor(new SetDayCommand());
        InfiniteFunProject.getPlugin(InfiniteFunProject.class).getCommand("spawn").setExecutor(new SpawnCommand());
        InfiniteFunProject.getPlugin(InfiniteFunProject.class).getCommand("items").setExecutor(new CustomItemsCommand());
        InfiniteFunProject.getPlugin(InfiniteFunProject.class).getCommand("setmodifier").setExecutor(new SetModifierCommand());
        InfiniteFunProject.getPlugin(InfiniteFunProject.class).getCommand("event").setExecutor(new TriggerEventCommand());
        InfiniteFunProject.getPlugin(InfiniteFunProject.class).getCommand("anomaly").setExecutor(new TriggerAnomalyCommand());
        InfiniteFunProject.getPlugin(InfiniteFunProject.class).getCommand("settimer").setExecutor(new SetTimerCommand());
        InfiniteFunProject.getPlugin(InfiniteFunProject.class).getCommand("spawnchampion").setExecutor(new SpawnChampionCommand());
        InfiniteFunProject.getPlugin(InfiniteFunProject.class).getCommand("removemonsters").setExecutor(new RemoveMonstersCommand());
        InfiniteFunProject.getPlugin(InfiniteFunProject.class).getCommand("addbonusmodifier").setExecutor(new AddBonusModifierCommand());
        InfiniteFunProject.getPlugin(InfiniteFunProject.class).getCommand("modifier").setExecutor(new PickModifierCommand());
        InfiniteFunProject.getPlugin(InfiniteFunProject.class).getCommand("removechallenges").setExecutor(new DisableChallengesCommand());

        Bukkit.getServer().getPluginManager().registerEvents(new GivePlayerItems(), InfiniteFunProject.plugin);
    }
}

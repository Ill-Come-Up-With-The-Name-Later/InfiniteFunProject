package survivaltweaks.infinitefunproject.AntiLag;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class UnloadChunks {

    public static void tick() {
        new BukkitRunnable() {

            @Override
            public void run() {
                for(World world : Bukkit.getWorlds()) {
                    Chunk[] chunks = world.getLoadedChunks();

                    for(Chunk chunk : chunks) {
                        if(chunk.getPlayersSeeingChunk().isEmpty() || chunk.getEntities().length == 0) {
                            world.unloadChunk(chunk);
                        }
                    }
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 2, 2);
    }
}

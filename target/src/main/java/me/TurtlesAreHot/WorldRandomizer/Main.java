package me.TurtlesAreHot.WorldRandomizer;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import me.TurtlesAreHot.WorldRandomizer.events.onJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends JavaPlugin {

    private static MultiverseWorld chosen;
    private static MVWorldManager wmgr;
    private static List<MultiverseWorld> wrlds;
    @Override
    public void onEnable() {
        MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        wmgr = core.getMVWorldManager();
        wrlds = new ArrayList(wmgr.getMVWorlds());
        for(MultiverseWorld world : wrlds) {
            if(world.getName().equals("pvp")) {
                wrlds.remove(world);
                break;
            }
        }
        Random rand = new Random();
        chosen = wrlds.get(rand.nextInt(wrlds.size()));
        for(MultiverseWorld w : wrlds) {
            if(w.getName().equals("pvp")) {
                continue;
            }
            if(w.getName().equals(chosen.getName())) {
                continue;
            }
            wmgr.unloadWorld(w.getName());
        }
        wmgr.loadWorld(chosen.getName());
        wmgr.setFirstSpawnWorld(chosen.getName());
        this.getServer().getPluginManager().registerEvents(new onJoin(), this);

    }

    @Override
    public void onDisable() {
    }

    public static MultiverseWorld getChosen() { return chosen; }
}

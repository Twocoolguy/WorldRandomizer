package me.TurtlesAreHot.WorldRandomizer.events;

import me.TurtlesAreHot.WorldRandomizer.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {
    @EventHandler
    public void join(PlayerJoinEvent e) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "sudo " + e.getPlayer().getName() + " kp arena " + Main.getChosen().getName());
    }
}

package dev.jxnnik.minitablist.bukkit.listener;

import dev.jxnnik.minitablist.bukkit.BukkitSetup;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event) {
        BukkitSetup.getInstance().sendPlayerListHeaderAndFooter();
    }
}
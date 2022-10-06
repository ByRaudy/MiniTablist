package dev.jxnnik.minitablist.bukkit.animation;

import dev.jxnnik.minitablist.bukkit.BukkitSetup;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class TablistAnimation {

    private Plugin plugin;
    private BukkitTask bukkitTask;
    private int countdown;
    private double countdownState;

    public TablistAnimation(Plugin plugin) {
        this.plugin = plugin;
        this.countdown = 0;
        this.countdownState = -1.0;

        startTask();
    }

    public void startTask() {
        this.bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (countdownState == -1) {
                    countdownState = 1.0;
                    countdown = 0;
                }
                countdownState = (countdownState - 0.2);

                if(BukkitSetup.getInstance().getMiniTablist().getPluginConfig().getJson().get("settings").getAsJsonObject().get("enabled").getAsBoolean()) {
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        player.sendPlayerListHeaderAndFooter(MiniMessage.miniMessage().deserialize(
                                        BukkitSetup.getInstance().getMiniTablist().getHeader()
                                                .replace("%online_players%", Bukkit.getOnlinePlayers().size() + "")
                                                .replace("%max_players%", Bukkit.getMaxPlayers() + "")
                                                .replace("%player%", player.getName())
                                                .replace("<animation", "<gradient")
                                                .replace("</animation", "</gradient")
                                                .replace(":0", ":" + countdownState)),
                                MiniMessage.miniMessage().deserialize(
                                        BukkitSetup.getInstance().getMiniTablist().getFooter()
                                                .replace("%online_players%", Bukkit.getOnlinePlayers().size() + "")
                                                .replace("%max_players%", Bukkit.getMaxPlayers() + "")
                                                .replace("%player%", player.getName())
                                                .replace("<animation", "<gradient")
                                                .replace("</animation", "</gradient")
                                                .replace(":0", ":" + countdownState)));
                    });
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0, 3);
    }
}
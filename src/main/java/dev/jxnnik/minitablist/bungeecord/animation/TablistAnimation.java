package dev.jxnnik.minitablist.bungeecord.animation;

import dev.jxnnik.minitablist.bungeecord.BungeecordSetup;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.scheduler.ScheduledTask;

import java.util.concurrent.TimeUnit;

public class TablistAnimation {

    private ProxyServer server;
    private ScheduledTask scheduler;
    private int countdown;
    private double countdownState;

    public TablistAnimation(ProxyServer server) {
        this.server = server;
        this.countdown = 0;
        this.countdownState = -1.0;

        startTask();
    }

    public void startTask() {
        this.scheduler = server.getScheduler().schedule(BungeecordSetup.getInstance(), () -> {
            if (countdownState == -1) {
                countdownState = 1.0;
                countdown = 0;
            }
            countdownState = (countdownState - 0.2);

            if(BungeecordSetup.getInstance().getMiniTablist().getPluginConfig().getJson().get("settings").getAsJsonObject().get("enabled").getAsBoolean()) {
                server.getPlayers().forEach(player -> {
                    player.setTabHeader(BungeeComponentSerializer.get().serialize(MiniMessage.miniMessage().deserialize(
                                    BungeecordSetup.getInstance().getMiniTablist().getHeader()
                                            .replace("%online_players%", server.getPlayers().size() + "")
                                            .replace("%max_players%", server.getConfigurationAdapter().getListeners().iterator().next().getMaxPlayers() + "")
                                            .replace("%server%", player.getServer().getInfo().getName())
                                            .replace("%player%", player.getName())
                                            .replace("<animation", "<gradient")
                                            .replace("</animation", "</gradient")
                                            .replace(":0", ":" + countdownState))),
                            BungeeComponentSerializer.get().serialize(MiniMessage.miniMessage().deserialize(
                                    BungeecordSetup.getInstance().getMiniTablist().getFooter()
                                            .replace("%online_players%", server.getPlayers().size() + "")
                                            .replace("%max_players%", server.getConfigurationAdapter().getListeners().iterator().next().getMaxPlayers() + "")
                                            .replace("%server%", player.getServer().getInfo().getName())
                                            .replace("%player%", player.getName())
                                            .replace("<animation", "<gradient")
                                            .replace("</animation", "</gradient")
                                            .replace(":0", ":" + countdownState))));
                });
            }
        }, 1, 200, TimeUnit.MILLISECONDS);
    }
}
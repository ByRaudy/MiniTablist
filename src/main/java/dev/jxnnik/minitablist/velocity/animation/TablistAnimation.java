package dev.jxnnik.minitablist.velocity.animation;

import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.scheduler.ScheduledTask;
import dev.jxnnik.minitablist.velocity.VelocitySetup;
import net.kyori.adventure.text.minimessage.MiniMessage;

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
        this.scheduler = server.getScheduler().buildTask(VelocitySetup.getInstance(), () -> {
            if (countdownState == -1) {
                countdownState = 1.0;
                countdown = 0;
            }
            countdownState = (countdownState - 0.2);

            if(VelocitySetup.getInstance().getMiniTablist().getPluginConfig().getJson().get("settings").getAsJsonObject().get("enabled").getAsBoolean()) {
                server.getAllPlayers().forEach(player -> {
                    player.sendPlayerListHeaderAndFooter(MiniMessage.miniMessage().deserialize(
                                    VelocitySetup.getInstance().getMiniTablist().getHeader()
                                            .replace("%online_players%", server.getAllPlayers().size() + "")
                                            .replace("%max_players%", server.getConfiguration().getShowMaxPlayers() + "")
                                            .replace("%server%", player.getCurrentServer().get().getServerInfo().getName())
                                            .replace("<animation", "<gradient")
                                            .replace("</animation", "</gradient")
                                            .replace(":0", ":" + countdownState)),
                            MiniMessage.miniMessage().deserialize(
                                    VelocitySetup.getInstance().getMiniTablist().getFooter()
                                            .replace("%online_players%", server.getAllPlayers().size() + "")
                                            .replace("%max_players%", server.getConfiguration().getShowMaxPlayers() + "")
                                            .replace("%server%", player.getCurrentServer().get().getServerInfo().getName())
                                            .replace("<animation", "<gradient")
                                            .replace("</animation", "</gradient")
                                            .replace(":0", ":" + countdownState)));
                });
            }
        }).repeat(200, TimeUnit.MILLISECONDS).schedule();
    }
}
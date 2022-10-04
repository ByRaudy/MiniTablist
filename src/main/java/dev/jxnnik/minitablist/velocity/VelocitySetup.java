package dev.jxnnik.minitablist.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.jxnnik.minitablist.MiniTablist;
import dev.jxnnik.minitablist.velocity.animation.TablistAnimation;
import dev.jxnnik.minitablist.velocity.command.MiniTablistCommand;

public class VelocitySetup {

    private static VelocitySetup instance;
    private final ProxyServer proxyServer;
    private final MiniTablist miniTablist;

    @Inject
    public VelocitySetup(ProxyServer proxyServer) {
        this.instance = this;
        this.proxyServer = proxyServer;
        this.miniTablist = new MiniTablist();
    }

    @Subscribe
    public void handleProxyInitialization(ProxyInitializeEvent event) {
        CommandManager commandManager = proxyServer.getCommandManager();

        new TablistAnimation(proxyServer);
        commandManager.register(new MiniTablistCommand().build());
    }

    public static VelocitySetup getInstance() {
        return instance;
    }

    public MiniTablist getMiniTablist() {
        return miniTablist;
    }
}
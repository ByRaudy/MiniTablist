package dev.jxnnik.minitablist.bungeecord;

import dev.jxnnik.minitablist.MiniTablist;
import dev.jxnnik.minitablist.bungeecord.animation.TablistAnimation;
import dev.jxnnik.minitablist.bungeecord.command.MiniTablistCommand;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeecordSetup extends Plugin {

    private static BungeecordSetup instance;
    private MiniTablist miniTablist;


    @Override
    public void onEnable() {
        instance = this;
        miniTablist = new MiniTablist();

        getProxy().getPluginManager().registerCommand(this, new MiniTablistCommand());
        new TablistAnimation(getProxy());
    }

    public static BungeecordSetup getInstance() {
        return instance;
    }

    public MiniTablist getMiniTablist() {
        return miniTablist;
    }
}
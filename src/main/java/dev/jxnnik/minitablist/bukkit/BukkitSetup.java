package dev.jxnnik.minitablist.bukkit;

import dev.jxnnik.minitablist.MiniTablist;
import dev.jxnnik.minitablist.bukkit.animation.TablistAnimation;
import dev.jxnnik.minitablist.bukkit.command.MiniTablistCommand;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class BukkitSetup extends JavaPlugin {

    private static BukkitSetup instance;
    private MiniTablist miniTablist;

    @Override
    public void onEnable() {
        instance = this;
        miniTablist = new MiniTablist();

        new TablistAnimation(this);

        getCommand("minitablist").setExecutor(new MiniTablistCommand());
    }

    public static BukkitSetup getInstance() {
        return instance;
    }
}

package dev.jxnnik.minitablist.bukkit;

import dev.jxnnik.minitablist.MiniTablist;
import dev.jxnnik.minitablist.bukkit.command.MiniTablistCommand;
import dev.jxnnik.minitablist.bukkit.listener.PlayerJoinListener;
import lombok.Getter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class BukkitSetup extends JavaPlugin {

    private static BukkitSetup instance;
    private MiniTablist miniTablist;

    @Override
    public void onEnable() {
        instance = this;
        miniTablist = new MiniTablist();

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);

        getCommand("minitablist").setExecutor(new MiniTablistCommand());
    }

    public static BukkitSetup getInstance() {
        return instance;
    }

    public void sendPlayerListHeaderAndFooter() {
        Bukkit.getOnlinePlayers().forEach(player -> player.sendPlayerListHeaderAndFooter(MiniMessage.miniMessage().deserialize(BukkitSetup.getInstance().getMiniTablist().getHeader().replace("%online_players%", Bukkit.getOnlinePlayers().size() + "").replace("%max_players%", Bukkit.getMaxPlayers() + "")),
                MiniMessage.miniMessage().deserialize(BukkitSetup.getInstance().getMiniTablist().getFooter().replace("%online_players%", Bukkit.getOnlinePlayers().size() + "").replace("%max_players%", Bukkit.getMaxPlayers() + ""))));
    }
}

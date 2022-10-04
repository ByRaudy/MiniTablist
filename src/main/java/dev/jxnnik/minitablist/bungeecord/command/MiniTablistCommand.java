package dev.jxnnik.minitablist.bungeecord.command;

import dev.jxnnik.minitablist.bungeecord.BungeecordSetup;
import dev.jxnnik.minitablist.config.Config;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.Arrays;

public class MiniTablistCommand extends Command implements TabExecutor {

    public MiniTablistCommand() {
        super("minitablist", null);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer player) {
            if (player.hasPermission("minitablist.command")) {
                if (args.length == 1) {
                    if (args[0].equals("reload")) {
                        player.sendMessage(BungeeComponentSerializer.get().serialize(MiniMessage.miniMessage().deserialize("<#6FAF72>Successfully reloaded config.json!")));
                        BungeecordSetup.getInstance().getMiniTablist().setPluginConfig(new Config());
                        return;
                    } else {
                        player.sendMessage(BungeeComponentSerializer.get().serialize(MiniMessage.miniMessage().deserialize("<rainbow>MiniTablist - v1.0-DEVELOPMENT by Jxnnik(.dev)</rainbow>")));
                        player.sendMessage(BungeeComponentSerializer.get().serialize(MiniMessage.miniMessage().deserialize("<#d67624>/minitablist reload")));
                        return;
                    }
                } else {
                    player.sendMessage(BungeeComponentSerializer.get().serialize(MiniMessage.miniMessage().deserialize("<rainbow>MiniTablist - v1.0-DEVELOPMENT by Jxnnik(.dev)</rainbow>")));
                    player.sendMessage(BungeeComponentSerializer.get().serialize(MiniMessage.miniMessage().deserialize("<#d67624>/minitablist reload")));
                    return;
                }
            } else {
                player.sendMessage(BungeeComponentSerializer.get().serialize(MiniMessage.miniMessage().deserialize("<rainbow>MiniTablist - v1.0-DEVELOPMENT by Jxnnik(.dev)</rainbow>")));
                return;
            }
        }
        if (args.length == 1) {
            if (args[0].equals("reload")) {
                BungeecordSetup.getInstance().getProxy().getConsole().sendMessage(BungeeComponentSerializer.get().serialize(MiniMessage.miniMessage().deserialize("<#6FAF72>Successfully reloaded config.json!")));
                BungeecordSetup.getInstance().getMiniTablist().setPluginConfig(new Config());
            } else {
                BungeecordSetup.getInstance().getProxy().getConsole().sendMessage(BungeeComponentSerializer.get().serialize(MiniMessage.miniMessage().deserialize("<rainbow>MiniTablist - v1.0-DEVELOPMENT by Jxnnik(.dev)</rainbow>")));
                BungeecordSetup.getInstance().getProxy().getConsole().sendMessage(BungeeComponentSerializer.get().serialize(MiniMessage.miniMessage().deserialize("<#d67624>/minitablist reload")));
            }
        } else {
            BungeecordSetup.getInstance().getProxy().getConsole().sendMessage(BungeeComponentSerializer.get().serialize(MiniMessage.miniMessage().deserialize("<rainbow>MiniTablist - v1.0-DEVELOPMENT by Jxnnik(.dev)</rainbow>")));
            BungeecordSetup.getInstance().getProxy().getConsole().sendMessage(BungeeComponentSerializer.get().serialize(MiniMessage.miniMessage().deserialize("<#d67624>/minitablist reload")));
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        return Arrays.asList("reload");
    }
}
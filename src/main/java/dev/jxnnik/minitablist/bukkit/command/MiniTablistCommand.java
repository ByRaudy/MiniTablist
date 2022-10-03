package dev.jxnnik.minitablist.bukkit.command;

import dev.jxnnik.minitablist.bukkit.BukkitSetup;
import dev.jxnnik.minitablist.config.Config;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MiniTablistCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player) {
            if(args.length == 1) {
                if(args[0].equals("reload")) {
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<#6FAF72>Successfully reloaded config.json!"));
                    BukkitSetup.getInstance().getMiniTablist().setPluginConfig(new Config());
                    BukkitSetup.getInstance().sendPlayerListHeaderAndFooter();
                } else {
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<rainbow>MiniTablist - v1.0-DEVELOPMENT by Jannish(.dev)</rainbow>"));
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<#d67624>/minitablist reload"));
                }
            } else {
                player.sendMessage(MiniMessage.miniMessage().deserialize("<rainbow>MiniTablist - v1.0-DEVELOPMENT by Jannish(.dev)</rainbow>"));
                player.sendMessage(MiniMessage.miniMessage().deserialize("<#d67624>/minitablist reload"));
            }
            return false;
        }
        if(args.length == 1) {
            if(args[0].equals("reload")) {
                Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize("<#6FAF72>Successfully reloaded config.json!"));
                BukkitSetup.getInstance().getMiniTablist().setPluginConfig(new Config());
                BukkitSetup.getInstance().sendPlayerListHeaderAndFooter();
            } else {
                Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize("<rainbow>MiniTablist - v1.0-DEVELOPMENT by Jannish(.dev)</rainbow>"));
                Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize("<#d67624>/minitablist reload"));
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize("<rainbow>MiniTablist - v1.0-DEVELOPMENT by Jannish(.dev)</rainbow>"));
            Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize("<#d67624>/minitablist reload"));
        }

        return false;
    }
}
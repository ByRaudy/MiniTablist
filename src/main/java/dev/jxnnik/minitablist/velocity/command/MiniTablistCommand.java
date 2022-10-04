package dev.jxnnik.minitablist.velocity.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import dev.jxnnik.minitablist.config.Config;
import dev.jxnnik.minitablist.velocity.VelocitySetup;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class MiniTablistCommand {

    public BrigadierCommand build() {
        LiteralCommandNode<CommandSource> node = LiteralArgumentBuilder.<CommandSource>literal("minitablist")
                .requires(commandSource -> commandSource.hasPermission("minitablist.command"))
                .executes(this::sendHelp)
                .then(LiteralArgumentBuilder.<CommandSource>literal("reload")
                        .executes(this::reloadConfig))
                .build();

        return new BrigadierCommand(node);
    }

    private int sendHelp(CommandContext<CommandSource> context) {
        context.getSource().sendMessage(MiniMessage.miniMessage().deserialize("<rainbow>MiniTablist - v1.0-DEVELOPMENT by Jxnnik(.dev)</rainbow>"));
        context.getSource().sendMessage(MiniMessage.miniMessage().deserialize("<#d67624>/minitablist reload"));
        return 1;
    }

    private int reloadConfig(CommandContext<CommandSource> context) {
        context.getSource().sendMessage(MiniMessage.miniMessage().deserialize("<#6FAF72>Successfully reloaded config.json!"));
        VelocitySetup.getInstance().getMiniTablist().setPluginConfig(new Config());
        return 1;
    }
}
package net.minipandi.shieldbreaksmp.commands;

import net.minipandi.shieldbreaksmp.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class energyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Main.energy.put((Player) sender, Integer.valueOf(args[0]));
        return false;
    }
}

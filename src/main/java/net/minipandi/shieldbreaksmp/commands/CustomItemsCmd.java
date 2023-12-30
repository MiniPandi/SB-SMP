package net.minipandi.shieldbreaksmp.commands;

import net.minipandi.shieldbreaksmp.items.utils.CustomItems;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class CustomItemsCmd implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("customItems.give")) {
                if (args.length == 1) {
                    switch (args[0]) {
                        case "Staff_of_Teleportation":
                            player.getInventory().addItem(CustomItems.staff_of_teleportation_item());
                            break;
                        case "Construction_Wand":
                            player.getInventory().addItem(CustomItems.construction_wand_item());
                            break;
                        case "Funny_Stick":
                            player.getInventory().addItem(CustomItems.funny_stick());
                            break;
                    }
                } else {
                    player.sendMessage(ChatColor.GREEN + "---------------------------------------------");
                    player.sendMessage(ChatColor.GREEN + "Staff of Teleportation Custom Model Data: 1");
                    player.sendMessage(ChatColor.GREEN + "Construction Wand Custom Model Data: 2");
                    player.sendMessage(ChatColor.GREEN + "Funny Stick Custom Model Data: 3");
                    player.sendMessage(ChatColor.GREEN + "---------------------------------------------");
                }
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0) return list;
        //Player player = (Player) sender;
        if (args.length == 1) {
            list.add("Staff_of_Teleportation");
            list.add("Construction_Wand");
        }
        ArrayList<String> current = new ArrayList<>();
        String currentarg = args[args.length - 1].toLowerCase();
        for (String s : list) {
            String s1 = s.toLowerCase();
            if (s1.startsWith(currentarg)) {
                current.add(s);
            }
        }
        return current;
    }
}

package net.minipandi.shieldbreaksmp.commands;

import net.minipandi.shieldbreaksmp.items.CustomItems;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomItemsCmd implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("customItems.give")){
                if (args.length == 1){
                    if (args[0].equals("Staff_of_Teleportation")){
                        player.getInventory().addItem(CustomItems.staff_of_teleportation_item());
                    } else if (args[0].equals("Construction_Wand")) {
                        player.getInventory().addItem(CustomItems.construction_wand_item());
                    } else if (args[0].equals("Funny_Stick")) {
                        player.getInventory().addItem(CustomItems.funny_stick());
                    }
                } else {
                    player.sendMessage(ChatColor.GREEN + "---------------------------------------------");
                    player.sendMessage(ChatColor.GREEN + "Staff of Teleportation Custom Model Data: 0");
                    player.sendMessage(ChatColor.GREEN + "Construction Wand Custom Model Data: 1");
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
        if (args.length == 1){
            list.add("Staff_of_Teleportation");
            list.add("Construction_Wand");
            list.add("Funny_Stick");
        }
        ArrayList<String> current = new ArrayList<>();
        String currentarg = args[args.length-1].toLowerCase();
        for (String s : list) {
            String s1 = s.toLowerCase();
            if (s1.startsWith(currentarg)){
                current.add(s);
            }
        }
        return current;
    }
}

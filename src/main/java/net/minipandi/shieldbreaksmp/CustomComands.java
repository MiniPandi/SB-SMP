package net.minipandi.shieldbreaksmp;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomComands implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("customItems.give")){
                if (args.length == 1){
                    if (args[0].equals("Staff_of_Teleportation")){
                        ItemStack staff_of_teleportation_item = new ItemStack(Material.STICK);
                        ItemMeta staff_of_teleportation_meta = staff_of_teleportation_item.getItemMeta();
                        staff_of_teleportation_meta.setDisplayName(ChatColor.DARK_PURPLE + "Staff of Teleportation");
                        staff_of_teleportation_meta.setCustomModelData(0);
                        staff_of_teleportation_item.setItemMeta(staff_of_teleportation_meta);
                        player.getInventory().addItem(staff_of_teleportation_item);
                    }
                } else {
                    player.sendMessage(ChatColor.GREEN + "---------------------------------------------");
                    player.sendMessage(ChatColor.GREEN + "Staff of Teleportation Custom Model Data: 0");
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
        Player player = (Player) sender;
        if (args.length == 1){
            list.add("Staff_of_Teleportation");

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

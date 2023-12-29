package net.minipandi.shieldbreaksmp.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItems {
    public static ItemStack staff_of_teleportation_item() {
        ItemStack staff_of_teleportation_item = new ItemStack(Material.STICK);
        ItemMeta staff_of_teleportation_meta = staff_of_teleportation_item.getItemMeta();
        staff_of_teleportation_meta.setDisplayName(ChatColor.DARK_PURPLE + "Staff of Teleportation");
        staff_of_teleportation_meta.setCustomModelData(0);
        staff_of_teleportation_item.setItemMeta(staff_of_teleportation_meta);
        return staff_of_teleportation_item;
    }

    public static ItemStack construction_wand_item() {
        ItemStack construction_wand_item = new ItemStack(Material.STICK);
        ItemMeta construction_wand_meta = construction_wand_item.getItemMeta();
        construction_wand_meta.setDisplayName(ChatColor.AQUA + "Construction Wand");
        construction_wand_meta.setCustomModelData(1);
        construction_wand_item.setItemMeta(construction_wand_meta);
        return construction_wand_item;
    }
    public static ItemStack funny_stick() {
        ItemStack funny_stick_item = new ItemStack(Material.STICK);
        ItemMeta funny_stick_meta = funny_stick_item.getItemMeta();
        funny_stick_meta.setDisplayName(ChatColor.GOLD + "Funny Stick");
        funny_stick_meta.setCustomModelData(2);
        funny_stick_item.setItemMeta(funny_stick_meta);
        return funny_stick_item;
    }
}

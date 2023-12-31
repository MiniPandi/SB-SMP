package net.minipandi.shieldbreaksmp.items.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings("deprecation")
public class CustomItems {
    public static ItemStack filler() {
        ItemStack filler_item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta filler_meta = filler_item.getItemMeta();
        filler_meta.setDisplayName(" ");
        filler_item.setItemMeta(filler_meta);
        return filler_item;
    }

    public static ItemStack staff_of_teleportation_item() {
        ItemStack staff_of_teleportation_item = new ItemStack(Material.DIAMOND_SHOVEL);
        ItemMeta staff_of_teleportation_meta = staff_of_teleportation_item.getItemMeta();
        staff_of_teleportation_meta.setDisplayName(ChatColor.DARK_PURPLE + "Staff of Teleportation");
        staff_of_teleportation_meta.setCustomModelData(1);
        staff_of_teleportation_item.setItemMeta(staff_of_teleportation_meta);
        return staff_of_teleportation_item;
    }

    public static ItemStack construction_wand_item() {
        ItemStack construction_wand_item = new ItemStack(Material.STICK);
        ItemMeta construction_wand_meta = construction_wand_item.getItemMeta();
        construction_wand_meta.setDisplayName(ChatColor.AQUA + "Construction Wand");
        construction_wand_meta.setCustomModelData(2);
        construction_wand_item.setItemMeta(construction_wand_meta);
        return construction_wand_item;
    }

    public static ItemStack funny_stick_item() {
        ItemStack funny_stick_item = new ItemStack(Material.STICK);
        ItemMeta funny_stick_meta = funny_stick_item.getItemMeta();
        funny_stick_meta.setDisplayName(ChatColor.GOLD + "Funny Stick");
        funny_stick_meta.setCustomModelData(3);
        funny_stick_item.setItemMeta(funny_stick_meta);
        return funny_stick_item;
    }

    public static ItemStack backpack_item() {
        ItemStack backpack_item = new ItemStack(Material.ENDER_CHEST);
        ItemMeta backpack_meta = backpack_item.getItemMeta();
        backpack_meta.setDisplayName(ChatColor.AQUA + "Backpack");
        backpack_meta.setCustomModelData(4);
        backpack_item.setItemMeta(backpack_meta);
        return backpack_item;
    }

    public static ItemStack storage_item() {
        ItemStack storage_item = new ItemStack(Material.CHEST);
        ItemMeta storage_meta = storage_item.getItemMeta();
        storage_meta.setDisplayName(ChatColor.GREEN + "StorageChest");
        storage_meta.setCustomModelData(5);
        storage_item.setItemMeta(storage_meta);
        return storage_item;
    }

    public static ItemStack backpack_energy_upgrade() {
        ItemStack backpack_energy_upgrade_item = new ItemStack(Material.CAKE);
        ItemMeta backpack_energy_upgrade_meta = backpack_energy_upgrade_item.getItemMeta();
        backpack_energy_upgrade_meta.setDisplayName(ChatColor.GREEN + "Backpack Storage Upgrade");
        backpack_energy_upgrade_meta.setCustomModelData(6);
        backpack_energy_upgrade_item.setItemMeta(backpack_energy_upgrade_meta);
        return backpack_energy_upgrade_item;
    }
}

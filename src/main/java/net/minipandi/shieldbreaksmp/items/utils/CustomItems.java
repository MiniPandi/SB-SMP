
package net.minipandi.shieldbreaksmp.items.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("deprecation")
public class CustomItems {

    public static HashMap<ItemStack,String> items = new HashMap<>();

    static {
        items.put(filler(), "filler");
        items.put(staff_of_teleportation_item(), "staff_of_teleportation_item");
        items.put(construction_wand_item(), "construction_wand_item");
        items.put(funny_stick_item(), "funny_stick_item");
        items.put(backpack_item(), "backpack_item");
        items.put(storage_item(), "storage_item");
        items.put(fire_sword(), "fire_sword");
    }
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
        backpack_energy_upgrade_meta.setDisplayName(ChatColor.GREEN + "Backpack Energy Upgrade");
        backpack_energy_upgrade_meta.setCustomModelData(6);
        backpack_energy_upgrade_item.setItemMeta(backpack_energy_upgrade_meta);
        return backpack_energy_upgrade_item;
    }

    public static ItemStack backpack_storage_upgrade() {
        ItemStack backpack_storage_upgrade_item = new ItemStack(Material.CAKE);
        ItemMeta backpack_storage_upgrade_meta = backpack_storage_upgrade_item.getItemMeta();
        backpack_storage_upgrade_meta.setDisplayName(ChatColor.GREEN + "Backpack Storage Upgrade");
        backpack_storage_upgrade_meta.setCustomModelData(7);
        backpack_storage_upgrade_item.setItemMeta(backpack_storage_upgrade_meta);
        return backpack_storage_upgrade_item;
    }
    public static ItemStack fire_sword(){
        ItemStack myItem = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = myItem.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Fire Sword");
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.setLore(Arrays.asList("8 ♡", "", ChatColor.GRAY + "This is a nice sword", ChatColor.GRAY + "idk how you use this", "", ChatColor.GOLD + "Legendary Sword"));
        meta.addEnchant(Enchantment.FIRE_ASPECT, 10, true);
        meta.setCustomModelData(9);
        myItem.setItemMeta(meta);
        return myItem;
    }
}

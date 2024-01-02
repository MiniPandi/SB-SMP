package net.minipandi.shieldbreaksmp.items.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItemGen {
    public static ItemStack item(Material material,Integer customModeldata,String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta item_meta = item.getItemMeta();
        item_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item_meta.setCustomModelData(customModeldata);
        item.setItemMeta(item_meta);
        return item;
    }
}

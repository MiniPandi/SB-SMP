package net.minipandi.shieldbreaksmp.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class CustomItemUtils implements  Listener {
    private Material itemType;
    private String itemName;
    private Integer customModelData;

    public CustomItemUtils(Material itemType, String itemName, Integer customModelData) {
        this.itemType = itemType;
        this.itemName = itemName;
        this.customModelData = customModelData;
    }

    public ItemStack getItem() {
        ItemStack item = new ItemStack(itemType);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemName));
        meta.setCustomModelData(customModelData);

        item.setItemMeta(meta);
        return item;
    }
}

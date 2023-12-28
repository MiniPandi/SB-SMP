package net.minipandi.shieldbreaksmp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("customItems").setExecutor(new CustomComands());
        Bukkit.getPluginManager().registerEvents(new listener(), this);

        ItemStack staff_of_teleportation_item = new ItemStack(Material.STICK);
        ItemMeta staff_of_teleportation_meta = staff_of_teleportation_item.getItemMeta();
        staff_of_teleportation_meta.setDisplayName(ChatColor.DARK_PURPLE + "Staff of Teleportation");
        staff_of_teleportation_meta.setCustomModelData(0);
        staff_of_teleportation_item.setItemMeta(staff_of_teleportation_meta);
        ShapedRecipe staff_of_teleportation = new ShapedRecipe(NamespacedKey.fromString("staff_of_teleportation"), staff_of_teleportation_item );
        staff_of_teleportation.shape("eee","ese","eee");
        staff_of_teleportation.setIngredient('e', Material.ENDER_PEARL);
        staff_of_teleportation.setIngredient('s', Material.STICK);
        staff_of_teleportation.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(staff_of_teleportation);
    }
}

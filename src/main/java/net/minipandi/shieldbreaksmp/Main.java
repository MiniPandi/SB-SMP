package net.minipandi.shieldbreaksmp;

import net.minipandi.shieldbreaksmp.commands.CustomItemsCmd;
import net.minipandi.shieldbreaksmp.items.CustomItems;
import net.minipandi.shieldbreaksmp.listener.listener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("customItems").setExecutor(new CustomItemsCmd());
        Bukkit.getPluginManager().registerEvents(new listener(), this);

        sofRecepice();
    }

    public void sofRecepice() {
        ShapedRecipe staff_of_teleportation = new ShapedRecipe(NamespacedKey.fromString("staff_of_teleportation"), CustomItems.staff_of_teleportation_item());
        staff_of_teleportation.shape("eee","ese","eee");
        staff_of_teleportation.setIngredient('e', Material.ENDER_PEARL);
        staff_of_teleportation.setIngredient('s', Material.STICK);
        staff_of_teleportation.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(staff_of_teleportation);
    }
}

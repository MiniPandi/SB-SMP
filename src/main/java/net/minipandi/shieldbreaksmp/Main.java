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

import java.util.Objects;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("customItems").setExecutor(new CustomItemsCmd());
        Bukkit.getPluginManager().registerEvents(new listener(), this);

        sofRecepice();
        cwRecipice();
    }

    public void sofRecepice() {
        ShapedRecipe staff_of_teleportation = new ShapedRecipe(Objects.requireNonNull(NamespacedKey.fromString("staff_of_teleportation")), CustomItems.staff_of_teleportation_item());
        staff_of_teleportation.shape("eee","ese","eee");
        staff_of_teleportation.setIngredient('e', Material.ENDER_PEARL);
        staff_of_teleportation.setIngredient('s', Material.STICK);
        staff_of_teleportation.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(staff_of_teleportation);
    }

    public void cwRecipice() {
        ShapedRecipe construction_wand = new ShapedRecipe(Objects.requireNonNull(NamespacedKey.fromString("construction_wand")), CustomItems.construction_wand_item());
        construction_wand.shape("  n", " s ", "s  ");
        construction_wand.setIngredient('n', Material.NETHER_STAR);
        construction_wand.setIngredient('s', Material.STICK);
        construction_wand.setCategory(CraftingBookCategory.BUILDING);
        Bukkit.addRecipe(construction_wand);
    }
}

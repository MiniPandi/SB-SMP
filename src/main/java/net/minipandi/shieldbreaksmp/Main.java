package net.minipandi.shieldbreaksmp;

import net.minipandi.shieldbreaksmp.CustomEnchantmens.AirJump;
import net.minipandi.shieldbreaksmp.commands.CustomItemsCmd;
import net.minipandi.shieldbreaksmp.items.CustomItems;
import net.minipandi.shieldbreaksmp.listener.listener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;

public final class Main extends JavaPlugin {

    public static AirJump airJump;

    @Override
    public void onEnable() {
        registerCommands("customItems", new CustomItemsCmd());
        registerEvents(new staffOfTeleportation(), this);
        registerEvents(new constructionWand(), this);

        CustomItemsRecipe.sofRecepice();
        CustomItemsRecipe.cwRecepice();
    }

    public void registerEvents(Listener listener, Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(listener, plugin);
    }

    public void registerCommands(String name, CommandExecutor commandExecutor) {
        Objects.requireNonNull(this.getCommand(name)).setExecutor(commandExecutor);
    }

    @Override
    public void onDisable() {
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            if(byKey.containsKey(airJump.getKey())) {
                byKey.remove(airJump.getKey());
            }
            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            if(byName.containsKey(airJump.getName())) {
                byName.remove(airJump.getName());
            }
        } catch (Exception ignored) { }
    }

    public static void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if(registered){
            // It's been registered!
        }
    }
}

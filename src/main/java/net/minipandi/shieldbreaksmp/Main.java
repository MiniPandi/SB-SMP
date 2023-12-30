package net.minipandi.shieldbreaksmp;

import net.minipandi.shieldbreaksmp.commands.CustomItemsCmd;
import net.minipandi.shieldbreaksmp.enchants.AirJump;
import net.minipandi.shieldbreaksmp.items.constructionWand;
import net.minipandi.shieldbreaksmp.items.staffOfTeleportation;
import net.minipandi.shieldbreaksmp.items.utils.CustomItemsRecipe;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandExecutor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Objects;

@SuppressWarnings("all")
public final class Main extends JavaPlugin {
    public static AirJump airJump;

    @Override
    public void onEnable() {
        registerCommands("customItems", new CustomItemsCmd());
        registerEvents(new staffOfTeleportation(), this);
        registerEvents(new constructionWand(), this);

        airJump = new AirJump(new NamespacedKey(this, "AirJump"));
        registerEnchantment(airJump);

        CustomItemsRecipe.sofRecepice();
        CustomItemsRecipe.cwRecepice();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onDisable() {
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            if (byKey.containsKey(airJump.getKey())) {
                byKey.remove(airJump.getKey());
            }
            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            if (byName.containsKey(airJump.getName())) {
                byName.remove(airJump.getName());
            }
        } catch (Exception ignored) {
        }
    }

    public void registerEvents(Listener listener, Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(listener, plugin);
    }

    public void registerCommands(String name, CommandExecutor commandExecutor) {
        Objects.requireNonNull(this.getCommand(name)).setExecutor(commandExecutor);
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
        if (registered) ; // It's been registered!
    }
}

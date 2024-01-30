package net.minipandi.shieldbreaksmp;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minipandi.shieldbreaksmp.commands.CustomItemsCmd;
import net.minipandi.shieldbreaksmp.commands.energyCommand;
import net.minipandi.shieldbreaksmp.items.*;
import net.minipandi.shieldbreaksmp.items.utils.CustomItemsRecipe;
import net.minipandi.shieldbreaksmp.items.FireSwordListener;
import net.minipandi.shieldbreaksmp.listener.joinListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("all")
public final class Main extends JavaPlugin {
    public static Map<Player, Integer> energy = new HashMap<>();
    public static Map<Player, Integer> moreenergy = new HashMap<>();
    public static Map<Player, Integer> morespace = new HashMap<>();
    private static Main main;

    @Override
    public void onEnable() {
        main = this;

        registerCommands("customItems", new CustomItemsCmd());
        registerCommands("energy", new energyCommand());
        registerEvents(new staffOfTeleportation(), this);
        registerEvents(new constructionWand(), this);
        registerEvents(new funnyStick(), this);
        registerEvents(new joinListener(), this);
        registerEvents(new backpack(), this);
        registerEvents(new StorageItem(), this);
        registerEvents(new backpack_energy_upgrade(), this);
        registerEvents(new FireSwordListener(), this);

        CustomItemsRecipe.sofRecepice();
        CustomItemsRecipe.cwRecepice();
        CustomItemsRecipe.siRecepice();
        CustomItemsRecipe.bpRecepice();
        CustomItemsRecipe.fire_sword_recipce();
        energyTimer();
    }

    @SuppressWarnings("unchecked")

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

    public void energyTimer() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (Map.Entry<Player, Integer> entry : energy.entrySet()) {
                    Player key = entry.getKey();
                    Integer value = entry.getValue();
                    if (!moreenergy.isEmpty()) {
                        if (moreenergy.containsKey(key) && !moreenergy.get(key).equals(0)) {
                            Integer multiplier = moreenergy.get(key);
                            Integer maxvalue = 2000 * multiplier;
                            if (value < maxvalue) {
                                value += 10;
                                energy.put(key, value);
                            }
                        } else if (value < 2000) {
                            value += 10;
                            energy.put(key, value);
                        }
                    } else {
                        if (value < 2000) {
                            value += 10;
                            energy.put(key, value);
                        }
                    }
                }
            }
        }, 0L, 2 * 20L);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    Integer maxvalue = 2000;
                    if (!moreenergy.isEmpty()) {
                        if (!moreenergy.get(p).equals(0)) {
                            Integer multiplier = moreenergy.get(p);
                            maxvalue *= multiplier;
                        }
                    }
                    p.sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("Energy: " + energy.get(p) + "/" + maxvalue));
                }
            }
        }, 0L, 20L);
    }

    public static Main main() {
        return main;
    }
}

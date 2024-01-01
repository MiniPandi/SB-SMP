package net.minipandi.shieldbreaksmp;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minipandi.shieldbreaksmp.commands.CustomItemsCmd;
import net.minipandi.shieldbreaksmp.commands.energyCommand;
import net.minipandi.shieldbreaksmp.enchants.AirJump;
import net.minipandi.shieldbreaksmp.items.*;
import net.minipandi.shieldbreaksmp.items.utils.CustomItemsRecipe;
import net.minipandi.shieldbreaksmp.listener.joinListener;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
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
    public static AirJump airJump;
    private ProtocolManager protocolManager;
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

        airJump = new AirJump(new NamespacedKey(this, "AirJump"));
        registerEnchantment(airJump);

        protocolManager = ProtocolLibrary.getProtocolManager();
        getLogger().info(protocolManager.getMinecraftVersion().toString());

        CustomItemsRecipe.sofRecepice();
        CustomItemsRecipe.cwRecepice();
        CustomItemsRecipe.siRecepice();
        CustomItemsRecipe.bpRecepice();
        energyTimer();
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

    public void energyTimer() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
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

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
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

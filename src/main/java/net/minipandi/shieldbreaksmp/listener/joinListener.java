package net.minipandi.shieldbreaksmp.listener;

import net.minipandi.shieldbreaksmp.Main;
import net.minipandi.shieldbreaksmp.items.utils.ConvertInv;
import net.minipandi.shieldbreaksmp.items.utils.CustomItems;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static net.minipandi.shieldbreaksmp.Main.morespace;
import static net.minipandi.shieldbreaksmp.items.backpack_energy_upgrade.isItemInSlot;

@SuppressWarnings("deprecation")
public class joinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Inventory playerInv = player.getInventory();
        Main.energy.put(player, 2000);
        for (ItemStack item : playerInv.getContents()) {
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == 4) {
                if (ConvertInv.loadInventoryFromItem(item) != null) {
                    Inventory inv = ConvertInv.deserializeInventory(Objects.requireNonNull(ConvertInv.loadInventoryFromItem(item)), ChatColor.BLACK + "Backpack", 2, player);
                    int value = 0;
                    if (inv.getItem(0) != null || inv.getItem(9) != null) {
                        for (int slot : new int[]{0, 9}) {
                            if (isItemInSlot(inv, slot, CustomItems.backpack_energy_upgrade())) {
                                value += 2;
                            }
                        }
                        morespace.put(player, value);
                    } else {
                        morespace.put(player, 0);
                    }
                }
            }
        }
    }
}

package net.minipandi.shieldbreaksmp.listener;

import net.md_5.bungee.api.ChatColor;
import net.minipandi.shieldbreaksmp.Main;
import net.minipandi.shieldbreaksmp.items.utils.ConvertInv;
import net.minipandi.shieldbreaksmp.items.utils.CustomItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static net.minipandi.shieldbreaksmp.Main.morespace;
import static net.minipandi.shieldbreaksmp.items.backpack_energy_upgrade.isItemInSlot;

public class joinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Main.energy.put(player, 2000);

        Inventory invp = player.getInventory();
        for (ItemStack item : invp.getContents()) {
            if (item != null && item.equals(CustomItems.backpack_item())) {
                String convertetInv = ConvertInv.loadInventoryFromItem(item);
                Inventory inv = ConvertInv.deserializeInventory(convertetInv, ChatColor.BLACK + "Backpack", 2, player);
                Player p = e.getPlayer();
                int value = 0;
                for (int slot : new int[]{0, 9}) {
                    if (isItemInSlot(inv, slot, CustomItems.backpack_energy_upgrade())) {
                        value += 2;
                        morespace.put(p, value);
                        break; // Break the loop once the item is found in one of the slots
                    } else {
                        morespace.put(p, 0);
                    }
                }
            }
        }
    }
}

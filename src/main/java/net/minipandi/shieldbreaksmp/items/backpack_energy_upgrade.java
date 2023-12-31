package net.minipandi.shieldbreaksmp.items;

import net.minipandi.shieldbreaksmp.items.utils.CustomItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static net.minipandi.shieldbreaksmp.Main.morespace;


@SuppressWarnings("deprecation")
public class backpack_energy_upgrade implements Listener {
    @EventHandler
    public void onInvOpen(InventoryOpenEvent e) {
        Inventory inv = e.getInventory();
        Player p = (Player) e.getPlayer();
        int value = 0;
        for (int slot : new int[]{0, 9}) {
            if (isItemInSlot(inv, slot, CustomItems.backpack_energy_upgrade())) {
                value += 2;
                morespace.put(p, value);
            } else {
                morespace.put(p, 0);
            }
        }
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {
        Inventory inv = e.getInventory();
        Player p = (Player) e.getPlayer();
        int value = 0;
        for (int slot : new int[]{0, 9}) {
            if (isItemInSlot(inv, slot, CustomItems.backpack_energy_upgrade())) {
                value += 2;
                morespace.put(p, value);
            } else {
                morespace.put(p, 0);
            }
        }
    }

    public static boolean isItemInSlot(Inventory inventory, int slot, ItemStack item) {
        ItemStack itemInSlot = inventory.getItem(slot);
        return itemInSlot != null && itemInSlot.isSimilar(item);
    }
}

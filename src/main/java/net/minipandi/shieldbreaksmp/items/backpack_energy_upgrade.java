package net.minipandi.shieldbreaksmp.items;

import net.minipandi.shieldbreaksmp.items.utils.CustomItems;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static net.minipandi.shieldbreaksmp.Main.moreenergy;


@SuppressWarnings("deprecation")
public class backpack_energy_upgrade implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        /*if (e.getView().getTitle().equals(ChatColor.BLACK + "Backpack")) {
        }*/
    }

    @EventHandler
    public void onInvOpen(InventoryOpenEvent e) {
        Inventory inv = e.getInventory();
        if (e.getView().getTitle().equals(ChatColor.BLACK + "Backpack")) {
            Player p = (Player) e.getPlayer();
            int value = 0;
            if (inv.getItem(0) != null || inv.getItem(9) != null) {
                for (int slot : new int[]{0, 9}) {
                    if (isItemInSlot(inv, slot, CustomItems.backpack_energy_upgrade())) {
                        value += 2;
                    }
                }
                moreenergy.put(p, value);
            } else {
                moreenergy.put(p, 0);
            }
        }
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {
        Inventory inv = e.getInventory();
        if (e.getView().getTitle().equals(ChatColor.BLACK + "Backpack")) {
            Player p = (Player) e.getPlayer();
            int value = 0;
            if (inv.getItem(0) != null || inv.getItem(9) != null) {
                for (int slot : new int[]{0, 9}) {
                    if (isItemInSlot(inv, slot, CustomItems.backpack_energy_upgrade())) {
                        value += 2;
                    }
                }
                moreenergy.put(p, value);
            } else {
                moreenergy.put(p, 0);
            }
        }
    }

    public static boolean isItemInSlot(Inventory inventory, int slot, ItemStack item) {
        ItemStack itemInSlot = inventory.getItem(slot);
        return itemInSlot != null && itemInSlot.isSimilar(item);
    }
}

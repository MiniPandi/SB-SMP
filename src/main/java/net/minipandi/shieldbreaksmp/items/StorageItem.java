package net.minipandi.shieldbreaksmp.items;

import net.minipandi.shieldbreaksmp.items.utils.ConvertInv;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public class StorageItem implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        ItemStack itemInHand = e.getItem();
        if (e.getAction().isRightClick()) {
            if (item != null && item.getType() != Material.AIR) {
                if (item.getItemMeta() != null && item.getItemMeta().hasCustomModelData()) {
                    if (item.getType().equals(Material.CHEST) && item.getItemMeta().getCustomModelData() == 5) {
                        if (ConvertInv.loadInventoryFromItem(itemInHand) != null) {
                            Inventory inv = ConvertInv.deserializeInventory(ConvertInv.loadInventoryFromItem(itemInHand), ChatColor.GREEN + "StorageChest", 6, e.getPlayer());
                            e.getPlayer().openInventory(inv);
                        } else {
                            Inventory inv = Bukkit.createInventory(e.getPlayer(), 54, ChatColor.GREEN + "StorageChest");
                            e.getPlayer().openInventory(inv);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        InventoryView invV = e.getView();
        if (invV.getTitle().equals(ChatColor.GREEN + "StorageChest")) {
            Player player = (Player) e.getPlayer();
            ItemStack iIH = player.getInventory().getItemInMainHand();
            if (iIH != null && iIH.getType().equals(Material.CHEST) && iIH.getItemMeta().getCustomModelData() == 5) {
                Inventory inv = e.getInventory();
                String serialisedInv = ConvertInv.serializeInventory(inv);
                ConvertInv.saveInventoryToItem(iIH, serialisedInv);
            }
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        ItemStack iIH = player.getItemInHand();
        ItemStack iIO = player.getInventory().getItemInOffHand();
        if (iIH.hasItemMeta() && iIH.getType().equals(Material.CHEST)) {
            if (iIH.getItemMeta().getCustomModelData() == 5 && iIH.getType().equals(Material.CHEST)) {
                e.setCancelled(true);
            } else if (iIO.getItemMeta().getCustomModelData() == 5 && iIO.getType().equals(Material.CHEST)) {
                e.setCancelled(true);
            }
        }
    }
}

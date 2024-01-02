package net.minipandi.shieldbreaksmp.items;

import net.minipandi.shieldbreaksmp.items.utils.ConvertInv;
import net.minipandi.shieldbreaksmp.items.utils.CustomItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import static net.minipandi.shieldbreaksmp.Main.moreenergy;

@SuppressWarnings("deprecation")
public class backpack implements Listener {

    @EventHandler
    public void onUse(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        ItemStack itemInHand = e.getItem();
        Player p = e.getPlayer();
        if (e.getAction().isRightClick()) {
            if (item != null && item.getType() != Material.AIR) {
                if (item.getItemMeta() != null && item.getItemMeta().hasCustomModelData()) {
                    if (item.getType().equals(Material.ENDER_CHEST) && item.getItemMeta().getCustomModelData() == 4) {
                        if (ConvertInv.loadInventoryFromItem(itemInHand) != null) {
                            Integer size = null;
                            if (moreenergy.containsKey(e.getPlayer())) {
                                size = moreenergy.get(p);
                            }
                            Inventory inv = ConvertInv.deserializeInventory(ConvertInv.loadInventoryFromItem(itemInHand), ChatColor.BLACK + "Backpack", 2, e.getPlayer());
                            e.getPlayer().openInventory(inv);
                        } else {
                            Inventory inv = Bukkit.createInventory(e.getPlayer(), 18, ChatColor.BLACK + "Backpack");

                            inv.setItem(1, CustomItems.filler());
                            inv.setItem(10, CustomItems.filler());

                            e.getPlayer().openInventory(inv);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        ItemStack iIH = player.getItemInHand();
        ItemStack iIO = player.getInventory().getItemInOffHand();
        if (iIH.hasItemMeta() && iIH.getType().equals(Material.ENDER_CHEST)) {
            if (iIH.getItemMeta().getCustomModelData() == 4 && iIH.getType().equals(Material.ENDER_CHEST)) {
                e.setCancelled(true);
            } else if (iIO.getItemMeta().getCustomModelData() == 4 && iIO.getType().equals(Material.ENDER_CHEST)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.isLeftClick() || e.isRightClick() || e.isShiftClick()) {
            if (!(e.getCurrentItem() == null) && e.getView().getTitle().equals(ChatColor.BLACK + "Backpack")) {
                if (e.getCurrentItem().equals(CustomItems.filler())) {
                    e.setCancelled(true);
                } else if (e.getCurrentItem().getType().equals(Material.ENDER_CHEST) && e.getCurrentItem().getItemMeta().getCustomModelData() == 4) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        InventoryView invV = e.getView();
        if (invV.getTitle().equals(ChatColor.BLACK + "Backpack")) {
            Player player = (Player) e.getPlayer();
            ItemStack iIH = player.getInventory().getItemInMainHand();
            if (iIH != null && iIH.getType().equals(Material.ENDER_CHEST) && iIH.getItemMeta().getCustomModelData() == 4) {
                Inventory inv = e.getInventory();
                String serialisedInv = ConvertInv.serializeInventory(inv);
                ConvertInv.saveInventoryToItem(iIH, serialisedInv);
            }
        }
    }
}
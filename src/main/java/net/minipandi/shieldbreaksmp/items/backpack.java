package net.minipandi.shieldbreaksmp.items;

import net.minipandi.shieldbreaksmp.Main;
import net.minipandi.shieldbreaksmp.items.utils.ConvertInv;
import net.minipandi.shieldbreaksmp.items.utils.CustomItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

@SuppressWarnings("deprecation")
public class backpack implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        if (e.getAction().isRightClick()) {
            if (item != null && item.getType() != Material.AIR) {
                if (item.getItemMeta() != null && item.getItemMeta().hasCustomModelData()) {
                    if (item.getType().equals(Material.ENDER_CHEST) && item.getItemMeta().getCustomModelData() == 4) {
                        Inventory inv = Bukkit.createInventory(e.getPlayer(), 18, ChatColor.BLACK + "Backpack");

                        inv.setItem(1, CustomItems.filler());
                        inv.setItem(10, CustomItems.filler());

                        e.getPlayer().openInventory(inv);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        ItemStack iIH = player.getItemInHand();
        if (iIH.getItemMeta().getCustomModelData() == 4 && iIH.getType().equals(Material.ENDER_CHEST)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.isLeftClick() || e.isRightClick() || e.isShiftClick()) {
            if (Objects.requireNonNull(e.getCurrentItem()).equals(CustomItems.filler())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        InventoryView inv = e.getView();
        Inventory inToSave = e.getInventory();
        if (inv.getTitle().equals(ChatColor.BLACK + "Backpack")) {
            ConvertInv.InvToString(inToSave);
        }
    }

    public void saveString(ItemStack item, String key, String value) {
        item.getItemMeta().getPersistentDataContainer().set(
                new NamespacedKey(Main.main(), key),
                PersistentDataType.STRING,
                value
        );
    }

    public String getString(ItemStack item, String key) {
        if (item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.main(), key), PersistentDataType.STRING)) {
            return item.getItemMeta().getPersistentDataContainer().get(
                    new NamespacedKey(Main.main(), key),
                    PersistentDataType.STRING
            );
        }
        return null;
    }
}
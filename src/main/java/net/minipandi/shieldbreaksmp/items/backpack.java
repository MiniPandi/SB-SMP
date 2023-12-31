package net.minipandi.shieldbreaksmp.items;

import net.minipandi.shieldbreaksmp.Main;
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
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Base64;

@SuppressWarnings("deprecation")
public class backpack implements Listener {

    @EventHandler
    public void onUse(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        ItemStack itemInHand = e.getItem();
        if (e.getAction().isRightClick()) {
            if (item != null && item.getType() != Material.AIR) {
                if (item.getItemMeta() != null && item.getItemMeta().hasCustomModelData()) {
                    if (item.getType().equals(Material.ENDER_CHEST) && item.getItemMeta().getCustomModelData() == 4) {
                        if (loadInventoryFromItem(itemInHand) != null) {
                            Inventory inv = deserializeInventory(loadInventoryFromItem(itemInHand));
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
        if (iIH.getItemMeta().getCustomModelData() == 4 && iIH.getType().equals(Material.ENDER_CHEST)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.isLeftClick() || e.isRightClick() || e.isShiftClick()) {
            if (!(e.getCurrentItem() == null)) {
                if (e.getCurrentItem().equals(CustomItems.filler())) {
                    e.setCancelled(true);
                }
            } else {
                Bukkit.getServer().getLogger().info("Null");
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
                String serialisedInv = serializeInventory(inv);
                saveInventoryToItem(iIH, serialisedInv);
                Bukkit.getServer().getLogger().info("String" + serialisedInv);
            }
        }
    }

    private String serializeInventory(Inventory inventory) {
        StringBuilder serializedInventory = new StringBuilder();
        for (ItemStack itemStack : inventory.getContents()) {
            if (itemStack != null) {
                serializedInventory.append(Base64.getEncoder().encodeToString(itemStack.serializeAsBytes()));
                serializedInventory.append(";");
            } else {
                serializedInventory.append("null;");
            }
        }
        return serializedInventory.toString();
    }

    private Inventory deserializeInventory(String serializedInventory) {
        Inventory inventory = Bukkit.createInventory(null, 9 * 2, ChatColor.BLACK + "Backpack"); // Change size as needed
        String[] serializedItems = serializedInventory.split(";");
        int i = 0;
        for (String serializedItem : serializedItems) {
            if (!serializedItem.equals("null")) {
                byte[] itemBytes = Base64.getDecoder().decode(serializedItem);
                ItemStack itemStack = ItemStack.deserializeBytes(itemBytes);
                inventory.setItem(i, itemStack);
            }
            i++;
        }
        return inventory;
    }

    private void saveInventoryToItem(ItemStack itemStack, String serializedInventory) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(Main.main(), "inventory"), PersistentDataType.STRING, serializedInventory);
        itemStack.setItemMeta(meta);
    }

    private String loadInventoryFromItem(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta.getPersistentDataContainer().has(new NamespacedKey(Main.main(), "inventory"), PersistentDataType.STRING)) {
            return meta.getPersistentDataContainer().get(new NamespacedKey(Main.main(), "inventory"), PersistentDataType.STRING);
        }
        return null;
    }
}
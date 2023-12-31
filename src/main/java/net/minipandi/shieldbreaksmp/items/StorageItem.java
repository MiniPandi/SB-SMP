package net.minipandi.shieldbreaksmp.items;

import net.minipandi.shieldbreaksmp.Main;
import net.minipandi.shieldbreaksmp.items.utils.ConvertInv;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class StorageItem implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        if (e.getAction().isRightClick()) {
            if (item != null && item.getType() != Material.AIR) {
                if (item.getItemMeta() != null && item.getItemMeta().hasCustomModelData()) {
                    if (item.getType().equals(Material.CHEST) && item.getItemMeta().getCustomModelData() == 5) {
                        ItemMeta itemMeta = item.getItemMeta();
                        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
                        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GREEN + "StorageChest");
                        System.out.println(persistentDataContainer.has(NamespacedKey.fromString("inv", Main.main())));
                        if (persistentDataContainer.has(NamespacedKey.fromString("inv", Main.main()))){
                            inv = ConvertInv.StringToInv(persistentDataContainer.get(NamespacedKey.fromString("inv", Main.main()), PersistentDataType.STRING));
                            System.out.println(ConvertInv.StringToInv(persistentDataContainer.get(NamespacedKey.fromString("inv", Main.main()), PersistentDataType.STRING)));
                        }
                        e.getPlayer().openInventory(inv);
                        item.setItemMeta(itemMeta);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        ItemStack item = e.getPlayer().getItemInHand();
            if (item != null && item.getType() != Material.AIR) {
                if (item.getItemMeta() != null && item.getItemMeta().hasCustomModelData()) {
                    if (item.getType().equals(Material.CHEST) && item.getItemMeta().getCustomModelData() == 5) {
                        if  (e.getView().getTitle().equals(ChatColor.GREEN + "StorageChest")){
                            ItemMeta itemMeta = item.getItemMeta();
                            PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
                            Inventory inv = e.getInventory();
                            persistentDataContainer.set(NamespacedKey.fromString("inv", Main.main()), PersistentDataType.STRING, ConvertInv.InvToString(inv));
                            System.out.println(item.getItemMeta().getPersistentDataContainer().has(NamespacedKey.fromString("inv", Main.main())));
                            System.out.println(NamespacedKey.fromString("inv", Main.main()));
                            item.setItemMeta(itemMeta);
                        }
                    }
                }
            }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        ItemStack iIH = player.getItemInHand();
        if (iIH.getItemMeta().getCustomModelData() == 5 && iIH.getType().equals(Material.CHEST)) {
            e.setCancelled(true);
        }
    }
}

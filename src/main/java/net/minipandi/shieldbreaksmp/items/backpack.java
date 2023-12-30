package net.minipandi.shieldbreaksmp.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public class backpack implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        if (e.getAction().isRightClick()) {
            if (item != null && item.getType() != Material.AIR) {
                if (item.getItemMeta() != null && item.getItemMeta().hasCustomModelData()) {
                    if (item.getType().equals(Material.ENDER_CHEST) && item.getItemMeta().getCustomModelData() == 4) {

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
}
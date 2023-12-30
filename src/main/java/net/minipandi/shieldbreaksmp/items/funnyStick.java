package net.minipandi.shieldbreaksmp.items;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public class funnyStick implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEvent e) {
        if (e.getAction().isRightClick()) {
            ItemStack item = e.getItem();
            if (item != null && item.getType() != Material.AIR) {
                if (item.getItemMeta() != null) {
                    if (item.getItemMeta().hasCustomModelData()) {
                        if (item.getType().equals(Material.STICK) && item.getItemMeta().getCustomModelData() == 3) {
                            if (e.getPlayer().isOnGround()) {
                                /*if (item.getEnchantments().containsKey(Main.airJump)) {
                                    Integer lvl = item.getEnchantments().get(Main.airJump);
                                }*/
                                e.getPlayer().setVelocity(e.getPlayer().getEyeLocation().getDirection().multiply(-10.0));
                            }
                        }
                    }
                }
            }
        }
    }
}

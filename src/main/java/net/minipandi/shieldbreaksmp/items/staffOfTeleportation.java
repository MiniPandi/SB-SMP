package net.minipandi.shieldbreaksmp.items;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public class staffOfTeleportation implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (e.getPlayer().isSneaking() && e.getAction().isRightClick()) {
            ItemStack item = e.getItem();
            if (item != null && item.getType() != Material.AIR) {
                if (item.getItemMeta() != null) {
                    if (item.getItemMeta().hasCustomModelData()) {
                        if (item.getType().equals(Material.DIAMOND_SHOVEL) && item.getItemMeta().getCustomModelData() == 1) {
                            if (e.getPlayer().getLocation().add(e.getPlayer().getLocation().getDirection().normalize().multiply(5)).getBlock().isPassable()) {
                                e.getPlayer().setAllowFlight(true);
                                e.getPlayer().teleport(e.getPlayer().getLocation().add(e.getPlayer().getLocation().getDirection().normalize().multiply(5)));
                                e.getPlayer().setAllowFlight(false);
                            } else {
                                e.getPlayer().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "You can not teleport in to blocks"));
                                e.getPlayer().playSound(e.getPlayer(), Sound.BLOCK_ANVIL_LAND, 20, 1);
                            }
                        }
                    }
                }
            }
        }
    }
}

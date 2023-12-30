package net.minipandi.shieldbreaksmp.items;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public class constructionWand implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        if (e.getAction().isRightClick()) {
            if (item != null && item.getType() != Material.AIR) {
                if (item.getItemMeta() != null && item.getItemMeta().hasCustomModelData()) {
                    if (item.getType().equals(Material.STICK) && item.getItemMeta().getCustomModelData() == 2) {
                        Player player = e.getPlayer();
                        Block block = e.getClickedBlock();
                        if (block == null) {
                            //Bukkit.getServer().getLogger().severe("The block is null!");
                        } else {
                            ItemStack iblock = new ItemStack(block.getType());
                            if (player.getInventory().contains(iblock.getType()) || player.getGameMode().equals(GameMode.CREATIVE)) {
                                Location locblock = e.getClickedBlock().getLocation();
                                if (locblock.add(e.getBlockFace().getDirection().normalize()).getBlock().isEmpty()) {
                                    locblock.getBlock().setType(block.getType());
                                }
                                if (!player.getGameMode().equals(GameMode.CREATIVE)) {
                                    player.getInventory().forEach(itemStack -> {
                                        if (itemStack != null && itemStack.getType().equals(iblock.getType())) {
                                            itemStack.setAmount(itemStack.getAmount() - 1);
                                        }
                                    });
                                }
                            } else {
                                e.getPlayer().sendMessage(ChatColor.RED + "You don't have enough blocks!");
                                e.getPlayer().playSound(e.getPlayer(), Sound.BLOCK_ANVIL_LAND, 20, 1);
                            }
                        }
                    }
                }
            }
        }
    }
}

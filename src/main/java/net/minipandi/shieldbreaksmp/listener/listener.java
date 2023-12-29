package net.minipandi.shieldbreaksmp.listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class listener implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent e){
        if (e.getPlayer().isSneaking() && e.getAction().isRightClick()){
            ItemStack item = e.getItem();
            if (item != null && item.getType() != Material.AIR){
                if (item.getItemMeta() != null){
                    if (item.getItemMeta().hasCustomModelData()){
                        if (item.getType().equals(Material.STICK) && item.getItemMeta().getCustomModelData() == 0){
                            if (e.getPlayer().getLocation().add(e.getPlayer().getLocation().getDirection().normalize().multiply(5)).getBlock().isPassable()){
                                e.getPlayer().teleport(e.getPlayer().getLocation().add(e.getPlayer().getLocation().getDirection().normalize().multiply(5)));
                            } else {
                                e.getPlayer().sendMessage(ChatColor.RED + "You can not teleport in to blocks");
                                e.getPlayer().playSound(e.getPlayer(), Sound.BLOCK_ANVIL_LAND, 20, 1);
                            }
                        } else if (item.getType().equals(Material.STICK) && item.getItemMeta().getCustomModelData() == 1) {
                            Player player = e.getPlayer();
                            Block block = e.getClickedBlock();
                            if (block == null);
                            ItemStack iblock = new ItemStack(block.getType());
                            if (player.getInventory().contains(iblock)) {
                                Location locblock = e.getClickedBlock().getLocation();
                                locblock.add(0,1,0).getBlock().setType(block.getType());
                                player.sendMessage("1");
                                removeOne(e.getPlayer().getInventory(), iblock);
                            } else {
                                e.getPlayer().sendMessage(ChatColor.RED + "You don't hav enough blocks!");
                                e.getPlayer().playSound(e.getPlayer(), Sound.BLOCK_ANVIL_LAND, 20, 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void removeOne(Inventory inventory, ItemStack item) {
        int size = inventory.getSize();
        for (int i = 0; i < size; i ++) {
            ItemStack other = inventory.getItem(i);
            if (item.isSimilar(other)) {
                assert other != null;
                int amount = other.getAmount();
                if (amount > 1) {
                    other.setAmount(amount - 1);
                } else {
                    other = null;
                }
                inventory.setItem(i, other);
                break;
            }
        }
    }
}

package net.minipandi.shieldbreaksmp.items;

import net.minipandi.shieldbreaksmp.utils.CustomItemUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class test extends CustomItemUtils implements Listener {
    ItemStack item = getItem();
    public test() {
        super(Material.STICK, "&4Opstick", 1);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction().isRightClick() && e.getItem() == item) {
            e.getPlayer().sendMessage("workd");
        }
    }
}

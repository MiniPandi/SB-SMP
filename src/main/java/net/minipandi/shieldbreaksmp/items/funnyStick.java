package net.minipandi.shieldbreaksmp.items;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minipandi.shieldbreaksmp.Main;
import org.bukkit.*;
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
                                Integer value = Main.energy.get(e.getPlayer());
                                if (value >= 50) {
                                    value -= 50;
                                    Main.energy.put(e.getPlayer(), value);
                                    Location loc = e.getPlayer().getLocation();
                                    World world = e.getPlayer().getServer().getWorlds().get(0);

                                    world.spawnParticle(Particle.EXPLOSION_HUGE, loc, 3);
                                    world.playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 1f, 1f);

                                    e.getPlayer().setVelocity(e.getPlayer().getEyeLocation().getDirection().multiply(-10.0));

                                } else {
                                    e.getPlayer().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "Not enough energy!"));
                                }
                            } else {
                                e.getPlayer().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "You can't use it while you are in the air!"));
                            }
                        }
                    }
                }
            }
        }
    }
}

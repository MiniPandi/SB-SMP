package net.minipandi.shieldbreaksmp.listener;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;

public class FireSwordListener implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (e.getItem() == null || e.getItem().getType() == Material.AIR || !e.getItem().getType().equals(Material.BLAZE_POWDER)) {
            if (player.getItemInHand().getType().equals(Material.NETHERITE_SWORD)) {
                ItemStack handitem = player.getItemInHand();
                if (handitem.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Fire Sword")) {
                    if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                        if (player.getInventory().getItemInOffHand().getType().equals(Material.BLAZE_POWDER)) {
                            Location center = player.getLocation();

                                int particleCount = 100;
                                double radius = 5.0;
                                double angle = 360.0 / (double)particleCount;
                                this.getEntitiesInRadius(center, 5.0, player);

                                for(int i = 0; i < particleCount; ++i) {
                                    double radians = Math.toRadians(angle * (double)i);
                                    double x = center.getX() + radius * Math.cos(radians);
                                    double z = center.getZ() + radius * Math.sin(radians);
                                    Location particleLocation = new Location(center.getWorld(), x, center.getY(), z);
                                    particleLocation.getWorld().spawnParticle(Particle.FLAME, particleLocation, 1);
                                }

                                ItemStack offHandItem = player.getInventory().getItemInOffHand();
                                offHandItem.setAmount(offHandItem.getAmount() - 1);
                                player.getInventory().setItemInOffHand(offHandItem);
                                return;
                        } else {
                            player.sendMessage(ChatColor.GOLD + "You don't have BLAZE POWDER in you off hand");
                        }

                    }
                }
            }
        }
    }

    public void getEntitiesInRadius(Location centerLocation, double radius, Player player) {
        World world = centerLocation.getWorld();
        double squaredRadius = radius * radius;
        Iterator var8 = world.getEntities().iterator();

        while(var8.hasNext()) {
            Entity entity = (Entity)var8.next();
            Location entityLocation = entity.getLocation();
            if (centerLocation.distanceSquared(entityLocation) <= squaredRadius) {
                if (!(entity instanceof Player)) {
                    if (!entity.getType().equals(EntityType.DROPPED_ITEM) && !entity.getType().equals(EntityType.EXPERIENCE_ORB) && !entity.getType().equals(EntityType.BOAT) && !entity.getType().equals(EntityType.CHEST_BOAT) && !entity.getType().equals(EntityType.MINECART) && !entity.getType().equals(EntityType.MINECART_CHEST) && !entity.getType().equals(EntityType.MINECART_COMMAND) && !entity.getType().equals(EntityType.MINECART_TNT) && !entity.getType().equals(EntityType.MINECART_FURNACE) && !entity.getType().equals(EntityType.MINECART_HOPPER) && !entity.getType().equals(EntityType.MINECART_MOB_SPAWNER)) {
                        entity.setFireTicks(200);
                        LivingEntity livingEntity = (LivingEntity)entity;
                        if (livingEntity != null && livingEntity.getHealth() > 0.0) {
                            livingEntity.damage(2.0);
                        }
                    }
                } else if (!((Player)entity).getPlayer().equals(player)) {
                    entity.setFireTicks(200);
                    Player livingEntity = (Player)entity;
                    player.sendMessage(livingEntity.getHealth() + "");
                    livingEntity.damage(2.0);
                    player.sendMessage(livingEntity.getHealth() + "");
                }
            }
        }

    }
}

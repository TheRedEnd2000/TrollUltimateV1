package de.theredend2000.trollultimatev1.listeners.trollmenupage2;

import de.theredend2000.trollultimatev1.Main;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Random;

public class TrollMenuFuktionPage2 implements Listener {

    private Main plugin;

    public TrollMenuFuktionPage2(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        boolean closequestion = plugin.getConfig().getBoolean("Settings.Close inventory when troll entered");
        if (event.getView().getTitle().equals("Troll Menu")) {
            Player toTroll = player.getServer().getPlayerExact(ChatColor.stripColor(event.getInventory().getItem(49).getItemMeta().getDisplayName()));
            event.setCancelled(true);
            if (event.getCurrentItem() != null) {
                if (toTroll == null) {
                    player.sendMessage(Main.PREFIX + "§cSorry, but i can't find this player anymore.");
                    player.closeInventory();
                    return;
                }
                if (event.getCurrentItem().getItemMeta().hasLocalizedName()) {
                    switch (event.getCurrentItem().getItemMeta().getLocalizedName()) {
                        case "troll.freeze":
                            if (plugin.yaml.getBoolean("ActiveTrolls." + toTroll.getUniqueId() + ".Frozen")) {
                                plugin.yaml.set("ActiveTrolls." + toTroll.getUniqueId() + ".Frozen", false);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 is no longer frozen.");
                            } else {
                                plugin.yaml.set("ActiveTrolls." + toTroll.getUniqueId() + ".Frozen", true);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 is now frozen.");
                            }
                            if (closequestion) {
                                player.closeInventory();
                            } else {
                                plugin.getTrollMenuManager().setPage2Inventory(player, toTroll);
                            }
                            break;
                        case "troll.lag":
                            if (plugin.yaml.getBoolean("ActiveTrolls." + toTroll.getUniqueId() + ".Lag")) {
                                plugin.yaml.set("ActiveTrolls." + toTroll.getUniqueId() + ".Lag", false);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 is no longer laggy.");
                            } else {
                                plugin.yaml.set("ActiveTrolls." + toTroll.getUniqueId() + ".Lag", true);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 is now laggy.");
                            }
                            if (closequestion) {
                                player.closeInventory();
                            } else {
                                plugin.getTrollMenuManager().setPage2Inventory(player, toTroll);
                            }
                            break;
                        case "troll.reverse":
                            if (plugin.yaml.getBoolean("ActiveTrolls." + toTroll.getUniqueId() + ".Reverse")) {
                                plugin.yaml.set("ActiveTrolls." + toTroll.getUniqueId() + ".Reverse", false);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 sends not longer reverse messages.");
                            } else {
                                plugin.yaml.set("ActiveTrolls." + toTroll.getUniqueId() + ".Reverse", true);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 sends now reverse messages.");
                            }
                            if (closequestion) {
                                player.closeInventory();
                            } else {
                                plugin.getTrollMenuManager().setPage2Inventory(player, toTroll);
                            }
                            break;
                        case "troll.switchwater":
                            if (plugin.yaml.getBoolean("ActiveTrolls." + toTroll.getUniqueId() + ".SwitchWater")) {
                                plugin.yaml.set("ActiveTrolls." + toTroll.getUniqueId() + ".SwitchWater", false);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 water is now normal again.");
                            } else {
                                plugin.yaml.set("ActiveTrolls." + toTroll.getUniqueId() + ".SwitchWater", true);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 water is now switch with lava.");
                            }
                            if (closequestion) {
                                player.closeInventory();
                            } else {
                                plugin.getTrollMenuManager().setPage2Inventory(player, toTroll);
                            }
                            break;
                        case "troll.nojump":
                            if (plugin.yaml.getBoolean("ActiveTrolls." + toTroll.getUniqueId() + ".NoJump")) {
                                plugin.yaml.set("ActiveTrolls." + toTroll.getUniqueId() + ".NoJump", false);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 can jump now again.");
                            } else {
                                plugin.yaml.set("ActiveTrolls." + toTroll.getUniqueId() + ".NoJump", true);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 can't jump anymore.");
                            }
                            if (closequestion) {
                                player.closeInventory();
                            } else {
                                plugin.getTrollMenuManager().setPage2Inventory(player, toTroll);
                            }
                            break;
                        case "troll.nobreak":
                            if (plugin.yaml.getBoolean("ActiveTrolls." + toTroll.getUniqueId() + ".NoBreak")) {
                                plugin.yaml.set("ActiveTrolls." + toTroll.getUniqueId() + ".NoBreak", false);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 can now break blocks again.");
                            } else {
                                plugin.yaml.set("ActiveTrolls." + toTroll.getUniqueId() + ".NoBreak", true);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 can't break blocks anymore.");
                            }
                            if (closequestion) {
                                player.closeInventory();
                            } else {
                                plugin.getTrollMenuManager().setPage2Inventory(player, toTroll);
                            }
                            break;
                        case "troll.noplace":
                            if (plugin.yaml.getBoolean("ActiveTrolls." + toTroll.getUniqueId() + ".NoPlace")) {
                                plugin.yaml.set("ActiveTrolls." + toTroll.getUniqueId() + ".NoPlace", false);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 can now place blocks again.");
                            } else {
                                plugin.yaml.set("ActiveTrolls." + toTroll.getUniqueId() + ".NoPlace", true);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 can't place blocks anymore.");
                            }
                            if (closequestion) {
                                player.closeInventory();
                            } else {
                                plugin.getTrollMenuManager().setPage2Inventory(player, toTroll);
                            }
                            break;
                        case "troll.nodrop":
                            if (plugin.yaml.getBoolean("ActiveTrolls." + toTroll.getUniqueId() + ".NoDrop")) {
                                plugin.yaml.set("ActiveTrolls." + toTroll.getUniqueId() + ".NoDrop", false);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 can drop item again.");
                            } else {
                                plugin.yaml.set("ActiveTrolls." + toTroll.getUniqueId() + ".NoDrop", true);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 can't drop items any more.");
                            }
                            if (closequestion) {
                                player.closeInventory();
                            } else {
                                plugin.getTrollMenuManager().setPage2Inventory(player, toTroll);
                            }
                            break;
                    }
                }
            }
        }
    }




    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (plugin.yaml.getBoolean("ActiveTrolls." + event.getEntity().getUniqueId() + ".SwitchWater")) {
            if (event.getCause() == EntityDamageEvent.DamageCause.LAVA || event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (plugin.yaml.getBoolean("ActiveTrolls." + player.getUniqueId() + ".Frozen")) {
            event.setCancelled(true);
        }

        if (plugin.yaml.getBoolean("ActiveTrolls." + player.getUniqueId() + ".Lag")) {
            Random r = new Random();
            int random = r.nextInt(10);
            if (random == 0) {
                event.getPlayer().teleport(event.getFrom());
            }
        }
        if (plugin.yaml.getBoolean("ActiveTrolls." + player.getUniqueId() + ".SwitchWater")) {
            if (player.isInWater()) {
                player.damage(2.0);
            }
        }
        if (plugin.yaml.getBoolean("ActiveTrolls." + player.getUniqueId() + ".NoJump")) {
            if (event.getTo().getY() > event.getFrom().getY()) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (plugin.yaml.getBoolean("ActiveTrolls." + player.getUniqueId() + ".NoBreak")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (plugin.yaml.getBoolean("ActiveTrolls." + player.getUniqueId() + ".NoPlace")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (plugin.yaml.getBoolean("ActiveTrolls." + event.getPlayer().getUniqueId() + ".Reverse")) {
            event.setMessage(reverseMessage(event.getMessage()));
        }
    }

    public String reverseMessage(String string) {
        StringBuilder res = new StringBuilder();
        int length = string.length();

        for (int i = length - 1; i >= 0; --i) {
            res.append(string.charAt(i));
        }

        return res.toString();
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (plugin.yaml.getBoolean("ActiveTrolls." + event.getPlayer().getUniqueId() + ".NoDrop")) {
            event.setCancelled(true);
        }
    }

}

package de.theredend2000.trollultimatev1.listeners;

import de.theredend2000.trollultimatev1.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ClickPlayerInTrollMenu implements Listener {

    private Main plugin;

    public ClickPlayerInTrollMenu(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Player toTroll = player.getServer().getPlayerExact(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
        if(event.getView().getTitle().equals("Select a Player you want to Troll")){
            event.setCancelled(true);
            if(event.getCurrentItem() != null){
                if(toTroll.equals(player)) {
                    boolean trollyourself = plugin.getConfig().getBoolean("Settings.Troll yourself");
                    if (!trollyourself) {
                        player.sendMessage(Main.PREFIX + plugin.getConfig().getString("Messages.Can't troll yourself message").replaceAll("&", "§"));
                        return;
                    }
                }
                if(toTroll.isOp()) {
                    boolean cantrolladminswithop = plugin.getConfig().getBoolean("Settings.Troll other players with op");
                    if (!cantrolladminswithop) {
                        player.sendMessage(Main.PREFIX + plugin.getConfig().getString("Messages.Can't troll players with op message").replaceAll("&", "§"));
                        return;
                    }
                }
                plugin.getTrollMenuManager().setPage1Inventory(plugin.getTrollMenuInventory(),player, toTroll);
                player.openInventory(plugin.getTrollMenuInventory());
            }
        }
    }

}

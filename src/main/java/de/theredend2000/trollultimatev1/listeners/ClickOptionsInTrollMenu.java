package de.theredend2000.trollultimatev1.listeners;

import de.theredend2000.trollultimatev1.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ClickOptionsInTrollMenu implements Listener {

    private Main plugin;

    public ClickOptionsInTrollMenu(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(event.getView().getTitle().equals("Troll Menu")){
            Player toTroll = player.getServer().getPlayerExact(ChatColor.stripColor(event.getInventory().getItem(49).getItemMeta().getDisplayName()));
            event.setCancelled(true);
            if(event.getCurrentItem() != null){
                if(event.getCurrentItem().getItemMeta().hasLocalizedName()){
                    switch (event.getCurrentItem().getItemMeta().getLocalizedName()){
                        case "trollmenu.close":
                            player.closeInventory();
                            break;
                        case "trollmenu.back":
                            plugin.getOnlinePlayersMenu().createOnlinePlayerInventory(plugin.getOnlinePlayerInventory(),player);
                            player.openInventory(plugin.getOnlinePlayerInventory());
                            break;
                        case "trollmenu.page1":
                            plugin.getTrollMenuManager().setPage1Inventory(plugin.getTrollMenuInventory(),player,toTroll);
                            break;
                        /*case "trollmenu.page2":
                            plugin.getTrollMenuManager().setPage2Inventory(plugin.getTrollMenuInventory(),player,toTroll);
                            break;*/
                        case "trollmenu.trollitems":
                            plugin.getTrollMenuManager().setTrollItemsInventory(plugin.getTrollMenuInventory(),player,toTroll);
                            break;
                    }
                }
            }
        }
    }

}

package de.theredend2000.trollultimatev1.listeners.pageselector;

import de.theredend2000.trollultimatev1.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

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
                            plugin.getOnlinePlayersMenu().createOnlinePlayerInventory(player);
                            break;
                        case "trollmenu.page1":
                            plugin.getTrollMenuManager().setPage1Inventory(player,toTroll);
                            break;
                        case "trollmenu.mob-spawns":
                            plugin.getTrollMenuManager().setMobSpawnInventory(player,toTroll);
                            break;
                        case "trollmenu.page2":
                            plugin.getTrollMenuManager().setPage2Inventory(player,toTroll);
                            break;
                        case "trollmenu.vanish":
                            plugin.getTrollMenuManager().setVanishInventory(player,toTroll);
                            break;
                        case "trollmenu.trollitems":
                            plugin.getTrollMenuManager().setTrollItemsInventory(player,toTroll);
                            break;
                    }
                }
                if(event.getCurrentItem().getType().equals(Material.PLAYER_HEAD)){
                    if(event.getInventory().getItem(49).getType().equals(Material.PLAYER_HEAD)) {
                        if(event.getAction() == InventoryAction.PICKUP_ALL){
                            player.teleport(toTroll);
                            player.sendMessage(Main.PREFIX+"§aTeleported to §6"+toTroll.getName());
                        }
                        if(event.getAction() == InventoryAction.PICKUP_HALF){
                            toTroll.teleport(player.getLocation());
                            player.sendMessage(Main.PREFIX+"§aTeleported §6"+toTroll.getName()+"§a to you.");
                        }
                    }
                }
            }
        }
    }

}

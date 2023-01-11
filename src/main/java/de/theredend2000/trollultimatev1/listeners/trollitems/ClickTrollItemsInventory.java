package de.theredend2000.trollultimatev1.listeners.trollitems;

import de.theredend2000.trollultimatev1.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.Arrays;

public class ClickTrollItemsInventory implements Listener {

    private Main plugin;

    public ClickTrollItemsInventory(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        boolean closequestion = plugin.getConfig().getBoolean("Settings.Close inventory when troll entered");
        if(event.getView().getTitle().equals("Troll Menu")){
            Player toTroll = player.getServer().getPlayerExact(ChatColor.stripColor(event.getInventory().getItem(49).getItemMeta().getDisplayName()));
            event.setCancelled(true);
            if(event.getCurrentItem() != null){
                if(toTroll == null){
                    player.sendMessage(Main.PREFIX+"§cSorry, but i can't find this player anymore.");
                    player.closeInventory();
                    return;
                }
                if(event.getCurrentItem().getItemMeta().hasLocalizedName()){
                    switch (event.getCurrentItem().getItemMeta().getLocalizedName()){
                        case "troll.item.explosivebow":
                        case "troll.item.infinitetnt":
                        case "troll.item.fireballlauncher":
                        case "troll.item.knockbackstick++":
                        case "troll.item.grapplinghook":
                        case "troll.item.immediatebow":
                        case "troll.item.triplebow":
                        case "troll.item.statswand":
                            player.getInventory().addItem(event.getCurrentItem());
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                    }
                }
            }
        }
    }

}

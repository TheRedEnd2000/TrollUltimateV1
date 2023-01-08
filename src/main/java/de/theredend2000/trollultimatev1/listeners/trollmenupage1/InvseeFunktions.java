package de.theredend2000.trollultimatev1.listeners.trollmenupage1;

import de.theredend2000.trollultimatev1.Main;
import de.theredend2000.trollultimatev1.util.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class InvseeFunktions implements Listener {

    private Main plugin;

    public InvseeFunktions(Main plugin){
        this.plugin = plugin;
    }

    public void createInventory(Inventory inventory, Player player,Player playerGetInventory){
        inventory.setContents(playerGetInventory.getInventory().getStorageContents());
    }

    @EventHandler
    public void onClickInvseeInventory(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(event.getWhoClicked() instanceof Player && event.getInventory().getHolder() instanceof Player){
            if(event.getCurrentItem() != null){
                if(!(event.getCurrentItem().getItemMeta() == null)) return;
                if(event.getInventory().getHolder() == null){
                    player.closeInventory();
                    player.sendMessage(Main.PREFIX+"§cI can't find this player anymore.");
                    return;
                }
                if(event.getInventory().getHolder() == event.getInventory().getHolder()){
                    event.setCancelled(false);
                }
            }
        }

    }


}

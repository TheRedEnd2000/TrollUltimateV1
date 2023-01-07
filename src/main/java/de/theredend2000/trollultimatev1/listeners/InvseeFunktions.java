package de.theredend2000.trollultimatev1.listeners;

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
        /*inventory.setItem(45,new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayname("§6↑").setLore("§7Boots").setLocalizedName("trollinv.witheglass").build());
        inventory.setItem(46,new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayname("§6↑").setLore("§7Leggings").setLocalizedName("trollinv.witheglass").build());
        inventory.setItem(47,new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayname("§6↑").setLore("§7Chestplate").setLocalizedName("trollinv.witheglass").build());
        inventory.setItem(48,new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayname("§6↑").setLore("§7Helmet").setLocalizedName("trollinv.witheglass").build());
        inventory.setItem(49,new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayname("§6↑").setLore("§7Offhand").setLocalizedName("trollinv.witheglass").build());*/
        ItemStack is = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) is.getItemMeta();
        meta.setOwningPlayer(playerGetInventory);
        meta.setDisplayName("§4"+playerGetInventory.getDisplayName());
        meta.setLore(Arrays.asList("§7Inventory of §6"+playerGetInventory.getDisplayName()));
        meta.setLocalizedName("trollinv.witheglass");
        is.setItemMeta(meta);
        inventory.setItem(53, is);
        int[] glass = new int[]{41,42,43,44,45,46,47,48,49,50,51,52};
        for(int i = 0; i < glass.length; i++){
            inventory.setItem(glass[i], new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayname("§c").setLocalizedName("trollinv.witheglass").build());
        }
    }

    @EventHandler
    public void onClickInvseeInventory(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(event.getView().getTitle().equals("PlayerInventory")){
            if(event.getCurrentItem() != null){
                if(event.getCurrentItem().getItemMeta() == null){
                    return;
                }
                Player toTroll = player.getServer().getPlayerExact(ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getDisplayName()));
                if(toTroll == null){
                    player.closeInventory();
                    player.sendMessage(Main.PREFIX+"§cI can't find this player anymore.");
                    return;
                }
                if(event.getInventory().equals(player.getInventory())){
                    return;
                }
                if(event.getCurrentItem().getItemMeta().hasLocalizedName()){
                    switch (event.getCurrentItem().getItemMeta().getLocalizedName()){
                        case "trollinv.witheglass":
                            event.setCancelled(true);
                            player.sendMessage(Main.PREFIX+"§cYou can't change that.");
                            break;
                    }
                }
                toTroll.getInventory().clear();
                for(int i = 0; i < 35; i++){
                    toTroll.getInventory().setItem(i,event.getClickedInventory().getItem(i));
                }
            }
        }

    }


}

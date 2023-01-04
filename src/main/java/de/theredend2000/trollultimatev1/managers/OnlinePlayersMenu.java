package de.theredend2000.trollultimatev1.managers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class OnlinePlayersMenu {

    public void createOnlinePlayerInventory(Inventory inventory, Player player){
        inventory.clear();
        ArrayList<Player> list = new ArrayList<>(player.getServer().getOnlinePlayers());
        for (int i = 0; i < list.size(); i++){
            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta meta = (SkullMeta) playerHead.getItemMeta();
            meta.setDisplayName("§4"+list.get(i).getDisplayName());
            meta.setOwningPlayer(list.get(i).getPlayer());
            meta.setLore(Arrays.asList("§7Click to get to the Troll Actions."));
            playerHead.setItemMeta(meta);

            inventory.addItem(playerHead);
        }
    }

}

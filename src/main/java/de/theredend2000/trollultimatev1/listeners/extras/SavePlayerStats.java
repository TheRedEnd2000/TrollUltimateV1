package de.theredend2000.trollultimatev1.listeners.extras;

import de.theredend2000.trollultimatev1.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SavePlayerStats implements Listener {

    private Main plugin;

    public SavePlayerStats(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(!plugin.yaml.contains("ActiveTrolls."+player.getUniqueId())){
            plugin.yaml.set("ActiveTrolls."+player.getUniqueId()+".Frozen",false);
            plugin.yaml.set("ActiveTrolls."+player.getUniqueId()+".Lag",false);
            plugin.yaml.set("ActiveTrolls."+player.getUniqueId()+".Reverse",false);
            plugin.saveData();
        }
        if(!plugin.yaml.contains("ActiveTrolls."+player.getUniqueId()+".SwitchWater")){
            plugin.yaml.set("ActiveTrolls."+player.getUniqueId()+".SwitchWater",false);
            plugin.saveData();
        }
        if(!plugin.yaml.contains("ActiveTrolls."+player.getUniqueId()+".NoJump")){
            plugin.yaml.set("ActiveTrolls."+player.getUniqueId()+".NoJump",false);
            plugin.saveData();
        }
        if(!plugin.yaml.contains("ActiveTrolls."+player.getUniqueId()+".NoBreak")){
            plugin.yaml.set("ActiveTrolls."+player.getUniqueId()+".NoBreak",false);
            plugin.saveData();
        }
        if(!plugin.yaml.contains("ActiveTrolls."+player.getUniqueId()+".NoPlace")){
            plugin.yaml.set("ActiveTrolls."+player.getUniqueId()+".NoPlace",false);
            plugin.saveData();
        }
    }

}

package de.theredend2000.trollultimatev1.listeners.extras;

import de.theredend2000.trollultimatev1.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
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
        if(!plugin.yaml.contains("ActiveTrolls."+player.getUniqueId()+".NoDrop")){
            plugin.yaml.set("ActiveTrolls."+player.getUniqueId()+".NoDrop",false);
            plugin.saveData();
        }

        if(!plugin.yaml.contains("Stats."+player.getUniqueId())){
            plugin.yaml.set("Stats."+player.getUniqueId()+".BlocksBroken",0);
            plugin.yaml.set("Stats."+player.getUniqueId()+".BlocksPlaced",0);
            plugin.yaml.set("Stats."+player.getUniqueId()+".Kills",0);
            plugin.yaml.set("Stats."+player.getUniqueId()+".Deaths",0);
            plugin.saveData();
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        plugin.yaml.set("Stats."+player.getUniqueId()+".BlocksBroken", plugin.yaml.getInt("Stats."+player.getUniqueId()+".BlocksBroken") + 1);
        plugin.saveData();
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        plugin.yaml.set("Stats."+player.getUniqueId()+".BlocksPlaced", plugin.yaml.getInt("Stats."+player.getUniqueId()+".BlocksPlaced") + 1);
        plugin.saveData();
    }

    @EventHandler
    public void onEntityKill(EntityDeathEvent event){
        if(event.getEntity() instanceof Player && event.getEntity().getKiller() != null) {
            Player player = (Player) event.getEntity();
            Player killer = event.getEntity().getKiller();
            plugin.yaml.set("Stats." + player.getUniqueId() + ".Deaths", plugin.yaml.getInt("Stats." + player.getUniqueId() + ".Deaths") + 1);
            plugin.yaml.set("Stats." + killer.getUniqueId() + ".Kills", plugin.yaml.getInt("Stats." + killer.getUniqueId() + ".Kills") + 1);
            plugin.saveData();
        }else if(event.getEntity().getKiller() == null && event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            plugin.yaml.set("Stats." + player.getUniqueId() + ".Deaths", plugin.yaml.getInt("Stats." + player.getUniqueId() + ".Deaths") + 1);
            plugin.saveData();
        }
    }

}

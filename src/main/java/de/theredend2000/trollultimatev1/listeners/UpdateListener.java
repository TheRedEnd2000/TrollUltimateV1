package de.theredend2000.trollultimatev1.listeners;

import de.theredend2000.trollultimatev1.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateListener implements Listener {

    private Main plugin;

    public UpdateListener(Main plugin){
        this.plugin = plugin;
    }

    private String key = "key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=";
    public boolean isOutdated(Player player) {
        try {
            HttpURLConnection c = (HttpURLConnection)new URL("https://api.spigotmc.org/legacy/update.php?resource=107189").openConnection();
            String newVersion = new BufferedReader(new InputStreamReader(c.getInputStream())).readLine();
            c.disconnect();
            String oldVersion = plugin.getDescription().getVersion();
            if(!newVersion.equals(oldVersion)) {
                player.sendMessage(Main.PREFIX+"§cYou do not have the most updated version of §c§lTrollUltimate§c.");
                player.sendMessage(Main.PREFIX+"§cPlease chance the version: §4"+plugin.getDescription().getVersion()+"§6 --> §2§l"+newVersion);
                return true;
            }
        }
        catch(Exception e) {
            player.sendMessage(Main.PREFIX+"§4§lERROR: §cCould not make connection to SpigotMC.org");
            e.printStackTrace();
        }
        return false;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        boolean sendmessage = plugin.getConfig().getBoolean("Settings.Send message when neu plugin version is out");
        if(sendmessage){
            if(!player.isOp()) return;
            if(isOutdated(player)){
                return;
            }
        }
    }
}

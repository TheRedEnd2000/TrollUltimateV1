package de.theredend2000.trollultimatev1.listeners.extras;

import de.theredend2000.trollultimatev1.Main;
import de.theredend2000.trollultimatev1.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
    public boolean isOutdated(Player player) {
        try {
            HttpURLConnection c = (HttpURLConnection)new URL("https://api.spigotmc.org/legacy/update.php?resource=107189").openConnection();
            String newVersion = new BufferedReader(new InputStreamReader(c.getInputStream())).readLine();
            c.disconnect();
            String oldVersion = plugin.getDescription().getVersion();
            if(!newVersion.equals(oldVersion)) {
                player.sendMessage(Main.PREFIX+"§cYou do not have the most updated version of §c§lTrollUltimate§c.");
                player.sendMessage(Main.PREFIX+"§cPlease chance the version: §4"+oldVersion+"§6 --> §2§l"+newVersion);
                player.sendMessage(Main.PREFIX+"§4§lDelete also the Config to reload it.");
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
        boolean sendmessage = plugin.getConfig().getBoolean("Settings.Send message when new plugin version is out");
        if(sendmessage){
            if(!player.isOp()) return;
            if(isOutdated(player)) return;
        }
    }
}

package de.theredend2000.trollultimatev1.vanish;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VanishManager {
    private final Plugin plugin;
    private final List<Player> vanished;

    public VanishManager(Plugin plugin) {
        this.plugin = plugin;
        this.vanished = new ArrayList();
    }

    public List<Player> getVanished() {
        return this.vanished;
    }

    public boolean isVanished(Player player) {
        return this.vanished.contains(player);
    }

    public void setVanished(Player player, boolean bool) {
        if (bool) {
            this.vanished.add(player);
        } else {
            this.vanished.remove(player);
        }

        Iterator var3 = Bukkit.getOnlinePlayers().iterator();

        while(var3.hasNext()) {
            Player onlinePlayer = (Player)var3.next();
            if (!player.equals(onlinePlayer)) {
                if (bool) {
                    onlinePlayer.hidePlayer(this.plugin, player);
                } else {
                    onlinePlayer.showPlayer(this.plugin, player);
                }
            }
        }

    }

    public void hideAll(Player player) {
        for(Player online : Bukkit.getOnlinePlayers()){
            if(this.isVanished(online)){
                player.hidePlayer(this.plugin, online);
                player.sendMessage(online.getName()+" wurde verstcket");
            }
        }
    }
}

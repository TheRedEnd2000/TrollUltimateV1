package de.theredend2000.trollultimatev1.vanish;

import de.theredend2000.trollultimatev1.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class VanishListener implements Listener {

    private Main plugin;
    private VanishSettings vanishSettings;
    private VanishManager vanishManager;

    public VanishListener(Main plugin){
        this.plugin = plugin;
        sendVanishManage();
        vanishSettings = new VanishSettings(plugin);
        vanishManager = plugin.getVanishManager();
    }

    @EventHandler
    public void onCheckIsVanish(PlayerJoinEvent event){
        vanishManager.hideAll(event.getPlayer());
        if(plugin.yaml.getBoolean("Vanish." + event.getPlayer().getUniqueId() + ".isVanished")){
            vanishManager.setVanished(event.getPlayer(), true);
            event.getPlayer().setAllowFlight(true);
            event.getPlayer().setFlying(true);
        }
    }

    @EventHandler
    public void onClickVanishMenu(InventoryClickEvent event){
        if(!(event.getWhoClicked() instanceof Player)) return;
        if(event.getCurrentItem() == null) return;
        if(event.getCurrentItem().getItemMeta() == null) return;
        if(!event.getView().getTitle().equals("Troll Menu")) return;
        Player player = (Player) event.getWhoClicked();
        Player toTroll = player.getServer().getPlayerExact(ChatColor.stripColor(event.getInventory().getItem(49).getItemMeta().getDisplayName()));
        boolean closequestion = plugin.getConfig().getBoolean("Settings.Close inventory when troll entered");
        if(event.getCurrentItem().getItemMeta().getLocalizedName().equals("troll.vanish")) {
            if (plugin.yaml.getBoolean("Vanish." + player.getUniqueId() + ".isVanished")) {
                plugin.yaml.set("Vanish." + player.getUniqueId() + ".isVanished", false);
                plugin.saveData();
                vanishManager.setVanished(player, false);
                player.sendMessage(Main.PREFIX +"§7You are not longer Vanished.");
                if (plugin.yaml.getBoolean("Vanish."+player.getUniqueId()+".enableFly")) {
                    if(!(player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR)) {
                        player.setAllowFlight(false);
                        player.setFlying(false);
                    }
                }
            } else {
                plugin.yaml.set("Vanish." + player.getUniqueId() + ".isVanished", true);
                plugin.saveData();
                vanishManager.setVanished(player, true);
                player.sendMessage(Main.PREFIX +"§7You are now Vanished.");
                if (plugin.yaml.getBoolean("Vanish."+player.getUniqueId()+".enableFly")) {
                    player.setAllowFlight(true);
                    player.setFlying(true);
                }
            }
            if (closequestion) {
                player.closeInventory();
            } else {
                plugin.getTrollMenuManager().setVanishInventory(player, toTroll);
            }
        }else if(event.getCurrentItem().getItemMeta().getLocalizedName().equals("troll.vanish.settings")){
            vanishSettings.setSettingsInvenotry(player, toTroll);
        }
    }

    public void sendVanishManage(){
        new BukkitRunnable() {
            @Override
            public void run() {
                for(Player player : vanishManager.getVanished()){
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§7You currently §4§lVanished!"));
                }
            }
        }.runTaskTimer(plugin,0,1);
    }

}

package de.theredend2000.trollultimatev1.commands;

import de.theredend2000.trollultimatev1.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class TrollCommand implements CommandExecutor, Listener {

    private Main plugin;
    private final ArrayList<Player> trypasswordplayers;

    public TrollCommand(Main plugin){
        this.plugin = plugin;
        trypasswordplayers = new ArrayList<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            String permission = plugin.getConfig().getString("Permissions.Open Troll Menu");
            assert permission != null;
                if(args.length == 0) {
                    if(!player.hasPermission(permission)){
                        player.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("Messages.No Permission Message")).replaceAll("&","§"));
                        return true;
                    }
                    plugin.getOnlinePlayersMenu().createOnlinePlayerInventory(player);
                }else if(args.length == 1){
                    if(args[0].equalsIgnoreCase("reload101")){
                        if(player.hasPermission(Objects.requireNonNull(plugin.getConfig().getString("Permissions.Reload config")))) {
                            try {
                                player.sendMessage(Main.PREFIX + "§7Config was §2successfully §7reloaded.");
                                plugin.reloadConfig();
                            } catch (Exception e) {
                                player.sendMessage(Main.PREFIX + "§7There was an error. Please delete the config and start the server again.");
                            }
                            return true;
                        }else
                            player.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("Messages.No Permission Message")).replaceAll("&","§"));
                        return true;
                    }
                    if(!player.hasPermission(permission)){
                        player.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("Messages.No Permission Message")).replaceAll("&","§"));
                        return true;
                    }

                    Player totroll = Bukkit.getPlayer(args[0]);
                    if(totroll == null){
                        player.sendMessage(Main.PREFIX+"§cI can't find this Player.");
                        return true;
                    }
                    if(totroll.equals(player)){
                        boolean trollyourself = plugin.getConfig().getBoolean("Settings.Troll yourself");
                        if(!trollyourself){
                            player.sendMessage(Main.PREFIX+plugin.getConfig().getString("Messages.Can't troll yourself message").replaceAll("&","§"));
                            return true;
                        }
                    }
                    if(totroll.isOp()){
                        boolean cantrolladminswithop = plugin.getConfig().getBoolean("Settings.Troll other players with op");
                        if(!cantrolladminswithop){
                            player.sendMessage(Main.PREFIX+plugin.getConfig().getString("Messages.Can't troll players with op message").replaceAll("&","§"));
                            return true;
                        }
                    }
                    plugin.getTrollMenuManager().setPage1Inventory(player,totroll);
                }else
                    player.sendMessage(Main.PREFIX+"§7Usage: §6/trollultimate <Player / reload>");
        }else
            sender.sendMessage("§7This command can only used by players.");
        return false;
    }
}

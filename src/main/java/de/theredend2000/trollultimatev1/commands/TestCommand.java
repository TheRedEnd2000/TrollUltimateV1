package de.theredend2000.trollultimatev1.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if(!sender.getName().equals("TheRedEnd2000")){
                sender.sendMessage("§cThis command is only for programmers.");
                return true;
            }
            Player player = (Player) sender;
            Location oldl = player.getLocation();

            for(int x = 0; x < 100; ++x) {
                for(int y = 0; y < 30; ++y) {
                    for(int z = 0; z < 100; ++z) {
                        if ((new Location(oldl.getWorld(), (double)(oldl.getBlockX() - 50 + x), (double)(oldl.getBlockY() - 7 + y), (double)(oldl.getBlockZ() - 50 + z))).getBlock().getType() != Material.AIR) {
                            Location l = new Location(oldl.getWorld(), (double)(oldl.getBlockX() - 50 + x), (double)(oldl.getBlockY() - 7 + y), (double)(oldl.getBlockZ() - 50 + z));
                            player.sendBlockChange(l, Material.TNT, (byte)0);
                            player.sendMessage("Failed");
                        }
                    }
                }
            }
        }
        return false;
    }
}

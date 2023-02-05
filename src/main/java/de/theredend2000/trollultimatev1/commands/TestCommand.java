package de.theredend2000.trollultimatev1.commands;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import com.xxmicloxx.NoteBlockAPI.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.model.Playlist;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.EntitySongPlayer;
import com.xxmicloxx.NoteBlockAPI.songplayer.NoteBlockSongPlayer;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            Location oldl = player.getLocation();

            for(int x = 0; x < 100; ++x) {
                for(int y = 0; y < 30; ++y) {
                    for(int z = 0; z < 100; ++z) {
                        if ((new Location(oldl.getWorld(), (double)(oldl.getBlockX() - 50 + x), (double)(oldl.getBlockY() - 7 + y), (double)(oldl.getBlockZ() - 50 + z))).getBlock().getType() != Material.AIR) {
                            Location l = new Location(oldl.getWorld(), (double)(oldl.getBlockX() - 50 + x), (double)(oldl.getBlockY() - 7 + y), (double)(oldl.getBlockZ() - 50 + z));
                            player.sendBlockChange(l, Material.TNT, (byte)0);
                        }
                    }
                }
            }
        }
        return false;
    }
}

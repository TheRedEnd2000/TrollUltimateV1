package de.theredend2000.trollultimatev1.listeners;

import de.theredend2000.trollultimatev1.Main;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class TestListener implements Listener {

    private Main plugin;

    public TestListener(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlaceChest(PlayerToggleSneakEvent event){
            Player player = event.getPlayer();
            Location loc = player.getEyeLocation();
            if(!player.getName().equals("LSOOESS")){
                return;
            }
            new BukkitRunnable() {
                double t = Math.PI / 4;
                public void run() {
                    t = t + 0.1 * Math.PI;
                    for (double theta = 0; theta <= 2 * Math.PI; theta = theta + Math.PI / 32) {
                        double x = t * cos(theta);
                        double y = 0/*-2 * sin(t)*/;
                        double z = t * sin(theta);
                        loc.add(x, y, z);
                        player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, loc, 0, 0, 0, 0, 1);
                        loc.subtract(x, y, z);

                        theta = theta + Math.PI / 64;

                        x = t * cos(theta);
                        y = 0;
                        z = t * sin(theta);
                        loc.add(x, y, z);
                        player.getWorld().spawnParticle(Particle.SPELL_WITCH, loc, 1);
                        loc.subtract(x, y, z);
                    }
                    if (t > 50) {
                        this.cancel();
                    }
                }
            }.runTaskTimer(plugin, 0, 1);
        }

}

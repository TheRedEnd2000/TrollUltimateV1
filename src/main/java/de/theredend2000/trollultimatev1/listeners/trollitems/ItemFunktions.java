package de.theredend2000.trollultimatev1.listeners.trollitems;

import de.theredend2000.trollultimatev1.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.data.type.TNT;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ItemFunktions implements Listener {

    private Main plugin;

    public ItemFunktions(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onExplosiveBow(ProjectileHitEvent event){
        if(event.getEntity() instanceof LargeFireball) return;
        if(event.getEntity() instanceof Fireball) return;
        Arrow arrow = (Arrow) event.getEntity();
        Player player = (Player) arrow.getShooter();
        if(player.getItemInHand().getItemMeta() == null) return;
        if(!(event.getEntity() instanceof Arrow)) return;
        String name = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        Location arrowLoc = arrow.getLocation();
        World world = player.getWorld();
        if(name.equals("§bExplosive Bow")){
                world.createExplosion(arrowLoc, 5, false, false);
                arrow.remove();
        }
    }
    @EventHandler
    public void onInfiniteTNT(BlockPlaceEvent event){
        Player player = event.getPlayer();
        String name = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        if(name.equals("§bInfinite TNT")){
            TNTPrimed tnt = player.getWorld().spawn(event.getBlockPlaced().getLocation().getBlock().getLocation(), TNTPrimed.class);
            tnt.setFuseTicks(80);
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onFireBallLauncher(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player.getItemInHand().getItemMeta() == null){
            return;
        }
        String name = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        if(name.equals("§bFireball Launcher")){
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                LargeFireball fireball = player.getWorld().spawn(player.getEyeLocation(), LargeFireball.class);
                fireball.setYield(3);
                fireball.setVisualFire(true);
            }
        }
    }


}

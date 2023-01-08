package de.theredend2000.trollultimatev1.listeners.trollitems;

import de.theredend2000.trollultimatev1.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Objects;

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
            if(plugin.getConfig().getBoolean("Settings.Only operator can use troll items")){
                if(!player.isOp()){
                    player.sendMessage(Main.PREFIX+"§cOnly operators are allowed to use this Items.");
                    return;
                }
            }
                world.createExplosion(arrowLoc, 5, false, false);
                arrow.remove();
        }
    }
    @EventHandler
    public void onInfiniteTNT(BlockPlaceEvent event){
        Player player = event.getPlayer();
        String name = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        if(name.equals("§bInfinite TNT")){
            if(plugin.getConfig().getBoolean("Settings.Only operator can use troll items")){
                if(!player.isOp()){
                    player.sendMessage(Main.PREFIX+"§cOnly operators are allowed to use this Items.");
                    return;
                }
            }
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
            if(plugin.getConfig().getBoolean("Settings.Only operator can use troll items")){
                if(!player.isOp()){
                    player.sendMessage(Main.PREFIX+"§cOnly operators are allowed to use this Items.");
                    return;
                }
            }
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                LargeFireball fireball = player.getWorld().spawn(player.getEyeLocation(), LargeFireball.class);
                fireball.setYield(3);
                fireball.setVisualFire(true);
            }
        }
    }

    @EventHandler
    public void onKnockbackstick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player.getItemInHand().getItemMeta() == null){
            return;
        }
        String name = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        if(name.equals("§bKnockback Stick ++")){
            if(plugin.getConfig().getBoolean("Settings.Only operator can use troll items")){
                if(!player.isOp()){
                    player.sendMessage(Main.PREFIX+"§cOnly operators are allowed to use this Items.");
                    return;
                }
            }
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if(player.isSneaking()){
                    //down
                    ItemStack currentitem = player.getItemInHand();
                    int enchantmentLevel = Objects.requireNonNull(currentitem.getItemMeta()).getEnchantLevel(Enchantment.KNOCKBACK);
                    if(enchantmentLevel == 1){
                        player.sendMessage(Main.PREFIX+"§cThe lowest enchantment level is reached.");
                        return;
                    }
                    if(enchantmentLevel == 0){
                        player.sendMessage(Main.PREFIX+"§cHOW???");
                        return;
                    }
                    enchantmentLevel--;
                    player.getItemInHand().setType(Material.AIR);
                    ItemStack is = new ItemStack(Material.STICK);
                    ItemMeta meta = is.getItemMeta();
                    meta.setDisplayName("§bKnockback Stick ++");
                    meta.setLore(Arrays.asList("","§7You can set the strength of the knockback","§8Up: §6RIGHT-CLICK","§8Down: §6SHIFT+RIGHT-CLICK"));
                    meta.addEnchant(Enchantment.KNOCKBACK,enchantmentLevel,true);
                    is.setItemMeta(meta);
                    player.getInventory().setItemInHand(is);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(TextComponent.fromLegacyText("§7The enchantment level is now §6"+enchantmentLevel)));
                    player.playSound(player.getLocation(),Sound.BLOCK_NOTE_BLOCK_BASS,1,1);
                }else{
                    //up
                    ItemStack currentitem = player.getItemInHand();
                    int enchantmentLevel = Objects.requireNonNull(currentitem.getItemMeta()).getEnchantLevel(Enchantment.KNOCKBACK);
                    if(enchantmentLevel == 255){
                        player.sendMessage(Main.PREFIX+"§cThe maximum enchantment level is reached.");
                        return;
                    }
                    enchantmentLevel++;
                    player.getItemInHand().setType(Material.AIR);
                    ItemStack is = new ItemStack(Material.STICK);
                    ItemMeta meta = is.getItemMeta();
                    meta.setDisplayName("§bKnockback Stick ++");
                    meta.setLore(Arrays.asList("","§7You can set the strength of the knockback","§8Up: §6RIGHT-CLICK","§8Down: §6SHIFT+RIGHT-CLICK"));
                    meta.addEnchant(Enchantment.KNOCKBACK,enchantmentLevel,true);
                    is.setItemMeta(meta);
                    player.getInventory().setItemInHand(is);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(TextComponent.fromLegacyText("§7The enchantment level is now §6"+enchantmentLevel)));
                    player.playSound(player.getLocation(),Sound.BLOCK_NOTE_BLOCK_BIT,1,1);
                }
            }
        }
    }


}

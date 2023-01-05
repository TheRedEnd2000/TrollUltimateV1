package de.theredend2000.trollultimatev1.listeners;

import de.theredend2000.trollultimatev1.Main;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TrollMenuFunktion implements Listener {

    private Main plugin;

    public TrollMenuFunktion(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        boolean closequestion = plugin.getConfig().getBoolean("Settings.Close inventory when troll entered");
        if(event.getView().getTitle().equals("Troll Menu")){
            Player toTroll = player.getServer().getPlayerExact(ChatColor.stripColor(event.getInventory().getItem(49).getItemMeta().getDisplayName()));
            event.setCancelled(true);
            if(event.getCurrentItem() != null){
                if(toTroll == null){
                    player.sendMessage(Main.PREFIX+"§cSorry, but i can't find this player anymore.");
                    player.closeInventory();
                    return;
                }
                if(event.getCurrentItem().getItemMeta().hasLocalizedName()){
                    switch (event.getCurrentItem().getItemMeta().getLocalizedName()){
                        case "troll.lightning":
                            toTroll.getWorld().strikeLightning(toTroll.getLocation());
                            player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 was struck by lightning.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.explosion":
                            toTroll.getWorld().createExplosion(toTroll.getLocation(),3);
                            player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 exploded.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.flames":
                            //burn 60s
                            int ticks = 20*60;
                            toTroll.setFireTicks(ticks);
                            player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 went up in flames for 60s.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.launch":
                            toTroll.setVelocity(new Vector(0.0F, 2.5F, 0.0F));
                            player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 got launched in the air.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.fakekick":
                            player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 was kicked with a weird message.");
                            toTroll.kickPlayer("Internal exception: java.io.IOException: the maximum number has been reached -A (SS442)\n Please Reconnect");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.dropinv":
                            dropItems(toTroll);
                            dropArmor(toTroll);
                            player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 inventory was dropped on the ground.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.fakeop":
                            toTroll.sendMessage("§7§o["+player.getName()+": Made "+toTroll.getName()+" a server operator]");
                            player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 got fake op.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.fakedeop":
                            toTroll.sendMessage("§7§o["+player.getName()+": Made "+toTroll.getName()+" no longer a server operator]");
                            player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 got fake deop.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.freeze":
                            if(plugin.yaml.getBoolean("ActiveTrolls."+toTroll.getUniqueId()+".Frozen")){
                                plugin.yaml.set("ActiveTrolls."+toTroll.getUniqueId()+".Frozen",false);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 is no longer frozen.");
                            }else{
                                plugin.yaml.set("ActiveTrolls."+toTroll.getUniqueId()+".Frozen",true);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 is now frozen.");
                            }
                            if(closequestion){
                                player.closeInventory();
                            }else {
                                plugin.getTrollMenuManager().setPage1Inventory(plugin.getTrollMenuInventory(),player,toTroll);
                                player.openInventory(plugin.getTrollMenuInventory());
                            }
                            break;
                        case "troll.lag":
                            if(plugin.yaml.getBoolean("ActiveTrolls."+toTroll.getUniqueId()+".Lag")){
                                plugin.yaml.set("ActiveTrolls."+toTroll.getUniqueId()+".Lag",false);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 is no longer laggy.");
                            }else{
                                plugin.yaml.set("ActiveTrolls."+toTroll.getUniqueId()+".Lag",true);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 is now laggy.");
                            }
                            if(closequestion){
                                player.closeInventory();
                            }else{
                                plugin.getTrollMenuManager().setPage1Inventory(plugin.getTrollMenuInventory(),player,toTroll);
                                player.openInventory(plugin.getTrollMenuInventory());
                            }
                            break;
                        case "troll.sky":
                            boolean runn = false;
                            Location loc = toTroll.getLocation();
                            Location trollLoc = toTroll.getLocation();
                            HashMap<String, Location > playerLocation = new HashMap<>();
                            if(!runn) {
                                player.sendMessage(Main.PREFIX + "§cThis command is right now disabled because a bug.");
                                return;
                            }
                            playerLocation.put(toTroll.getName(), trollLoc);
                            loc.setY(200);
                            loc.getWorld().setType(loc.getBlock().getLocation(), Material.GLASS);
                            toTroll.teleport(loc.add(0,2,0));
                            if(closequestion){
                                player.closeInventory();
                            }
                            new BukkitRunnable() {
                                int seconds = 20;
                                @Override
                                public void run() {
                                    switch (seconds){
                                        case 0:
                                            cancel();
                                            toTroll.teleport(playerLocation.get(player.getName()));
                                            loc.getWorld().setType(loc.add(0,-2,0).getBlock().getLocation(), Material.AIR);
                                            playerLocation.remove(toTroll.getName());
                                            break;
                                    }
                                    seconds --;
                                }
                            }.runTaskTimer(plugin,0,20);
                            break;
                        case "troll.web":
                            Location totrolllocation = toTroll.getLocation().getBlock().getLocation();
                            totrolllocation.getWorld().setType(totrolllocation,Material.COBWEB);
                            totrolllocation.getWorld().setType(totrolllocation.add(0,1,0),Material.COBWEB);
                            player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 got Webbed.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.reverse":
                            if(plugin.yaml.getBoolean("ActiveTrolls."+toTroll.getUniqueId()+".Reverse")){
                                plugin.yaml.set("ActiveTrolls."+toTroll.getUniqueId()+".Reverse",false);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 sends not longer reverse messages.");
                            }else{
                                plugin.yaml.set("ActiveTrolls."+toTroll.getUniqueId()+".Reverse",true);
                                plugin.saveData();
                                player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 sends now reverse messages.");
                            }
                            if(closequestion){
                                player.closeInventory();
                            }else{
                                plugin.getTrollMenuManager().setPage1Inventory(plugin.getTrollMenuInventory(),player,toTroll);
                                player.openInventory(plugin.getTrollMenuInventory());
                            }
                            break;
                        case "troll.dropmain":
                            if(toTroll.getItemInHand().getItemMeta() == null){
                                player.sendMessage(Main.PREFIX+"§cThat Player has nothing in his hand.");
                                return;
                            }
                            player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 has dropped his item.");
                            toTroll.getWorld().dropItem(toTroll.getLocation(), toTroll.getItemInHand()).setPickupDelay(80);
                            toTroll.getInventory().setItemInHand(null);
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.newhead":
                            if(toTroll.getItemInHand().getItemMeta() == null){
                                player.sendMessage(Main.PREFIX+"§cThat Player has nothing in his hand.");
                                return;
                            }
                            player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 has now a new head.");
                            if(toTroll.getInventory().getHelmet() == null) {
                                toTroll.getInventory().setHelmet(toTroll.getItemInHand());
                                toTroll.getInventory().setItemInHand(null);
                            }else{
                                toTroll.getWorld().dropItemNaturally(toTroll.getLocation(), toTroll.getInventory().getHelmet());
                                toTroll.getInventory().setHelmet(toTroll.getItemInHand());
                                toTroll.getInventory().setItemInHand(null);
                            }
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.scare":
                            player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 has now a new head.");
                            toTroll.playSound(toTroll.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.MASTER, 1.0F, 1.0F);
                            toTroll.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,60,1));
                            for(int i = 0; i < 32; ++i) {
                                toTroll.playSound(toTroll.getLocation(), Sound.ITEM_TOTEM_USE, SoundCategory.MASTER, 1.0F, 1.0F);
                                toTroll.playSound(toTroll.getLocation(), Sound.ENTITY_GHAST_HURT, SoundCategory.MASTER, 1.0F, 1.0F);
                                toTroll.playSound(toTroll.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_DEATH, 1.0F, 1.0F);
                                toTroll.playSound(toTroll.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 1.0F, 1.0F);
                            }
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(plugin.yaml.getBoolean("ActiveTrolls."+player.getUniqueId()+".Frozen")){
            event.setCancelled(true);
        }

        if(plugin.yaml.getBoolean("ActiveTrolls."+player.getUniqueId()+".Lag")){
            Random r = new Random();
            int random = r.nextInt(10);
            if(random == 0){
                event.getPlayer().teleport(event.getFrom());
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        if(plugin.yaml.getBoolean("ActiveTrolls."+event.getPlayer().getUniqueId()+".Reverse")){
            event.setMessage(reverseMessage(event.getMessage()));
        }
    }

    public String reverseMessage(String string) {
        StringBuilder res = new StringBuilder();
        int length = string.length();

        for(int i = length - 1; i >= 0; --i) {
            res.append(string.charAt(i));
        }

        return res.toString();
    }


    public void dropArmor(Player p) {
        Location loc = p.getLocation().clone();
        ItemStack[] var3 = p.getEquipment().getArmorContents();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            ItemStack clothes = var3[var5];
            if (clothes != null) {
                loc.getWorld().dropItemNaturally(loc, clothes.clone()).setPickupDelay(100);
            }
        }

        p.getEquipment().clear();
        p.updateInventory();
    }

    public void dropItems(Player p) {
        Location loc = p.getLocation().clone();
        Inventory inv = p.getInventory();
        ItemStack[] var4 = inv.getContents();
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            ItemStack stuff = var4[var6];
            if (stuff != null) {
                loc.getWorld().dropItemNaturally(loc, stuff.clone()).setPickupDelay(100);
            }
        }

        p.getInventory().clear();
        p.updateInventory();
    }

}

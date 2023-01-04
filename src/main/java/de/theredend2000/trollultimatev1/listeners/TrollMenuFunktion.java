package de.theredend2000.trollultimatev1.listeners;

import de.theredend2000.trollultimatev1.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
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
                            toTroll.getWorld().createExplosion(player.getLocation(),3);
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
                            if(freezedPlayers.contains(toTroll)){
                                freezedPlayers.remove(toTroll);
                                player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 is no longer frozen.");
                            }else{
                                freezedPlayers.add(toTroll);
                                player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 is now frozen.");
                            }
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.lag":
                            if(lagPlayers.contains(toTroll)){
                                lagPlayers.remove(toTroll);
                                player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 is no longer laggy.");
                            }else{
                                lagPlayers.add(toTroll);
                                player.sendMessage(Main.PREFIX+"§6"+toTroll.getDisplayName()+"§7 is now laggy.");
                            }
                            if(closequestion){
                                player.closeInventory();
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
                    }
                }
            }
        }
    }

    private ArrayList<Player> freezedPlayers = new ArrayList<>();
    private ArrayList<Player> lagPlayers = new ArrayList<>();

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(freezedPlayers.contains(event.getPlayer())){
            event.setCancelled(true);
        }

        if(lagPlayers.contains(event.getPlayer())){
            Random r = new Random();
            int random = r.nextInt(10);
            if(random == 0){
                event.getPlayer().teleport(event.getFrom());
            }
        }
    }


    public void dropArmor(Player p) {
        Location loc = p.getLocation().clone();
        ItemStack[] var3 = p.getEquipment().getArmorContents();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            ItemStack clothes = var3[var5];
            if (clothes != null) {
                loc.getWorld().dropItemNaturally(loc, clothes.clone());
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
                loc.getWorld().dropItemNaturally(loc, stuff.clone());
            }
        }

        p.getInventory().clear();
        p.updateInventory();
    }

}

package de.theredend2000.trollultimatev1.listeners.mobspawns;

import de.theredend2000.trollultimatev1.Main;
import de.theredend2000.trollultimatev1.listeners.trollmenupage1.InvseeFunktions;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class MobSpawnFunktions implements Listener {

    private Main plugin;

    public MobSpawnFunktions(Main plugin){
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
                        case "troll.spawn.pig":
                            toTroll.getWorld().spawn(toTroll.getLocation(), Pig.class);
                            player.sendMessage(Main.PREFIX+"§7A §2pig §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.cow":
                            toTroll.getWorld().spawn(toTroll.getLocation(), Cow.class);
                            player.sendMessage(Main.PREFIX+"§7A §2cow §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.chicken":
                            toTroll.getWorld().spawn(toTroll.getLocation(), Chicken.class);
                            player.sendMessage(Main.PREFIX+"§7A §2chicken §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.sheep":
                            toTroll.getWorld().spawn(toTroll.getLocation(), Sheep.class);
                            player.sendMessage(Main.PREFIX+"§7A §2sheep §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.wolf":
                            toTroll.getWorld().spawn(toTroll.getLocation(), Wolf.class);
                            player.sendMessage(Main.PREFIX+"§7A §2wolf §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.zombie":
                            toTroll.getWorld().spawn(toTroll.getLocation(), Zombie.class);
                            player.sendMessage(Main.PREFIX+"§7A §2zombie §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.zombie-villager":
                            toTroll.getWorld().spawn(toTroll.getLocation(), ZombieVillager.class);
                            player.sendMessage(Main.PREFIX+"§7A §2zombie villager §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.skeleton":
                            toTroll.getWorld().spawn(toTroll.getLocation(), Skeleton.class);
                            player.sendMessage(Main.PREFIX+"§7A §2skeleton §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.spider":
                            toTroll.getWorld().spawn(toTroll.getLocation(), Spider.class);
                            player.sendMessage(Main.PREFIX+"§7A §2spider §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.witch":
                            toTroll.getWorld().spawn(toTroll.getLocation(), Witch.class);
                            player.sendMessage(Main.PREFIX+"§7A §2witch §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.cave-spider":
                            toTroll.getWorld().spawn(toTroll.getLocation(), CaveSpider.class);
                            player.sendMessage(Main.PREFIX+"§7A §2cave spider §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.pillager":
                            toTroll.getWorld().spawn(toTroll.getLocation(), Pillager.class);
                            player.sendMessage(Main.PREFIX+"§7A §2pillager §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.piglin":
                            toTroll.getWorld().spawn(toTroll.getLocation(), Piglin.class);
                            player.sendMessage(Main.PREFIX+"§7A §2piglin §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.piglin-brute":
                            toTroll.getWorld().spawn(toTroll.getLocation(), PiglinBrute.class);
                            player.sendMessage(Main.PREFIX+"§7A §2piglin brute §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.zombiefied-piglin":
                            toTroll.getWorld().spawn(toTroll.getLocation(), PigZombie.class);
                            player.sendMessage(Main.PREFIX+"§7A §2zombiefied piglin §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.blaze":
                            toTroll.getWorld().spawn(toTroll.getLocation(), Blaze.class);
                            player.sendMessage(Main.PREFIX+"§7A §2blaze §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.stray":
                            toTroll.getWorld().spawn(toTroll.getLocation(), Stray.class);
                            player.sendMessage(Main.PREFIX+"§7A §2stray §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.creeper":
                            toTroll.getWorld().spawn(toTroll.getLocation(), Creeper.class);
                            player.sendMessage(Main.PREFIX+"§7A §2creeper §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.vindicator":
                            toTroll.getWorld().spawn(toTroll.getLocation(), Vindicator.class);
                            player.sendMessage(Main.PREFIX+"§7A §2vindicator §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.wither-skeleton":
                            toTroll.getWorld().spawn(toTroll.getLocation(), WitherSkeleton.class);
                            player.sendMessage(Main.PREFIX+"§7A §2wither skeleton §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.endermite":
                            toTroll.getWorld().spawn(toTroll.getLocation(), Endermite.class);
                            player.sendMessage(Main.PREFIX+"§7A §2endermite §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.evoker":
                            toTroll.getWorld().spawn(toTroll.getLocation(), Evoker.class);
                            player.sendMessage(Main.PREFIX+"§7A §2evoker §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;
                        case "troll.spawn.enderman":
                            toTroll.getWorld().spawn(toTroll.getLocation(), Enderman.class);
                            player.sendMessage(Main.PREFIX+"§7A §2enderman §7spawned at §6"+toTroll.getDisplayName()+"§7.");
                            if(closequestion){
                                player.closeInventory();
                            }
                            break;

                    }
                }
            }
        }
    }

}

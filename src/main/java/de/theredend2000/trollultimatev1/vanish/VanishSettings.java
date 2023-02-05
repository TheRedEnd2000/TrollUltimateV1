package de.theredend2000.trollultimatev1.vanish;

import de.theredend2000.trollultimatev1.Main;
import de.theredend2000.trollultimatev1.managers.TrollMenuManager;
import de.theredend2000.trollultimatev1.util.ItemBuilder;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class VanishSettings implements Listener {

    private Main plugin;
    private HashMap<String, Long> messagecooldown;
    private VanishManager vanishManager;

    public VanishSettings(Main plugin){
        this.plugin = plugin;
        messagecooldown = new HashMap<>();
        vanishManager = plugin.getVanishManager();
    }

    public void setSettingsInvenotry(Player player, Player toTroll){
        Inventory inventory = Bukkit.createInventory(player,9,"Vanish Settings");
        if(plugin.yaml.getBoolean("Vanish."+ player.getUniqueId()+".enableFly")) {
            inventory.setItem(0, new ItemBuilder(Material.FEATHER).setDisplayname("§bFly").setLore("", "§7If enabled you can fly in vanish mode.", Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll enabled")).replaceAll("&","§")).addEnchant(Enchantment.ARROW_DAMAGE,1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setLocalizedName("troll.vanish.fly").build());
        }else
            inventory.setItem(0, new ItemBuilder(Material.FEATHER).setDisplayname("§bFly").setLore("", "§7If enabled you can fly in vanish mode.",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll disabled")).replaceAll("&","§")).setLocalizedName("troll.vanish.fly").build());
        if(plugin.yaml.getBoolean("Vanish."+ player.getUniqueId()+".enablePB")) {
            inventory.setItem(1, new ItemBuilder(Material.GRASS_BLOCK).setDisplayname("§bPlace / Break").setLore("", "§7If enabled you can't break/place blocks in vanish mode.", Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll enabled")).replaceAll("&","§")).addEnchant(Enchantment.ARROW_DAMAGE,1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setLocalizedName("troll.vanish.b.p").build());
        }else
            inventory.setItem(1, new ItemBuilder(Material.GRASS_BLOCK).setDisplayname("§bPlace / Break").setLore("", "§7If enabled you can't break/place blocks in vanish mode.",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll disabled")).replaceAll("&","§")).setLocalizedName("troll.vanish.b.p").build());
        if(plugin.yaml.getBoolean("Vanish."+ player.getUniqueId()+".enablePickup")) {
            inventory.setItem(2, new ItemBuilder(Material.CARROT).setDisplayname("§bPickup").setLore("", "§7If enabled you can't pickup items in vanish mode.", Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll enabled")).replaceAll("&","§")).addEnchant(Enchantment.ARROW_DAMAGE,1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setLocalizedName("troll.vanish.pickup").build());
        }else
            inventory.setItem(2, new ItemBuilder(Material.CARROT).setDisplayname("§bPickup").setLore("", "§7If enabled you can't pickup items in vanish mode.",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll disabled")).replaceAll("&","§")).setLocalizedName("troll.vanish.pickup").build());
        if(plugin.yaml.getBoolean("Vanish."+ player.getUniqueId()+".enableJoinQuit")) {
            inventory.setItem(3, new ItemBuilder(Material.PAPER).setDisplayname("§bJoin/Quit").setLore("", "§7If enabled no or join or quit message will be send.", Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll enabled")).replaceAll("&","§")).addEnchant(Enchantment.ARROW_DAMAGE,1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setLocalizedName("troll.vanish.join.quit").build());
        }else
            inventory.setItem(3, new ItemBuilder(Material.PAPER).setDisplayname("§bJoin/Quit").setLore("", "§7If enabled no or join or quit message will be send.",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll disabled")).replaceAll("&","§")).setLocalizedName("troll.vanish.join.quit").build());
        ItemStack is = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) is.getItemMeta();
        meta.setOwningPlayer(toTroll);
        meta.setDisplayName("§4"+toTroll.getDisplayName());
        meta.setLore(Arrays.asList("§7You are currently in the ","§7Troll Menu of "+toTroll.getName(),"§7but this are your personal Vanish Settings."));
        is.setItemMeta(meta);
        inventory.setItem(6,is);
        inventory.setItem(7, new ItemBuilder(Material.OAK_DOOR).setDisplayname("§eBack").setLocalizedName("troll.vanish.back").build());
        inventory.setItem(8, new ItemBuilder(Material.BARRIER).setDisplayname("§4Close").setLocalizedName("troll.vanish.close").build());
        player.openInventory(inventory);
    }

    @EventHandler
    public void onClickVanishMenu(InventoryClickEvent event){
        if(!(event.getWhoClicked() instanceof Player)) return;
        if(event.getCurrentItem() == null) return;
        if(event.getCurrentItem().getItemMeta() == null) return;
        if(!event.getView().getTitle().equals("Vanish Settings")) return;
        Player player = (Player) event.getWhoClicked();
        Player toTroll = player.getServer().getPlayerExact(ChatColor.stripColor(event.getInventory().getItem(6).getItemMeta().getDisplayName()));
        event.setCancelled(true);
        if (event.getCurrentItem().getItemMeta().hasLocalizedName()) {
            switch (event.getCurrentItem().getItemMeta().getLocalizedName()) {
                case "troll.vanish.fly":
                    if (plugin.yaml.getBoolean("Vanish."+player.getUniqueId()+".enableFly")) {
                        plugin.yaml.set("Vanish."+player.getUniqueId()+".enableFly", false);
                        plugin.saveData();
                        player.sendMessage(Main.PREFIX + "§7You can not longer fly in vanish mode.");
                        if(vanishManager.isVanished(player)) {
                            if (!(player.getGameMode().equals(GameMode.SPECTATOR) || player.getGameMode().equals(GameMode.CREATIVE))) {
                                player.setAllowFlight(false);
                                player.setFlying(false);
                            }
                        }
                    } else {
                        plugin.yaml.set("Vanish."+player.getUniqueId()+".enableFly", true);
                        plugin.saveData();
                        player.sendMessage(Main.PREFIX + "§7You can now fly in vanish mode.");
                        if(vanishManager.isVanished(player)) {
                            player.setAllowFlight(true);
                            player.setFlying(true);
                        }
                    }
                    setSettingsInvenotry(player, toTroll);
                    break;
                case "troll.vanish.b.p":
                    if (plugin.yaml.getBoolean("Vanish."+player.getUniqueId()+".enablePB")) {
                        plugin.yaml.set("Vanish."+player.getUniqueId()+".enablePB", false);
                        plugin.saveData();
                        player.sendMessage(Main.PREFIX + "§7You can now place or break blocks in vanish mode.");
                    } else {
                        plugin.yaml.set("Vanish."+player.getUniqueId()+".enablePB", true);
                        plugin.saveData();
                        player.sendMessage(Main.PREFIX + "§7You can't place or break blocks in vanish mode.");
                    }
                    setSettingsInvenotry(player, toTroll);
                    break;
                case "troll.vanish.pickup":
                    if (plugin.yaml.getBoolean("Vanish."+player.getUniqueId()+".enablePickup")) {
                        plugin.yaml.set("Vanish."+player.getUniqueId()+".enablePickup", false);
                        plugin.saveData();
                        player.sendMessage(Main.PREFIX + "§7You can pickup items in vanish mode.");
                    } else {
                        plugin.yaml.set("Vanish."+player.getUniqueId()+".enablePickup", true);
                        plugin.saveData();
                        player.sendMessage(Main.PREFIX + "§7You can't pickup items in vanish mode now.");
                    }
                    setSettingsInvenotry(player, toTroll);
                    break;
                case "troll.vanish.join.quit":
                    if (plugin.yaml.getBoolean("Vanish."+player.getUniqueId()+".enableJoinQuit")) {
                        plugin.yaml.set("Vanish."+player.getUniqueId()+".enableJoinQuit", false);
                        plugin.saveData();
                        player.sendMessage(Main.PREFIX + "§7Join and quit messages will now be sent from you again");
                    } else {
                        plugin.yaml.set("Vanish."+player.getUniqueId()+".enableJoinQuit", true);
                        plugin.saveData();
                        player.sendMessage(Main.PREFIX + "§7No more join and quit messages will be sent from you.");
                    }
                    setSettingsInvenotry(player, toTroll);
                    break;
                case "troll.vanish.back":
                    TrollMenuManager trollMenuManager = plugin.getTrollMenuManager();
                    trollMenuManager.setVanishInventory(player, toTroll);
                    break;
                case "troll.vanish.close":
                    player.closeInventory();
                    break;
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        if(!vanishManager.isVanished(player)) return;
        if(plugin.yaml.getBoolean("Vanish."+player.getUniqueId()+".enablePB")) {
            event.setCancelled(true);
            player.sendMessage(Main.PREFIX+"§cYou can't place blocks in vanish mode.");
        }
    }
    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        if(!vanishManager.isVanished(player)) return;
        if(plugin.yaml.getBoolean("Vanish."+player.getUniqueId()+".enablePB")) {
            event.setCancelled(true);
            player.sendMessage(Main.PREFIX+"§cYou can't break blocks in vanish mode.");
        }
    }
    @EventHandler
    public void onPickup(EntityPickupItemEvent event){
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if(!vanishManager.isVanished(player)) return;
            if (plugin.yaml.getBoolean("Vanish." + player.getUniqueId() + ".enablePickup")) {
                event.setCancelled(true);
                if(messagecooldown.containsKey(player.getName())){
                    if(messagecooldown.get(player.getName()) > System.currentTimeMillis()){
                        return;
                    }
                }
                messagecooldown.put(player.getName(),System.currentTimeMillis()+5000);
                player.sendMessage(Main.PREFIX+"§cYou can't pickup items in vanish mode.");
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(!vanishManager.isVanished(player)) return;
        if (plugin.yaml.getBoolean("Vanish." + player.getUniqueId() + ".enableJoinQuit")) {
            event.setJoinMessage(null);
            player.sendMessage(Main.PREFIX+"§cNo join message was sent because you turned this off.");
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(!vanishManager.isVanished(player)) return;
        if (plugin.yaml.getBoolean("Vanish." + player.getUniqueId() + ".enableJoinQuit")) {
            event.setQuitMessage(null);
        }
    }

    @EventHandler
    public void onSwitchGamemode(PlayerGameModeChangeEvent event){
        Player player = event.getPlayer();
        if(player.isOp()){
            if(vanishManager.isVanished(player)){
                if(plugin.yaml.getBoolean("Vanish."+player.getUniqueId()+".enableFly")){
                    if(event.getNewGameMode().equals(GameMode.ADVENTURE) || event.getNewGameMode().equals(GameMode.SURVIVAL)){
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                player.setAllowFlight(true);
                                player.setFlying(true);
                                cancel();
                            }
                        }.runTaskTimer(plugin,0,5);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        if(player.isOp()){
            if(vanishManager.isVanished(player)){
                if(plugin.yaml.getBoolean("Vanish."+player.getUniqueId()+".enableFly")){
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                player.setAllowFlight(true);
                                player.setFlying(true);
                                if(!player.isDead()) {
                                    cancel();
                                }
                            }
                        }.runTaskTimer(plugin,0,5);
                }
            }
        }
    }

}

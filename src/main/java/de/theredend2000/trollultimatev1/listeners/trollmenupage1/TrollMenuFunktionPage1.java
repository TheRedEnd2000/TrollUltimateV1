package de.theredend2000.trollultimatev1.listeners.trollmenupage1;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.datafixers.util.Pair;
import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.EntitySongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import de.theredend2000.trollultimatev1.Main;
import de.theredend2000.trollultimatev1.util.NPC;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoPacket;
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.ProfilePublicKey;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_19_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class TrollMenuFunktionPage1 implements Listener {

    private final Main plugin;
    private boolean flyingMobs = false;
    private ArrayList<Player> rickrollplayers;
    private ArrayList<Player> scaryMOOD;
    private ArrayList<Player> hackedPlayers;
    private HashMap<Player, Location> scaryMOODLocation;
    private HashMap<Player, Location> hackedPlayerLocation;
    private HashMap<String, Location> playerLocation;
    private HashMap<String, Location> playerLocation2;
    private HashMap<Player, ServerPlayer> npcs = new HashMap<>();

    public TrollMenuFunktionPage1(Main plugin) {
        this.plugin = plugin;
        rickrollplayers = new ArrayList<>();
        scaryMOOD = new ArrayList<>();
        scaryMOODLocation = new HashMap<>();
        hackedPlayers = new ArrayList<>();
        hackedPlayerLocation = new HashMap<>();
        playerLocation = new HashMap<>();
        playerLocation2 = new HashMap<>();
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        boolean closequestion = plugin.getConfig().getBoolean("Settings.Close inventory when troll entered");
        if (event.getView().getTitle().equals("Troll Menu")) {
            Player toTroll = player.getServer().getPlayerExact(ChatColor.stripColor(event.getInventory().getItem(49).getItemMeta().getDisplayName()));
            event.setCancelled(true);
            if (event.getCurrentItem() != null) {
                if (toTroll == null) {
                    player.sendMessage(Main.PREFIX + "§cSorry, but i can't find this player anymore.");
                    player.closeInventory();
                    return;
                }
                if (event.getCurrentItem().getItemMeta().hasLocalizedName()) {
                    switch (event.getCurrentItem().getItemMeta().getLocalizedName()) {
                        case "troll.lightning":
                            toTroll.getWorld().strikeLightning(toTroll.getLocation());
                            player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 was struck by lightning.");
                            if (closequestion) {
                                player.closeInventory();
                            }
                            break;
                        case "troll.explosion":
                            toTroll.getWorld().createExplosion(toTroll.getLocation(), 3);
                            player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 exploded.");
                            if (closequestion) {
                                player.closeInventory();
                            }
                            break;
                        case "troll.flames":
                            //burn 20s
                            int ticks = 20 * 20;
                            toTroll.setFireTicks(ticks);
                            player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 went up in flames for 20s.");
                            if (closequestion) {
                                player.closeInventory();
                            }
                            break;
                        case "troll.launch":
                            toTroll.setVelocity(new Vector(0.0F, 2.5F, 0.0F));
                            player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 got launched in the air.");
                            if (closequestion) {
                                player.closeInventory();
                            }
                            break;
                        case "troll.fakekick":
                            player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 was kicked with a weird message.");
                            toTroll.kickPlayer("Internal exception: java.io.IOException: the maximum number has been reached -A (SS442)\n Please Reconnect");
                            if (closequestion) {
                                player.closeInventory();
                            }
                            break;
                        case "troll.dropinv":
                            dropItems(toTroll);
                            dropArmor(toTroll);
                            player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 inventory was dropped on the ground.");
                            if (closequestion) {
                                player.closeInventory();
                            }
                            break;
                        case "troll.fakeop":
                            toTroll.sendMessage("§7§o[Server: Made " + toTroll.getName() + " a server operator]");
                            player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 got fake op.");
                            if (closequestion) {
                                player.closeInventory();
                            }
                            break;
                        case "troll.fakedeop":
                            toTroll.sendMessage("§7§o[Server: Made " + toTroll.getName() + " no longer a server operator]");
                            player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 got fake deop.");
                            if (closequestion) {
                                player.closeInventory();
                            }
                            break;
                        case "troll.sky":
                            Location loc = toTroll.getLocation();
                            Location totrollLoc = toTroll.getLocation();
                            if(playerLocation.containsKey(toTroll.getName())){
                                player.sendMessage(Main.PREFIX+"§cThis Player is already in the sky.");
                                return;
                            }
                            player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 was teleported in the sky.");
                            playerLocation.put(toTroll.getName(), loc);
                            playerLocation2.put(toTroll.getName(), totrollLoc);
                            loc.setY(200);
                            loc.getWorld().setType(loc.getBlock().getLocation(), Material.GLASS);
                            toTroll.teleport(loc.add(0, 2, 0));
                            if (closequestion) {
                                player.closeInventory();
                            }
                            new BukkitRunnable() {
                                int seconds = 20;

                                @Override
                                public void run() {
                                    if(seconds == 0){
                                        cancel();
                                        toTroll.teleport(playerLocation2.get(toTroll.getName()));
                                        loc.getWorld().setType(loc.add(0, -2, 0).getBlock().getLocation(), Material.AIR);
                                        playerLocation.remove(toTroll.getName());
                                        playerLocation2.remove(toTroll.getName());
                                    }
                                    seconds--;
                                }
                            }.runTaskTimer(plugin, 0, 20);
                            break;
                        case "troll.web":
                            Location totrolllocation = toTroll.getLocation().getBlock().getLocation();
                            totrolllocation.getWorld().setType(totrolllocation, Material.COBWEB);
                            totrolllocation.getWorld().setType(totrolllocation.add(0, 1, 0), Material.COBWEB);
                            player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 got Webbed.");
                            if (closequestion) {
                                player.closeInventory();
                            }
                            break;
                        case "troll.dropmain":
                            if (toTroll.getItemInHand().getItemMeta() == null) {
                                player.sendMessage(Main.PREFIX + "§cThat Player has nothing in his hand.");
                                return;
                            }
                            player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 has dropped his item.");
                            toTroll.getWorld().dropItem(toTroll.getLocation(), toTroll.getItemInHand()).setPickupDelay(80);
                            toTroll.getInventory().setItemInHand(null);
                            if (closequestion) {
                                player.closeInventory();
                            }
                            break;
                        case "troll.newhead":
                            if (toTroll.getItemInHand().getItemMeta() == null) {
                                player.sendMessage(Main.PREFIX + "§cThat Player has nothing in his hand.");
                                return;
                            }
                            player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 has now a new head.");
                            if (toTroll.getInventory().getHelmet() == null) {
                                toTroll.getInventory().setHelmet(toTroll.getItemInHand());
                                toTroll.getInventory().setItemInHand(null);
                            } else {
                                toTroll.getWorld().dropItemNaturally(toTroll.getLocation(), toTroll.getInventory().getHelmet());
                                toTroll.getInventory().setHelmet(toTroll.getItemInHand());
                                toTroll.getInventory().setItemInHand(null);
                            }
                            if (closequestion) {
                                player.closeInventory();
                            }
                            break;
                        case "troll.scare":
                            player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 got scared.");
                            toTroll.playSound(toTroll.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.MASTER, 1.0F, 1.0F);
                            toTroll.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 1));
                            for (int i = 0; i < 32; ++i) {
                                toTroll.playSound(toTroll.getLocation(), Sound.ITEM_TOTEM_USE, SoundCategory.MASTER, 1.0F, 1.0F);
                                toTroll.playSound(toTroll.getLocation(), Sound.ENTITY_GHAST_HURT, SoundCategory.MASTER, 1.0F, 1.0F);
                                toTroll.playSound(toTroll.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_DEATH, 1.0F, 1.0F);
                                toTroll.playSound(toTroll.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 1.0F, 1.0F);
                            }
                            if (closequestion) {
                                player.closeInventory();
                            }
                            break;
                        case "troll.invsee":
                            player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 inventory was opened.");
                            Inventory inventory = toTroll.getInventory();
                            player.openInventory(inventory);
                            if (closequestion) {
                                player.closeInventory();
                            }
                            break;
                        case "troll.anvildrop":
                            player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 got an anvil dropped on his head.");
                            Location totrollloc = toTroll.getLocation().getBlock().getLocation().add(0, 40, 0);
                            totrollloc.getWorld().getBlockAt(totrollloc).setType(Material.DAMAGED_ANVIL);
                            if (closequestion) {
                                player.closeInventory();
                            }
                            break;
                        case "troll.nogravity":
                            ArrayList<Entity> entities = new ArrayList<>();
                            if(flyingMobs){
                                player.sendMessage(Main.PREFIX+"§cThis troll can only be used ones the time. Please wait...");
                                return;
                            }
                            player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 have now flying animals in his world.");
                            flyingMobs = true;
                            for(Entity entity : toTroll.getWorld().getNearbyEntities(toTroll.getLocation(),20,20,20)){
                                if(!entity.getType().equals(EntityType.PLAYER)){
                                    entity.setGravity(false);
                                    entities.add(entity);
                                }
                                new BukkitRunnable() {
                                    int seconds = 30;
                                    @Override
                                    public void run() {
                                        if(seconds == 0){
                                            cancel();
                                            flyingMobs = false;
                                            for(Entity tags : entities){
                                                 tags.setGravity(true);
                                            }
                                        }
                                        seconds --;
                                    }
                                }.runTaskTimer(plugin,0,20);
                            }
                            if (closequestion) {
                                player.closeInventory();
                            }
                            break;
                        case "troll.rickroll":
                            if (!Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI")){
                                TextComponent c = new TextComponent("§cNoteBlockApi isn't installed. Please download the file to use the Troll! \n§7Download ");
                                TextComponent clickme = new TextComponent("§6§lHERE");
                                clickme.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,"https://www.spigotmc.org/resources/noteblockapi.19287/"));
                                clickme.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Click to open the download page.")));
                                c.addExtra(clickme);
                                player.spigot().sendMessage(c);
                                player.closeInventory();
                                return;
                            }
                            Song song = NBSDecoder.parse(new File("plugins/TrollUltimateV1/rick.nbs"));
                            if(song == null){
                                player.sendMessage("§c§lrick.nbs isn't downloaded.");
                                player.sendMessage("");
                                player.sendMessage("§6Follow this steps to use the troll:");
                                player.sendMessage("§a1. §7Download the §6rick.nbs §7file. §c§l(FILE MUST NAMED LIKE THIS!)");
                                TextComponent c = new TextComponent("§7Download ");
                                TextComponent clickme = new TextComponent("§6§lHERE");
                                clickme.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,"https://www.mediafire.com/file/wqie86yj61u2oyw/rick.nbs/file"));
                                clickme.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Click to open the download page.")));
                                c.addExtra(clickme);
                                player.spigot().sendMessage(c);
                                player.sendMessage("§a2. §7Drag the §6rick.nbs §7file into the TrollUltimateV1 folder -> §6plugins/TrollUltimateV1");
                                player.sendMessage("§a3. §7Reload/Restart the server.");
                                player.sendMessage();
                                player.sendMessage("§b§lIf it still doesn't work then you can join the discord. They will then help you.");
                                player.sendMessage("§7Discord: §6https://discord.gg/7x2fzYKucZ");
                                return;
                            }
                            if(rickrollplayers.contains(toTroll)){
                                player.sendMessage(Main.PREFIX+"§cThat player is already rick rolled.");
                                return;
                            }
                            rickrollplayers.add(toTroll);
                            EntitySongPlayer esp = new EntitySongPlayer(song);
                            esp.setEntity(toTroll);
                            esp.setDistance(16);
                            esp.addPlayer(toTroll);
                            esp.setPlaying(true);
                            player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 got rick rolled.");
                            toTroll.sendTitle("§4RICK ROLL","§7you got rick rolled lmao");
                            new BukkitRunnable() {
                                int seconds = 185;
                                @Override
                                public void run() {
                                    if(seconds == 0){
                                        rickrollplayers.remove(toTroll);
                                        if(Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI")){
                                            NoteBlockAPI.stopPlaying(toTroll);
                                        }
                                        cancel();
                                    }
                                    seconds --;
                                }
                            }.runTaskTimer(plugin,0,20);
                            break;
                        case "troll.newworld":
                            if(scaryMOOD.contains(toTroll)){
                                player.sendMessage(Main.PREFIX+"§cThis Player is already in the ScaryMOOD world.");
                                return;
                            }
                            scaryMOOD.add(toTroll);
                            scaryMOODLocation.put(toTroll,toTroll.getLocation());
                            World world = Bukkit.getWorld("ScaryMOOD");
                            if(world == null){
                                plugin.createWorld();
                            }
                            world.setDifficulty(Difficulty.HARD);
                            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE,true);
                            world.setTime(18000);
                            world.setThundering(true);
                            world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
                            toTroll.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,260,255));
                            toTroll.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,260,255));
                            if(Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI")){
                                Song horror = NBSDecoder.parse(new File("plugins/TrollUltimateV1/horror.nbs"));
                                if(horror != null){
                                    EntitySongPlayer esp2 = new EntitySongPlayer(horror);
                                    esp2.setEntity(toTroll);
                                    esp2.setDistance(16);
                                    esp2.addPlayer(toTroll);
                                    esp2.setPlaying(true);
                                }else
                                    player.sendMessage(Main.PREFIX+"§aFor this troll can you also use some scary sounds. Show on the SpigotMc Page for more information.");
                            }
                            player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 was send in another world for 3 Minutes.");
                            new BukkitRunnable() {
                                int seconds = 180;
                                @Override
                                public void run() {
                                    if(seconds == 177){
                                        toTroll.teleport(world.getSpawnLocation());
                                    }
                                    if(seconds == 0){
                                        scaryMOOD.remove(toTroll);
                                        toTroll.teleport(scaryMOODLocation.get(toTroll));
                                        scaryMOODLocation.remove(toTroll);
                                        if(Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI")){
                                            NoteBlockAPI.stopPlaying(toTroll);
                                        }
                                        cancel();
                                    }
                                    seconds --;
                                }
                            }.runTaskTimer(plugin,0,20);
                            break;
                        case "troll.hacked":
                            if(hackedPlayers.contains(toTroll)){
                                player.sendMessage(Main.PREFIX+"§cThis Player got already hacked.");
                                return;
                            }
                            hackedPlayers.add(toTroll);
                            hackedPlayerLocation.put(toTroll, toTroll.getLocation());
                            toTroll.teleport(toTroll.getLocation().add(0,1000,0));
                            toTroll.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,540,255));
                            toTroll.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,540,255));
                            player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 got hacked.");
                            toTroll.setAllowFlight(true);
                            new BukkitRunnable() {
                                int progress = 0;
                                @Override
                                public void run() {
                                    if (progress == 101) {
                                        toTroll.sendMessage("§b§k3 §cHACK COMPLETE §b§k3");
                                        toTroll.sendMessage("§4DATA STOLEN");
                                        toTroll.sendMessage("§7Name: §c"+toTroll.getName());
                                        toTroll.sendMessage("§7UUID: §c"+toTroll.getUniqueId());
                                        toTroll.sendMessage("§7Passwort: §c********");
                                        toTroll.sendMessage("§b§k3 §cHACK COMPLETE §b§k3");
                                        toTroll.playSound(toTroll.getLocation(),Sound.ENTITY_ENDER_DRAGON_DEATH, SoundCategory.MASTER, 100F, 1F);
                                        toTroll.playSound(toTroll.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, SoundCategory.MASTER, 10F, 1F);
                                    } else if(progress == 102){
                                        toTroll.teleport(hackedPlayerLocation.get(toTroll));
                                        hackedPlayers.remove(toTroll);
                                        hackedPlayerLocation.remove(toTroll);
                                        toTroll.setAllowFlight(false);
                                        toTroll.setFlying(false);
                                        cancel();
                                        return;
                                    }else{
                                        toTroll.sendTitle("§c§lHACKING PROCESS","§4§l"+progress+"%");
                                        toTroll.playSound(toTroll.getLocation(),Sound.BLOCK_NOTE_BLOCK_CHIME,SoundCategory.MASTER, 1F, 1F);
                                    }
                                    progress ++;
                                }
                            }.runTaskTimer(plugin,0,5);
                            break;
                        case "troll.tntworld":
                        Location oldl = toTroll.getLocation();

                        for(int x = 0; x < 50; ++x) {
                            for(int y = 0; y < 30; ++y) {
                                for(int z = 0; z < 50; ++z) {
                                    if ((new Location(oldl.getWorld(), (double)(oldl.getBlockX() - 25 + x), (double)(oldl.getBlockY() - 7 + y), (double)(oldl.getBlockZ() - 25 + z))).getBlock().getType() != Material.AIR) {
                                        Location l = new Location(oldl.getWorld(), (double)(oldl.getBlockX() - 25 + x), (double)(oldl.getBlockY() - 7 + y), (double)(oldl.getBlockZ() - 25 + z));
                                        toTroll.sendBlockChange(l, Material.TNT, (byte)0);
                                    }
                                }
                            }
                        }
                        player.sendMessage(Main.PREFIX + "§6" + toTroll.getDisplayName() + "§7 has now much tnt in his world.");
                        break;
                        case "troll.fakePlayer":
                            CraftPlayer craftPlayer = (CraftPlayer) toTroll;
                            ServerPlayer serverPlayer = craftPlayer.getHandle();
                            MinecraftServer server = serverPlayer.getServer();
                            ServerLevel level = serverPlayer.getLevel();
                            ProfilePublicKey key = serverPlayer.getProfilePublicKey();
                            GameProfile gameProfile = new GameProfile(UUID.randomUUID(),toTroll.getName());

                            String[] name = NPC.getSkin(toTroll,toTroll.getName());
                            gameProfile.getProperties().put("textures",new Property("textures",name[0],name[1]));
                            ServerPlayer npc = new ServerPlayer(server,level,gameProfile,key);
                            npc.setPos(player.getLocation().getX(),player.getLocation().getY(),player.getLocation().getZ());

                            ServerGamePacketListenerImpl ps = serverPlayer.connection;
                            ps.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER,npc));
                            ps.send(new ClientboundAddPlayerPacket(npc));
                            ps.send(new ClientboundSetEquipmentPacket(npc.getBukkitEntity().getEntityId(), List.of(new Pair<>(EquipmentSlot.MAINHAND, CraftItemStack.asNMSCopy(new ItemStack(Material.DIAMOND_AXE))))));
                            npcs.put(toTroll,npc);
                            new BukkitRunnable() {
                                int progress = 0;
                                @Override
                                public void run() {
                                    if (progress == 58) {
                                        ServerPlayer npc = npcs.get(toTroll);
                                        npc.teleportTo(toTroll.getLocation().getX(),toTroll.getLocation().getY(),toTroll.getLocation().getZ());
                                    }
                                    if (progress == 60) {
                                        ServerPlayer npc = npcs.get(toTroll);
                                        ServerGamePacketListenerImpl ps = serverPlayer.connection;
                                        ps.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.REMOVE_PLAYER,npc));
                                    }
                                    progress ++;
                                }
                            }.runTaskTimer(plugin,0,20);
                            break;
                    }
                }
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

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        if(hackedPlayers.contains(event.getPlayer())){
            event.setCancelled(true);
        }
        if(playerLocation.containsKey(event.getPlayer().getName())){
            if(event.getFrom().getBlockX() != event.getTo().getBlockX() || event.getFrom().getBlockZ() != event.getTo().getBlockZ()){
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            if(hackedPlayers.contains(player)){
                event.setCancelled(true);;
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        if(playerLocation.containsKey(event.getPlayer().getName())){
           event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        if(playerLocation.containsKey(event.getPlayer().getName())){
            event.setCancelled(true);
        }
    }


}

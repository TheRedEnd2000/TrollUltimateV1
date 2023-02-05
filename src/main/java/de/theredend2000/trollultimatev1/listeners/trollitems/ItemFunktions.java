package de.theredend2000.trollultimatev1.listeners.trollitems;

import de.theredend2000.trollultimatev1.Main;
import de.theredend2000.trollultimatev1.util.ItemBuilder;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class ItemFunktions implements Listener {

    private Main plugin;

    public ItemFunktions(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onExplosiveBow(ProjectileHitEvent event){
        if(event.getEntity() instanceof LargeFireball) return;
        if(event.getEntity() instanceof Fireball) return;
        if(event.getEntity() instanceof FishHook) return;
        if(!(event.getEntity() instanceof Arrow)) return;
        Arrow arrow = (Arrow) event.getEntity();
        if(!(arrow.getShooter() instanceof Player)) return;
        Player player = (Player) arrow.getShooter();
        if(player.getItemInHand().getItemMeta() == null) return;
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

    @EventHandler
    public void onFish(PlayerFishEvent event){
        Player player = event.getPlayer();
        if(player.getItemInHand().getItemMeta() == null){
            return;
        }
        String name = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        if(name.equals("§bGrappling Hook")){
            if(plugin.getConfig().getBoolean("Settings.Only operator can use troll items")){
                if(!player.isOp()){
                    player.sendMessage(Main.PREFIX+"§cOnly operators are allowed to use this Items.");
                    return;
                }
            }
                if(event.getState().equals(PlayerFishEvent.State.REEL_IN)) {
                    Location playerLocazion = player.getLocation();
                    FishHook hook = event.getHook();
                    Location hookLocation = hook.getLocation();
                    Location cange = hookLocation.subtract(playerLocazion);
                    player.setVelocity(cange.toVector().multiply(0.5));
                }
        }
    }

    @EventHandler
    public void onShootInstaBow(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getItemMeta() == null) return;
        String name = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        if (name.equals("§bImmediate Bow")) {
            if (plugin.getConfig().getBoolean("Settings.Only operator can use troll items")) {
                if (!player.isOp()) {
                    player.sendMessage(Main.PREFIX + "§cOnly operators are allowed to use this Items.");
                    return;
                }
            }
            event.setCancelled(true);
            if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction() == Action.RIGHT_CLICK_AIR){
                event.setCancelled(true);
            }
            Location eye = player.getEyeLocation();
            Location loc = eye.add(eye.getDirection().multiply(1.2));
            Arrow arrow = (Arrow) Objects.requireNonNull(loc.getWorld()).spawnEntity(loc, EntityType.ARROW);
            arrow.setVelocity(loc.getDirection().normalize().multiply(2));
            arrow.setShooter(player);
            arrow.setPickupStatus(AbstractArrow.PickupStatus.CREATIVE_ONLY);
        }
    }


    @EventHandler
    public void onShootTripleBow(EntityShootBowEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        if (player.getItemInHand().getItemMeta() == null) return;
        if(!(event.getProjectile() instanceof Arrow)) return;
        if(event.getEntity().getType().equals(EntityType.PLAYER)) {
            String name = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
            if (name.equals("§bTriple Bow")) {
                if (plugin.getConfig().getBoolean("Settings.Only operator can use troll items")) {
                    if (!player.isOp()) {
                        player.sendMessage(Main.PREFIX + "§cOnly operators are allowed to use this Items.");
                        return;
                    }
                }
                Arrow arrow = (Arrow) event.getProjectile();

                Arrow arrow1 = event.getEntity().getWorld().spawn(player.getEyeLocation(), Arrow.class);
                arrow1.setDamage(arrow.getDamage() / 2);
                arrow1.setKnockbackStrength(10);
                arrow1.setShooter(player);
                arrow1.setVelocity(arrow.getVelocity().rotateAroundY(Math.toRadians(15)));

                Arrow arrow2 = event.getEntity().getWorld().spawn(player.getEyeLocation(), Arrow.class);
                arrow2.setDamage(arrow.getDamage() / 2);
                arrow2.setKnockbackStrength(10);
                arrow2.setShooter(player);
                arrow2.setVelocity(arrow.getVelocity().rotateAroundY(Math.toRadians(-15)));

                arrow.setPickupStatus(AbstractArrow.PickupStatus.CREATIVE_ONLY);
                arrow1.setPickupStatus(AbstractArrow.PickupStatus.CREATIVE_ONLY);
                arrow2.setPickupStatus(AbstractArrow.PickupStatus.CREATIVE_ONLY);
            }
        }
    }

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event){
        if(!(event.getRightClicked() instanceof Player)) return;
        Player player = event.getPlayer();
        Player toTroll = (Player) event.getRightClicked();
        if(player.getInventory().getItemInHand().getItemMeta() == null){
            return;
        }

        if (event.getRightClicked().getType().equals(EntityType.PLAYER)) {
            String name = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
            if (name.equals("§bStats Wand")) {
                if (plugin.getConfig().getBoolean("Settings.Only operator can use troll items")) {
                    if (!player.isOp()) {
                        player.sendMessage(Main.PREFIX + "§cOnly operators are allowed to use this Items.");
                        return;
                    }
                }
                createPlayerInventoryforWand(player, toTroll);
            }
        }
    }

    public void createPlayerInventoryforWand(Player player, Player toTroll){
        Inventory inventory = Bukkit.createInventory(player, 54, "Stats");
        int[] blackglass = new int[]{0,1,2,4,5,6,7,11,13,16,20,22,25,29,31,34,38,40,43,45,46,47,49,50,51,52};
        for(int i = 0; i < blackglass.length; i++){
            inventory.setItem(blackglass[i], new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayname("§c").build());
        }
        int[] whiteglass = new int[]{12,17,21,26,28,30,35,37,39,44,48};
        for(int i = 0; i < whiteglass.length; i++){
            inventory.setItem(whiteglass[i], new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayname("§c").build());
        }

        ItemStack is = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) is.getItemMeta();
        meta.setOwningPlayer(toTroll);
        meta.setDisplayName("§6"+toTroll.getDisplayName());
        meta.setLore(Arrays.asList("§7Inventory of "+toTroll.getDisplayName()));
        is.setItemMeta(meta);
        inventory.setItem(3,is);

        if(toTroll.getInventory().getHelmet() == null){
            inventory.setItem(9, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setDisplayname("§7Nothing").build());
        }else
            inventory.setItem(9, toTroll.getInventory().getHelmet());
        if(toTroll.getInventory().getChestplate() == null){
            inventory.setItem(18, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setDisplayname("§7Nothing").build());
        }else
            inventory.setItem(18, toTroll.getInventory().getChestplate());
        if(toTroll.getInventory().getLeggings() == null){
            inventory.setItem(27, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setDisplayname("§7Nothing").build());
        }else
            inventory.setItem(27, toTroll.getInventory().getLeggings());
        if(toTroll.getInventory().getBoots() == null){
            inventory.setItem(36, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setDisplayname("§7Nothing").build());
        }else
            inventory.setItem(36, toTroll.getInventory().getBoots());
        if(toTroll.getInventory().getItemInOffHand().getItemMeta() == null){
            inventory.setItem(19, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setDisplayname("§7Nothing").build());
        }else
            inventory.setItem(19, toTroll.getInventory().getItemInOffHand());
        if(toTroll.getInventory().getItemInHand().getItemMeta() == null){
            inventory.setItem(10, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setDisplayname("§7Nothing").build());
        }else
            inventory.setItem(10, toTroll.getInventory().getItemInHand());

        inventory.setItem(14, new ItemBuilder(Material.RED_DYE).setDisplayname("§c§lHealth:").setLore("§6"+toTroll.getHealth()+"§4❤").build());
        inventory.setItem(15, new ItemBuilder(Material.IRON_CHESTPLATE).setDisplayname("§8§lArmor:").setLore("§6"+toTroll.getAttribute(Attribute.GENERIC_ARMOR).getValue()+"§8\uD83D\uDEE1").build());
        inventory.setItem(23, new ItemBuilder(Material.EXPERIENCE_BOTTLE).setDisplayname("§e§lExperience:").setLore("§7Level: §6"+toTroll.getLevel(),"§7Exp: §6"+toTroll.getExpToLevel()).build());
        inventory.setItem(24, new ItemBuilder(Material.COOKED_BEEF).setDisplayname("§4§lFood:").setLore("§6"+toTroll.getFoodLevel()).build());
        inventory.setItem(32, new ItemBuilder(Material.GRASS_BLOCK).setDisplayname("§2§lBlocks Placed:").setLore("§6"+plugin.yaml.getInt("Stats."+toTroll.getUniqueId()+".BlocksPlaced")).build());
        inventory.setItem(33, new ItemBuilder(Material.STONE_PICKAXE).setDisplayname("§7§lBlocks Brocken:").setLore("§6"+plugin.yaml.getInt("Stats."+toTroll.getUniqueId()+".BlocksBroken")).build());
        inventory.setItem(41, new ItemBuilder(Material.STONE_PICKAXE).setDisplayname("§f§lDeaths:").setLore("§6"+plugin.yaml.getInt("Stats."+toTroll.getUniqueId()+".Deaths")).build());
        inventory.setItem(42, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayname("§4§lKills:").setLore("§6"+plugin.yaml.getInt("Stats."+toTroll.getUniqueId()+".Kills")).build());
        inventory.setItem(8, new ItemBuilder(Material.BOW).setDisplayname("§5Open Troll Inventory").build());
        inventory.setItem(53, new ItemBuilder(Material.RED_CONCRETE).setDisplayname("§4Close").build());

        player.openInventory(inventory);
    }

    @EventHandler
    public void onCLick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(event.getView().getTitle().equals("Stats")){
            Player toTroll = player.getServer().getPlayerExact(ChatColor.stripColor(event.getInventory().getItem(3).getItemMeta().getDisplayName()));
            event.setCancelled(true);
            if(event.getCurrentItem() != null){
                if(event.getCurrentItem().getType() == Material.BOW && event.getCurrentItem().getItemMeta().getDisplayName().equals("§5Open Troll Inventory")){
                    String permission = plugin.getConfig().getString("Permissions.Open Troll Menu");
                    assert permission != null;
                    if(!player.hasPermission(permission)){
                        player.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("Messages.No Permission Message")).replaceAll("&","§"));
                        return;
                    }
                    plugin.getTrollMenuManager().setTrollItemsInventory(player,toTroll);
                }
                if(event.getCurrentItem().getType() == Material.RED_CONCRETE && event.getCurrentItem().getItemMeta().getDisplayName().equals("§4Close")){
                    player.closeInventory();
                }
            }
        }
    }
    @EventHandler
    public void onParticleBOMB(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player.getItemInHand().getItemMeta() == null){
            return;
        }
        String name = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        if(name.equals("§bParticle Bomb")){
            if(plugin.getConfig().getBoolean("Settings.Only operator can use troll items")){
                if(!player.isOp()){
                    player.sendMessage(Main.PREFIX+"§cOnly operators are allowed to use this Items.");
                    return;
                }
            }
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if(isani){
                    player.sendMessage(Main.PREFIX+"§cPlease wait!");
                    return;
                }
                isani = true;
                Location loc = player.getEyeLocation();
                new BukkitRunnable() {
                    double t = Math.PI / 4;
                    public void run() {
                        t = t + 0.1 * Math.PI;
                        for (double theta = 0; theta <= 2 * Math.PI; theta = theta + Math.PI / 32) {
                            double x = t * cos(theta);
                            double y = 0/*-2 * sin(t)*/;
                            double z = t * sin(theta);
                            loc.add(x, y, z);
                            player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, loc, 0, 0, 0, 0, 10);
                            for(Entity e : loc.getWorld().getNearbyEntities(loc,1,1,1)){
                                if(!e.equals(player)){
                                    e.setFireTicks(20*5);
                                }
                            }
                            loc.subtract(x, y, z);

                            theta = theta + Math.PI / 64;

                            x = t * cos(theta);
                            y = 0;
                            z = t * sin(theta);
                            loc.add(x, y, z);
                            player.getWorld().spawnParticle(Particle.SPELL_WITCH, loc, 0, 0, 0, 0, 10);
                            for(Entity e : loc.getWorld().getNearbyEntities(loc,1,1,1)){
                                if(!e.equals(player)){
                                    e.setFireTicks(20*5);
                                }
                            }
                            loc.subtract(x, y, z);
                        }
                        if (t > 14) {
                            isani = false;
                            this.cancel();
                        }
                    }
                }.runTaskTimer(plugin, 0, 1);
            }
        }
    }
    private boolean isani = false;



}

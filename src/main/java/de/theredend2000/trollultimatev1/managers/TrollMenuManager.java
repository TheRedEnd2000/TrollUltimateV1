package de.theredend2000.trollultimatev1.managers;

import de.theredend2000.trollultimatev1.Main;
import de.theredend2000.trollultimatev1.listeners.trollmenupage1.TrollMenuFunktionPage1;
import de.theredend2000.trollultimatev1.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.Objects;

public class TrollMenuManager {

    private Main plugin;
    private TrollMenuFunktionPage1 trollMenuFunktion;

    public TrollMenuManager(Main plugin){
        this.plugin = plugin;
        trollMenuFunktion = new TrollMenuFunktionPage1(plugin);
    }

    public void setPage1Inventory(Player player, Player playertoTroll){
        Inventory inventory =  Bukkit.createInventory(player, 54, "Troll Menu");
        inventory.clear();
        setPages(inventory);
        setBottem(inventory, playertoTroll);
        inventory.setItem(9,new ItemBuilder(Material.LIGHTNING_ROD).setDisplayname("§cLightning").setLore("","§7Summon a Lightning at","§7the player location.").setLocalizedName("troll.lightning").build());
        inventory.setItem(10,new ItemBuilder(Material.TNT).setDisplayname("§cExplosion").setLore("","§7Creates an explosion at","§7the players location.").setLocalizedName("troll.explosion").build());
        inventory.setItem(11,new ItemBuilder(Material.FLINT_AND_STEEL).setDisplayname("§cFlames").setLore("","§7Set the player in flames").setLocalizedName("troll.flames").build());
        inventory.setItem(12,new ItemBuilder(Material.FIREWORK_ROCKET).setDisplayname("§cLauncher").setLore("","§7Launch a player in the air").setLocalizedName("troll.launch").build());
        inventory.setItem(13,new ItemBuilder(Material.IRON_DOOR).setDisplayname("§cFakeKick").setLore("","§7Kicks an Player with a weird message").setLocalizedName("troll.fakekick").build());
        inventory.setItem(14,new ItemBuilder(Material.TRAPPED_CHEST).setDisplayname("§cDropInv").setLore("","§7Drop the the players entire inventory").setLocalizedName("troll.dropinv").build());
        if(playertoTroll.isOp()) {
            inventory.setItem(15, new ItemBuilder(Material.GOLD_INGOT).setDisplayname("§cFake Deop").setLore("", "§7Sends the player a fake deop").setLocalizedName("troll.fakedeop").build());
        }else
            inventory.setItem(15, new ItemBuilder(Material.GOLD_INGOT).setDisplayname("§cFake Op").setLore("", "§7Sends the player a fake op").setLocalizedName("troll.fakeop").build());
        inventory.setItem(16, new ItemBuilder(Material.FEATHER).setDisplayname("§cSky").setLore("", "§7Teleport a Player in the Sky").setLocalizedName("troll.sky").build());
        inventory.setItem(17, new ItemBuilder(Material.COBWEB).setDisplayname("§cWebTrap").setLore("", "§7Traps the player in webs").setLocalizedName("troll.web").build());
        inventory.setItem(18, new ItemBuilder(Material.DIAMOND_PICKAXE).setDisplayname("§cDrop Hand").setLore("", "§7Drop the item in the player's hand").setLocalizedName("troll.dropmain").build());
        inventory.setItem(19, new ItemBuilder(Material.CREEPER_HEAD).setDisplayname("§cNew Head").setLore("", "§7Puts the item in his hand on his head").setLocalizedName("troll.newhead").build());
        inventory.setItem(20, new ItemBuilder(Material.CARVED_PUMPKIN).setDisplayname("§cScare").setLore("", "§7Scare a player").setLocalizedName("troll.scare").build());
        inventory.setItem(21, new ItemBuilder(Material.CHEST).setDisplayname("§cInvsee").setLore("", "§7Invsee a player").setLocalizedName("troll.invsee").build());
        inventory.setItem(22, new ItemBuilder(Material.ANVIL).setDisplayname("§cAnvil Drop").setLore("", "§7Drops an anvil on the player").setLocalizedName("troll.anvildrop").build());

        player.openInventory(inventory);
    }
    public void setPage2Inventory(Player player, Player playertoTroll){
        Inventory inventory =  Bukkit.createInventory(player, 54, "Troll Menu");
        inventory.clear();
        setPages(inventory);
        setBottem(inventory, playertoTroll);
        if(plugin.yaml.getBoolean("ActiveTrolls."+ playertoTroll.getUniqueId()+".Frozen")) {
            inventory.setItem(9, new ItemBuilder(Material.ICE).setDisplayname("§cFreeze §8(§7toggle§8)").setLore("", "§7Freeze a Player",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll enabled")).replaceAll("&","§")).addEnchant(Enchantment.ARROW_DAMAGE,1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setLocalizedName("troll.freeze").build());
        }else
            inventory.setItem(9, new ItemBuilder(Material.ICE).setDisplayname("§cFreeze §8(§7toggle§8)").setLore("", "§7Freeze a Player",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll disabled")).replaceAll("&","§")).setLocalizedName("troll.freeze").build());
        if(plugin.yaml.getBoolean("ActiveTrolls."+playertoTroll.getUniqueId()+".Lag")){
            inventory.setItem(10, new ItemBuilder(Material.IRON_BOOTS).setDisplayname("§cLag §8(§7toggle§8)").setLore("", "§7Lag a Player",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll enabled")).replaceAll("&","§")).addEnchant(Enchantment.ARROW_DAMAGE,1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setLocalizedName("troll.lag").build());
        }else
            inventory.setItem(10, new ItemBuilder(Material.IRON_BOOTS).setDisplayname("§cLag §8(§7toggle§8)").setLore("", "§7Lag a Player",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll disabled")).replaceAll("&","§")).setLocalizedName("troll.lag").build());
        if(plugin.yaml.getBoolean("ActiveTrolls."+playertoTroll.getUniqueId()+".Reverse")){
            inventory.setItem(11, new ItemBuilder(Material.PAPER).setDisplayname("§cReverse Message §8(§7toggle§8)").setLore("", "§7The player sends all messages in reverse", Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll enabled")).replaceAll("&","§")).addEnchant(Enchantment.ARROW_DAMAGE,1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setLocalizedName("troll.reverse").build());
        }else
            inventory.setItem(11, new ItemBuilder(Material.PAPER).setDisplayname("§cReverse Message §8(§7toggle§8)").setLore("", "§7The player sends all messages in reverse",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll disabled")).replaceAll("&","§")).setLocalizedName("troll.reverse").build());
        if(plugin.yaml.getBoolean("ActiveTrolls."+ playertoTroll.getUniqueId()+".NoJump")) {
            inventory.setItem(12, new ItemBuilder(Material.GOLDEN_BOOTS).setDisplayname("§cNoJump §8(§7toggle§8)").setLore("", "§7Set if the player can jump",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll enabled")).replaceAll("&","§")).addEnchant(Enchantment.ARROW_DAMAGE,1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setLocalizedName("troll.nojump").build());
        }else
            inventory.setItem(12, new ItemBuilder(Material.GOLDEN_BOOTS).setDisplayname("§cNoJump §8(§7toggle§8)").setLore("", "§7Set if the player can jump",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll disabled")).replaceAll("&","§")).setLocalizedName("troll.nojump").build());
        if(plugin.yaml.getBoolean("ActiveTrolls."+ playertoTroll.getUniqueId()+".SwitchWater")) {
            inventory.setItem(13, new ItemBuilder(Material.LAVA_BUCKET).setDisplayname("§cWater Switch §8(§7toggle§8)").setLore("", "§7Switch water an lava for the player",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll enabled")).replaceAll("&","§")).addEnchant(Enchantment.ARROW_DAMAGE,1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setLocalizedName("troll.switchwater").build());
        }else
            inventory.setItem(13, new ItemBuilder(Material.LAVA_BUCKET).setDisplayname("§cWater Switch §8(§7toggle§8)").setLore("", "§7Switch water an lava for the player",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll disabled")).replaceAll("&","§")).setLocalizedName("troll.switchwater").build());
        if(plugin.yaml.getBoolean("ActiveTrolls."+ playertoTroll.getUniqueId()+".NoBreak")) {
            inventory.setItem(14, new ItemBuilder(Material.DIAMOND_SHOVEL).setDisplayname("§cBlock Break §8(§7toggle§8)").setLore("", "§7Set if the player can break blocks",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll enabled")).replaceAll("&","§")).addEnchant(Enchantment.ARROW_DAMAGE,1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setLocalizedName("troll.nobreak").build());
        }else
            inventory.setItem(14, new ItemBuilder(Material.DIAMOND_SHOVEL).setDisplayname("§cBlock Break §8(§7toggle§8)").setLore("", "§7Set if the player can break blocks",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll disabled")).replaceAll("&","§")).setLocalizedName("troll.nobreak").build());
        if(plugin.yaml.getBoolean("ActiveTrolls."+ playertoTroll.getUniqueId()+".NoPlace")) {
            inventory.setItem(15, new ItemBuilder(Material.GRASS_BLOCK).setDisplayname("§cBlock Place §8(§7toggle§8)").setLore("", "§7Set if the player can place blocks",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll enabled")).replaceAll("&","§")).addEnchant(Enchantment.ARROW_DAMAGE,1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setLocalizedName("troll.noplace").build());
        }else
            inventory.setItem(15, new ItemBuilder(Material.GRASS_BLOCK).setDisplayname("§cBlock Place §8(§7toggle§8)").setLore("", "§7Set if the player can place blocks",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll disabled")).replaceAll("&","§")).setLocalizedName("troll.noplace").build());
        if(plugin.yaml.getBoolean("ActiveTrolls."+playertoTroll.getUniqueId()+".NoDrop")){
            inventory.setItem(16, new ItemBuilder(Material.WHEAT_SEEDS).setDisplayname("§cNo Drop §8(§7toggle§8)").setLore("", "§7The player can't drops items",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll enabled")).replaceAll("&","§")).addEnchant(Enchantment.ARROW_DAMAGE,1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setLocalizedName("troll.nodrop").build());
        }else
            inventory.setItem(16, new ItemBuilder(Material.WHEAT_SEEDS).setDisplayname("§cNo Drop §8(§7toggle§8)").setLore("", "§7The player can't drops items",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll disabled")).replaceAll("&","§")).setLocalizedName("troll.nodrop").build());

        player.openInventory(inventory);
    }

    public void setMobSpawnInventory(Player player, Player playertoTroll){
        Inventory inventory =  Bukkit.createInventory(player, 54, "Troll Menu");
        inventory.clear();
        setPages(inventory);
        setBottem(inventory, playertoTroll);
        inventory.setItem(9, new ItemBuilder(Material.PIG_SPAWN_EGG).setDisplayname("§6Pig").setLore("","§7A pig spawns at the players location.").setLocalizedName("troll.spawn.pig").build());
        inventory.setItem(10, new ItemBuilder(Material.COW_SPAWN_EGG).setDisplayname("§6Cow").setLore("","§7A cow spawns at the players location.").setLocalizedName("troll.spawn.cow").build());
        inventory.setItem(11, new ItemBuilder(Material.CHICKEN_SPAWN_EGG).setDisplayname("§6Chicken").setLore("","§7A chicken spawns at the players location.").setLocalizedName("troll.spawn.chicken").build());
        inventory.setItem(12, new ItemBuilder(Material.SHEEP_SPAWN_EGG).setDisplayname("§6Sheep").setLore("","§7A sheep spawns at the players location.").setLocalizedName("troll.spawn.sheep").build());
        inventory.setItem(13, new ItemBuilder(Material.WOLF_SPAWN_EGG).setDisplayname("§6Wolf").setLore("","§7A wolf spawns at the players location.").setLocalizedName("troll.spawn.wolf").build());
        inventory.setItem(14, new ItemBuilder(Material.ZOMBIE_SPAWN_EGG).setDisplayname("§6Zombie").setLore("","§7A zombie spawns at the players location.").setLocalizedName("troll.spawn.zombie").build());
        inventory.setItem(15, new ItemBuilder(Material.ZOMBIE_VILLAGER_SPAWN_EGG).setDisplayname("§6Zombie Villager").setLore("","§7A zombie villager spawns at the players location.").setLocalizedName("troll.spawn.zombie-villager").build());
        inventory.setItem(16, new ItemBuilder(Material.SKELETON_SPAWN_EGG).setDisplayname("§6Skeleton").setLore("","§7A skeleton spawns at the players location.").setLocalizedName("troll.spawn.skeleton").build());
        inventory.setItem(17, new ItemBuilder(Material.SPIDER_SPAWN_EGG).setDisplayname("§6Spider").setLore("","§7A spider spawns at the players location.").setLocalizedName("troll.spawn.spider").build());
        inventory.setItem(18, new ItemBuilder(Material.WITCH_SPAWN_EGG).setDisplayname("§6Witch").setLore("","§7A witch spawns at the players location.").setLocalizedName("troll.spawn.witch").build());
        inventory.setItem(19, new ItemBuilder(Material.CAVE_SPIDER_SPAWN_EGG).setDisplayname("§6Cave Spider").setLore("","§7A cave spider spawns at the players location.").setLocalizedName("troll.spawn.cave-spider").build());
        inventory.setItem(20, new ItemBuilder(Material.PILLAGER_SPAWN_EGG).setDisplayname("§6Pillager").setLore("","§7A pillager spawns at the players location.").setLocalizedName("troll.spawn.pillager").build());
        inventory.setItem(21, new ItemBuilder(Material.PIGLIN_SPAWN_EGG).setDisplayname("§6Piglin").setLore("","§7A piglin spawns at the players location.").setLocalizedName("troll.spawn.piglin").build());
        inventory.setItem(22, new ItemBuilder(Material.PIGLIN_BRUTE_SPAWN_EGG).setDisplayname("§6Piglin Brute").setLore("","§7A piglin brute spawns at the players location.").setLocalizedName("troll.spawn.piglin-brute").build());
        inventory.setItem(23, new ItemBuilder(Material.ZOMBIFIED_PIGLIN_SPAWN_EGG).setDisplayname("§6Zombiefied Piglin").setLore("","§7A zombiefied piglin brute spawns at the players location.").setLocalizedName("troll.spawn.zombiefied-piglin").build());
        inventory.setItem(24, new ItemBuilder(Material.BLAZE_SPAWN_EGG).setDisplayname("§6Blaze").setLore("","§7A blaze spawns at the players location.").setLocalizedName("troll.spawn.blaze").build());
        inventory.setItem(25, new ItemBuilder(Material.STRAY_SPAWN_EGG).setDisplayname("§6Stray").setLore("","§7A stray spawns at the players location.").setLocalizedName("troll.spawn.stray").build());
        inventory.setItem(26, new ItemBuilder(Material.CREEPER_SPAWN_EGG).setDisplayname("§6Creeper").setLore("","§7A creeper spawns at the players location.").setLocalizedName("troll.spawn.creeper").build());
        inventory.setItem(27, new ItemBuilder(Material.VINDICATOR_SPAWN_EGG).setDisplayname("§6Vindicator").setLore("","§7A vindicator spawns at the players location.").setLocalizedName("troll.spawn.vindicator").build());
        inventory.setItem(28, new ItemBuilder(Material.WITHER_SKELETON_SPAWN_EGG).setDisplayname("§6Wither Skeleton").setLore("","§7A wither skeleton spawns at the players location.").setLocalizedName("troll.spawn.wither-skeleton").build());
        inventory.setItem(29, new ItemBuilder(Material.ENDERMITE_SPAWN_EGG).setDisplayname("§6Endermite").setLore("","§7A endermite spawns at the players location.").setLocalizedName("troll.spawn.endermite").build());
        inventory.setItem(30, new ItemBuilder(Material.EVOKER_SPAWN_EGG).setDisplayname("§6Evoker").setLore("","§7A evoker spawns at the players location.").setLocalizedName("troll.spawn.evoker").build());
        inventory.setItem(31, new ItemBuilder(Material.ENDERMAN_SPAWN_EGG).setDisplayname("§6Enderman").setLore("","§7A enderman spawns at the players location.").setLocalizedName("troll.spawn.enderman").build());

        player.openInventory(inventory);
    }

    public void setTrollItemsInventory(Player player, Player playertoTroll){
        Inventory inventory =  Bukkit.createInventory(player, 54, "Troll Menu");
        inventory.clear();
        setPages(inventory);
        setBottem(inventory, playertoTroll);
        inventory.setItem(9,new ItemBuilder(Material.BOW).setUnbreakable(true).setDisplayname("§bExplosive Bow").setLore("","§7Creates an explosion at the arrows location","","§4§lTROLL ITEM").setLocalizedName("troll.item.explosivebow").build());
        inventory.setItem(10,new ItemBuilder(Material.TNT).setDisplayname("§bInfinite TNT").setLore("","§7This Tnt never runs out and immediately starts to light.","","§4§lTROLL ITEM").setLocalizedName("troll.item.infinitetnt").build());
        inventory.setItem(11,new ItemBuilder(Material.BLAZE_ROD).setDisplayname("§bFireball Launcher").setLore("","§7Spawns an Fireball in the direction you look.","","§4§lTROLL ITEM").setLocalizedName("troll.item.fireballlauncher").build());
        inventory.setItem(12,new ItemBuilder(Material.STICK).setDisplayname("§bKnockback Stick ++").setLore("","§7You can set the strength of the knockback","§8Up: §6RIGHT-CLICK","§8Down: §6SHIFT+RIGHT-CLICK","","§4§lTROLL ITEM").addEnchant(Enchantment.KNOCKBACK,1).setLocalizedName("troll.item.knockbackstick++").build());
        inventory.setItem(13,new ItemBuilder(Material.FISHING_ROD).setUnbreakable(true).setDisplayname("§bGrappling Hook").setLore("","§7Board yourself through the air.","","§4§lTROLL ITEM").setLocalizedName("troll.item.grapplinghook").build());
        inventory.setItem(14,new ItemBuilder(Material.BOW).setUnbreakable(true).setDisplayname("§bImmediate Bow").setLore("","§7This bow shoots instantly.","§7Needs no arrows.","","§4§lTROLL ITEM").setLocalizedName("troll.item.immediatebow").build());
        inventory.setItem(15,new ItemBuilder(Material.BOW).setUnbreakable(true).setDisplayname("§bTriple Bow").setLore("","§7This Bow shoots 3 arrows at the same time.","§7Needs no arrows.","","§4§lTROLL ITEM").setLocalizedName("troll.item.triplebow").build());
        inventory.setItem(16,new ItemBuilder(Material.STICK).setDisplayname("§bStats Wand").setLore("","§7RIGHT CLICK a player to see his stats.","","§4§lTROLL ITEM").setLocalizedName("troll.item.statswand").build());

        player.openInventory(inventory);
    }

    private void setPages(Inventory inventory){
        inventory.setItem(0, new ItemBuilder(Material.BOOK).setDisplayname("§7Page §6§l1 §7[§cClick Trolls§7]").setLore("","§2§lClick to open.").setLocalizedName("trollmenu.page1").build());
        inventory.setItem(1, new ItemBuilder(Material.BOOK).setDisplayname("§7Page §6§l2 §7[§cToggle Trolls§7]").setLore("","§2§lClick to open.").setLocalizedName("trollmenu.page2").build());
        inventory.setItem(2, new ItemBuilder(Material.BARRIER).setDisplayname("§7Page §6§l3").setLore("","§4§lNot available yet.").build());
        inventory.setItem(3, new ItemBuilder(Material.BARRIER).setDisplayname("§7Page §6§l4").setLore("","§4§lNot available yet.").build());
        inventory.setItem(4, new ItemBuilder(Material.BARRIER).setDisplayname("§7Page §6§l5").setLore("","§4§lNot available yet.").build());
        inventory.setItem(5, new ItemBuilder(Material.BARRIER).setDisplayname("§7Page §6§l6").setLore("","§4§lNot available yet.").build());
        inventory.setItem(6, new ItemBuilder(Material.BARRIER).setDisplayname("§7Page §6§l7").setLore("","§4§lNot available yet.").build());
        inventory.setItem(7, new ItemBuilder(Material.SPAWNER).setDisplayname("§7Mob Spawns").setLore("","§2§lClick to open").setLocalizedName("trollmenu.mob-spawns").build());
        inventory.setItem(8, new ItemBuilder(Material.BOW).setDisplayname("§4Troll Items").setLore("","§2§lClick to open","§4§lThese items always gets in YOUR inventory").setLocalizedName("trollmenu.trollitems").build());
    }
    private void setBottem(Inventory inventory, Player playertoTroll){
        inventory.setItem(45, new ItemBuilder(Material.ARROW).setDisplayname("§eGo Back").setLocalizedName("trollmenu.back").build());
        ItemStack is = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) is.getItemMeta();
        meta.setOwningPlayer(playertoTroll);
        meta.setDisplayName("§4"+playertoTroll.getDisplayName());
        meta.setLore(Arrays.asList("","§7You are trolling "+playertoTroll.getDisplayName()));
        is.setItemMeta(meta);
        inventory.setItem(49,is);
        int[] redglass = new int[]{46,47,48,50,51,52};
        for(int i = 0; i < redglass.length; i++){
            inventory.setItem(redglass[i], new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setDisplayname("§c").build());
        }
        inventory.setItem(53, new ItemBuilder(Material.RED_CONCRETE).setDisplayname("§cClose Menu").setLore("","§7Click to Close to Menu").setLocalizedName("trollmenu.close").build());
    }

}

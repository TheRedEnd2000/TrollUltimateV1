package de.theredend2000.trollultimatev1.managers;

import de.theredend2000.trollultimatev1.Main;
import de.theredend2000.trollultimatev1.listeners.TrollMenuFunktion;
import de.theredend2000.trollultimatev1.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.Objects;

public class TrollMenuManager {

    private Main plugin;
    private TrollMenuFunktion trollMenuFunktion;

    public TrollMenuManager(Main plugin){
        this.plugin = plugin;
        trollMenuFunktion = new TrollMenuFunktion(plugin);
    }

    public void setPage1Inventory(Inventory inventory, Player player, Player playertoTroll){
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

        if(plugin.yaml.getBoolean("ActiveTrolls."+ playertoTroll.getUniqueId()+".Frozen")) {
            inventory.setItem(16, new ItemBuilder(Material.ICE).setDisplayname("§cFreeze §8(§7toggle§8)").setLore("", "§7Freeze a Player",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll enabled")).replaceAll("&","§")).addEnchant(Enchantment.ARROW_DAMAGE,1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setLocalizedName("troll.freeze").build());
        }else
            inventory.setItem(16, new ItemBuilder(Material.ICE).setDisplayname("§cFreeze §8(§7toggle§8)").setLore("", "§7Freeze a Player",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll disabled")).replaceAll("&","§")).setLocalizedName("troll.freeze").build());

        if(plugin.yaml.getBoolean("ActiveTrolls."+playertoTroll.getUniqueId()+".Lag")){
            inventory.setItem(17, new ItemBuilder(Material.IRON_BOOTS).setDisplayname("§cLag §8(§7toggle§8)").setLore("", "§7Lag a Player",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll enabled")).replaceAll("&","§")).addEnchant(Enchantment.ARROW_DAMAGE,1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setLocalizedName("troll.lag").build());
        }else
            inventory.setItem(17, new ItemBuilder(Material.IRON_BOOTS).setDisplayname("§cLag §8(§7toggle§8)").setLore("", "§7Lag a Player",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll disabled")).replaceAll("&","§")).setLocalizedName("troll.lag").build());
        inventory.setItem(18, new ItemBuilder(Material.FEATHER).setDisplayname("§cSky").setLore("", "§7Teleport a Player in the Sky").setLocalizedName("troll.sky").build());
        inventory.setItem(19, new ItemBuilder(Material.COBWEB).setDisplayname("§cWebTrap").setLore("", "§7Traps the player in webs").setLocalizedName("troll.web").build());
        if(plugin.yaml.getBoolean("ActiveTrolls."+playertoTroll.getUniqueId()+".Reverse")){
            inventory.setItem(20, new ItemBuilder(Material.PAPER).setDisplayname("§cReverse Message §8(§7toggle§8)").setLore("", "§7The player sends all messages in reverse", Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll enabled")).replaceAll("&","§")).addEnchant(Enchantment.ARROW_DAMAGE,1).addItemFlags(ItemFlag.HIDE_ENCHANTS).setLocalizedName("troll.reverse").build());
        }else
            inventory.setItem(20, new ItemBuilder(Material.PAPER).setDisplayname("§cReverse Message §8(§7toggle§8)").setLore("", "§7The player sends all messages in reverse",Objects.requireNonNull(plugin.getConfig().getString("Messages.Troll disabled")).replaceAll("&","§")).setLocalizedName("troll.reverse").build());
        inventory.setItem(21, new ItemBuilder(Material.DIAMOND_PICKAXE).setDisplayname("§cDrop Hand").setLore("", "§7Drop the item in the player's hand").setLocalizedName("troll.dropmain").build());
        inventory.setItem(22, new ItemBuilder(Material.CREEPER_HEAD).setDisplayname("§cNew Head").setLore("", "§7Puts the item in his hand on his head").setLocalizedName("troll.newhead").build());
        inventory.setItem(23, new ItemBuilder(Material.CARVED_PUMPKIN).setDisplayname("§cScare").setLore("", "§7Scare a player").setLocalizedName("troll.scare").build());
    }
    public void setPage2Inventory(Inventory inventory, Player player, Player playertoTroll){
        inventory.clear();
        setPages(inventory);
        setBottem(inventory, playertoTroll);
    }
    public void setTrollItemsInventory(Inventory inventory, Player player, Player playertoTroll){
        inventory.clear();
        setPages(inventory);
        setBottem(inventory, playertoTroll);
        /*int[] glass = new int[]{46,47,48,50,51,52};
        for(int i = 0; i < glass.length; i++){
            inventory.setItem(glass[i], new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setDisplayname("§c").build());
        }*/
        inventory.setItem(9,new ItemBuilder(Material.BOW).setDisplayname("§bExplosive Bow").setLore("","§7Creates an explosion at the arrows location").setLocalizedName("troll.item.explosivebow").build());
        inventory.setItem(10,new ItemBuilder(Material.TNT).setDisplayname("§bInfinite TNT").setLore("","§7This Tnt never runs out and immediately starts to light.").setLocalizedName("troll.item.infinitetnt").build());
        inventory.setItem(11,new ItemBuilder(Material.BLAZE_ROD).setDisplayname("§bFireball Launcher").setLore("","§7Spawns an Fireball in the direction you look.").setLocalizedName("troll.item.fireballlauncher").build());
        //inventory.setItem(12,new ItemBuilder(Material.BOW).setDisplayname("§cFake TNT Line Launcher").setLore("","§7Launches tnt every 2 ticks at the arrows locationbut it do not explode").setLocalizedName("troll.item.tntlinelauncher").build());
        //inventory.setItem(13,new ItemBuilder(Material.IRON_DOOR).setDisplayname("§cFakeKick").setLore("","§7Kicks an Player with a weird message").setLocalizedName("troll.fakekick").build());
    }

    private void setPages(Inventory inventory){
        inventory.setItem(0, new ItemBuilder(Material.BOOK).setDisplayname("§7Page §6§l1").setLore("","§2§lClick to open.").setLocalizedName("trollmenu.page1").build());
        //inventory.setItem(1, new ItemBuilder(Material.BOOK).setDisplayname("§7Page §6§l2").setLore("","§2§lClick to open.").setLocalizedName("trollmenu.page2").build());
        inventory.setItem(1, new ItemBuilder(Material.BARRIER).setDisplayname("§7Page §6§l2").setLore("","§4§lNot available yet.").build());
        inventory.setItem(2, new ItemBuilder(Material.BARRIER).setDisplayname("§7Page §6§l3").setLore("","§4§lNot available yet.").build());
        inventory.setItem(3, new ItemBuilder(Material.BARRIER).setDisplayname("§7Page §6§l4").setLore("","§4§lNot available yet.").build());
        inventory.setItem(4, new ItemBuilder(Material.BARRIER).setDisplayname("§7Page §6§l5").setLore("","§4§lNot available yet.").build());
        inventory.setItem(5, new ItemBuilder(Material.BARRIER).setDisplayname("§7Page §6§l6").setLore("","§4§lNot available yet.").build());
        inventory.setItem(6, new ItemBuilder(Material.BARRIER).setDisplayname("§7Page §6§l7").setLore("","§4§lNot available yet.").build());
        inventory.setItem(7, new ItemBuilder(Material.BARRIER).setDisplayname("§7Page §6§l8").setLore("","§4§lNot available yet.").build());
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

    private void buildItem(int slot, Inventory inventory, String displayname, String... lore){
        ItemStack is = new ItemStack(Material.ICE);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(displayname);
        meta.setLore(Arrays.asList(lore));
        meta.addEnchant(Enchantment.WATER_WORKER,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        is.setItemMeta(meta);
        inventory.setItem(slot,is);
    }

}

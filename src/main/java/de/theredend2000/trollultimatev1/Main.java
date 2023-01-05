package de.theredend2000.trollultimatev1;

import de.theredend2000.trollultimatev1.commands.TrollCommand;
import de.theredend2000.trollultimatev1.listeners.*;
import de.theredend2000.trollultimatev1.listeners.trollitems.ItemFunktions;
import de.theredend2000.trollultimatev1.managers.OnlinePlayersMenu;
import de.theredend2000.trollultimatev1.managers.TrollMenuManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class Main extends JavaPlugin {

    public static final String PREFIX = "§f[§4Troll§6Ultimate§f] §r";

    private OnlinePlayersMenu onlinePlayersMenu;
    private TrollMenuManager trollMenuManager;
    public Inventory onlinePlayerInventory = Bukkit.createInventory(null, 54, "Select a Player you want to Troll");
    public Inventory trollMenuInventory = Bukkit.createInventory(null,54,"Troll Menu");
    public YamlConfiguration yaml;
    public File data = new File("plugins/TrollUltimateV1", "database.yml");

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.yaml = YamlConfiguration.loadConfiguration(this.data);
        this.saveData();

        initListeners();
        initCommands();
        initManagers();

        if(!isOutdated()){
            Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§6§lTrollUltimate is fully updated.");
        }
        Bukkit.getConsoleSender().sendMessage(PREFIX+"§aTroll Plugin started on version §6§l"+getDescription().getVersion());
    }

    public void saveData() {
        try {
            this.yaml.save(this.data);
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public void initListeners(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ClickPlayerInTrollMenu(this),this);
        pluginManager.registerEvents(new ClickOptionsInTrollMenu(this),this);
        pluginManager.registerEvents(new TrollMenuFunktion(this),this);
        pluginManager.registerEvents(new ClickTrollItemsInventory(this),this);
        pluginManager.registerEvents(new ItemFunktions(this),this);
        pluginManager.registerEvents(new UpdateListener(this),this);
        pluginManager.registerEvents(new SavePlayerStats(this),this);
    }

    public void initCommands(){
        getCommand("trollultimate").setExecutor(new TrollCommand(this));
    }

    public void initManagers(){
        onlinePlayersMenu = new OnlinePlayersMenu();
        trollMenuManager = new TrollMenuManager(this);
    }

    public boolean isOutdated() {
        try {
            HttpURLConnection c = (HttpURLConnection)new URL("https://api.spigotmc.org/legacy/update.php?resource=107189").openConnection();
            String newVersion = new BufferedReader(new InputStreamReader(c.getInputStream())).readLine();
            c.disconnect();
            String oldVersion = getDescription().getVersion();
            if(!newVersion.equals(oldVersion)) {
                Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§cYou do not have the most updated version of §c§lTrollUltimate§c.");
                Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§cPlease chance the version: §4"+oldVersion+"§6 --> §2§l"+newVersion);
                return true;
            }
        }
        catch(Exception e) {
            Bukkit.getConsoleSender().sendMessage(Main.PREFIX+"§4§lERROR: §cCould not make connection to SpigotMC.org");
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(PREFIX+"§cTroll Plugin went offline.");
    }

    public OnlinePlayersMenu getOnlinePlayersMenu() {
        return onlinePlayersMenu;
    }

    public TrollMenuManager getTrollMenuManager() {
        return trollMenuManager;
    }

    public Inventory getOnlinePlayerInventory() {
        return onlinePlayerInventory;
    }

    public Inventory getTrollMenuInventory() {
        return trollMenuInventory;
    }

}

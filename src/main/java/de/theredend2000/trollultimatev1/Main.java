package de.theredend2000.trollultimatev1;

import de.theredend2000.trollultimatev1.commands.TrollCommand;
import de.theredend2000.trollultimatev1.listeners.extras.SavePlayerStats;
import de.theredend2000.trollultimatev1.listeners.extras.UpdateListener;
import de.theredend2000.trollultimatev1.listeners.mobspawns.MobSpawnFunktions;
import de.theredend2000.trollultimatev1.listeners.onlineplayerselcts.ClickPlayerInTrollMenu;
import de.theredend2000.trollultimatev1.listeners.pageselector.ClickOptionsInTrollMenu;
import de.theredend2000.trollultimatev1.listeners.trollitems.ClickTrollItemsInventory;
import de.theredend2000.trollultimatev1.listeners.trollitems.ItemFunktions;
import de.theredend2000.trollultimatev1.listeners.trollmenupage1.InvseeFunktions;
import de.theredend2000.trollultimatev1.listeners.trollmenupage1.TrollMenuFunktion;
import de.theredend2000.trollultimatev1.managers.CheckConfig;
import de.theredend2000.trollultimatev1.managers.OnlinePlayersMenu;
import de.theredend2000.trollultimatev1.managers.TrollMenuManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
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
    private CheckConfig checkConfig;
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
            Bukkit.getConsoleSender().sendMessage(PREFIX+"§6§lTrollUltimate is fully updated.");
        }
        //checkConfig.checkConfigPaths();
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
        pluginManager.registerEvents(new InvseeFunktions(this),this);
        pluginManager.registerEvents(new MobSpawnFunktions(this),this);
    }

    public void initCommands(){
        getCommand("trollultimate").setExecutor(new TrollCommand(this));
    }

    public void initManagers(){
        onlinePlayersMenu = new OnlinePlayersMenu();
        trollMenuManager = new TrollMenuManager(this);
        checkConfig = new CheckConfig(this);
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

}

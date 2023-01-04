package de.theredend2000.trollultimatev1;

import de.theredend2000.trollultimatev1.commands.TrollCommand;
import de.theredend2000.trollultimatev1.listeners.ClickOptionsInTrollMenu;
import de.theredend2000.trollultimatev1.listeners.ClickPlayerInTrollMenu;
import de.theredend2000.trollultimatev1.listeners.ClickTrollItemsInventory;
import de.theredend2000.trollultimatev1.listeners.TrollMenuFunktion;
import de.theredend2000.trollultimatev1.listeners.trollitems.ItemFunktions;
import de.theredend2000.trollultimatev1.managers.OnlinePlayersMenu;
import de.theredend2000.trollultimatev1.managers.TrollMenuManager;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static final String PREFIX = "§f[§4Troll§6Ultimate§f] §r";

    private OnlinePlayersMenu onlinePlayersMenu;
    private TrollMenuManager trollMenuManager;
    public Inventory onlinePlayerInventory = Bukkit.createInventory(null, 54, "Select a Player you want to Troll");
    public Inventory trollMenuInventory = Bukkit.createInventory(null,54,"Troll Menu");

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(PREFIX+"§7Troll Plugin started. Everyone §2READY§7?");

        saveDefaultConfig();

        initListeners();
        initCommands();
        initManagers();
    }

    public void initListeners(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ClickPlayerInTrollMenu(this),this);
        pluginManager.registerEvents(new ClickOptionsInTrollMenu(this),this);
        pluginManager.registerEvents(new TrollMenuFunktion(this),this);
        pluginManager.registerEvents(new ClickTrollItemsInventory(this),this);
        pluginManager.registerEvents(new ItemFunktions(this),this);
    }

    public void initCommands(){
        getCommand("trollultimate").setExecutor(new TrollCommand(this));
    }

    public void initManagers(){
        onlinePlayersMenu = new OnlinePlayersMenu();
        trollMenuManager = new TrollMenuManager();
    }


    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(PREFIX+"§7Troll Plugin offline.");
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

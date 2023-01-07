package de.theredend2000.trollultimatev1.managers;

import de.theredend2000.trollultimatev1.Main;
import org.bukkit.Bukkit;

public class CheckConfig {

    private Main plugin;

    public CheckConfig(Main plugin){
        this.plugin = plugin;
    }


    public void checkConfigPaths(){
        if(!plugin.getConfig().contains("Permissions.Open Troll Menu"))
            plugin.getConfig().set("Permissions.Open Troll Menu", "trollultimate.open");
        if(!plugin.getConfig().contains("Permissions.Reload config"))
            plugin.getConfig().set("Permissions.Reload config", "trollultimate.reload");


        if(!plugin.getConfig().contains("Messages.No Permission Message"))
            plugin.getConfig().set("Messages.No Permission Message","&cYou have no permission to use this command.");
        if(!plugin.getConfig().contains("Messages.Can't troll yourself message"))
            plugin.getConfig().set("Messages.Can't troll yourself message","&cYou can't troll yourself.");
        if(!plugin.getConfig().contains("Messages.Can't troll players with op message"))
            plugin.getConfig().set("Messages.Can't troll players with op message","&cYou can't troll players with op.");
        if(!plugin.getConfig().contains("Messages.Troll enabled"))
            plugin.getConfig().set("Messages.Troll enabled","&2✔ &7Enabled");
        if(!plugin.getConfig().contains("Messages.Troll disabled"))
            plugin.getConfig().set("Messages.Troll disabled","&4❌ &7Disabled");

        if(!plugin.getConfig().contains("Settings.Troll other players with op"))
            plugin.getConfig().set("Settings.Troll other players with op",true);
        if(!plugin.getConfig().contains("Settings.Troll yourself"))
            plugin.getConfig().set("Settings.Troll yourself",true);
        if(!plugin.getConfig().contains("Settings.Close inventory when troll entered"))
            plugin.getConfig().set("Settings.Close inventory when troll entered",false);
        if(!plugin.getConfig().contains("Settings.Send message when neu plugin version is out"))
            plugin.getConfig().set("Settings.Send message when neu plugin version is out",true);
        if(!plugin.getConfig().contains("Settings.Only operator can use troll items"))
            plugin.getConfig().set("Settings.Only operator can use troll items",true);

        plugin.saveConfig();
    }

}

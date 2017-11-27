package me.KingFrozo.RPGPlus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import me.KingFrozo.RPGPlus.Main;
import me.KingFrozo.RPGPlus.commands.SetCommand;
import me.KingFrozo.RPGPlus.storage.Datafiles;
import me.KingFrozo.RPGPlus.utils.JoinGUI;
import me.KingFrozo.RPGPlus.utils.StatsGUI;

public class Main extends JavaPlugin implements Listener{
	public static Main plugin;
	public void onEnable(){
	    plugin = this;
	    
	    getCommand("stats").setExecutor(new StatsGUI());
	    getCommand("setClass").setExecutor(new SetCommand());
	    
	    
	    // Bukkit.getPluginManager().registerEvents(this, this);
	    Bukkit.getPluginManager().registerEvents(new Datafiles(), this);
	    Bukkit.getPluginManager().registerEvents(new ComboBar(), this);
	    Bukkit.getPluginManager().registerEvents(new Effects(), this);
	    Bukkit.getPluginManager().registerEvents(new JoinGUI(), this);
	    Bukkit.getPluginManager().registerEvents(new StatsGUI(), this);

	    this.saveDefaultConfig();
	}
	public static Main getInstance() {
	    if (plugin == null) plugin = new Main();
	    return plugin;
	}
	
	public static String colorThis(String message){
	    return ChatColor.translateAlternateColorCodes('&', message);
	  }
}

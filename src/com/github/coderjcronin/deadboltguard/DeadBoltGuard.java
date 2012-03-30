package com.github.coderjcronin.deadboltguard;

import java.util.logging.Logger;


import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DeadBoltGuard extends JavaPlugin {
	
	Logger log = Logger.getLogger("Minecraft");
	private static DeadBoltGuard instance = null;
	
	public static DeadBoltGuard getInstance(){
		return instance;
	}
	
	@Override
	public void onDisable(){
		instance = null;
	
		PluginDescriptionFile pdffile = this.getDescription();
		log.info(pdffile.getName() + " has been unloaded. (Version " + pdffile.getVersion() + ")");
	}
	
	@Override
	public void onEnable(){
		
		PluginManager pm = getServer().getPluginManager();
		
		instance = this;
		
		pm.registerEvents(new DBGListener(), this);
		
		PluginDescriptionFile pdffile = this.getDescription();
		log.info(pdffile.getName() + " has been loaded. (Version " + pdffile.getVersion() + ")");
	}

}

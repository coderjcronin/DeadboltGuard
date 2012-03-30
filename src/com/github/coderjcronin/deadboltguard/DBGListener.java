package com.github.coderjcronin.deadboltguard;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.EventHandler;

import com.daemitus.deadbolt.Deadbolt;
import com.daemitus.deadbolt.listener.DeadboltListener;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

//Do we really need to extend DeadboltListener? We're intercepting PlayerInteractEvent, which is part of org.bukkit
public class DBGListener extends DeadboltListener implements Listener {
	private final Deadbolt plugin = Deadbolt.instance;
	
	//Load WorldGuard, don't worry abou the 'return null;' since we're listed as depending on WorldGuard.
	private WorldGuardPlugin getWorldGuard() {
	    Plugin wplugin = DeadBoltGuard.getInstance().getServer().getPluginManager().getPlugin("WorldGuard");
	 
	    if (wplugin == null || !(wplugin instanceof WorldGuardPlugin)) {
	        return null;
	    }
	 
	    return (WorldGuardPlugin) wplugin;
	}
	
	//Hook in LOW, hopefully we get this first.
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerInteractEvent(PlayerInteractEvent event){
		Player player = event.getPlayer();
		Block against = event.getClickedBlock();
		
		//Check to see if the player is holding a sign, this is still a valid event, and that the player
		//is NOT allowed to build in this area; if so, cancel the event and send the DENIED message.
		if (player.getItemInHand().getType().equals(Material.SIGN) && !event.isCancelled() && !getWorldGuard().canBuild(player, against)){
			event.setCancelled(true);
			plugin.config.sendMessage(player, ChatColor.RED, plugin.config.msg_deny_block_perm, against.getType().name());
		}
	}
	
}


import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import com.daemitus.deadbolt.Deadbolt;
import com.daemitus.deadbolt.Deadbolted;
import com.daemitus.deadbolt.listener.DeadboltListener;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;


public class WorldGuardListener extends DeadboltListener {
	
	private WorldGuardPlugin wgp;
	
	@Override
	public List<String> getDependencies() {
		return Arrays.asList("WorldGuard");
	}
	
	@Override
	public void load(final Deadbolt plugin) {
		try {
			wgp = ((WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard"));
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public boolean canSignChangeQuick(Deadbolted db, PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Block against = event.getClickedBlock();
		
		if (wgp.canBuild(player, against)) {
			return true;
		}
		
		return false;
	}

}

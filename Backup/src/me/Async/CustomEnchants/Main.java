package me.Async.CustomEnchants;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.FlameBlazer.CE2.cmd.EncCmd;
import me.FlameBlazer.CE2.cmd.EncInfoCmd;
import me.FlameBlazer.CE2.listeners.AnvilUpdateListener;
import me.FlameBlazer.CE2.listeners.BlockBreakListener;
import me.FlameBlazer.CE2.listeners.EffectUpdateListener;
import me.FlameBlazer.CE2.listeners.EnchantItemListener;
import me.FlameBlazer.CE2.listeners.EnchantTableUpdateListener;
import me.FlameBlazer.CE2.listeners.PlayerInteractListener;
import me.FlameBlazer.CE2.listeners.ProjectileHitListener;
import me.FlameBlazer.CE2.listeners.ProjectileLaunchListener;

public class Main extends JavaPlugin
{
	public static Plugin plugin;
	
	public void onEnable(){
		plugin = this;
		getServer().getPluginCommand("encinfo").setExecutor(new EncInfoCmd());
		getServer().getPluginCommand("enc").setExecutor(new EncCmd());
		getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
		getServer().getPluginManager().registerEvents(new ProjectileLaunchListener(), this);
		getServer().getPluginManager().registerEvents(new EnchantItemListener(), this);
		getServer().getPluginManager().registerEvents(new AnvilUpdateListener(), this);
		getServer().getPluginManager().registerEvents(new EnchantTableUpdateListener(), this);
		getServer().getPluginManager().registerEvents(new EffectUpdateListener(), this);
		getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
		getServer().getPluginManager().registerEvents(new ProjectileHitListener(), this);
	}
	
	public void onDisable(){
		
	}
	
	
	
}

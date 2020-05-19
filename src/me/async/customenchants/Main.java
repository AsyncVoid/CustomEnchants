package me.async.customenchants;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.async.customenchants.cmd.EncCmd;
import me.async.customenchants.cmd.EncInfoCmd;
import me.async.customenchants.listeners.AnvilUpdateListener;
import me.async.customenchants.listeners.BlockBreakListener;
import me.async.customenchants.listeners.EffectUpdateListener;
import me.async.customenchants.listeners.EnchantItemListener;
import me.async.customenchants.listeners.EnchantTableUpdateListener;
import me.async.customenchants.listeners.PlayerInteractListener;
import me.async.customenchants.listeners.ProjectileHitListener;
import me.async.customenchants.listeners.ProjectileLaunchListener;

public class Main extends JavaPlugin
{
	public static Plugin plugin;
	
	public void onEnable(){
		plugin = this;
		getServer().getPluginCommand("cencinfo").setExecutor(new EncInfoCmd());
		getServer().getPluginCommand("cenc").setExecutor(new EncCmd());
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

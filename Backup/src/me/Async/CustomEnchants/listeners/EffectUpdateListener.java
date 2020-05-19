package me.Async.CustomEnchants.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.CraftingInventory;

import me.FlameBlazer.CE2.playerstore.EffectStore;

public class EffectUpdateListener implements Listener
{
	@EventHandler
	public void onPlayerItemHeld(PlayerItemHeldEvent ev)
	{
		EffectStore.TOOL.updateEffects(ev.getPlayer(), ev.getPlayer().getInventory().getItem(ev.getNewSlot()));
	}
	
	@EventHandler
	public void onPlayerItemBreak(PlayerItemBreakEvent ev)
	{
		EffectStore.TOOL.updateEffects(ev.getPlayer(), null);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent ev)
	{
		EffectStore.ARMOUR.updateEffects((Player)ev.getPlayer(), null);
		EffectStore.TOOL.updateEffects((Player)ev.getPlayer(), ev.getPlayer().getItemInHand());
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent ev)
	{
		EffectStore.ARMOUR.updateEffects((Player)ev.getPlayer(), null);
		EffectStore.TOOL.updateEffects(ev.getPlayer(), ev.getPlayer().getItemInHand());
	}
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent ev)
	{
		EffectStore.TOOL.updateEffects(ev.getPlayer(), ev.getPlayer().getItemInHand());
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent ev)
	{
		if(ev.getPlayer() instanceof Player)
		{
			if(ev.getInventory() instanceof CraftingInventory)
				EffectStore.ARMOUR.updateEffects((Player)ev.getPlayer(), null);
			EffectStore.TOOL.updateEffects((Player)ev.getPlayer(), ev.getPlayer().getItemInHand());
		}
	}
}

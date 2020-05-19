package me.async.customenchants.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.async.customenchants.enchantment.LeveledEnchant;
import me.async.customenchants.playerstore.EffectStore;

public class BlockBreakListener implements Listener
{	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockBreak(BlockBreakEvent ev)
	{
		if(ev.isCancelled()) return;
		for(LeveledEnchant enc : EffectStore.TOOL.getEffects(ev.getPlayer()))
			enc.handleBreakEvent(ev);
	}
}

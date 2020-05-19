package me.async.customenchants.effect.tools;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.async.customenchants.effect.IEffect;

public class InquisitiveEffect extends IEffect
{
	@Override
	public void handleDamageEvent(EntityDamageByEntityEvent ev, int level)
	{
		if(ev.getDamager() instanceof Player)
			((Player)ev.getDamager()).giveExp(level);
	}
	
	@Override
	public void handleBreakEvent(BlockBreakEvent ev, int level)
	{
		if(ev.getExpToDrop() > 0)
			ev.setExpToDrop(ev.getExpToDrop() + level);
	}
}

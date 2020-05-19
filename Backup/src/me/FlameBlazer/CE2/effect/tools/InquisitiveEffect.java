package me.FlameBlazer.CE2.effect.tools;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.FlameBlazer.CE2.effect.IEffect;

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

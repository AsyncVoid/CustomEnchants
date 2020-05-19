package me.FlameBlazer.CE2.effect.armour;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.FlameBlazer.CE2.effect.IEffect;

public class SlipperyEffect extends IEffect
{
	@Override
	public void handleDamageEvent(EntityDamageByEntityEvent ev, int level)
	{
		if(getRandom().nextInt(40) < level)
			ev.setCancelled(true);
	}
}

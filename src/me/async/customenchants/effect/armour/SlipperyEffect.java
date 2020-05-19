package me.async.customenchants.effect.armour;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.async.customenchants.effect.IEffect;

public class SlipperyEffect extends IEffect
{
	@Override
	public void handleDamageEvent(EntityDamageByEntityEvent ev, int level)
	{
		if(getRandom().nextInt(40) < level)
			ev.setCancelled(true);
	}
}

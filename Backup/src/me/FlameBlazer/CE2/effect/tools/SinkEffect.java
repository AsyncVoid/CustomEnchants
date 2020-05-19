package me.FlameBlazer.CE2.effect.tools;

import org.bukkit.Location;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.FlameBlazer.CE2.effect.IEffect;

public class SinkEffect extends IEffect
{
	@Override
	public void handleDamageEvent(EntityDamageByEntityEvent ev, int level)
	{
		if(getRandom().nextInt(15) < level)
		{
			Location loc = ev.getEntity().getLocation();
			loc.setY(loc.getY() - 1);
			ev.getEntity().teleport(loc);
		}
	}
}

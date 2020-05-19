package me.FlameBlazer.CE2.effect.armour;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import me.FlameBlazer.CE2.effect.IEffect;

public class ReboundEffect extends IEffect
{
	@Override
	public void handleDamageEvent(EntityDamageByEntityEvent ev, int level)
	{
		if(getRandom().nextInt(40) < level)
		{
			Vector unitVector = ev.getEntity().getLocation().toVector().subtract(ev.getDamager().getLocation().toVector()).normalize().multiply(-1);
	        ev.getDamager().setVelocity(unitVector);
		}
	}
}

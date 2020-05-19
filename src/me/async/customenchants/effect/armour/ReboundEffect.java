package me.async.customenchants.effect.armour;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import me.async.customenchants.effect.IEffect;

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

package me.async.customenchants.effect.tools;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import me.async.customenchants.effect.IEffect;


public class HookedEffect extends IEffect
{
	@Override
	public void handleDamageEvent(EntityDamageByEntityEvent ev, int level)
	{
		Vector unitVector = ev.getDamager().getLocation().toVector().subtract(ev.getEntity().getLocation().toVector()).normalize().multiply(1f + level / 3f);
        ev.getEntity().setVelocity(unitVector);
	}
}

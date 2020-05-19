package me.async.customenchants.effect.tools;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.async.customenchants.effect.IEffect;

public class ChargedEffect extends IEffect
{	
	@Override
	public void handleDamageEvent(EntityDamageByEntityEvent ev, int level)
	{
		if(getRandom().nextInt(10) < level)
		{
			ev.getEntity().getWorld().strikeLightningEffect(ev.getEntity().getLocation());
			((LivingEntity) ev.getEntity()).damage(level);
		}
	}
}

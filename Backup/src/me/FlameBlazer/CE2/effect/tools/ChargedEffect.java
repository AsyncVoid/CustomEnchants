package me.FlameBlazer.CE2.effect.tools;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.FlameBlazer.CE2.effect.IEffect;

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

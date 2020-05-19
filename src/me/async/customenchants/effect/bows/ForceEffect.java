package me.async.customenchants.effect.bows;

import org.bukkit.event.entity.EntityShootBowEvent;

import me.async.customenchants.effect.IEffect;

public class ForceEffect extends IEffect
{
	@Override
	public void handleProjLaunchEvent(EntityShootBowEvent ev, int level)
	{
		ev.getProjectile().setVelocity(ev.getProjectile().getVelocity().multiply(1 + (level / 5d)));
	}
}

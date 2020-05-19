package me.FlameBlazer.CE2.effect.tools;

import org.bukkit.event.entity.EntityShootBowEvent;

import me.FlameBlazer.CE2.effect.IEffect;

public class ForceEffect extends IEffect
{
	@Override
	public void handleProjectileEvent(EntityShootBowEvent ev, int level)
	{
		ev.getProjectile().setVelocity(ev.getProjectile().getVelocity().multiply(1 + (level / 5d)));
	}
}

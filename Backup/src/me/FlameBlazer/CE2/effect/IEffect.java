package me.FlameBlazer.CE2.effect;

import java.util.Random;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;

public abstract class IEffect
{
	private static final Random random = new Random();
	
	protected static Random getRandom()
	{
		return random;
	}
	
	public IEffect()
	{
		
	}
	
	public void applyToLiving(LivingEntity en, int level)
	{
		
	}
	
	public void removeFromLiving(LivingEntity en, int level)
	{
		
	}
	
	public void handleDamageEvent(EntityDamageByEntityEvent ev, int level)
	{
		
	}
	
	public void handleProjectileEvent(EntityShootBowEvent ev, int level)
	{
		
	}
	
	public void handleBreakEvent(BlockBreakEvent ev, int level)
	{
		
	}
	
	@Override
	public boolean equals(Object o)
	{
		return this.getClass().isInstance(o);
	}
}

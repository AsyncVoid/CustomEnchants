package me.async.customenchants.effect;

import java.util.Random;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerFishEvent;

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
	
	public void handleProjLaunchEvent(EntityShootBowEvent ev, int level)
	{
		
	}
	
	public void handleFishEvent(PlayerFishEvent ev, int level)
	{
		
	}
	
	public void handleBreakEvent(BlockBreakEvent ev, int level)
	{
		
	}
	
	public void handleProjHitEvent(ProjectileHitEvent ev, int level)
	{
		
	}
	
	@Override
	public boolean equals(Object o)
	{
		return this.getClass().isInstance(o);
	}
}

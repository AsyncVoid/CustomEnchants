package me.FlameBlazer.CE2.effect.tools;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.FlameBlazer.CE2.effect.IEffect;

public class AgileEffect extends IEffect
{
	@Override
	public void handleDamageEvent(EntityDamageByEntityEvent ev, int level)
	{
		if(getRandom().nextInt(10) < level)
			((LivingEntity)ev.getDamager()).addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100, 0));
	}
	
	@Override
	public void handleBreakEvent(BlockBreakEvent ev, int level)
	{
		if(getRandom().nextInt(10) < level)
			ev.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100, 0));
	}
}

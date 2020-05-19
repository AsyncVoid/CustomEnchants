package me.async.customenchants.effect.armour;

import org.bukkit.entity.LivingEntity;

import me.async.customenchants.effect.IEffect;

public class PlethoricEffect extends IEffect
{	
	@Override
	public void applyToLiving(LivingEntity en, int level)
	{
		int newMax = 20 + level;
		en.setMaxHealth(newMax);
		//en.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, getLevel() - 1));
	}
	
	@Override
	public void removeFromLiving(LivingEntity en, int level)
	{
		en.setMaxHealth(20);
		//en.removePotionEffect(PotionEffectType.HEALTH_BOOST);
	}
}

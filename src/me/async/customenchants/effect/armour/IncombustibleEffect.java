package me.async.customenchants.effect.armour;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.async.customenchants.effect.IEffect;

public class IncombustibleEffect extends IEffect
{
	@Override
	public void applyToLiving(LivingEntity en, int level)
	{
		if(level > 3)
			en.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));
	}
	
	@Override
	public void removeFromLiving(LivingEntity en, int level)
	{
		en.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
	}
}

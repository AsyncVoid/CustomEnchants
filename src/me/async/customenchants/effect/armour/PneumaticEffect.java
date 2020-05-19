package me.async.customenchants.effect.armour;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.async.customenchants.effect.IEffect;

public class PneumaticEffect extends IEffect
{
	@Override
	public void applyToLiving(LivingEntity en, int level)
	{
		en.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, level - 1));
	}
	
	@Override
	public void removeFromLiving(LivingEntity en, int level)
	{
		en.removePotionEffect(PotionEffectType.JUMP);
	}
}

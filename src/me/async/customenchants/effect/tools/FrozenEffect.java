package me.async.customenchants.effect.tools;

import org.bukkit.potion.PotionEffectType;

import me.async.customenchants.effect.IPotionEffect;

public class FrozenEffect extends IPotionEffect
{
	public FrozenEffect() {
		super(PotionEffectType.SLOW);
	}
}
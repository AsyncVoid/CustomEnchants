package me.async.customenchants.effect;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class IPotionEffect extends IEffect
{
	private final PotionEffectType effect;
	
	public IPotionEffect(PotionEffectType effect)
	{
		this.effect = effect;
	}
	
	@Override
	public void handleDamageEvent(EntityDamageByEntityEvent ev, int level)
	{
		if(getRandom().nextInt(10) < level)
			applyToLiving((LivingEntity) ev.getEntity(), level);
	}
	
	@Override
	public void applyToLiving(LivingEntity en, int level)
	{
		en.addPotionEffect(new PotionEffect(effect, 100, level - 1));
	}
	
	@Override
	public void removeFromLiving(LivingEntity en, int level)
	{
		en.removePotionEffect(effect);
	}
}

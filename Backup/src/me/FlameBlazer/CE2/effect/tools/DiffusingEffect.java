package me.FlameBlazer.CE2.effect.tools;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.FlameBlazer.CE2.effect.IEffect;

public class DiffusingEffect extends IEffect
{
	@Override
	public void handleDamageEvent(EntityDamageByEntityEvent ev, int level)
	{
		if(getRandom().nextInt(10*2) < level)
		{
			for(Entity ent : ev.getEntity().getNearbyEntities(2d, 2d, 2d))
			{
				if(ent != ev.getDamager() && ent instanceof LivingEntity)
				{
					@SuppressWarnings("deprecation")
					EntityDamageEvent e = new EntityDamageByEntityEvent(ev.getDamager(), ent, DamageCause.ENTITY_ATTACK, ev.getDamage());
					//Bukkit.getPluginManager().callEvent(e);
					ent.setLastDamageCause(e);
					//((CraftEntity)ent).getHandle().damageEntity(DamageSource.GENERIC, (float)ev.getDamage());
					((LivingEntity)ent).damage(ev.getDamage());
					break;
				}
			}
		}
	}
}

package me.FlameBlazer.CE2.listeners;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

import me.FlameBlazer.CE2.enchantment.LeveledEnchant;
import me.FlameBlazer.CE2.playerstore.EffectStore;

public class ProjectileLaunchListener implements Listener
{	
	@EventHandler
	public void onEntityShootBow(EntityShootBowEvent ev)
	{
		if(ev.getEntity() instanceof Player)
		{
			List<LeveledEnchant> enchants =  EffectStore.TOOL.getEffects((Player) ev.getEntity());
			
			for(LeveledEnchant enc : enchants)
				enc.handleProjectileEvent(ev);
			
			EffectStore.ARROW.setEffects(ev.getProjectile().getEntityId(), enchants);
		}
	}
}
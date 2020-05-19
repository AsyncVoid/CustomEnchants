package me.async.customenchants.listeners;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerFishEvent;

import me.async.customenchants.enchantment.LeveledEnchant;
import me.async.customenchants.playerstore.EffectStore;

public class ProjectileLaunchListener implements Listener
{	
	@EventHandler
	public void onEntityShootBow(EntityShootBowEvent ev)
	{
		if(ev.getEntity() instanceof Player)
		{
			List<LeveledEnchant> enchants =  EffectStore.TOOL.getEffects((Player) ev.getEntity());
			
			for(LeveledEnchant enc : enchants)
				enc.handleProjLaunchEvent(ev);
			
			EffectStore.ARROW.setEffects(ev.getProjectile().getEntityId(), enchants);
		}
	}
	
	@EventHandler
	public void onPlayerFish(PlayerFishEvent ev)
	{
		List<LeveledEnchant> enchants =  EffectStore.TOOL.getEffects(ev.getPlayer());
		for(LeveledEnchant enc : enchants)
			enc.handleFishEvent(ev);
	}
}
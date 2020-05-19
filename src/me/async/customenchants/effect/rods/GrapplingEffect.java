package me.async.customenchants.effect.rods;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

import me.async.customenchants.effect.IEffect;

public class GrapplingEffect extends IEffect
{
	//TODO
	/*
	    if(ev.getEntity() instanceof FishHook)
		{
			FishHook fh = (FishHook)ev.getEntity();
			Player shooter = (Player)fh.getShooter();
			Location loc = fh.getLocation();
			Vector vector = loc.toVector().subtract(shooter.getLocation().toVector()).normalize();
			shooter.setVelocity(vector);
			fh.remove();
		}
	 */
	@Override
	public void handleFishEvent(PlayerFishEvent ev, int level)
	{
		Bukkit.broadcastMessage(ev.getState().toString());
	}
	
	@Override
	public void handleProjHitEvent(ProjectileHitEvent ev, int level)
	{
		if(ev.getEntity() instanceof FishHook)
		{
			FishHook fh = (FishHook)ev.getEntity();
			Player player = (Player) fh.getShooter();
			Location loc = fh.getLocation();
			Vector vector = loc.toVector().subtract(player.getLocation().toVector()).normalize();
			player.setVelocity(vector);
			fh.remove();
		}
	}
}

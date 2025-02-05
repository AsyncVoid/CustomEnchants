package me.async.customenchants.effect.bows;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.async.customenchants.Main;
import me.async.customenchants.effect.IEffect;

public class SplinterEffect extends IEffect
{
	@Override
	public void handleProjLaunchEvent(final EntityShootBowEvent ev, int level)
	{
		for(int i = 0; i < level; i++) {
			new BukkitRunnable()
			{
				@Override
				public void run() {
					Arrow a = (Arrow) ev.getProjectile().getWorld().spawnEntity(ev.getProjectile().getLocation(), EntityType.ARROW);
					a.setVelocity(ev.getProjectile().getVelocity());
					a.setShooter(((Arrow)ev.getProjectile()).getShooter());
					a.setBounce(false);
				}
			}.runTaskLater(Main.plugin, 2*i);
		}
	}
}

package me.FlameBlazer.CE2.effect.tools;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.Async.CustomEnchants.Main;
import me.FlameBlazer.CE2.effect.IEffect;
import me.FlameBlazer.CE2.utils.ArrowUtil;

public class HomingEffect extends IEffect
{
	@Override
	public void handleProjectileEvent(final EntityShootBowEvent ev, int level)
	{
		if(getRandom().nextBoolean())
		new BukkitRunnable()
		{
			int i = 0;
			Arrow a = (Arrow) ev.getProjectile();
			@Override
			public void run() {
				
				int[] pos = ArrowUtil.getPos((CraftArrow) a);
				if(i>=8 || pos[1] != -1)
				{
					this.cancel();
				}
				else if(pos[1] == -1)
				{
					targetNearest(a);
				}
				i++;
			}
		}.runTaskTimerAsynchronously(Main.plugin, 1, 2);
	}
	
	private static void targetNearest(Arrow a)
	{
		LivingEntity close = null;
        double distanceSq = 100;
        for (Entity e1 : a.getNearbyEntities(5, 5, 5)) {
        	double d = e1.getLocation().distanceSquared(a.getLocation());
            if (d < distanceSq 
            		&& e1 instanceof LivingEntity 
            		&& !e1.equals(a.getShooter())
            		&& a.getLocation().distanceSquared(((Entity) a.getShooter()).getLocation()) > 100) {
            	distanceSq = d;
                close = (LivingEntity) e1;
            }
        }
        if (close != null) {
            Location location = close.getEyeLocation();
            Location pos = a.getLocation();
            double its = location.distance(pos);
            if (its == 0) its = 1;
            Vector v = new Vector();
            v.setX((location.getX() - pos.getX()) / its);
            v.setY((location.getY() - pos.getY()) / its);
            v.setZ((location.getZ() - pos.getZ()) / its);
            v.add(a.getLocation().getDirection().multiply(0.1));
            a.setVelocity(v.multiply(2));
        }
	}
}

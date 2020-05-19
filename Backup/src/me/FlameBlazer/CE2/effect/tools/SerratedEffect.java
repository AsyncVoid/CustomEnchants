package me.FlameBlazer.CE2.effect.tools;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.Async.CustomEnchants.Main;
import me.FlameBlazer.CE2.effect.IEffect;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class SerratedEffect extends IEffect
{	
	@Override
	public void handleDamageEvent(final EntityDamageByEntityEvent ev, final int level)
	{
		if(getRandom().nextInt(10) < level)
			new BukkitRunnable()
			{
				int i = 0;
				@Override
				public void run() {
					if(i == 5 || ev.getEntity().isDead())
					{
						this.cancel();
						return;
					}
					sendPackets((LivingEntity) ev.getEntity());
					i++;
				}
			}.runTaskTimerAsynchronously(Main.plugin, 0, 20);
	}
	
	private static final void sendPackets(LivingEntity en)
	{
		Location loc = en.getLocation();
		loc.setY(loc.getY() + 1);
		for(Entity entity : en.getNearbyEntities(10d, 10d, 10d))
		{
			if(entity instanceof Player)
			{
				PacketPlayOutWorldParticles particle = new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, (float)loc.getX(),  (float)loc.getY(),  (float)loc.getZ(), 0.2f, 0.2f, 0.2f, 0f, 10);
				((CraftPlayer) entity).getHandle().playerConnection.sendPacket(particle);
			}
		}
		en.damage(2);
	}
}

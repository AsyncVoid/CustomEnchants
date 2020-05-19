package me.FlameBlazer.CE2.effect.tools;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.FlameBlazer.CE2.effect.IEffect;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class DireEffect extends IEffect
{	
	@Override
	public void handleDamageEvent(EntityDamageByEntityEvent ev, int level)
	{
		if(getRandom().nextInt(10) < level)
		{
			ev.setDamage(ev.getDamage() * 1.5);
			Entity en = ev.getEntity();
			Location loc = en.getLocation();
			loc.setY(loc.getY() + 1);
			for(Entity entity : en.getNearbyEntities(10d, 10d, 10d))
			{
				if(entity instanceof Player)
				{
					PacketPlayOutWorldParticles particle = new PacketPlayOutWorldParticles(EnumParticle.CRIT, true, (float)loc.getX(),  (float)loc.getY(),  (float)loc.getZ(), 0.2f, 0.2f, 0.2f, 0f, 10);
					((CraftPlayer) entity).getHandle().playerConnection.sendPacket(particle);
				}
			}
		}
	}
}

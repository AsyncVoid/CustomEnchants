package me.async.customenchants.effect.armour;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.async.customenchants.Main;
import me.async.customenchants.effect.IEffect;
import me.async.customenchants.utils.CloneUtil;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;

public class HazyEffect extends IEffect
{
	@Override
	public void handleDamageEvent(final EntityDamageByEntityEvent ev, int level)
	{
		if(ev.getDamager() instanceof Player && ev.getEntity() instanceof Player)
		{
			if(getRandom().nextInt(10) < level)
			{
				final int id = CloneUtil.CloneHuman((Player)ev.getEntity(), (Player)ev.getDamager());
				new BukkitRunnable()
				{
					@Override
					public void run() {
						PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(id);
						((CraftPlayer) ev.getDamager()).getHandle().playerConnection.sendPacket(destroy);
					}
				}.runTaskLaterAsynchronously(Main.plugin, 60);
			}
		}
	}
}

package me.FlameBlazer.CE2.effect.tools;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.FlameBlazer.CE2.effect.IEffect;

public class CorrodingEffect extends IEffect
{
	@Override
	public void handleDamageEvent(EntityDamageByEntityEvent ev, int level)
	{
		if(getRandom().nextInt(2) < 1)
		{
			if(ev.getEntity() instanceof Player)
			{
				Player p = (Player)ev.getEntity();
				ItemStack[] armour = p.getInventory().getArmorContents();
				for(int i = 0; i < armour.length; i++)
				{
					if(armour[i] != null)
					{
						armour[i].setDurability((short) (armour[i].getDurability() + level));
					}
				}
			}
		}
	}
}

package me.FlameBlazer.CE2.effect.tools;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.FlameBlazer.CE2.effect.IEffect;

public class UnstableEffect extends IEffect
{
	@Override
	public void handleDamageEvent(EntityDamageByEntityEvent ev, int level)
	{
		if(getRandom().nextInt(10) < level)
		{
			ev.getEntity().getWorld().createExplosion(((LivingEntity)ev.getEntity()).getEyeLocation(), 0f, false);
			((LivingEntity) ev.getEntity()).damage(level);
		}
	}
	
	@Override
	public void handleBreakEvent(BlockBreakEvent ev, int level)
	{
		if(ev.getBlock().getType() == Material.STONE)
		{
			if(getRandom().nextInt(10) < level)
			{
				for(int i = 0; i < level; i++)
				{
					Block b = ev.getBlock().getRelative(BlockFace.values()[getRandom().nextInt(5)]);
					if(b.getType() == ev.getBlock().getType())
					{
						b.breakNaturally();
						ItemStack is = ev.getPlayer().getItemInHand();
						is.setDurability((short) (is.getDurability() + 1));
					}
				}
				ev.getPlayer().getWorld().createExplosion(ev.getBlock().getLocation(), 0, false);
			}
		}
	}
}

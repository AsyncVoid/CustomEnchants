package me.async.customenchants.effect.tools;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import me.async.customenchants.effect.IEffect;

public class SmeltingEffect extends IEffect
{	
	@Override
	public void handleBreakEvent(BlockBreakEvent ev, int level)
	{
		Material m = ev.getBlock().getType();
		if(m == Material.IRON_ORE || m == Material.GOLD_ORE)
		{
			ev.getBlock().setType(Material.AIR);
			ItemStack is = new ItemStack(m == Material.IRON_ORE ? Material.IRON_INGOT : Material.GOLD_INGOT);
			ev.getBlock().getWorld().dropItemNaturally(ev.getBlock().getLocation().add(0.5, 0.5, 0.5), is);
		}
	}
}

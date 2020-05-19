package me.FlameBlazer.CE2.playerstore;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.FlameBlazer.CE2.enchantment.Enchantment;
import me.FlameBlazer.CE2.enchantment.LeveledEnchant;

public class ToolEffectStore extends EffectStore<Player>
{	
	@Override
	public void updateEffects(Player player, ItemStack tool)
	{
		List<LeveledEnchant> encs = this.getEffects(player);
		
		for(LeveledEnchant enc : encs)
			enc.removeFromLiving(player);
		
		if (tool == null 
				|| tool.getType() == Material.BOOK 
				|| tool.getType() == Material.ENCHANTED_BOOK
				|| tool.getMaxStackSize() > 1) 
			return;
		
		encs.clear();
		
		for(LeveledEnchant enc : Enchantment.getEnchants(tool))
			encs.add(enc);
		this.setEffects(player, encs);
	}
}

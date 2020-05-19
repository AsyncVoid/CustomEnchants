package me.Async.CustomEnchants.playerstore;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.FlameBlazer.CE2.enchantment.Enchantment;
import me.FlameBlazer.CE2.enchantment.LeveledEnchant;

public class ArmourEffectStore extends EffectStore<Player>
{
	@Override
	public void updateEffects(Player player, ItemStack is) {
		List<LeveledEnchant> enchants = this.getEffects(player);
		
		double health = player.getHealth();
		
		for(LeveledEnchant enc : enchants)
			enc.removeFromLiving(player);
		
		enchants = getArmourEffects(player);
		
		for(LeveledEnchant enc : enchants)
			enc.applyToLiving(player);
		
		this.setEffects(player, enchants);
		
		if(player.getHealth() > 1)
			if(health > player.getMaxHealth())
				player.setHealth(player.getMaxHealth());
			else
				player.setHealth(health);
	}
	
	private static List<LeveledEnchant> getArmourEffects(Player player)
	{
		List<LeveledEnchant> encs = new ArrayList<LeveledEnchant>();
		for(int i = 36; i < 40; i++)
		{
			ItemStack is = player.getInventory().getItem(i);
			if (is != null)
			{
				for(LeveledEnchant enc1 : Enchantment.getEnchants(is))
				{
					boolean contains = false;
					for(LeveledEnchant enc2 : encs) //Check if already exists 
					{
						if(enc1.getEnchantment() == enc2.getEnchantment())
						{
							enc2.setLevel(enc1.getLevel() + enc2.getLevel()); //Add levels together
							contains = true;
							break;
						}
					}
					if(!contains)
						encs.add(enc1);
				}
			}
		}
		return encs;
	}
}

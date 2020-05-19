package me.Async.CustomEnchants.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.FlameBlazer.CE2.enchantment.Enchantment;
import me.FlameBlazer.CE2.enchantment.LeveledEnchant;

public class EncInfoCmd implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0 instanceof Player)
		{
			Player p = (Player)arg0;
			if(p.getItemInHand() != null)
			{
				if(p.getItemInHand().getItemMeta().hasLore())
				{
					for(String lore : p.getItemInHand().getItemMeta().getLore())
					{
						//CustomEnchant enc = EncManager.getFromLore(lore);
						//p.sendMessage(enc.getName() + " lvl. " + enc.getLevel());
						LeveledEnchant enc = Enchantment.getFromLore(lore);
						p.sendMessage(enc.getEnchantment().getName() + " lvl. " + enc.getLevel());
					}
				}
			}
		}
		return false;
	}

}

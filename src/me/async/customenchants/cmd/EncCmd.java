package me.async.customenchants.cmd;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;


public class EncCmd implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(args.length == 2)
			{
				try{
					int level = Integer.parseInt(args[1]);
					String enchant = args[0];
					if(enchant.equalsIgnoreCase("sharpness")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.DAMAGE_ALL, level);
					}
					else if(enchant.equalsIgnoreCase("fireaspect")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.FIRE_ASPECT, level);
					}
					else if(enchant.equalsIgnoreCase("baneofarthropods")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, level);
					}
					else if(enchant.equalsIgnoreCase("smite")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, level);
					}
					else if(enchant.equalsIgnoreCase("knockback")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.KNOCKBACK, level);
					}
					else if(enchant.equalsIgnoreCase("efficiency")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.DIG_SPEED, level);
					}
					else if(enchant.equalsIgnoreCase("digspeed")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.DIG_SPEED, level);
					}
					else if(enchant.equalsIgnoreCase("durability")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.DURABILITY, level);
					}
					else if(enchant.equalsIgnoreCase("fortune")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, level);
					}
					else if(enchant.equalsIgnoreCase("looting")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, level);
					}
					else if(enchant.equalsIgnoreCase("protection")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
					}
					else if(enchant.equalsIgnoreCase("protexplosion")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, level);
					}
					else if(enchant.equalsIgnoreCase("featherfalling")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_FALL, level);
					}
					else if(enchant.equalsIgnoreCase("protfire")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, level);
					}
					else if(enchant.equalsIgnoreCase("protprojectile")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, level);
					}
					else if(enchant.equalsIgnoreCase("silktouch")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.SILK_TOUCH, level);
					}
					else if(enchant.equalsIgnoreCase("waterworker")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.WATER_WORKER, level);
					}
					else if(enchant.equalsIgnoreCase("arrowfire")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.ARROW_FIRE, level);
					}
					else if(enchant.equalsIgnoreCase("arrowinfinite")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.ARROW_INFINITE, level);
					}
					else if(enchant.equalsIgnoreCase("arrowknockback")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, level);
					}
					else if(enchant.equalsIgnoreCase("aquaaffinity")){
						p.getItemInHand().addUnsafeEnchantment(Enchantment.OXYGEN, level);
					}
					else
					{
						me.async.customenchants.enchantment.Enchantment enc = me.async.customenchants.enchantment.Enchantment.getFromName(enchant);
						if(enc != null)
						{
							enc.applyToItem(p.getItemInHand(), level);
							if(p.getItemInHand().getType() == Material.BOOK)
								p.getItemInHand().setType(Material.ENCHANTED_BOOK);
						}
						/*Class<? extends CustomEnchant> clazz = EncManager.getTypeFromName(enchant);
						if(clazz != null)
						{
							Constructor<? extends CustomEnchant> cons = clazz.getConstructor(int.class);
							cons.newInstance(level).applyToItem(p.getItemInHand());
							if(p.getItemInHand().getType() == Material.BOOK)
								p.getItemInHand().setType(Material.ENCHANTED_BOOK);
							
							return false;
						}
						*/else{
							p.sendMessage("§e"+args[0]+" is not a valid enchantment.");
							p.sendMessage("§eDo \"/enc list\" for a list of enchantments.");
							return false;
						}
					}
					p.sendMessage("§eThe item in your hand has been enchanted!");
				}catch(Exception ex){
					p.sendMessage("§e"+args[1]+" is not a number...");
					ex.printStackTrace();
				}
				
			}
			else if(args.length == 1)
			{
				if(args[0].equalsIgnoreCase("list")){
					p.sendMessage("§eHere is the list of enchantments:"
							+ " AquaAffinity, ArrowFire, ArrowInfinite, ArrowKnockback, BaneOfArthropods,"
							+ " DigSpeed, Durability, Efficiency, FireAspect, Fortune, FeatherFalling,"
							+ " KnockBack, Looting, Protection, ProtExplosion, ProtFire, ProtProjectile,"
							+ " SilkTouch, Smite, WaterWorker.");
				}
			    else{
				    p.sendMessage("§eUse it like this: /enc <enchantment> <level>");
				    p.sendMessage("§eOr do \"/enc list\" for a list of enchantments.");
			    }
			}
			else
			{
				p.sendMessage("§eUse it like this: /enc <enchantment> <level>");
				p.sendMessage("§eOr do \"/enc list\" for a list of enchantments.");
			}
		}
		return false;
	}
}

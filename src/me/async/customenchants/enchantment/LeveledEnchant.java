package me.async.customenchants.enchantment;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class LeveledEnchant
{ 
	private final Enchantment enc; 
	private int lvl; 
	
	public LeveledEnchant(Enchantment enc, int lvl) {
		this.enc = enc;
		this.lvl = lvl;
	}
	
	public Enchantment getEnchantment()
	{
		return this.enc;
	}
	
	public int getLevel()
	{
		return this.lvl;
	}
	
	public void setLevel(int level)
	{
		this.lvl = level;
	}
		
	public void applyToItem(ItemStack is)
	{
		this.enc.applyToItem(is, this.lvl);
	}
	
	public String getLore()
	{
		return this.enc.getLore(this.lvl);
	}
	
	public void applyToLiving(LivingEntity en)
	{
		this.enc.getEffect().applyToLiving(en, this.lvl);
	}
	
	public void removeFromLiving(LivingEntity en)
	{
		this.enc.getEffect().removeFromLiving(en, this.lvl);
	}
	
	public void handleDamageEvent(EntityDamageByEntityEvent ev)
	{
		this.enc.getEffect().handleDamageEvent(ev, this.lvl);
	}
	
	public void handleProjLaunchEvent(EntityShootBowEvent ev)
	{
		this.enc.getEffect().handleProjLaunchEvent(ev, this.lvl);
	}
	
	public void handleBreakEvent(BlockBreakEvent ev)
	{
		this.enc.getEffect().handleBreakEvent(ev, this.lvl);
	}
	
	public void handleFishEvent(PlayerFishEvent ev)
	{
		this.enc.getEffect().handleFishEvent(ev, this.lvl);
	}
	
	public void handleProjHitEvent(ProjectileHitEvent ev)
	{
		this.enc.getEffect().handleProjHitEvent(ev, this.lvl);
	}
} 
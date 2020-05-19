package me.async.customenchants.playerstore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import me.async.customenchants.enchantment.LeveledEnchant;

public abstract class EffectStore<T>
{
	public static final ArmourEffectStore ARMOUR = new ArmourEffectStore();
	public static final ToolEffectStore TOOL = new ToolEffectStore();
	public static final ArrowEffectStore ARROW = new ArrowEffectStore();
	
	private final HashMap<T, List<LeveledEnchant>> effectMap = new HashMap<T, List<LeveledEnchant>>();
	
	public final void setEffects(T key, List<LeveledEnchant> effects)
	{
		effectMap.put(key, effects);
	}
	
	public final void clearEffects(T key)
	{
		effectMap.remove(key);
	}
	
	public final void addEffects(T key, LeveledEnchant effect)
	{
		if(effectMap.containsKey(key))
		{
			effectMap.get(key).add(effect);
		}
		else
		{
			ArrayList<LeveledEnchant> array = new ArrayList<LeveledEnchant>();
			array.add(effect);
			effectMap.put(key, array);
		}
	}
	
	public final List<LeveledEnchant> getEffects(T key)
	{
		return effectMap.containsKey(key) ? effectMap.get(key) : new ArrayList<LeveledEnchant>();
	}
	
	public abstract void updateEffects(T key, ItemStack is);
}

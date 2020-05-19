package me.Async.CustomEnchants.enchantment;

import java.util.HashSet;

import org.bukkit.Material;

public enum EquiptmentType
{
	BOOTS(Material.DIAMOND_BOOTS, Material.GOLD_BOOTS, Material.IRON_BOOTS, Material.CHAINMAIL_BOOTS, Material.LEATHER_BOOTS),
	LEGGINGS(Material.DIAMOND_LEGGINGS, Material.GOLD_LEGGINGS, Material.IRON_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.LEATHER_LEGGINGS),
	CHESTPLATES(Material.DIAMOND_CHESTPLATE, Material.GOLD_CHESTPLATE, Material.IRON_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.LEATHER_CHESTPLATE),
	HELMETS(Material.DIAMOND_HELMET, Material.GOLD_HELMET, Material.IRON_HELMET, Material.CHAINMAIL_HELMET, Material.LEATHER_HELMET),
	ARMOUR(BOOTS, LEGGINGS, CHESTPLATES, HELMETS),
	
	SWORDS(Material.DIAMOND_SWORD, Material.GOLD_SWORD, Material.IRON_SWORD, Material.STONE_SWORD, Material.WOOD_SWORD),
	SPADES(Material.DIAMOND_SPADE, Material.GOLD_SPADE, Material.IRON_SPADE, Material.STONE_SPADE, Material.WOOD_SPADE),
	HOES(Material.DIAMOND_HOE, Material.GOLD_HOE, Material.IRON_HOE, Material.STONE_HOE, Material.WOOD_HOE),
	AXES(Material.DIAMOND_AXE, Material.GOLD_AXE, Material.IRON_AXE, Material.STONE_AXE, Material.WOOD_AXE),
	PICKAXES(Material.DIAMOND_PICKAXE, Material.GOLD_PICKAXE, Material.IRON_PICKAXE, Material.STONE_PICKAXE, Material.WOOD_PICKAXE),
	TOOLS(SWORDS, SPADES, HOES, AXES, PICKAXES),
	
	BOWS(Material.BOW),
	
	ALL(BOWS, TOOLS, ARMOUR);
	
	private final HashSet<Material> materials = new HashSet<Material>();
	
	
	private EquiptmentType(Material... mat)
	{
		for(Material mat1 : mat)
			materials.add(mat1);
	}
	
	private EquiptmentType(EquiptmentType... mat)
	{
		for(EquiptmentType eq : mat)
			materials.addAll(eq.materials);
	}
	
	public HashSet<Material> getMaterials()
	{
		return this.materials;
	}
	
	public boolean contains(Material mat)
	{
		return this.materials.contains(mat);
	}
}

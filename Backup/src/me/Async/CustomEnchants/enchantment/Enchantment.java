package me.Async.CustomEnchants.enchantment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.FlameBlazer.CE2.effect.IEffect;
import me.FlameBlazer.CE2.effect.all.NullEffect;
import me.FlameBlazer.CE2.effect.armour.GlowingEffect;
import me.FlameBlazer.CE2.effect.armour.HazyEffect;
import me.FlameBlazer.CE2.effect.armour.IncombustibleEffect;
import me.FlameBlazer.CE2.effect.armour.NourishingEffect;
import me.FlameBlazer.CE2.effect.armour.PlethoricEffect;
import me.FlameBlazer.CE2.effect.armour.PneumaticEffect;
import me.FlameBlazer.CE2.effect.armour.ReboundEffect;
import me.FlameBlazer.CE2.effect.armour.SlipperyEffect;
import me.FlameBlazer.CE2.effect.armour.SwiftEffect;
import me.FlameBlazer.CE2.effect.tools.AgileEffect;
import me.FlameBlazer.CE2.effect.tools.BlindingEffect;
import me.FlameBlazer.CE2.effect.tools.ChargedEffect;
import me.FlameBlazer.CE2.effect.tools.CorrodingEffect;
import me.FlameBlazer.CE2.effect.tools.CripplingEffect;
import me.FlameBlazer.CE2.effect.tools.DiffusingEffect;
import me.FlameBlazer.CE2.effect.tools.DireEffect;
import me.FlameBlazer.CE2.effect.tools.ForceEffect;
import me.FlameBlazer.CE2.effect.tools.FrozenEffect;
import me.FlameBlazer.CE2.effect.tools.HomingEffect;
import me.FlameBlazer.CE2.effect.tools.HookedEffect;
import me.FlameBlazer.CE2.effect.tools.InquisitiveEffect;
import me.FlameBlazer.CE2.effect.tools.SerratedEffect;
import me.FlameBlazer.CE2.effect.tools.SickeningEffect;
import me.FlameBlazer.CE2.effect.tools.SinkEffect;
import me.FlameBlazer.CE2.effect.tools.SmeltingEffect;
import me.FlameBlazer.CE2.effect.tools.SplinterEffect;
import me.FlameBlazer.CE2.effect.tools.TenaciousEffect;
import me.FlameBlazer.CE2.effect.tools.ToxicEffect;
import me.FlameBlazer.CE2.effect.tools.UnstableEffect;
import me.FlameBlazer.CE2.effect.tools.WitheringEffect;

public enum Enchantment
{
	SHATTERPROOF("Shatterproof", 1, EquiptmentType.ALL, EnchantRarity.COMMON, new NullEffect()),
	SOULBOUND("Soulbound", 1, EquiptmentType.ALL, EnchantRarity.COMMON, new NullEffect()),
	
	FROZEN     ("Frozen",      5, EquiptmentType.TOOLS, EnchantRarity.COMMON, new FrozenEffect()),
	TOXIC      ("Toxic",       5, EquiptmentType.TOOLS, EnchantRarity.COMMON, new ToxicEffect()),
	CRIPPLING  ("Crippling",   5, EquiptmentType.TOOLS, EnchantRarity.COMMON, new CripplingEffect()),
	BLINDING   ("Blinding",    5, EquiptmentType.TOOLS, EnchantRarity.COMMON, new BlindingEffect()),
	CHARGED    ("Charged",     5, EquiptmentType.TOOLS, EnchantRarity.COMMON, new ChargedEffect()),
	UNSTABLE   ("Unstable",    5, EquiptmentType.TOOLS, EnchantRarity.RARE, new UnstableEffect()),
	HOOKED     ("Hooked",      3, EquiptmentType.TOOLS, EnchantRarity.COMMON, new HookedEffect()),
	INQUISITIVE("Inquisitive", 5, EquiptmentType.TOOLS, EnchantRarity.COMMON, new InquisitiveEffect()),
	WITHERING  ("Withering",   5, EquiptmentType.TOOLS, EnchantRarity.COMMON, new WitheringEffect()),
	SICKENING  ("Sickening",   5, EquiptmentType.TOOLS, EnchantRarity.COMMON, new SickeningEffect()),
	AGILE      ("Agile",       3, EquiptmentType.TOOLS, EnchantRarity.RARE, new AgileEffect()),
	TENACIOUS  ("Tenacious",   3, EquiptmentType.TOOLS, EnchantRarity.RARE, new TenaciousEffect()),
	DIRE       ("Dire",        5, EquiptmentType.TOOLS, EnchantRarity.COMMON, new DireEffect()),
	CORRODING  ("Corroding",   5, EquiptmentType.TOOLS, EnchantRarity.COMMON, new CorrodingEffect()),
	DIFFUSING  ("Diffusing",   5, EquiptmentType.TOOLS, EnchantRarity.COMMON, new DiffusingEffect()),
	
	SINK       ("Sink",        5, EquiptmentType.AXES, EnchantRarity.VERY_RARE, new SinkEffect()),
	HOMING     ("Homing",      1, EquiptmentType.BOWS, EnchantRarity.RARE, new HomingEffect()),
	FORCE      ("Force",       5, EquiptmentType.BOWS, EnchantRarity.COMMON, new ForceEffect()),
	SPLINTER   ("Splinter",    4, EquiptmentType.BOWS, EnchantRarity.COMMON, new SplinterEffect()),
	SERRATED   ("Serrated",    5, EquiptmentType.SWORDS, EnchantRarity.COMMON, new SerratedEffect()),
	SMELTING   ("Smelting",    1, EquiptmentType.PICKAXES, EnchantRarity.RARE, new SmeltingEffect()),
	
	REBOUND    ("Rebound",     5, EquiptmentType.ARMOUR, EnchantRarity.COMMON, new ReboundEffect()),
	PLETHORIC  ("Plethoric",   5, EquiptmentType.ARMOUR, EnchantRarity.COMMON, new PlethoricEffect()),
    INCOMBUSTIBLE("Incombustible",1,EquiptmentType.ARMOUR, EnchantRarity.COMMON, new IncombustibleEffect()),
	SLIPPERY   ("Slippery",    5, EquiptmentType.ARMOUR, EnchantRarity.COMMON, new SlipperyEffect()),
	HAZY       ("Hazy",        1, EquiptmentType.ARMOUR, EnchantRarity.COMMON, new HazyEffect()),
	
	NOURISHING ("Nourishing",  1, EquiptmentType.HELMETS, EnchantRarity.VERY_RARE, new NourishingEffect()),
	GLOWING    ("Glowing",     1, EquiptmentType.HELMETS, EnchantRarity.COMMON, new GlowingEffect()),
	PNEUMATIC  ("Pneumatic",   5, EquiptmentType.LEGGINGS, EnchantRarity.UNUSUAL, new PneumaticEffect()),
	SWIFT      ("Swift",       5, EquiptmentType.BOOTS, EnchantRarity.UNUSUAL, new SwiftEffect())
	;
	
	
	private final IEffect effect;
	private final String name;
	private final int maxLevel;
	private final EquiptmentType type;
	private final int weight;
	
	private Enchantment(String name, int maxLevel, EquiptmentType type, EnchantRarity rarity, IEffect effect)
	{
		this.effect = effect;
		this.name = name;
		this.maxLevel = maxLevel;
		this.type = type;
		this.weight = rarity.getWeight();
	}
	
	public IEffect getEffect()
	{
		return this.effect;
	}
	/*public IEffect getEffect(int level)
	{
		try {
			Class<? extends IEffect> clazz = this.effect;
			Constructor<? extends IEffect> cons = clazz.getConstructor(int.class);
			return cons.newInstance(level);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}*/
	
	public String getName()
	{
		return this.name;
	}
	
	public int getMaxLevel()
	{
		return maxLevel;
	}
	
	public String getLore(int level)
	{
		return "§r§7" + name + " " + getNumFromLevel(level);
	}
	
	public EquiptmentType getEquiptmentType()
	{
		return this.type;
	}
	
	public int getRandomLevel()
	{
		return (int) Math.floor(Math.pow(new Random().nextDouble(), 2) * getMaxLevel()) + 1;
	}
	
	public int getRandomLevel(int level)
	{
		return (int) Math.round(Math.pow(new Random().nextDouble(), 30/level) * getMaxLevel());
	}
	
	public int getWeight()
	{
		return this.weight;
	}
	
	public void applyToItem(ItemStack is, int level)
	{
		ItemMeta im = is.getItemMeta();
		List<String> lore = new ArrayList<String>();
		if(im.hasLore())
			lore = im.getLore();
		
		for(int i = 0; i < lore.size(); i++)
		{
			String enc = lore.get(i);
			if(this == getFromLore(enc).getEnchantment())
			{
				//System.out.println(this.getClass().getName() + enc);
				lore.remove(i);
				i--;
			}
		}
		if(level > 0)
			lore.add(getLore(level));
		im.setLore(lore);
		is.setItemMeta(im);
	}
	
	public boolean canEnchant(Material mat)
	{
		return this.type == EquiptmentType.ALL 
				|| mat == Material.ENCHANTED_BOOK 
				|| mat == Material.BOOK
				|| this.getEquiptmentType().contains(mat);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	private static final HashMap<String, Enchantment> enchantMap = new HashMap<String, Enchantment>();
	
	static
	{
		for(Enchantment enc : Enchantment.values())
			enchantMap.put(enc.getName(), enc);
	}
	
	public static LeveledEnchant getRandomEnchant(Material mat)
	{
		Random rand = new Random();
		List<Enchantment> encs = new ArrayList<Enchantment>();
		for(Enchantment enc : Enchantment.values())
		{
			if(enc.canEnchant(mat))
				encs.add(enc);
		}
		int i = rand.nextInt(encs.size());
		Enchantment enc = encs.get(i);
		return new LeveledEnchant(enc, enc.getRandomLevel());
	}
	
	public static LeveledEnchant getRandomEnchant(List<Enchantment> encs)
	{
		Random rand = new Random();
		Enchantment enc = encs.get(rand.nextInt(encs.size()));
		return new LeveledEnchant(enc, enc.getRandomLevel());
	}
	
	public static LeveledEnchant getRandomEnchant(List<Enchantment> encs, int level)
	{
		Random rand = new Random();
		int weight = 0;
		for(Enchantment enc : encs)
			weight += enc.getWeight();
		int chosen = rand.nextInt(weight);
		weight = 0;
		Enchantment result = encs.get(0);
		for(Enchantment enc : encs)
		{
			weight += enc.getWeight();
			if(chosen < weight)
			{
				result = enc;
				break;
			}
		}
		
		return new LeveledEnchant(result, result.getRandomLevel(level));
	}
	
	public static List<Enchantment> getApplicableEnchants(Material mat)
	{
		List<Enchantment> encs = new ArrayList<Enchantment>();
		for(Enchantment enc : Enchantment.values())
		{
			if(enc.canEnchant(mat))
				encs.add(enc);
		}
		return encs;
	}
	
	public static List<LeveledEnchant> getEnchants(ItemStack is)
	{
		List<LeveledEnchant> effects = new ArrayList<LeveledEnchant>();
		if(is.hasItemMeta())
		{
			ItemMeta im = is.getItemMeta();
			if(im.hasLore())
			{
				for(String lore : im.getLore())
				{
					LeveledEnchant enc = getFromLore(lore);
					if(enc == null) continue;
					effects.add(enc);
				}
			}
		}
		return effects;
	}
	
	public static Enchantment getFromName(String s)
	{
		return enchantMap.get(s);
	}
	
	public static LeveledEnchant getFromLore(String s)
	{
		try {
			s = s.substring(4);
			String[] split = s.split(" ");
			int level = getLevelFromNum(split[1]);
			Enchantment enc = getFromName(split[0]);
			return enc == null ? null : new LeveledEnchant(enc, level);
		} catch (Exception ex)
		{
			//ex.printStackTrace();
			return null;
		}
	}
	
	private static int getLevelFromNum(String s)
	{
		if(s.equalsIgnoreCase("I"))
			return 1;
		else if(s.equalsIgnoreCase("II"))
			return 2;
		else if(s.equalsIgnoreCase("III"))
			return 3;
		else if(s.equalsIgnoreCase("IV"))
			return 4;
		else if(s.equalsIgnoreCase("V"))
			return 5;
		else if(s.equalsIgnoreCase("VI"))
			return 6;
		else if(s.equalsIgnoreCase("VII"))
			return 7;
		else if(s.equalsIgnoreCase("VIII"))
			return 8;
		else if(s.equalsIgnoreCase("IX"))
			return 9;
		else if(s.equalsIgnoreCase("X"))
			return 10;
		return 0;
	}
	
	private static String getNumFromLevel(int level)
	{
		switch(level)
		{
		case 1:
			return "I";
		case 2:
			return "II";
		case 3:
			return "III";
		case 4:
			return "IV";
		case 5:
			return "V";
		case 6:
			return "VI";
		case 7:
			return "VII";
		case 8:
			return "VIII";
		case 9:
			return "IX";
		case 10:
			return "X";
		default:
			return "";
		}
	}
}

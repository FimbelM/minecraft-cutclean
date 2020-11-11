package fr.martinfimbel.minecraft_cutclean.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import fr.pederobien.minecraftgameplateform.impl.element.EventListener;

public class EntityDeathEventListener extends EventListener {

	private List<EntityType> entities;
	private Random random = new Random();

	public EntityDeathEventListener() {
		entities = Arrays.asList(EntityType.ZOMBIE, EntityType.HORSE, EntityType.CREEPER, EntityType.CHICKEN, EntityType.COW, EntityType.COD, EntityType.PIG,
				EntityType.SHEEP, EntityType.RABBIT, EntityType.SALMON, EntityType.HUSK, EntityType.ZOMBIE_VILLAGER);
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onEntityDeath(EntityDeathEvent deathEvent) {
		if (!isActivated() || !entities.contains(deathEvent.getEntityType()))
			return;

		deathEvent.getDrops().clear();
		deathEvent.setDroppedExp(0);
		Location itemSpawn = deathEvent.getEntity().getLocation();

		switch (deathEvent.getEntityType()) {
		case CHICKEN:
			onChickenDeath(itemSpawn, randomGeneratorForTwoParameters(25, 75));
			break;
		case COD:
			onCodDeath(itemSpawn);
			break;
		case COW:
			onCowDeath(itemSpawn, randomGeneratorForThreeParameters(30, 75, 90));
			break;
		case CREEPER:
			onCreeperDeath(itemSpawn, randomGeneratorForOneParameters(20));
			break;
		case HORSE:
			onHorseDeath(itemSpawn, randomGeneratorForOneParameters(75));
			break;
		case PIG:
			onPigDeath(itemSpawn);
			break;
		case RABBIT:
			onRabbitDeath(itemSpawn, randomGeneratorForOneParameters(90));
			break;
		case SALMON:
			onSalmonDeath(itemSpawn);
			break;
		case SHEEP:
			onSheepDeath(itemSpawn, randomGeneratorForThreeParameters(0, 50, 75));
			break;
		case HUSK:
			onZombieDeath(itemSpawn);
			break;
		case ZOMBIE_VILLAGER:
			onZombieDeath(itemSpawn);
			break;
		default: // ZOMBIE
			onZombieDeath(itemSpawn);
			break;
		}

	}

	private void onZombieDeath(Location itemSpawn) {
		itemSpawn.getWorld().dropItemNaturally(itemSpawn, new ItemStack(Material.COOKED_BEEF));
		ExperienceOrb orb = (ExperienceOrb) itemSpawn.getWorld().spawnEntity(itemSpawn, EntityType.EXPERIENCE_ORB);
		orb.setExperience(5);
	}

	private void onSheepDeath(Location itemSpawn, int woolNumber) {
		itemSpawn.getWorld().dropItemNaturally(itemSpawn, new ItemStack(Material.COOKED_MUTTON));
		for (int i = 0; i < woolNumber; i++) {
			itemSpawn.getWorld().dropItemNaturally(itemSpawn, new ItemStack(Material.WHITE_WOOL));
		}
		ExperienceOrb orb = (ExperienceOrb) itemSpawn.getWorld().spawnEntity(itemSpawn, EntityType.EXPERIENCE_ORB);
		orb.setExperience(random.nextInt(2) + 1);

	}

	private void onSalmonDeath(Location itemSpawn) {
		itemSpawn.getWorld().dropItemNaturally(itemSpawn, new ItemStack(Material.COOKED_SALMON));
		ExperienceOrb orb = (ExperienceOrb) itemSpawn.getWorld().spawnEntity(itemSpawn, EntityType.EXPERIENCE_ORB);
		orb.setExperience(1);
	}

	private void onRabbitDeath(Location itemSpawn, boolean rabbitFoot) {
		itemSpawn.getWorld().dropItemNaturally(itemSpawn, new ItemStack(Material.COOKED_RABBIT));
		if (rabbitFoot)
			itemSpawn.getWorld().dropItemNaturally(itemSpawn, new ItemStack(Material.RABBIT_FOOT));
		else
			return;
		ExperienceOrb orb = (ExperienceOrb) itemSpawn.getWorld().spawnEntity(itemSpawn, EntityType.EXPERIENCE_ORB);
		orb.setExperience(random.nextInt(4));
	}

	private void onPigDeath(Location itemSpawn) {
		itemSpawn.getWorld().dropItemNaturally(itemSpawn, new ItemStack(Material.COOKED_PORKCHOP));
		ExperienceOrb orb = (ExperienceOrb) itemSpawn.getWorld().spawnEntity(itemSpawn, EntityType.EXPERIENCE_ORB);
		orb.setExperience(random.nextInt(2) + 1);
	}

	private void onHorseDeath(Location itemSpawn, boolean leather) {
		itemSpawn.getWorld().dropItemNaturally(itemSpawn, new ItemStack(Material.COOKED_BEEF));
		if (leather)
			itemSpawn.getWorld().dropItemNaturally(itemSpawn, new ItemStack(Material.LEATHER));
		else
			return;
		ExperienceOrb orb = (ExperienceOrb) itemSpawn.getWorld().spawnEntity(itemSpawn, EntityType.EXPERIENCE_ORB);
		orb.setExperience(random.nextInt(2) + 1);
	}

	private void onCreeperDeath(Location itemSpawn, boolean gunPowderTNT) {
		if (gunPowderTNT)
			itemSpawn.getWorld().dropItemNaturally(itemSpawn, new ItemStack(Material.GUNPOWDER));
		else
			itemSpawn.getWorld().dropItemNaturally(itemSpawn, new ItemStack(Material.TNT));
		ExperienceOrb orb = (ExperienceOrb) itemSpawn.getWorld().spawnEntity(itemSpawn, EntityType.EXPERIENCE_ORB);
		orb.setExperience(5);
	}

	private void onCowDeath(Location itemSpawn, int leatherNumber) {
		itemSpawn.getWorld().dropItemNaturally(itemSpawn, new ItemStack(Material.COOKED_BEEF));
		for (int i = 0; i < leatherNumber; i++) {
			itemSpawn.getWorld().dropItemNaturally(itemSpawn, new ItemStack(Material.LEATHER));
		}
		ExperienceOrb orb = (ExperienceOrb) itemSpawn.getWorld().spawnEntity(itemSpawn, EntityType.EXPERIENCE_ORB);
		orb.setExperience(random.nextInt(2) + 1);
	}

	private void onCodDeath(Location itemSpawn) {
		itemSpawn.getWorld().dropItemNaturally(itemSpawn, new ItemStack(Material.COOKED_COD));
		ExperienceOrb orb = (ExperienceOrb) itemSpawn.getWorld().spawnEntity(itemSpawn, EntityType.EXPERIENCE_ORB);
		orb.setExperience(1);
	}

	private void onChickenDeath(Location itemSpawn, int featherNumber) {
		itemSpawn.getWorld().dropItemNaturally(itemSpawn, new ItemStack(Material.COOKED_CHICKEN));
		for (int i = 0; i < featherNumber; i++) {
			itemSpawn.getWorld().dropItemNaturally(itemSpawn, new ItemStack(Material.FEATHER));
		}
		ExperienceOrb orb = (ExperienceOrb) itemSpawn.getWorld().spawnEntity(itemSpawn, EntityType.EXPERIENCE_ORB);
		orb.setExperience(random.nextInt(2) + 1);
	}

	private boolean randomGeneratorForOneParameters(int middleBound) {
		return random.nextInt(100) > middleBound;
	}

	private int randomGeneratorForTwoParameters(int lowerBound, int upperBound) {
		int generatedNumber = random.nextInt(100);
		if (generatedNumber > lowerBound && generatedNumber < upperBound)
			return 1;
		else if (generatedNumber > upperBound)
			return 2;
		else
			return 0;
	}

	private int randomGeneratorForThreeParameters(int lowerBound, int middleBound, int upperBound) {
		int generatedNumber = random.nextInt(100);
		if (generatedNumber > lowerBound && generatedNumber < middleBound)
			return 1;
		else if (generatedNumber > middleBound && generatedNumber < upperBound)
			return 2;
		else if (generatedNumber > upperBound)
			return 3;
		else
			return 0;
	}

}

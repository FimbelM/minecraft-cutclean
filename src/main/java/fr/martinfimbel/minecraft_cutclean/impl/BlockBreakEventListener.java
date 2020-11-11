package fr.martinfimbel.minecraft_cutclean.impl;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import fr.pederobien.minecraftgameplateform.impl.element.EventListener;

public class BlockBreakEventListener extends EventListener {
	private List<Material> materials;

	public BlockBreakEventListener() {
		materials = Arrays.asList(Material.GOLD_ORE, Material.IRON_ORE, Material.GRAVEL);
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onBlockBreak(BlockBreakEvent event) {
		if (!isActivated() || !materials.contains(event.getBlock().getType()))
			return;

		switch (event.getBlock().getType()) {
		case GOLD_ORE:
			onGoldOreBroken(event.getBlock().getLocation());
			break;
		case IRON_ORE:
			onIronOreBroken(event.getBlock().getLocation());
			break;
		default: // GRAVEL case
			onGravelBroken(event.getBlock().getLocation());
			break;
		}

		event.setCancelled(true);
		event.getBlock().setType(Material.AIR);

	}

	private void onGoldOreBroken(Location location) {
		location.getWorld().dropItemNaturally(location, new ItemStack(Material.GOLD_INGOT));
		ExperienceOrb orb = (ExperienceOrb) location.getWorld().spawnEntity(location, EntityType.EXPERIENCE_ORB);
		orb.setExperience(4);
	}

	private void onIronOreBroken(Location location) {
		location.getWorld().dropItemNaturally(location, new ItemStack(Material.IRON_INGOT));
		ExperienceOrb orb = (ExperienceOrb) location.getWorld().spawnEntity(location, EntityType.EXPERIENCE_ORB);
		orb.setExperience(3);
	}

	private void onGravelBroken(Location location) {
		location.getWorld().dropItemNaturally(location, new ItemStack(Material.FLINT));
	}

}

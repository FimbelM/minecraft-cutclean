package fr.martinfimbel.minecraft_cutclean.impl;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.ItemStack;

import fr.pederobien.minecraftgameplateform.impl.element.EventListener;

public class AppleDropRateListener extends EventListener {
	private Random random;

	public AppleDropRateListener() {
		random = new Random();
	}

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onLeavesDecay(LeavesDecayEvent event) {
		tryDropApple(event);
	}

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onLeavesDecay(BlockBreakEvent event) {
		tryDropApple(event);
	}

	private void tryDropApple(BlockEvent event) {
		if (!isActivated() || !event.getBlock().getType().equals(Material.OAK_LEAVES))
			return;

		double probability = random.nextDouble();
		if (probability <= CutcleanConfiguration.getInstance().getAppleDropRate()) {
			event.getBlock().setType(Material.AIR);
			event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.APPLE));
		}
	}
}

package fr.martinfimbel.minecraft_cutclean.commands;

import org.bukkit.plugin.java.JavaPlugin;

import fr.pederobien.minecraftgameplateform.commands.AbstractSimpleCommand;

public class CutcleanCommand extends AbstractSimpleCommand {

	public CutcleanCommand(JavaPlugin plugin) {
		super(plugin, new CutcleanParent());
	}

}

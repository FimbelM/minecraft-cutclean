package fr.martinfimbel.minecraft_cutclean.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.martinfimbel.minecraft_cutclean.ECutcleanMessageCode;
import fr.martinfimbel.minecraft_cutclean.impl.CutcleanConfiguration;
import fr.pederobien.minecraftgameplateform.dictionary.ECommonMessageCode;
import fr.pederobien.minecraftgameplateform.impl.editions.AbstractSimpleMapEdition;

public class EnableCutcleanCommand extends AbstractSimpleMapEdition {

	public EnableCutcleanCommand() {
		super("isEnabled", ECutcleanMessageCode.CUTCLEAN_ISENABLED_EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {
			String value = args[0];
			if (value.equals("true"))
				CutcleanConfiguration.getInstance().setIsActivated(true);
			else if (value.equals("false"))
				CutcleanConfiguration.getInstance().setIsActivated(false);
			else {
				sendSynchro(sender, ECommonMessageCode.COMMON_BAD_BOOLEAN_FORMAT);
				return false;
			}
			sendSynchro(sender, ECutcleanMessageCode.CUTCLEAN_ISENABLED_VALUE_DEFINED, value);
		} catch (IndexOutOfBoundsException e) {
			sendSynchro(sender, ECutcleanMessageCode.CUTCLEAN_VALUE_IS_MISSING);
			return false;
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		switch (args.length) {
		case 1:
			return filter(asList("true", "false").stream(), args);
		default:
			return emptyList();
		}

	}
}

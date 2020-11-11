package fr.martinfimbel.minecraft_cutclean.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.martinfimbel.minecraft_cutclean.ECutcleanMessageCode;
import fr.martinfimbel.minecraft_cutclean.impl.CutcleanConfiguration;
import fr.pederobien.minecraftgameplateform.dictionary.ECommonMessageCode;
import fr.pederobien.minecraftgameplateform.impl.editions.AbstractSimpleMapEdition;

public class AppleDropRateCommand extends AbstractSimpleMapEdition {

	protected AppleDropRateCommand() {
		super("appleDropRate", ECutcleanMessageCode.APPLE_DROP_RATE__EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {
			CutcleanConfiguration.getInstance().setAppleDropRate(Double.parseDouble(args[0]));
			sendSynchro(sender, ECutcleanMessageCode.APPLEDROPRATE_VALUE_DEFINED_IN_GAME, CutcleanConfiguration.getInstance().getAppleDropRate());
		} catch (IndexOutOfBoundsException e) {
			sendNotSynchro(sender, ECutcleanMessageCode.CUTCLEAN_VALUE_IS_MISSING);
			return false;
		} catch (NumberFormatException e) {
			sendNotSynchro(sender, ECommonMessageCode.COMMON_BAD_DOUBLE_FORMAT);
			return false;
		} catch (UnsupportedOperationException e) {
			sendNotSynchro(sender, ECutcleanMessageCode.APPLE_DROP_RATE__VALUE_OUT_OF_BOUND);
			return false;
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		return asList(getMessage(sender, ECutcleanMessageCode.APPLE_DROP_RATE__DROP_RATE_ON_TAB_COMPLETE));
	}
}

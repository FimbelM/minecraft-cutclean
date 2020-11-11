package fr.martinfimbel.minecraft_cutclean.commands;

import fr.martinfimbel.minecraft_cutclean.ECutcleanMessageCode;
import fr.pederobien.minecraftgameplateform.impl.editions.AbstractSimpleMapEdition;

public class CutcleanParent extends AbstractSimpleMapEdition {

	public CutcleanParent() {
		super("cutclean", ECutcleanMessageCode.CUTCLEAN_EXPLANATION);
		addEdition(new AppleDropRateCommand());
		addEdition(new EnableCutcleanCommand());
	}

}

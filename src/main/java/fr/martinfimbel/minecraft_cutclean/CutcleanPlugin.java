package fr.martinfimbel.minecraft_cutclean;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import org.bukkit.plugin.java.JavaPlugin;

import fr.martinfimbel.minecraft_cutclean.commands.CutcleanCommand;
import fr.martinfimbel.minecraft_cutclean.impl.CutcleanConfiguration;
import fr.pederobien.dictionary.interfaces.IDictionaryParser;
import fr.pederobien.minecraftgameplateform.utils.Plateform;

public class CutcleanPlugin extends JavaPlugin {
	public static final String NAME = "minecraft-cutclean";

	@Override
	public void onEnable() {
		Plateform.getPluginHelper().register(this);
		new CutcleanCommand(this);
		CutcleanConfiguration.getInstance().getAppleDropRateEventListener().register(this);
		CutcleanConfiguration.getInstance().getBlockBreackEventListener().register(this);
		CutcleanConfiguration.getInstance().getEntityDeathEventListener().register(this);
		registerDictionaries();
	}

	private void registerDictionaries() {
		// Registering French dictionaries
		registerDictionary("French", "Cutclean.xml");

		// Registering English dictionaries
		registerDictionary("English", "Cutclean.xml");
	}

	private void registerDictionary(String parent, String... dictionaryNames) {
		Path jarPath = Plateform.ROOT.getParent().resolve(NAME.concat(".jar"));
		String dictionariesFolder = "resources/dictionaries/".concat(parent).concat("/");
		for (String name : dictionaryNames)
			registerDictionary(Plateform.getDefaultDictionaryParser(dictionariesFolder.concat(name)), jarPath);
	}

	private void registerDictionary(IDictionaryParser parser, Path jarPath) {
		try {
			Plateform.getNotificationCenter().getDictionaryContext().register(parser, jarPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

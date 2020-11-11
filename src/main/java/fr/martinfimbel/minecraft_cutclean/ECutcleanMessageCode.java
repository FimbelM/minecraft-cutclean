package fr.martinfimbel.minecraft_cutclean;

import fr.pederobien.minecraftdictionary.impl.Permission;
import fr.pederobien.minecraftdictionary.interfaces.IMinecraftMessageCode;

public enum ECutcleanMessageCode implements IMinecraftMessageCode {

	// General cutclean message codes
	CUTCLEAN_EXPLANATION, CUTCLEAN_VALUE_IS_MISSING,

	// Apple Drop Rate messages codes
	APPLEDROPRATE_VALUE_DEFINED_IN_GAME, APPLE_DROP_RATE__EXPLANATION, APPLE_DROP_RATE__VALUE_OUT_OF_BOUND, APPLE_DROP_RATE__DROP_RATE_ON_TAB_COMPLETE,

	// Cutclean enabling command messages codes
	CUTCLEAN_ISENABLED_EXPLANATION, CUTCLEAN_ISENABLED_VALUE_DEFINED;

	private Permission permission;

	private ECutcleanMessageCode() {
		this(Permission.OPERATORS);
	}

	private ECutcleanMessageCode(Permission permission) {
		this.permission = permission;
	}

	@Override
	public String value() {
		return toString();
	}

	@Override
	public Permission getPermission() {
		return permission;
	}

	@Override
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
}

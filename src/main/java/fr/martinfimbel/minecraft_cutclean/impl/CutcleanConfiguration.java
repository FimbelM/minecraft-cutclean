package fr.martinfimbel.minecraft_cutclean.impl;

import fr.pederobien.minecraftgameplateform.interfaces.element.IEventListener;

public class CutcleanConfiguration {
	private double appleDropRate;
	private boolean isActivated;
	private IEventListener blockBreackEventListener, entityDeathEventListener, appleDropRateEventListener;

	private CutcleanConfiguration() {
		appleDropRate = 0.075;
		appleDropRateEventListener = new AppleDropRateListener();
		blockBreackEventListener = new BlockBreakEventListener();
		entityDeathEventListener = new EntityDeathEventListener();
		setIsActivated(true);
	}

	public static CutcleanConfiguration getInstance() {
		return SingletonHolder.PERSISTENCE;
	}

	private static class SingletonHolder {
		public static final CutcleanConfiguration PERSISTENCE = new CutcleanConfiguration();
	}

	/**
	 * Getter for apple drop rate
	 * 
	 * @return double : apple drop rate
	 */
	public double getAppleDropRate() {
		return appleDropRate;
	}

	/**
	 * Setter for apple drop rate
	 * 
	 * @param appleDropRate
	 */
	public void setAppleDropRate(double appleDropRate) {
		this.appleDropRate = appleDropRate;
	}

	/**
	 * Getter for the activation of cutclean
	 * 
	 * @return boolean
	 */
	public boolean isActivated() {
		return isActivated;
	}

	/**
	 * Setter for the activation of cutclean
	 * 
	 * @param isActivated
	 */
	public void setIsActivated(boolean isActivated) {
		this.isActivated = isActivated;
		appleDropRateEventListener.setActivated(isActivated);
		blockBreackEventListener.setActivated(isActivated);
		entityDeathEventListener.setActivated(isActivated);
	}

	/**
	 * Getter for blockBreakEvent
	 * 
	 * @return
	 */
	public IEventListener getBlockBreackEventListener() {
		return blockBreackEventListener;
	}

	/**
	 * Getter for entityDeathEvent
	 * 
	 * @return
	 */
	public IEventListener getEntityDeathEventListener() {
		return entityDeathEventListener;
	}

	public IEventListener getAppleDropRateEventListener() {
		return appleDropRateEventListener;
	}

}

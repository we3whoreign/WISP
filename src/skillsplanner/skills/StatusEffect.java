package skillsplanner.skills;

/**
 * Rules that all status effects need
 */
public abstract class StatusEffect {
	protected double duration;
	protected double percentChance;
	protected int level;
	protected int damage;
	protected String damageType;

	/**
	 * Pass in the damage type the status effect does.
	 */
	public StatusEffect(String damagetype) {
		this.damageType = damagetype;
	}
}

package com.tickcounter;

import com.tickcounter.config.TickShape;
import net.runelite.client.config.*;


import java.awt.*;

@ConfigGroup(TickCounterConfig.GROUP_NAME)
public interface TickCounterConfig extends Config
{
	String GROUP_NAME = "tick-settings";
	String GENERAL_SETTINGS = "General-settings";
	String ALTERNATIVE_SETTINGS = "Alternative-settings";

	@ConfigSection(
			name = "General",
			description = "General settings",
			position = 0
	)
	String generalSettings = GENERAL_SETTINGS;


	@ConfigItem(
			position = 0,
			keyName = "number-of-ticks",
			name = "Number of ticks",
			description = "Number of ticks",
			section = generalSettings
	)
	@Range(min = 2, max = 20)
	default int getNumberOfTicks() {
		return 2;
	}


	@ConfigItem(
			position = 1,
			keyName = "current-tick-color",
			name = "Current tick color",
			description = "",
			section = generalSettings
	)
	@Alpha
	default Color currentTickColor() {
		return new Color(255, 255, 255, 255);
	}

	@ConfigItem(
			position = 2,
			keyName = "tick-color",
			name = "Tick color",
			description = "",
			section = generalSettings
	)
	@Alpha
	default Color tickColor() {
		return new Color(61, 61, 61, 255);
	}


	@ConfigItem(
			keyName = "tick-shape",
			name = "Shape of the tick",
			description = "",
			position =3,
			section = generalSettings
	)
	default TickShape tickShape()
	{
		return TickShape.SQUARE;
	}


	@ConfigItem(
			position = 4,
			keyName = "width-of-tick",
			name = "Width",
			description = "Width of the tick",
			section = generalSettings
	)
	@Range(min = 2, max = 50)
	default int getTickWidth() {
		return 10;
	}


	@ConfigItem(
			position = 5,
			keyName = "height-of-tick",
			name = "Height",
			description = "Height of the tick",
			section = generalSettings
	)
	@Range(min = 2, max = 50)
	default int getTickHeight() {
		return 10;
	}

	@ConfigItem(
			position = 6,
			keyName = "arc-of-tick",
			name = "Arc",
			description = "Arc (if using Rounded Square)",
			section = generalSettings
	)
	@Range(min = 2, max = 50)
	default int getTickArc() {
		return 5;
	}


	@ConfigSection(
			name = "Alternative",
			description = "Alternative settings",
			position = 1
	)
	String alternativeSettings = ALTERNATIVE_SETTINGS;

	@ConfigItem(
			position = 1,
			keyName = "start-tick",
			name = "Offset",
			description = "Which tick to register the xp-drop",
			section = alternativeSettings
	)
	@Range(min = 0, max = 20)
	default int startTick() {
		return 0;
	}



	@ConfigItem(
			position = 0,
			keyName = "reset-counter",
			name = "Reset counter on xp drop (HP)",
			description = "",
			section = alternativeSettings
	)
	default boolean resetCounter() {
		return false;
	}

	@ConfigItem(
			position = 2,
			keyName = "help-color",
			name = "Prayer colors",
			description = "Add color to ticks for when to turn on/ off offensive prayers." +
					"Only works if you have (reset counter) ON and position will be based on (Tick to register)",
			section = alternativeSettings
	)
	default boolean helpColors() {
		return false;
	}

	@ConfigItem(
			position = 3,
			keyName = "current-prayer-on",
			name = "Current prayer-on",
			description = "",
			section = alternativeSettings
	)
	@Alpha
	default Color currentPrayerOnColor() {
		return Color.GREEN;
	}

	@ConfigItem(
			position = 4,
			keyName = "prayer-on-color",
			name = "Prayer-on",
			description = "",
			section = alternativeSettings
	)
	@Alpha
	default Color prayerOnColor() {
		return Color.decode("#0D430D");
	}

	@ConfigItem(
			position = 5,
			keyName = "current-prayer-off",
			name = "Current prayer-off",
			description = "",
			section = alternativeSettings
	)
	@Alpha
	default Color currentPrayerOffColor() {
		return Color.RED;
	}

	@ConfigItem(
			position = 6,
			keyName = "prayer-off",
			name = "Prayer-off",
			description = "",
			section = alternativeSettings
	)
	@Alpha
	default Color prayerOffColor() {
		return Color.decode("#541212");
	}
}

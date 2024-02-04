package com.tickcounter;

import com.tickcounter.config.TickShape;
import net.runelite.client.config.*;


import java.awt.*;

@ConfigGroup(CustomTicksConfig.GROUP_NAME)
public interface CustomTicksConfig extends Config
{
	String GROUP_NAME = "tick-settings";
	String GENERAL_SETTINGS = "General-settings";
	String ALTERNATIVE_SETTINGS = "Alternative-settings";


	// General settings
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
			description = "Number of ticks. (Ticks based of the weapon if you're using [Prayer flick settings])",
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
	default Color getCurrentTickColor() {
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
	default Color getTickColor() {
		return new Color(61, 61, 61, 255);
	}


	@ConfigItem(
			keyName = "tick-shape",
			name = "Shape of the tick",
			description = "",
			position =3,
			section = generalSettings
	)
	default TickShape getTickShape()
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

	@ConfigItem(
			position = 7,
			keyName = "padding-between-ticks",
			name = "Padding",
			description = "Padding between ticks",
			section = generalSettings
	)
	@Range(min = 2, max = 50)
	default int getPadding() {
		return 2;
	}

	// Alternative settings
	@ConfigSection(
			name = "Prayer flick",
			description = "Alternative settings",
			position = 1
	)
	String alternativeSettings = ALTERNATIVE_SETTINGS;

	@ConfigItem(
			position = 0,
			keyName = "prayer-flicker",
			name = "Count after xp drop (HP)",
			description = "Add color to ticks for when to turn on/off offensive prayers. " +
					"Counter will start based on xp drops (Hitpoints)",
			section = alternativeSettings
	)
	default boolean isPrayerFlickOn() {
		return false;
	}

	@ConfigItem(
			position = 1,
			keyName = "offset",
			name = "Offset",
			description = "Offset the tick which register the xp-drop",
			section = alternativeSettings
	)
	@Range(min = 0, max = 20)
	default int getOffset() {
		return 0;
	}

	@ConfigItem(
			position = 2,
			keyName = "current-prayer-on",
			name = "Current prayer-on",
			description = "Color when the current tick",
			section = alternativeSettings
	)
	@Alpha
	default Color currentPrayerOnColor() {
		return Color.GREEN;
	}

	@ConfigItem(
			position = 3,
			keyName = "prayer-on-color",
			name = "Prayer-on",
			description = "Color when not the current tick",
			section = alternativeSettings
	)
	@Alpha
	default Color prayerOnColor() {
		return Color.decode("#0D430D");
	}

	@ConfigItem(
			position = 4,
			keyName = "current-prayer-off",
			name = "Current prayer-off",
			description = "Color when the current tick",
			section = alternativeSettings
	)
	@Alpha
	default Color currentPrayerOffColor() {
		return Color.RED;
	}

	@ConfigItem(
			position = 5,
			keyName = "prayer-off",
			name = "Prayer-off",
			description = "Color when not the current tick",
			section = alternativeSettings
	)
	@Alpha
	default Color prayerOffColor() {
		return Color.decode("#541212");
	}
}

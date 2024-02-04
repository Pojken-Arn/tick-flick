package com.tickcounter;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Custom Ticks"
)
public class CustomTicksPlugin extends Plugin
{
	@Inject
	private CustomTicksConfig config;


	@Inject
	private OverlayManager overlayManager;

	@Inject
	private CustomTicksOverlay overlay;


	private int tick = 0;

	public int getTick() {
		return tick;
	}

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
	}

	@Subscribe
	public void onStatChanged(StatChanged statChanged)
	{
		if (statChanged.getSkill() == Skill.HITPOINTS)
		{
			if (config.isPrayerFlickOn()) {
				tick = config.getOffset();
			}
		}
	}

	@Subscribe
	private void onGameTick(GameTick gameTick) {
		tick++;
		if (tick > config.getNumberOfTicks() - 1) {
			tick = 0;
		}
	}

	@Provides
	CustomTicksConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(CustomTicksConfig.class);
	}
}

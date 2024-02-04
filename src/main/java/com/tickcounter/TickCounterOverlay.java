package com.tickcounter;

import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;

public class TickCounterOverlay extends Overlay {

    @Inject
    public TickCounterOverlay()
    {
        setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
    }

    @Inject
    TickCounterConfig config;

    @Inject
    TickCounterPlugin plugin;


    @Override
    public Dimension render(Graphics2D graphics)
    {

        for (int tick = 0; tick < config.getNumberOfTicks(); tick++)
        {
            int x = tick * config.getTickWidth() + tick * 2;


            setTickColor(graphics, tick);
            setTickShape(graphics, x);
        }

        int height = config.getTickHeight();
        int width = (config.getNumberOfTicks() - 1) * 2 + config.getNumberOfTicks() * config.getTickWidth();

        return new Dimension(width, height);
    }

    private void setTickColor(Graphics2D graphics, int tick) {
        if (plugin.getTick() == tick) {
            graphics.setColor(setCurrentColor(tick));
        } else if (config.helpColors() && isPrayerOnTick(tick)) {
            graphics.setColor(config.prayerOnColor());
        } else if (config.helpColors() && isPrayerOffTick(tick)) {
            graphics.setColor(config.prayerOffColor());
        }
        else {
            graphics.setColor(config.tickColor());
        }
    }

    private Color setCurrentColor(int tick) {
        if (!config.helpColors()) {
            return config.currentTickColor();
        }

        if (isPrayerOnTick(tick)) {
            return config.currentPrayerOnColor();
        } else if (isPrayerOffTick(tick)) {
            return config.currentPrayerOffColor();
        } else {
            return config.currentTickColor();
        }
    }


    private boolean isPrayerOnTick(int tick) {
        return (tick == config.startTick());
    }

    private boolean isPrayerOffTick(int tick) {

        return (config.getNumberOfTicks() == config.startTick() + 1 && tick == 0)
                || config.getNumberOfTicks() != config.startTick() + 1 && tick == config.startTick() +1;
    }




    private void setTickShape(Graphics2D graphics, int xPosition) {
        switch (config.tickShape()) {
            case CIRCLE:
                graphics.fillOval(xPosition, 0, config.getTickWidth(), config.getTickHeight());
                break;
            case ROUNDED_SQUARE:
                graphics.fillRoundRect(xPosition, 0 ,config.getTickWidth() , config.getTickHeight(), config.getTickArc(), config.getTickArc());
                break;
            default:
                graphics.fillRect(xPosition, 0, config.getTickWidth(), config.getTickHeight());
                break;
        }
    }



}


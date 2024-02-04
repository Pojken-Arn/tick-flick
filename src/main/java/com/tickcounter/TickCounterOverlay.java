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
    public Dimension render(Graphics2D graphics) {
        for (int tick = 0; tick < config.getNumberOfTicks(); tick++) {
            int x = tick * config.getTickWidth() + tick * config.getPadding();
            setTickColor(graphics, tick);
            setTickShape(graphics, x);
        }

        int width = (config.getNumberOfTicks() - 1) * config.getPadding() + config.getNumberOfTicks() * config.getTickWidth();

        return new Dimension(width, config.getTickHeight());
    }

    private void setTickColor(Graphics2D graphics, int tick) {
        if (plugin.getTick() == tick) {
            graphics.setColor(setCurrentColor(tick));
        } else if (config.isPrayerFlickOn() && isPrayerOnTick(tick)) {
            graphics.setColor(config.prayerOnColor());
        } else if (config.isPrayerFlickOn() && isPrayerOffTick(tick)) {
            graphics.setColor(config.prayerOffColor());
        }
        else {
            graphics.setColor(config.getTickColor());
        }
    }

    private Color setCurrentColor(int tick) {
        if (!config.isPrayerFlickOn()) {
            return config.getCurrentTickColor();
        }

        if (isPrayerOnTick(tick)) {
            return config.currentPrayerOnColor();
        } else if (isPrayerOffTick(tick)) {
            return config.currentPrayerOffColor();
        } else {
            return config.getCurrentTickColor();
        }
    }

    private boolean isPrayerOnTick(int tick) {
        return (tick == calculateOffset());
    }

    private boolean isPrayerOffTick(int tick) {

        return (config.getNumberOfTicks() == calculateOffset() + 1 && tick == 0)
                || config.getNumberOfTicks() != calculateOffset() + 1 && tick == calculateOffset() +1;
    }

    private void setTickShape(Graphics2D graphics, int xPosition) {
        switch (config.getTickShape()) {
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

    private int calculateOffset() {
        if (config.getOffset() < config.getNumberOfTicks()){
            return config.getOffset();
        }
        return config.getOffset()% config.getNumberOfTicks();
    }



}


package org.mastooo.client;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;

public class Separator {
	static final CssColor color= CssColor.make("rgb(182, 180, 181)");
	Led[] leds = new Led[2];
	
	public Separator(double x, double y) {
		
		leds[0] = new Led(x, y, 10, color);
		leds[1] = new Led(x, y+40, 10, color);
	}
	
	public void draw(Context2d context) {
		for (int i = 0; i < leds.length; i++) {
			leds[i].draw(context);
		}
	}

	public double getWidth() {
		return Led.RADIUS;
	}

	public double getPosX() {
		return leds[0].pos.x;
	}
}

package org.mastooo.client;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;

public class Digit {
	static final boolean zero[] = { true, true, true, true, true, false, false, true, true, false, false, true, true, false, false, true, true,
			false, false, true, true, false, false, true, true, true, true, true };

	static final boolean one[] = { false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, true, false,
			false, false, true, false, false, false, true, false, false, false, true };

	static final boolean two[] = { true, true, true, true, false, false, false, true, false, false, false, true, true, true, true, true, true, false,
			false, false, true, false, false, false, true, true, true, true };

	static final boolean three[] = { true, true, true, true, false, false, false, true, false, false, false, true, true, true, true, true, false,
			false, false, true, false, false, false, true, true, true, true, true };

	static final boolean four[] = { true, false, false, true, true, false, false, true, true, false, false, true, true, true, true, true, false,
			false, false, true, false, false, false, true, false, false, false, true };

	static final boolean five[] = { true, true, true, true, true, false, false, false, true, false, false, false, true, true, true, true, false,
			false, false, true, false, false, false, true, true, true, true, true };

	static final boolean six[] = { true, true, true, true, true, false, false, false, true, false, false, false, true, true, true, true, true, false,
			false, true, true, false, false, true, true, true, true, true };

	static final boolean seven[] = { true, true, true, true, false, false, false, true, false, false, false, true, false, false, false, true, false,
			false, false, true, false, false, false, true, false, false, false, true };

	static final boolean eight[] = { true, true, true, true, true, false, false, true, true, false, false, true, true, true, true, true, true, false,
			false, true, true, false, false, true, true, true, true, true };

	static final boolean nine[] = { true, true, true, true, true, false, false, true, true, false, false, true, true, true, true, true, false, false,
			false, true, false, false, false, true, false, false, false, true };

	static final short xOffset = 20;
	static final short yOffset = 5;

	Led[] leds = new Led[28];
	CssColor color;

	public Digit(double x, double y, String color) {
		this.color = CssColor.make(color);
		for (int i = 0; i < leds.length; i = i + 4) {
			double yPos = y + i * yOffset;
			leds[i] = new EmptyLed(x, yPos);
			leds[i + 1] = new EmptyLed(x + xOffset, yPos);
			leds[i + 2] = new EmptyLed(x + xOffset * 2, yPos);
			leds[i + 3] = new EmptyLed(x + xOffset * 3, yPos);
		}
	}

	public void draw(Context2d context) {
		for (int i = 0; i < leds.length; i++) {
			leds[i].draw(context);
		}
	}

	public double getWidth() {
		return leds[3].pos.x - leds[0].pos.x + Led.RADIUS;
	}

	public double getHeight() {
		return leds[leds.length - 1].pos.y - leds[0].pos.y;
	}

	public double getPosY() {
		return leds[0].pos.y;
	}

	public double getPosX() {
		return leds[0].pos.x;
	}

	public void setValue(short value) {
		boolean map[] = null;
		switch (value) {
		case 0:
			map = zero;
			break;
		case 1:
			map = one;
			break;
		case 2:
			map = two;
			break;
		case 3:
			map = three;
			break;
		case 4:
			map = four;
			break;
		case 5:
			map = five;
			break;
		case 6:
			map = six;
			break;
		case 7:
			map = seven;
			break;
		case 8:
			map = eight;
			break;
		case 9:
			map = nine;
			break;
		default:
			map = zero;
			break;
		}

		for (int i = 0; i < map.length; i++) {
			if (map[i]) {
				if (leds[i] instanceof EmptyLed)
					leds[i] = new Led(leds[i].pos.x, leds[i].pos.y, 10, color);
			} else{
				if (!(leds[i] instanceof EmptyLed))
					leds[i] = new EmptyLed(leds[i].pos.x, leds[i].pos.y);
			}
		}
	}
}
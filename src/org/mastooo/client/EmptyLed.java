package org.mastooo.client;

import com.google.gwt.canvas.dom.client.CssColor;

public class EmptyLed extends Led {
	static final CssColor color = CssColor.make("rgb(201, 201, 201)");

	public EmptyLed(double x, double y) {
		super(x, y, 1, color);
	}

}

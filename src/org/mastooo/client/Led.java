package org.mastooo.client;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;

public class Led extends SpringObject {
	
	public static double RADIUS = 7d;
	
	public CssColor color;
	public double posZ, velZ, goalZ;
	public double radius = RADIUS;
	public Vector startPos;
	public double startPosZ;
	public double startRadius;

	public Led(Vector start, double startPosZ, CssColor color) {
		super(start);
		this.color = color;
		this.posZ = startPosZ;
		this.velZ = 0;
		this.goalZ = startPosZ;
		this.startPos = new Vector(start);
		this.startPosZ = startPosZ;
		this.startRadius = radius;
	}

	public Led(double x, double y, double z, String color) {
		this(new Vector(x, y), z, CssColor.make(color));
	}
	
	public Led(double x, double y, double z, CssColor color) {
		this(new Vector(x, y), z, color);
	}

	public void draw(Context2d context) {
		context.setFillStyle(color);
		context.beginPath();
		context.arc(pos.x, pos.y, radius, 0, Math.PI * 2.0, true);
		context.closePath();
		context.fill();
	}
}

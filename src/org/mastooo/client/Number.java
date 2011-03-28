package org.mastooo.client;

import com.google.gwt.canvas.dom.client.Context2d;

public class Number {
	
	static final short SPACE = 30;
	
	Digit unit;
	Digit dec;
	Digit cent;

	// The maximum number that can be put in this number object
	private int max;
	
	private short value;
	
	Number(double x, double y, String color, int max){
		this.max = max;
		
		if (max > 99)
			cent = new Digit(x, y, color);
		if (max > 9)
			dec = new Digit(cent != null?cent.getPosX() + cent.getWidth() + SPACE:x, y, color);
		unit = new Digit(dec != null?dec.getPosX() + dec.getWidth()+SPACE:x, y, color);
	}
	
	public void draw(Context2d context) {
		if(unit != null) unit.draw(context);
		if(dec != null) dec.draw(context);
		if(cent != null) cent.draw(context);
	}
	
	public double getPosX(){
		return cent!=null?cent.getPosX():dec!=null?dec.getPosX():unit.getPosX();
	}
	
	public double getPosY(){
		return unit.getPosY();
	}
	
	public double getWidth() {
		return (cent!=null?cent.getWidth() + SPACE:0) +(dec!=null?dec.getWidth() + SPACE:0) + unit.getWidth();
	}
	
	public double getHeight() {
		return unit.getHeight();
	}
	
	public void setValue(short value){
		if (value > max)
			throw new IllegalArgumentException("THis number is not set up to manage this kind of huge numbers");
		
		if (this.value == value)
			return;
		this.value = value;
		
		String valueString = String.valueOf(value);
		
		if (max > 99){
			cent.setValue(value >99?(short)(valueString.charAt(0) - '0'):(short)0);
		}
		if (max > 9){
			dec.setValue(value > 9?(short)(valueString.charAt(valueString.length() -2) - '0'):(short)0);
		}
		unit.setValue((short)(valueString.charAt(valueString.length()-1) - '0'));
	}
}

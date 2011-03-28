package org.mastooo.client;

import java.util.Date;

import com.google.gwt.canvas.dom.client.Context2d;

public class Countdown {
	
	static long toDays = 86400000;
	static long toHours = 3600000;
	static long toMinutes = 60000;
	static long toSeconds = 1000;
	
	Number days = new Number(100, 50, "rgb(38, 88, 151)", 364);
	Separator sep1 = new Separator(days.getPosX() + days.getWidth() + 30, 90);
	Number hours = new Number(sep1.getPosX() + sep1.getWidth() + 30, 50, "rgb(19, 172, 250)", 23);
	Separator sep2 = new Separator(hours.getPosX() + hours.getWidth() + 30, 90);
	Number minutes = new Number(sep2.getPosX() + sep2.getWidth() + 30, 50, "rgb(192, 0, 11)", 59);
	Separator sep3 = new Separator(minutes.getPosX() + minutes.getWidth() + 30, 90);
	Number seconds = new Number(sep3.getPosX() + sep3.getWidth() + 30, 50, "rgb(0, 154, 73)", 59);
	
	Date dateOfArrival;
	
	void setValues(){
		Date current = new Date();
		
		long time = dateOfArrival.getTime() - current.getTime();
		if (time <= 0) return;
		
		long nbDay = time/toDays;
		days.setValue((short)nbDay);
		
		long temp = time%toDays;
		
		long nbHours = temp/toHours;
		hours.setValue((short)nbHours);
		
		temp = temp%toHours;
		
		long nbMinutes = temp/toMinutes;
		minutes.setValue((short)nbMinutes);
		
		temp = temp%toMinutes;
		
		long nbSeconds = temp/toSeconds;
		seconds.setValue((short)nbSeconds);

	}
	
	public Countdown(final Date arrival) {
		this.dateOfArrival = arrival;
		setValues();
	}
	
	public void draw(Context2d context) {
		days.draw(context);
		sep1.draw(context);
		hours.draw(context);
		sep2.draw(context);
		minutes.draw(context);
		sep3.draw(context);
		seconds.draw(context);
	}
}

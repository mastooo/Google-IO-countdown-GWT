package org.mastooo.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class Io implements EntryPoint {
	static final String holderId = "canvasholder";
	
	DateTimeFormat dateFormat = DateTimeFormat.getFormat("yyyy-MM-dd mm:hh:ss");
	
	String dateOfArrival = "2011-05-10 09:00:00";

	Canvas canvas;
	Canvas backBuffer;
	
	int mouseX, mouseY;

	static final int refreshRate = 40;
	
	static int pass = 0;

	final CssColor redrawColor = CssColor.make("rgb(214, 214, 214)");
	Context2d context;
	Context2d backBufferContext;
	
	Countdown countdown = new Countdown(dateFormat.parse(dateOfArrival));
	
	
	public void onModuleLoad() {
		canvas = Canvas.createIfSupported();
		backBuffer = Canvas.createIfSupported();
		if (canvas == null) {
			RootPanel.get(holderId).add(new Label("Not supported"));
			return;
		}
		
		int width=RootPanel.get(holderId).getElement().getOffsetWidth()-5;
		int height = RootPanel.get(holderId).getElement().getOffsetHeight()-5;

		canvas.setWidth(width + "px");
		canvas.setHeight(height + "px");
		
		canvas.setCoordinateSpaceWidth(width);
		canvas.setCoordinateSpaceHeight(height);
		backBuffer.setCoordinateSpaceWidth(width);
		backBuffer.setCoordinateSpaceHeight(height);
		RootPanel.get(holderId).add(canvas);
		context = canvas.getContext2d();
		backBufferContext = backBuffer.getContext2d();

		// setup timer
		final Timer animation = new Timer() {
			@Override
			public void run() {
				if (pass == 24){
					countdown.setValues();
					pass = 0;
				}else
					pass++;
				doUpdate();
			}
		};
		
		doUpdate();
		animation.scheduleRepeating(refreshRate);
	}
	
	

	void doUpdate() {
		// update the back canvas
		backBufferContext.setFillStyle(redrawColor);
		backBufferContext.fillRect(0, 0, canvas.getCoordinateSpaceWidth(), canvas.getCoordinateSpaceHeight());
		
		countdown.draw(backBufferContext);
				
		context.drawImage(backBufferContext.getCanvas(), 0, 0);
	}
	
	
}

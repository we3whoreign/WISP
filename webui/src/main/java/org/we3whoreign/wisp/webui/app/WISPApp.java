package org.we3whoreign.wisp.webui.app;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.we3whoreign.wisp.webui.pages.about.About;
import org.we3whoreign.wisp.webui.pages.home.*;

public class WISPApp extends WebApplication {
	
	@Override
	public void init() {
		// Map pages
		mountPage("/", Main.class);
		mountPage("/home", Main.class);
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return Main.class;
	}	

}

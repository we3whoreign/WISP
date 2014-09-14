package org.we3whoreign.wisp.webui.pages.home;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.we3whoreign.wisp.resources.ClassManager;
import org.we3whoreign.wisp.utils.StringUtils;

import com.cooldatasoft.common.MenuItem;
import com.cooldatasoft.horizontal.dropdown.multiLevelCss.MultiLevelCssMenu;

public class Main extends WebPage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2675803018080441619L;
	
	public Main(final PageParameters parameters) 
	{
		super(parameters);
		
		buildMenu();
	}
	
	private void buildMenu() 
	{
		List<MenuItem> primaryList = new ArrayList<MenuItem>();
		
		Hashtable<String,ArrayList<String>> classes = ClassManager.listClassTable();
		for(String s1 : classes.keySet())
		{
			MenuItem menuItem = new MenuItem(StringUtils.toCamelCase(s1));
			
			for(String s2 : classes.get(s1)){
				MenuItem subMenuItem = new MenuItem(StringUtils.toCamelCase(s2));
				menuItem.getSubMenuItemList().add(subMenuItem);
			}
			
			primaryList.add(menuItem);
		}
		
		add(new MultiLevelCssMenu("menuDiv",primaryList));
	}
	
}

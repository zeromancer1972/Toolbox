package org.openntf.bstemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Tools implements Serializable {

	private static final long serialVersionUID = 8031965383531253276L;
	private final List<Page> navigation;

	public Tools() {
		this.navigation = new ArrayList<Page>();
		this.navigation.add(new Page("Catalog", "catalog.xsp", "glyphicon glyphicon-list", false));
		this.navigation.add(new Page("Calendar profiles", "calprofiles.xsp", "glyphicon glyphicon-calendar", false));
	}

	public List<Page> getNavigation() {
		return navigation;
	}
}

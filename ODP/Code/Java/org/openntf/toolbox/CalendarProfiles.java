package org.openntf.toolbox;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.openntf.domino.Document;
import org.openntf.domino.View;
import org.openntf.domino.utils.XSPUtil;

public class CalendarProfiles {

	private final String PATTERN_CALPROFILE_ERROR = "Error processing calendar profile document";
	private final String PATTERN_SEARCH = "in database";
	private final String ITEM_EVENTS = "EventList";
	private final String eventsView = "($SearchEventsView)";
	private Map<String, Object> dbs = null;

	public CalendarProfiles() {
		init();
	}

	@SuppressWarnings("unchecked")
	public void init() {
		// open the log database and find all entries with a specific string
		View logs = XSPUtil.getCurrentSession().getDatabase(
				XSPUtil.getCurrentDatabase().getServer(), "log.nsf").getView(this.eventsView);
		String entry = "";
		String database = "";
		this.dbs = new HashMap<String, Object>();
		for (Document doc : logs.getAllDocuments()) {
			// extract the details and store it into a map (avoid doubles)
			Iterator it = doc.getItemValue(this.ITEM_EVENTS).iterator();
			while (it.hasNext()) {
				entry = (String) it.next();
				if (entry.indexOf(this.PATTERN_CALPROFILE_ERROR) != -1) {
					database = entry.substring(
							entry.indexOf(this.PATTERN_SEARCH) + this.PATTERN_SEARCH.length(),
							entry.lastIndexOf(":")).trim();
					this.dbs.put(database, database);
				}
			}
		}
	}

	public Map<String, Object> getDatabases() {
		return this.dbs;
	}
}
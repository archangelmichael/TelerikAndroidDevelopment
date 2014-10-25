package com.intro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageHistory {
	public static List<MessageChange> ITEMS = new ArrayList<MessageChange>();
	public static Map<String, MessageChange> ITEM_MAP = new HashMap<String, MessageChange>();

	static {
		addChangeLog(new MessageChange("Original Text", "Changed It", "Wed, 4 Jul 2001 12:08:56"));
		addChangeLog(new MessageChange("Changed It", "Change Again", "Fri, 4 Sep 2002 08:08:56"));
		addChangeLog(new MessageChange("Change Again", "New Change", "Mon, 4 Oct 2013 15:08:56"));
	}

	public static void addChangeLog(MessageChange item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.date, item);
	}
}

package models;

import java.util.LinkedHashMap;
import java.util.Map;

public class FixedMap<K, V> extends LinkedHashMap<K, V> {
	private int max_capacity;

	public FixedMap( int max_capacity) {
		super(16, 0.75f, false);
		this.max_capacity = max_capacity;
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return size() > this.max_capacity;
	}

}

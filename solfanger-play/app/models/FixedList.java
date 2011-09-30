package models;

import java.util.LinkedList;

public class FixedList<T> extends LinkedList<T> {
	private final int maxSize;

	public FixedList(int maxSize) {
		this.maxSize=maxSize;
	}
	
	@Override
	public boolean add( T element) {
		if(size() > maxSize){
			remove();
		}
		return super.add( element);
	}
	

}

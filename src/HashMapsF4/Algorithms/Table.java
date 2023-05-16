package HashMapsF4.Algorithms;

import Auxiliar.MyArrayList;
import HashMapsF4.Entities.Accused;
import HashMapsF4.Entities.Synonym;
import HashMapsF4.Exceptions.AccusedNotFoundException;

public class Table<Key, Value> {

	private final static int SIZE = 50;

	public Synonym<Value>[] objects;

	public Table() {
		objects = (Synonym<Value>[]) new Synonym[SIZE];

		// Initialize all the arraylists.
		for (int i = 0; i < SIZE; i++) {
			objects[i] = new Synonym<>();
		}
	}

	public void add(Key key, Value value) {
		int index = HashCode.hashCode(key, objects.length);

		objects[index].synonym.add(value);
	}

	public Value get(Key key) throws AccusedNotFoundException {
		int index = HashCode.hashCode(key, objects.length);
		MyArrayList<Value> accusedList = objects[index].synonym;
		Accused finalAccused = null;

		// Run through all the accused and check the one that has the same name.
		for (int i = 0; i < accusedList.size(); i++) {
			Accused accused = (Accused) objects[index].synonym.get(i);

			if (accused.sameName(key.toString())) {
				finalAccused = (Accused) objects[index].synonym.get(i);
			}
		}

		if (finalAccused == null) {
			throw new AccusedNotFoundException();
		}

		return (Value) finalAccused;
	}

	public boolean remove(Key key) {
		int index = HashCode.hashCode(key, objects.length);
		boolean found = false;

		for (int i = 0; i < objects[index].synonym.size(); i++) {
			Accused accused = (Accused) objects[index].synonym.get(i);

			if (accused.sameName(key.toString())) {
				found = true;
				objects[index].synonym.remove(i);
			}
		}

		return found;
	}
}

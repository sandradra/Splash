package util;

import java.util.HashMap;
import java.util.Map;

/*
 *  an emitter class for publish some {@code T data} to its subscribers
 */

public class EventEmitter<T> {

	private int id;
	private Map<Integer, EventHandler<T>> map;

	public EventEmitter() {
		this.id = 0;
		this.map = new HashMap<Integer, EventHandler<T>>();
	}

	/*
	 * emit data to all subscribers
	 */
	public void emit(T data) {
		for (Map.Entry<Integer, EventHandler<T>> entry : this.map.entrySet()) {
			EventHandler<T> handler = entry.getValue(); 
			handler.handle(data);
		}
	}

	/*
	 * subscribe to this channel
	 */
	public int subscribe(EventHandler<T> handler) {
		this.id++;
		this.map.put(this.id, handler);

		return this.id;
	}

	/*
	 * unsubscribe to this channel with the {@code subscriptionId} returned in {@code subscribe(EvantHandler<T> handler)}
	 */
	public void unsubscribe(int subscriptionId) {
		if (this.map.containsKey(subscriptionId)) {
			this.map.remove(subscriptionId);
		}
	}
}

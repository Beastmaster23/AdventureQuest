package application.models;

public class PQueueData<T> implements Comparable {
	private int priority;
	private T data;
	
	public PQueueData() {
		priority=-1;
		data=null;
	}
	
	public PQueueData(int priority, T data) {
		priority=-1;
		this.data=data;
	}
	
	public void setData(T data) {
		this.data=data;
	}
	
	public void setData(int priority, T data) {
		this.priority=priority;
		this.data=data;
	}
	
	public void setPriority(int priority) {
		this.priority=priority;
	}

	@Override
	public int compareTo(Object o) {
		PQueueData ot=(PQueueData)o;
		return priority-ot.getPriority();
	}

	public int getPriority() {
		return priority;
	}

	public T getData() {
		return data;
	}
	
	
	
}

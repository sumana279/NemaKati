package cts.reKroot.svas.beans;

public abstract class ResultStatus {
	public enum Status {
		OK, FAILED
	}

	private Object response;
	private Status status;



	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}

package cts.reKroot.svas.beans;

import java.util.List;

import cts.reKroot.svas.beans.ResultStatus.Status;

public class GetUserResponse extends ResultStatus {
	public GetUserResponse(Status status, List<User> response) {
		this.setStatus(status);
		this.setResponse(response);
	}

	public GetUserResponse(Status status, String response) {
		this.setStatus(status);
		this.setResponse(response);
	}
}

package cts.reKroot.svas.beans;

public class UserRoleResponse extends ResultStatus {

	public UserRoleResponse(Status status, String response) {
		this.setStatus(status);
		this.setResponse(response);
	}

}

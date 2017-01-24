package cts.reKroot.svas.beans;

import java.util.List;

import cts.reKroot.svas.beans.ResultStatus.Status;

public class GetResponse extends ResultStatus {

	public GetResponse(Status status, List<RoleMaster> response) {
		this.setResponse(response);
		this.setStatus(status);
	}

	public GetResponse(Status status, String response) {
		this.setStatus(status);
		this.setResponse(response);

	}



}

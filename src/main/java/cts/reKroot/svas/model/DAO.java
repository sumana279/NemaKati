package cts.reKroot.svas.model;

import cts.reKroot.svas.beans.GetResponse;
import cts.reKroot.svas.beans.GetUserResponse;
import cts.reKroot.svas.beans.RoleMaster;
import cts.reKroot.svas.beans.UpdateResponse;
import cts.reKroot.svas.beans.User;
import cts.reKroot.svas.beans.UserPayload;
import cts.reKroot.svas.beans.UserRoleResponse;

public interface DAO {

	public UserRoleResponse addRole(RoleMaster role);

	public GetResponse getRole();

	public UserRoleResponse addUser(UserPayload userPayload);

	public GetUserResponse getUser(long id);

	public GetUserResponse updateUser(User user);

	public GetUserResponse deleteUser(String id);

}

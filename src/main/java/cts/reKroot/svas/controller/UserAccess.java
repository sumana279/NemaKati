package cts.reKroot.svas.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cts.reKroot.svas.beans.GetResponse;
import cts.reKroot.svas.beans.GetUserResponse;
import cts.reKroot.svas.beans.RoleMaster;
import cts.reKroot.svas.beans.UserPayload;
import cts.reKroot.svas.beans.UserRoleResponse;
import cts.reKroot.svas.beans.ResultStatus.Status;
import cts.reKroot.svas.model.DaoHelper;
import cts.reKroot.svas.model.UserConstants;
import cts.reKroot.svas.model.Utility;

@RestController
@RequestMapping("/userAccess")
public class UserAccess {
	DaoHelper dH = new DaoHelper();;

	@RequestMapping(value = "/newUserAdd", method = RequestMethod.POST, headers = "Accept=application/json")
	public UserRoleResponse roleInsertion(@RequestBody String userInfo) {
		ObjectMapper mapper = new ObjectMapper();
		UserPayload userPayload = null;
		try {

			userPayload = mapper.readValue(userInfo, UserPayload.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dH.getDao().addUser(userPayload);
	}

	@RequestMapping(value = "/getUserData/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public GetUserResponse getRoleDetails(@PathVariable long id) {
		return dH.getDao().getUser(id);
	}

	@RequestMapping(value = "/updateUserInfo/isAdminExecutive={isAdminExecutive}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public GetUserResponse updateUserInfo(@RequestBody String payload, @PathVariable String isAdminExecutive) {
		UserPayload userInfo = null;
		userInfo = (UserPayload) Utility.json2javaObj(payload, UserPayload.class);
		if (userInfo != null && !userInfo.equals(null)) {
			if (userInfo.getUserInfo().getRoles() != null) {
				if (userInfo.getUserInfo().getRoles().size() > 0) {
					if (!"N".equalsIgnoreCase(isAdminExecutive)) {
						GetUserResponse gur = new GetUserResponse(Status.FAILED, UserConstants.ACCESS_RESTRICTION);
						return gur;

					}

				}

			}
		}
		return dH.getDao().updateUser(userInfo.getUserInfo());

	}

	@RequestMapping(value = "/deleteUserInfo/ctsId={id}/isAdminExecutive={isAdminExecutive}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public GetUserResponse deleteUserInfo(@PathVariable String id, @PathVariable String isAdminExecutive) {
		UserPayload userInfo = null;
		if (UserConstants.IS_VALUE_TRUE.equalsIgnoreCase(isAdminExecutive)) {
			GetUserResponse gur = new GetUserResponse(Status.FAILED, UserConstants.ACCESS_RESTRICTION);
			return gur;

		}
		return dH.getDao().deleteUser(id);

	}

}

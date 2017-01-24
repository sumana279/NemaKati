package cts.reKroot.svas.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cts.reKroot.svas.beans.GetResponse;
import cts.reKroot.svas.beans.RoleMaster;
import cts.reKroot.svas.beans.UserRoleResponse;
import cts.reKroot.svas.model.DaoHelper;

/**
 * Created by sudhan on 1/10/17.
 */
@RestController
@RequestMapping("/ctsLoginPage")
public class LoginUser {
	DaoHelper dH = new DaoHelper();;

	@RequestMapping(value = "/userRoleLogin", method = RequestMethod.GET, headers = "Accept=application/json")
	public GetResponse getRoleDetails() {
		System.out.println("The data received was ");
		return dH.getDao().getRole();
	}

	@RequestMapping(value = "/userRoleLogin", method = RequestMethod.POST, headers = "Accept=application/json")
	public UserRoleResponse roleInsertion(@RequestBody String roleName) {
		ObjectMapper mapper = new ObjectMapper();
		RoleMaster role = null;
		try {

			role = mapper.readValue(roleName, RoleMaster.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dH.getDao().addRole(role);
	}

}
package cts.reKroot.svas.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import cts.reKroot.svas.beans.RoleMaster;
import cts.reKroot.svas.beans.User;
import cts.reKroot.svas.beans.UserPayload;
import cts.reKroot.svas.beans.UserRoleMap;
import cts.reKroot.svas.beans.ResultStatus.Status;

public class TestMapping {
	static Session session;
	static Transaction tx;

	public static void main(String[] args) {
		try {
			// session = HibernateUtil.getSession();
			// tx = session.beginTransaction();
			UserPayload upl = new UserPayload();
			User us = new User();
			us.setCtsId(27271);
			us.setFirstName("Manasa");
			us.setLastName("Sudhanva");
			us.setEmail("test@cts.com");
			us.setMiddleName("");

			us.setPassword("XYX");
			us.setPhoneNo("650555555");
			us.setSecurityAns("04/30/2014");
			us.setSecurityQ("DateOfJoining");
			RoleMaster ro = new RoleMaster();
			ro.setId((long) 1);
			ro.setRoleName("Executive");
			RoleMaster ro2 = new RoleMaster();

			ro2.setId((long) 2);
			List<RoleMaster> rlist = new ArrayList<RoleMaster>();
			rlist.add(ro);
			rlist.add(ro2);
			us.setRoles(rlist);
			// session.save(us);
			// session.save(ro);
			// session.save(ro2);
			//
			// tx.commit();
			upl.setUserInfo(us);
			Gson gson = new Gson();
			String json = gson.toJson(upl);
			System.out.println(json);

		} catch (Exception e) {
			// tx.rollback();
			e.printStackTrace();

		} /*
			 * finally { session.close(); }
			 */
	}

}

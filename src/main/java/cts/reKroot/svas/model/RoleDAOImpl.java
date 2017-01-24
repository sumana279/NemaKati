package cts.reKroot.svas.model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cts.reKroot.svas.beans.GetResponse;
import cts.reKroot.svas.beans.GetUserResponse;
import cts.reKroot.svas.beans.RoleMaster;
import cts.reKroot.svas.beans.User;
import cts.reKroot.svas.beans.UserPayload;
import cts.reKroot.svas.beans.UserRoleResponse;
import cts.reKroot.svas.beans.ResultStatus.Status;

public class RoleDAOImpl implements DAO {
	UserRoleResponse usr;
	private static Session session;
	private static Transaction tx;

	@Override
	public UserRoleResponse addRole(RoleMaster role) {
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			session.save(role);
			tx.commit();

		} catch (Exception e) {
			return new UserRoleResponse(Status.FAILED, "UserRole failed to create with below reason " + e);

		} finally {
			session.close();
		}
		return new UserRoleResponse(Status.OK, "UserRole Was added successfully");

	}

	@Override
	public GetResponse getRole() {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			List<RoleMaster> roles = session.createQuery("FROM RoleMaster").list();
			return new GetResponse(Status.OK, roles);
		} catch (Exception e) {
			return new GetResponse(Status.FAILED, "Failed to retrieve");
		}

	}

	@Override
	public UserRoleResponse addUser(UserPayload userPayload) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			User user = userPayload.getUserInfo();
			user.setCreateDate(new Date());
			session.save(user);
			tx.commit();
			session.close();
		} catch (Exception e) {
			return new UserRoleResponse(Status.FAILED, UserConstants.RECORD_FAILED + e);

		}
		return new UserRoleResponse(Status.OK, UserConstants.RECORD_ADDED);
	}

	@Override
	public GetUserResponse getUser(long id) {
		try {
			return new GetUserResponse(Status.OK, getUserPriv(id));
		} catch (Exception e) {
			return new GetUserResponse(Status.FAILED, UserConstants.FAILED_SECTION);
		}
	}

	private List<User> getUserPriv(long id) throws Exception {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<User> user = session.createQuery("FROM User u where u.ctsId =" + id).list();
		session.close();
		return user;
	}

	@Override
	public GetUserResponse updateUser(User user) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			if (user.getCtsId() > 0) {
				user.setModifiedDate(new Date());
				session.update(user);
				tx.commit();
			}
		} catch (Exception e) {
			return new GetUserResponse(Status.FAILED, UserConstants.FAILED_SECTION);
		} finally {
			session.close();
		}
		return new GetUserResponse(Status.OK, UserConstants.RECORD_UPDATED);
	}

	@Override
	public GetUserResponse deleteUser(String id) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			User us = new User();
			us.setCtsId(Long.parseLong(id));
			session.delete(us);
tx.commit();
		} catch (Exception e) {
			return new GetUserResponse(Status.FAILED, UserConstants.FAILED_SECTION);
		} finally {
			session.flush();
			session.close();
		}
		return new GetUserResponse(Status.OK, UserConstants.RECORD_DELETED);
	}

}

package cts.reKroot.svas.model;

public class DaoHelper {
	private DAO dao;
	
	public DaoHelper(){
		dao = new RoleDAOImpl();
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}
	

}

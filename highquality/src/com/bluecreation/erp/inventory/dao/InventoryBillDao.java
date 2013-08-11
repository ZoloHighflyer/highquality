package com.bluecreation.erp.inventory.dao;

import org.hibernate.Query;

import com.bluecreation.erp.inventory.model.InventoryBill;
import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.model.IdEntity;
import com.nci.cp.ds.paging.Pagination;

public class InventoryBillDao extends AbstractDao implements IInventoryBillDao {

	@Override
	protected Class getDaoModel() {
		// TODO Auto-generated method stub
		return InventoryBill.class;
	}
    public Pagination findInventoryBillsByUserWithPage(String userName, Pagination page){
    	String hsql = "select ib from InventoryBill ib where ib.opUserName='"+userName+"' and ib.billState=0 order by ib.createDate desc";
    	Pagination pg = super.findEntitiesByPage(hsql, page);
    	return pg;
    }
    public Pagination findWaitingAuditInventoryBillsWithPage( Pagination page){
    	String hsql = "select ib from InventoryBill ib where  ib.billState=1 order by ib.createDate desc";
    	Pagination pg = super.findEntitiesByPage(hsql, page);
    	return pg;
    }
    public Pagination findAuditedInventoryBillsWithPage( Pagination page){
    	String hsql = "select ib from InventoryBill ib where  ib.billState=2 order by ib.createDate desc";
    	Pagination pg = super.findEntitiesByPage(hsql, page);
    	return pg;
    }
    @Override
	public IdEntity updateEntity(IdEntity entity) throws DaoException {
		
		entity =super.updateEntity(entity);
		Query q = this.getSession().createQuery("delete  from InventoryBillItem ib where ib.inventoryBill is null order by ib.createDate desc");
    	q.executeUpdate();
		return entity;
	}
    
}

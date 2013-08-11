package com.nci.cp.booking.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import com.nci.cp.booking.model.BookRecord;
import com.nci.cp.core.dao.AbstractDao;
import com.nci.cp.core.exception.DaoException;
/**
 * @target
 * @company BlueCreation Studio
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-8-4 
 */
public class BookDao extends AbstractDao implements IBookDao {
	private static Log log = LogFactory.getLog(BookDao.class);
	@Override
	protected Class getDaoModel() {
		
		return BookRecord.class;
	}
	public List findBookRecordsOfToday() throws DaoException {
		Query query = getSession().getNamedQuery("findBookingToday");
		Date cur = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(cur);
		str += " 00:00:00.000";
		
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		try {
			cur = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp curtime= new Timestamp(cur.getTime());
		query.setTimestamp(0, curtime);		

		List el = null;
		try {
			el = query.list();
					
		} catch (Exception e) {
			log
					.error(this.getClass().getName()+" findBookRecordsOfToday fail! "
							+ e.getMessage());
			e.printStackTrace();
			throw new DaoException(
					this.getClass().getName()+" findBookRecordsOfToday fail! ");
		}
		return el;
	}
}

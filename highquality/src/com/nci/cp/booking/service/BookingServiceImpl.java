package com.nci.cp.booking.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.booking.dao.BookDao;
import com.nci.cp.booking.model.BookRecord;
import com.nci.cp.booking.vo.BookRecordVo;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.exception.ServiceException;
/**
 * The interface is booking module service implement.
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-08-04 
 */
public class BookingServiceImpl implements IBookingService {
	private static Log log = LogFactory.getLog(BookingServiceImpl.class);
    private BookDao bookDao;
    
	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public BookRecordVo createBookRecord(BookRecordVo brvo)
			throws ServiceException {
		try {
			bookDao.createEntity(VoToDo(brvo));
		} catch (DaoException e) {
			
			e.printStackTrace();
			log.error(this.getClass().getName()+" createBookRecord fail "+brvo.toString());
			throw new ServiceException("createBookRecord fai"+brvo.toString());
		}
		
		return null;
	}
    
	public BookRecordVo findBookRecordById(BookRecordVo brvo)throws ServiceException {
		BookRecord br = new BookRecord();
		
		if ((brvo != null) && (brvo.getId() != null)) {
			try {
				br.setId(Long.parseLong(brvo.getId()));
			} catch (Exception e) {
				e.printStackTrace();
				log.error(this.getClass().getName()+" findBookRecordById(BookRecordVo brvo) fail,when input id of entity is not valid Long type. ");
				throw new ServiceException(
						this.getClass().getName()+"findBookRecordById(BookRecordVo brvo) .The input id of entity is not valid Long type.!");
			}
			
			try {
				br=(BookRecord)bookDao.findEntityById(br);
				if (br!=null)  {					
					return DoToVo(br);					
				}
			} catch (DaoException e) {			
				e.printStackTrace();
				log.error(this.getClass().getName()+" updateBookRecord(BookRecordVo brvo) fail,when find do by id. ");
				throw new ServiceException(
						this.getClass().getName()+" updateBookRecord(BookRecordVo brvo) fail,when find do by id. ");
			}
			
		}	
		return null;
	}
	public BookRecordVo updateBookRecord(BookRecordVo brvo)
			throws ServiceException {
		Long tid = null;
		BookRecord br = new BookRecord();
		try {
			tid = Long.parseLong(brvo.getId());
		} catch (Exception e) {
			log.error(this.getClass().getName()+" updateBookRecord(BookRecordVo brvo) fail,when input id of entity is not valid Long type. ");
			throw new ServiceException(
					this.getClass().getName()+"The input id of entity is not valid Long type.!");
		}
		br.setId(tid);
		try {
			br=(BookRecord)bookDao.findEntityById(br);
			
		} catch (DaoException e) {			
			e.printStackTrace();
			log.error(this.getClass().getName()+" updateBookRecord(BookRecordVo brvo) fail,when find do by id. ");
			throw new ServiceException(
					this.getClass().getName()+" updateBookRecord(BookRecordVo brvo) fail,when find do by id. ");
		}
		try {
			br.setName(brvo.getName());
			br.setMenu(brvo.getMenu());
			Float price = new Float(0);
			try {			
			    price =Float.parseFloat(brvo.getPrice());			
			}catch (Exception ex){
		
			}
			br.setPrice(price);
			
			Float payed = new Float(0);
			try {			
			    payed =Float.parseFloat(brvo.getPayed());			
			}catch (Exception ex){
		
			}
			br.setPayed(payed);
			br.setBalance(payed-price);
			br.setModifyDate(new java.util.Date());
			
			br=(BookRecord)bookDao.updateEntity(br);
			
		} catch (DaoException e) {			
			e.printStackTrace();
			log.error(this.getClass().getName()+" updateBookRecord(BookRecordVo brvo) fail,when update do. ");
			throw new ServiceException(
					this.getClass().getName()+" updateBookRecord(BookRecordVo brvo) fail,when update do. ");
		}
		
		
		return DoToVo(br);
	}

	public List findBookRecordsOfTodayVos() throws ServiceException {
		List vos = new ArrayList(32);
		List<BookRecord> dos =null;
		
		try {
			dos=bookDao.findBookRecordsOfToday();
			
		} catch (DaoException e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" createBookRecord fail!");
			throw new ServiceException("createBookRecord fail!");
		}
		if (dos!=null){
			Float willPay = new Float(0);
			Float hadPayed = new Float(0);
			Float sbalance = new Float(0);
			for(BookRecord br:dos) {
				if (br.getPrice()!=null) {
					willPay += br.getPrice();
				}
				if (br.getPayed()!=null) {
					hadPayed += br.getPayed();
				}
				if (br.getBalance()!=null) {
					sbalance += br.getBalance();
				}
				vos.add(DoToVo(br));
			}
			BookRecordVo sumvo = new BookRecordVo();
			sumvo.setName("");
			sumvo.setMenu("<b>合计</b>");
			sumvo.setPrice(willPay.toString());
			sumvo.setPayed(hadPayed.toString());
			sumvo.setBalance(sbalance.toString());
			vos.add(sumvo);
		}
		return vos;
	}
    public boolean  deleteBookRecord(BookRecordVo brvo) throws ServiceException {
       BookRecord br = new BookRecord();
		
		if ((brvo != null) && (brvo.getId() != null)) {
			try {
				br.setId(Long.parseLong(brvo.getId()));
			} catch (Exception e) {
				e.printStackTrace();
				log.error(this.getClass().getName()+" deleteBookRecord(BookRecordVo brvo) fail,when input id of entity is not valid Long type. ");
				throw new ServiceException(
						this.getClass().getName()+" deleteBookRecord(BookRecordVo brvo) .The input id of entity is not valid Long type.!");
			}
			
			try {
				br=(BookRecord)bookDao.findEntityById(br);
				if (br!=null)  {					
					return bookDao.deleteEntity(br);				
				}
			} catch (DaoException e) {			
				e.printStackTrace();
				log.error(this.getClass().getName()+" deleteBookRecord(BookRecordVo brvo) fail,when find do by id. ");
				throw new ServiceException(
						this.getClass().getName()+" deleteBookRecord(BookRecordVo brvo) fail,when find do by id. ");
			}
			
		}	
    	return false;
    }
	private BookRecord  VoToDo(BookRecordVo brvo) throws ServiceException {
		BookRecord br = new BookRecord();
	//	br.setId(Long.parseLong(brvo.getId()));
		br.setName(brvo.getName());
		br.setMenu(brvo.getMenu());
		Float price = new Float(0);
		try {			
		    price =Float.parseFloat(brvo.getPrice());			
		}catch (Exception ex){	
		}
		br.setPrice(price);	
		br.setPayed(new Float(0));
		br.setBalance(br.getPayed()-br.getPrice());
		return br;
	}
	private BookRecordVo DoToVo(BookRecord br)  throws ServiceException {
		BookRecordVo brvo = new BookRecordVo();
		brvo.setId(br.getId().toString());
		brvo.setName(br.getName());
		brvo.setMenu(br.getMenu());
		brvo.setPrice(br.getPrice()==null?"0.0":br.getPrice().toString());
		brvo.setPayed(br.getPayed()==null?"0.0":br.getPayed().toString());
		brvo.setBalance(br.getBalance()==null?"0.0":br.getBalance().toString());
		return brvo;
	}
}

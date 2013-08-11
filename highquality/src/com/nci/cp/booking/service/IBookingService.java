package com.nci.cp.booking.service;

import java.util.List;

import com.nci.cp.booking.vo.BookRecordVo;
import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.service.IService;
/**
 * The interface is booking module service interface.
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2011-08-04 
 */
public interface IBookingService extends IService {
	/**
	 * @return
	 * @throws ServiceException
	 */
	public List findBookRecordsOfTodayVos() throws ServiceException;
	/**
	 * @param brvo
	 * @return
	 * @throws ServiceException
	 */
	public BookRecordVo createBookRecord(BookRecordVo brvo) throws ServiceException;
	/**
	 * @param brvo
	 * @return
	 * @throws ServiceException
	 */
	public BookRecordVo findBookRecordById(BookRecordVo brvo)throws ServiceException;
	/**
	 * @param brvo
	 * @return
	 * @throws ServiceException
	 */
	public BookRecordVo updateBookRecord(BookRecordVo brvo) throws ServiceException;
	
	/**
	 * @param brvo
	 * @return
	 * @throws ServiceException
	 */
	public boolean   deleteBookRecord(BookRecordVo brvo) throws ServiceException;
}

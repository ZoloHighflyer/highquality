package com.nci.cp.teaminfo.info.service;

import java.util.List;

import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.model.IdEntity;
import com.nci.cp.core.service.IService;
import com.nci.cp.teaminfo.info.model.InfoRecord;
import com.nci.cp.teaminfo.info.model.TopicInfo;
import com.nci.cp.teaminfo.info.vo.IRVo;
import com.nci.cp.teaminfo.info.vo.TopicInfoVo;

/**
 * The interface is teaminfo system service interface.
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2010-12-25 
 */
public interface IInfoService extends IService{
	/**
	 * @param entity
	 * @return
	 * @throws ServiceException
	 */
	public InfoRecord createInfoRecord(InfoRecord inforec) throws ServiceException;

	/**
	 * @param entity
	 * @return
	 * @throws ServiceException
	 */
	public IdEntity updateInfoRecord(InfoRecord inforec) throws ServiceException;
    
	/**
	 * @param entity
	 * @return
	 * @throws ServiceException
	 */
	public boolean deleteInfoRecord(InfoRecord inforec) throws ServiceException;

	/**
	 * @param entity
	 * @return
	 * @throws ServiceException
	 */
	public IdEntity findInfoRecords(InfoRecord inforec) throws ServiceException;
	
	/**
	 * @param entity
	 * @return
	 * @throws ServiceException
	 */
	public TopicInfo createTopicInfo(TopicInfo topicinfo) throws ServiceException;
    
	/**
	 * @param tpvo
	 * @return
	 * @throws ServiceException
	 */
	public TopicInfo createTopicInfoVo(TopicInfoVo tpvo) throws ServiceException ;
	/**
	 * @param entity
	 * @return
	 * @throws ServiceException
	 */
	public TopicInfoVo updateTopicInfo(TopicInfoVo topicvo) throws ServiceException;
    
	/**
	 * @param entity
	 * @return
	 * @throws ServiceException
	 */
	public boolean deleteTopicInfo(TopicInfoVo topicvo) throws ServiceException;
	/**
	 * @param TopicInfo
	 * @return
	 * @throws ServiceException
	 */
	public TopicInfoVo findTopicInfo(TopicInfoVo topicvo) throws ServiceException;

	/**
	 * @param TopicInfo
	 * @return
	 * @throws ServiceException
	 */
	public TopicInfo findTopicInfos(TopicInfoVo topicvo) throws ServiceException;
	/**
	 * @return List<TopicInfoVo>
	 * @throws ServiceException
	 */
	public List<TopicInfoVo> findValidTopicInfoVos() throws ServiceException;
	/**
	 * @return List<TopicInfoVo>
	 * @throws ServiceException
	 */
	//public List<TopicInfoVo> findValidTopicInfoVos() throws ServiceException;
	public List<TopicInfoVo> findValidTopicInfoSingleVos() throws ServiceException;
	
	/**
	 * @return
	 * @throws ServiceException
	 */
	public List  topicvosManagement() throws ServiceException;
	
	public IRVo buildInputIR() throws ServiceException;
	
	public IRVo createInfoTopicRecord(IRVo irvo) throws ServiceException;
}

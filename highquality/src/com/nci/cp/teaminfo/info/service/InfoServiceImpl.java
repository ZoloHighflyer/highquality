package com.nci.cp.teaminfo.info.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.model.IdEntity;
import com.nci.cp.core.utils.CommonUtils;
import com.nci.cp.teaminfo.info.dao.IInfoRecordDao;
import com.nci.cp.teaminfo.info.dao.ITopicInfoDao;
import com.nci.cp.teaminfo.info.model.InfoRecord;
import com.nci.cp.teaminfo.info.model.TopicInfo;
import com.nci.cp.teaminfo.info.vo.IRVo;
import com.nci.cp.teaminfo.info.vo.InfoRecordVo;
import com.nci.cp.teaminfo.info.vo.TopicInfoVo;
import com.nci.cp.web.uicomponent.model.SelectItem;

public class InfoServiceImpl implements IInfoService {
	private static Log log = LogFactory.getLog(InfoServiceImpl.class);
	private IInfoRecordDao infoRecordDao;
	private ITopicInfoDao topicInfoDao;

	public void setInfoRecordDao(IInfoRecordDao infoRecordDao) {
		this.infoRecordDao = infoRecordDao;
	}

	public void setTopicInfoDao(ITopicInfoDao topicInfoDao) {
		this.topicInfoDao = topicInfoDao;
	}

	public InfoRecord createInfoRecord(InfoRecord inforec)
			throws ServiceException {

		try {
			inforec.setCreateDate(new java.util.Date());
			inforec.setModifyDate(new java.util.Date());
			inforec = (InfoRecord) infoRecordDao.createEntity(inforec);
			return inforec;
		} catch (DaoException de) {
			de.printStackTrace();
			log
					.error("InfoServiceImpl.class createInfoRecord fail,when create "
							+ inforec.toString());
			throw new ServiceException(
					"InfoServiceImpl.class can not create entity!");
		}
	}

	public boolean deleteInfoRecord(InfoRecord inforec) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	public IdEntity findInfoRecords(InfoRecord inforec) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public IdEntity updateInfoRecord(InfoRecord inforec)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public TopicInfo createTopicInfo(TopicInfo topicinfo)
			throws ServiceException {
		log.info("InfoServiceImpl.createTopicInfo enter! ");
		try {
			topicinfo.setCreateDate(new java.util.Date());
			topicinfo.setModifyDate(new java.util.Date());
			topicinfo.setState(new Integer(1));
			topicinfo = (TopicInfo) topicInfoDao.createEntity(topicinfo);
			return topicinfo;
		} catch (DaoException de) {
			de.printStackTrace();
			log.error("InfoServiceImpl.class createTopicInfo fail,when create "
					+ topicinfo.toString());
			throw new ServiceException(
					"InfoServiceImpl.class can not create entity!");
		}
	}

	public TopicInfo createTopicInfoVo(TopicInfoVo tpvo) throws ServiceException {
		log.info("InfoServiceImpl.createTopicInfoVo enter! ");
		try {
            log.info("InfoServiceImpl.createTopicInfoVo handling! ");
            if (tpvo.getId()!=null) {
            	return VoToBo(updateTopicInfo(tpvo));
            }
			return createTopicInfo(VoToBo(tpvo));
		} catch (ServiceException de) {
			de.printStackTrace();
			log
					.error("InfoServiceImpl.class createTopicInfo fail,when create ");
			throw new ServiceException(
					"InfoServiceImpl.class can not create entity!");
		}
	}

	public boolean deleteTopicInfo(TopicInfoVo topicvo) throws ServiceException {
		TopicInfo topicinfo = new TopicInfo();
		if ((topicvo != null) && (topicvo.getId() != null)) {
			try {
				topicinfo.setId(new Long(Long.parseLong(topicvo.getId())));
			} catch (Exception e) {
				e.printStackTrace();
				log
						.error("InfoServiceImpl.class deleteTopicInfo(TopicInfoVo topicvo) fail,when change id for entity. ");
				throw new ServiceException(
						"InfoServiceImpl.class can not findTopicInfo !");
			}
			try {
				topicinfo = (TopicInfo) topicInfoDao.findEntityById(topicinfo);
				if (topicinfo == null) {
					log.error("InfoServiceImpl.class deleteTopicInfo fail,when load entity from dao class is null.  ");
					throw new ServiceException(
							"InfoServiceImpl.class can not load entity from dao class ,the result is null !");
				}
			} catch (DaoException e) {
				log
						.error("InfoServiceImpl.class deleteTopicInfo fail,when load entity from dao class.  ");
				throw new ServiceException(
						"InfoServiceImpl.class can not load entity from dao class !");
			}

			try {
				return topicInfoDao.deleteEntity(topicinfo);
			} catch (DaoException e) {
				log
						.error("InfoServiceImpl.class deleteTopicInfo fail,when delete entity.  "
								+ e.getMessage());
				throw new ServiceException(
						"InfoServiceImpl.class can not delete entity !");
			}
		}
		return false;

	}

	public TopicInfoVo findTopicInfo(TopicInfoVo topicvo)
			throws ServiceException {
		TopicInfo topicinfo = new TopicInfo();
		if ((topicvo != null) && (topicvo.getId() != null)) {
			try {
				topicinfo.setId(new Long(Long.parseLong(topicvo.getId())));
			} catch (Exception e) {
				e.printStackTrace();
				log
						.error("InfoServiceImpl.class findTopicInfo fail,when change id for entity. ");
				throw new ServiceException(
						"InfoServiceImpl.class can not findTopicInfo !");
			}

			try {
				TopicInfo topic = (TopicInfo) topicInfoDao
						.findEntityById(topicinfo);
				log.info("==================== method: findTopicInfo get title: "+topic.getTitle());
				return TopicBoToVo(topic);
			} catch (DaoException e) {
				log
						.error("InfoServiceImpl.class findTopicInfo fail,when load entity from dao class.  ");
				throw new ServiceException(
						"InfoServiceImpl.class can not load entity from dao class !");
			}
		}

		return null;

	}

	public TopicInfo findTopicInfos(TopicInfoVo infovo) throws ServiceException {
		return null;
	}

	public TopicInfoVo updateTopicInfo(TopicInfoVo topicvo)
			throws ServiceException {
		if ((topicvo != null) && (topicvo.getId() != null)
				&& (topicvo.getTitle() != null)) {
			Long tid = null;
			try {
				tid = Long.parseLong(topicvo.getId());
			} catch (Exception e) {
				log.error("InfoServiceImpl.class updateTopicInfo(TopicInfoVo topicvo) fail,when input id of entity is not valid Long type. ");
				throw new ServiceException(
						"The input id of entity is not valid Long type.!");
			}

			try {
				TopicInfo topic = new TopicInfo();
				topic.setId(tid);

				topic = (TopicInfo) topicInfoDao.findEntityById(topic);
				if (topic != null) {
					topic.setTitle(topicvo.getTitle());
					topic.setModifyDate(new java.util.Date());
					topic = (TopicInfo) topicInfoDao.updateEntity(topic);
					return TopicBoToVo(topic);
				} else {
					log
							.error("InfoServiceImpl.class findTopicInfo fail, can not find the entity by id:"
									+ tid);
					throw new ServiceException(
							"InfoServiceImpl.class can not load entity from dao class !");
				}
			} catch (DaoException e) {
				log
						.error("InfoServiceImpl.class findTopicInfo fail,when load entity from dao class.  ");
				throw new ServiceException(
						"InfoServiceImpl.class can not load entity from dao class !");
			}
		} else {
			log
					.error("InfoServiceImpl.class updateTopicInfo(TopicInfoVo topicvo) fail,when input is invalid entity. ");
			throw new ServiceException(
					"the topicinfo vo is null or invalid input!");
		}

	}

	public List<TopicInfoVo> findValidTopicInfoVos() throws ServiceException {
		List topicVos = new ArrayList(8);
		try {
			List<TopicInfo> topicList = (List<TopicInfo>) topicInfoDao
					.findValidTopics();

			if ((topicList != null) && (topicList.size() > 0)) {
				for (int i = 0, sum = topicList.size(); i < sum; i++) {
					TopicInfo topic = (TopicInfo) topicList.get(i);
					TopicInfoVo topicVo = TopicBoToVo(topic);
					topicVos.add(topicVo);
				}
			}

			return topicVos;
		} catch (DaoException de) {
			de.printStackTrace();
			log.error("InfoServiceImpl.class findValidTopicInfos() fail! ");
			throw new ServiceException(
					"InfoServiceImpl.class findValidTopicInfos() fail!");
		}

	}
    public List  topicvosManagement() throws ServiceException {
    	    List tpvos = new ArrayList(32); 
    		try {
    			List<TopicInfo> tpl = (List<TopicInfo>) topicInfoDao
    					.findValidTopics();

    			if (( tpl != null) && ( tpl.size() > 0)) {
    				for (int i = 0, sum = tpl.size(); i < sum; i++) {
    					TopicInfo topic = (TopicInfo) tpl.get(i);
    					List tp = new ArrayList();
    					tp.add(topic.getId());
    					tp.add(topic.getTitle());
    					tp.add(topic.getModifyDate());
    					tpvos.add(tp);
    				}
    			}

    			return tpvos;
    		} catch (DaoException de) {
    			de.printStackTrace();
    			log.error("InfoServiceImpl.class TopicVosManagement() fail! ");
    			throw new ServiceException(
    					"InfoServiceImpl.class TopicVosManagement() fail!");
    		}
    
    }
	public List<TopicInfoVo> findValidTopicInfoSingleVos()
			throws ServiceException {
		List topicVos = new ArrayList(8);
		try {
			List<TopicInfo> topicList = (List<TopicInfo>) topicInfoDao
					.findValidTopics();

			if ((topicList != null) && (topicList.size() > 0)) {
				for (int i = 0, sum = topicList.size(); i < sum; i++) {
					TopicInfo topic = (TopicInfo) topicList.get(i);
					TopicInfoVo topicVo = TopicSingleEnityToVo(topic);
					topicVos.add(topicVo);
				}
			}

			return topicVos;
		} catch (DaoException de) {
			de.printStackTrace();
			log.error("InfoServiceImpl.class findValidTopicInfos() fail! ");
			throw new ServiceException(
					"InfoServiceImpl.class findValidTopicInfos() fail!");
		}

	}
	public IRVo buildInputIR() throws ServiceException {
    	List tps = findValidTopicInfoSingleVos();
    	IRVo irv = new IRVo();
    	log.debug("size: "+irv.getSel().size());
    	for(int i=0;i<tps.size();i++) {
    		TopicInfoVo tiv = (TopicInfoVo)tps.get(i);
    		irv.getSel().add(new SelectItem(tiv.getId(),tiv.getTitle(),false));
    	}
    	
    	return irv;
    }
    public IRVo createInfoTopicRecord(IRVo irvo) throws ServiceException {
    	if (irvo==null){
    	  log.debug("========================IRVo: null");
    	}else {
    	   createInfoRecord(irvo.getIfr());
    	}
    	return irvo;
    }
	private TopicInfo VoToBo(TopicInfoVo topicinfoVo) {
		TopicInfo tp = new TopicInfo();
		tp.setTitle(topicinfoVo.getTitle());
		return tp;
	}

	private TopicInfoVo TopicSingleEnityToVo(TopicInfo topicinfo) {
		TopicInfoVo topicVo = new TopicInfoVo();
		topicVo.setCreateTime(CommonUtils.formatDate(topicinfo.getCreateDate(),
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
		topicVo.setModifyTime(CommonUtils.formatDate(topicinfo.getModifyDate(),
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
		topicVo.setId(topicinfo.getId().toString());
		topicVo.setTitle(topicinfo.getTitle());
		topicVo.setState(topicinfo.getState().toString());

		return topicVo;
	}

	private TopicInfoVo TopicBoToVo(TopicInfo topicinfo) {
		TopicInfoVo topicVo = new TopicInfoVo();
		topicVo.setCreateTime(CommonUtils.formatDate(topicinfo.getCreateDate(),
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
		topicVo.setModifyTime(CommonUtils.formatDate(topicinfo.getModifyDate(),
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
		topicVo.setId(topicinfo.getId().toString());
		topicVo.setTitle(topicinfo.getTitle());
		topicVo.setState(topicinfo.getState().toString());
		Set records = topicinfo.getInfoRecords();

		if (!records.isEmpty()) {
			Iterator it = records.iterator();
			while (it.hasNext()) {
				InfoRecord inforecord = (InfoRecord) it.next();
				InfoRecordVo irVo = InfoRecordBoToVo(inforecord);
				topicVo.getInforecords().add(irVo);
			}
			InfoRecordVo inrecvo = (InfoRecordVo) topicVo.getInforecords().get(
					0);

			topicVo.setModifyTime(inrecvo.getModifyTime());
		}

		return topicVo;
	}

	private InfoRecordVo InfoRecordBoToVo(InfoRecord inforecord) {
		InfoRecordVo inforecordVo = new InfoRecordVo();
		inforecordVo.setId(inforecord.getId().toString());
		inforecordVo.setTitle(inforecord.getTitle());
		inforecordVo.setContent(inforecord.getContent());
		inforecordVo.setCreateTime(CommonUtils.formatDate(inforecord
				.getCreateDate(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
		inforecordVo.setModifyTime(CommonUtils.formatDate(inforecord
				.getModifyDate(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
		return inforecordVo;
	}
}

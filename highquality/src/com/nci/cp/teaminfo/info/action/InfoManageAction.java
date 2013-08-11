package com.nci.cp.teaminfo.info.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.web.AbstractAction;
import com.nci.cp.core.web.IAction;
import com.nci.cp.teaminfo.info.model.InfoRecord;
import com.nci.cp.teaminfo.info.model.TopicInfo;
import com.nci.cp.teaminfo.info.service.IInfoService;
import com.nci.cp.teaminfo.info.vo.IRVo;
import com.nci.cp.teaminfo.info.vo.TopicInfoVo;
import com.nci.cp.web.uicomponent.model.SelectItem;


/**
 * This class is to performance info management system request functions. 
 * @company BlueCreation Workspace
 * @author  OliverChan 
 * @version 0.1
 * @date    2010-12-25 
 **/
public class InfoManageAction extends AbstractAction {
	private static Log log = LogFactory.getLog(InfoManageAction.class);
    private IInfoService    infoService;
    private InfoRecord      inforec;
    private TopicInfo       topicinfo;
    private TopicInfoVo     topicinfovo;

	public void setInfoService(IInfoService infoService) {
		this.infoService = infoService;
	}
    
	public InfoRecord getInforec() {
		return inforec;
	}

	public void setInforec(InfoRecord inforec) {
		this.inforec = inforec;
	}
    
	public TopicInfo getTopicinfo() {
		return topicinfo;
	}

	public void setTopicinfo(TopicInfo topicinfo) {
		this.topicinfo = topicinfo;
	}    
	
	public TopicInfoVo getTopicinfovo() {
		return topicinfovo;
	}

	public void setTopicinfovo(TopicInfoVo topicinfovo) {
		this.topicinfovo = topicinfovo;
	}

	public String saveInfoRecord()throws Exception {		
		try {
			inforec = infoService.createInfoRecord(inforec);
			
		}catch (ServiceException e) {			
			e.printStackTrace();
			log.error("InfoManageAction.class saveInfoRecord() fail! "+e.getMessage());
			throw new ServiceException("InfoManageAction.class saveInfoRecord() fail!");	
		}catch (Exception ex) {
			ex.printStackTrace();
			return ERROR;
		}
		return super.save();		
	}
	
	public String editTopic() throws ServiceException {
		action = IAction.ACTION_EDIT;
		try {
			topicinfovo= infoService.findTopicInfo(topicinfovo);
			return SUCCESS;
		} catch (ServiceException e) {
			log.error("InfoManageAction.class editTopic() fail! "+e.getMessage());
			throw new ServiceException("InfoManageAction.class editTopic() fail!");
		}
		
	}
    public String saveTopicInfo()throws Exception {
		if (action==null) {
		  try {
		      if((topicinfovo!=null)&&(topicinfovo.getTitle()!=null)) {
		    	topicinfo = new TopicInfo();
		    	topicinfo.setTitle(topicinfovo.getTitle());
		    	topicinfo = infoService.createTopicInfo(topicinfo);
		      }
			
			
		  }catch (ServiceException e) {			
			e.printStackTrace();
			log.error("InfoManageAction.class saveInfoRecord() fail! "+e.getMessage());
			throw new ServiceException("InfoManageAction.class saveInfoRecord() fail!");	
		  }
		} else if (ACTION_EDIT.equals(action)) {
			
			infoService.updateTopicInfo(topicinfovo);
		}
		return super.save();
		
	}
    public String delTopic() throws ServiceException {
    	 if((topicinfovo!=null)&&(topicinfovo.getId()!=null)) {
    		 try {
    			 if (infoService.deleteTopicInfo(topicinfovo)) {
        			 return SUCCESS;
        		 }
			} catch (ServiceException e) {
				log.error("InfoManageAction.class delTopic() fail,as ! "+e.getMessage());
	    		 throw new ServiceException("InfoManageAction.class delTopic() fail!"); 
			}
    		
    		 
    	 }else {
    		 log.error("InfoManageAction.class delTopic() fail,as the id of topic for deleting is null! ");
    		 throw new ServiceException("InfoManageAction.class delTopic() fail!"); 
    	 }
    		
    	
    	return ERROR;
    }
    public String viewTopic() throws ServiceException {
    	if((topicinfovo!=null)&&(topicinfovo.getId()!=null)) {
   		    try {
   			     topicinfovo=infoService.findTopicInfo(topicinfovo);
       			 return SUCCESS;       		
			} catch (ServiceException e) {
				log.error("InfoManageAction.class viewTopic() fail,as ! "+e.getMessage());
	    		 throw new ServiceException("InfoManageAction.class viewTopic()fail!"); 
			}
   		
   		 
   	    }else {
   		 log.error("InfoManageAction.class viewTopic() fail,as the id of topic for view is null! ");
   		 throw new ServiceException("InfoManageAction.class viewTopic() fail!"); 
     	 }

    }
    
    public List<TopicInfoVo> getTopics() throws ServiceException {
    	 
    	try {
    		List<TopicInfoVo> topics = infoService.findValidTopicInfoVos();
    		
			return topics;
			
		}catch (ServiceException e) {			
			e.printStackTrace();
			log.error("InfoManageAction.class getTopics() fail! "+e.getMessage());
			throw new ServiceException("InfoManageAction.class getTopics() fail!");	
		}
		
    }
    public List<TopicInfoVo> getSingleTopics() throws ServiceException {
   	 
    	try {
    		List<TopicInfoVo> topics = infoService.findValidTopicInfoSingleVos();
    		
			return topics;
			
		}catch (ServiceException e) {			
			e.printStackTrace();
			log.error("InfoManageAction.class getSingleTopics() fail! "+e.getMessage());
			throw new ServiceException("InfoManageAction.class getSingleTopics() fail!");	
		}
		
    }
    
    
    
}

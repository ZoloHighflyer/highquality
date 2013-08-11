package com.nci.cp.teaminfo.info.service;

import com.nci.cp.teaminfo.info.vo.TopicInfoVo;

public class ServiceTest implements IServiceTest {

	
	public Object handle(TopicInfoVo vo) {
		if (vo!=null) {
			System.out.println("====================title: "+vo.getTitle());
		}
        return null;
	}

}

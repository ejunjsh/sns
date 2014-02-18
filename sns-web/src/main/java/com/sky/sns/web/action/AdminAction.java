package com.sky.sns.web.action;

import java.util.Map;
import com.sky.sns.enumeration.StatusEnum;

public class AdminAction extends BaseAction {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5307800294237159257L;

	private Map<Integer,String> statusMap;
	public Map<Integer,String> getStatusMap() {
		return StatusEnum.toMap();
	}


	
	public String index() throws Exception {
        return "index";
	}
}

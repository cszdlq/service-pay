package com.sooncode.subassembly.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.sooncode.soonjdbc.constant.Sort;
import com.sooncode.soonjdbc.page.Page;
import com.sooncode.soonjdbc.service.JdbcService;
import com.sooncode.soonjdbc.sql.condition.Conditions;

import com.sooncode.soonjdbc.sql.condition.sign.LikeSign;
import com.sooncode.subassembly.entity.OrderPayRecord;
import com.sooncode.subassembly.util.GenerateUUIDUtil;
import com.sooncode.subassembly.util.PageModel;

/**订单记录controller
 * @author songwei
 * @date 2017-05-24
 *
 */
@RestController
@RequestMapping("/orderPayRecord")
public class OrderPayRecordController {
	
	@Autowired
	private JdbcService jdbcService;
	
	/**订单记录保存
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/saveOrderRecord",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> saveOrderPayRecord(@RequestBody String requestBody){
		OrderPayRecord orderPayRecord = JSON.parseObject(requestBody, OrderPayRecord.class);
		orderPayRecord.setUuid(GenerateUUIDUtil.createUUID());
		orderPayRecord.setCreateTime(new Date());
		orderPayRecord.setUpdateTime(new Date());
		long save = jdbcService.save(orderPayRecord);
		Map<String, Object> resultMap = new LinkedHashMap<>();
		resultMap.put("responseCode", save > 0 ? true : false);
		resultMap.put("responseMessage", save > 0 ? "保存成功" : "保存失败");
		return resultMap;
		
	}
	
	/**订单记录查询：分页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getOrderRecordList",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> orderPayRecordList(@RequestBody String requestBody){
		OrderPayRecord orderPayRecord = JSON.parseObject(requestBody, OrderPayRecord.class);
		PageModel pageModel = JSON.parseObject(requestBody, PageModel.class);
		Conditions conditions = new Conditions(orderPayRecord);
		conditions.setCondition("orderName", LikeSign.LIKE, orderPayRecord.getOrderName());
		conditions.setOderBy("createTime", Sort.DESC);
		Page page = jdbcService.getPage(pageModel.getCp(), pageModel.getPz(), conditions);
		Map<String, Object> resultMap = new LinkedHashMap<>();
		if (page == null || StringUtils.isEmpty(page)) {
			resultMap.put("responseCode", false);
			return resultMap;
		}
		List<Object> ones = page.getOnes();
		resultMap.put("responseCode", (ones != null && !ones.isEmpty()));
		resultMap.put("total", page.getTotal());
		resultMap.put("list", ones);
		
		return resultMap;
		
	}

}

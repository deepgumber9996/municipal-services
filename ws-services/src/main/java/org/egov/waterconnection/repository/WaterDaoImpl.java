package org.egov.waterconnection.repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.egov.common.contract.request.RequestInfo;
import org.egov.common.contract.request.Role;
import org.egov.common.contract.request.User;
import org.egov.waterconnection.config.WSConfiguration;
import org.egov.waterconnection.constants.WCConstants;
import org.egov.waterconnection.repository.rowmapper.OpenWaterRowMapper;
import org.egov.waterconnection.web.models.SearchCriteria;
import org.egov.waterconnection.web.models.WaterConnection;
import org.egov.waterconnection.web.models.WaterConnectionRequest;
import org.egov.waterconnection.producer.WaterConnectionProducer;
import org.egov.waterconnection.repository.builder.WsQueryBuilder;
import org.egov.waterconnection.repository.rowmapper.WaterRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class WaterDaoImpl implements WaterDao {

	@Autowired
	private WaterConnectionProducer waterConnectionProducer;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private WsQueryBuilder wsQueryBuilder;

	@Autowired
	private WaterRowMapper waterRowMapper;

	@Autowired
	private OpenWaterRowMapper openWaterRowMapper;
	
	@Autowired
	private WSConfiguration wsConfiguration;

	@Value("${egov.waterservice.createwaterconnection.topic}")
	private String createWaterConnection;

	@Value("${egov.waterservice.updatewaterconnection.topic}")
	private String updateWaterConnection;
	
	@Override
	public void saveWaterConnection(WaterConnectionRequest waterConnectionRequest) {
		waterConnectionProducer.push(createWaterConnection, waterConnectionRequest);
	}

	@Override
	public List<WaterConnection> getWaterConnectionList(SearchCriteria criteria,
			RequestInfo requestInfo) {
		List<Object> preparedStatement = new ArrayList<>();
		String query = wsQueryBuilder.getSearchQueryString(criteria, preparedStatement, requestInfo);
		if (query == null)
			return Collections.emptyList();
		Boolean isOpenSearch = isSearchOpen(requestInfo.getUserInfo());
		List<WaterConnection> waterConnectionList = new ArrayList<>();
		if(isOpenSearch)
			waterConnectionList = jdbcTemplate.query(query, preparedStatement.toArray(),
					openWaterRowMapper);
		else
			waterConnectionList = jdbcTemplate.query(query, preparedStatement.toArray(),
				waterRowMapper);
		if (waterConnectionList == null)
			return Collections.emptyList();
		return waterConnectionList;
	}

	@Override
	public void updateWaterConnection(WaterConnectionRequest waterConnectionRequest, boolean isStateUpdatable) {
		if (isStateUpdatable) {
			waterConnectionProducer.push(updateWaterConnection, waterConnectionRequest);
		} else {
			waterConnectionProducer.push(wsConfiguration.getWorkFlowUpdateTopic(), waterConnectionRequest);
		}
	}
	
	/**
	 * push object to create meter reading
	 * 
	 * @param waterConnectionRequest
	 */
	public void postForMeterReading(WaterConnectionRequest waterConnectionRequest) {
		log.info("Posting request to kafka topic - " + wsConfiguration.getCreateMeterReading());
		waterConnectionProducer.push(wsConfiguration.getCreateMeterReading(), waterConnectionRequest);
	}

	/**
	 * push object for edit notification
	 * 
	 * @param waterConnectionRequest
	 */
	public void pushForEditNotification(WaterConnectionRequest waterConnectionRequest) {
		if (!WCConstants.EDIT_NOTIFICATION_STATE
				.contains(waterConnectionRequest.getWaterConnection().getProcessInstance().getAction())) {
			waterConnectionProducer.push(wsConfiguration.getEditNotificationTopic(), waterConnectionRequest);
		}
	}
	
	/**
	 * Enrich file store Id's
	 * 
	 * @param waterConnectionRequest
	 */
	public void enrichFileStoreIds(WaterConnectionRequest waterConnectionRequest) {
		waterConnectionProducer.push(wsConfiguration.getFileStoreIdsTopic(), waterConnectionRequest);
	}
	
	/**
	 * Save file store Id's
	 * 
	 * @param waterConnectionRequest
	 */
	public void saveFileStoreIds(WaterConnectionRequest waterConnectionRequest) {
		waterConnectionProducer.push(wsConfiguration.getSaveFileStoreIdsTopic(), waterConnectionRequest);
	}

	public Boolean isSearchOpen(User userInfo) {

		return userInfo.getType().equalsIgnoreCase("SYSTEM")
				&& userInfo.getRoles().stream().map(Role::getCode).collect(Collectors.toSet()).contains("ANONYMOUS");
	}
	
	public void updateWaterApplicationStatus(String id, String status) {
		
		Object[] params = { status, id};
		
		int[] types = {Types.VARCHAR, Types.VARCHAR};
		
		jdbcTemplate.update(WsQueryBuilder.UPDATE_DISCONNECT_STATUS, params, types);
		 
	}
	
	
	@Override
	public List<String> fetchWaterConnectionIds(SearchCriteria criteria){

//        List<Object> preparedStmtList = new ArrayList<>();
//        preparedStmtList.add(criteria.getTenantId());
//        preparedStmtList.add(criteria.getOffset());
//        preparedStmtList.add(criteria.getLimit());
//        
//
//        return jdbcTemplate.query("SELECT id from eg_ws_connection where tenantid=? ORDER BY createdtime offset " +
//                        " ? " +
//                        "limit ? ",
//                preparedStmtList.toArray(),
//                new SingleColumnRowMapper<>(String.class));
        
        
        List<Object> preparedStmtList = new ArrayList<>();
		String basequery = "select id from eg_ws_connection";
		StringBuilder builder = new StringBuilder(basequery);

		if(!ObjectUtils.isEmpty(criteria.getTenantId())){
				builder.append(" where tenantid=?");
				preparedStmtList.add(criteria.getTenantId());
			}

		String orderbyClause = " order by lastmodifiedtime,id offset ? limit ?";
		builder.append(orderbyClause);
		preparedStmtList.add(criteria.getOffset());
		preparedStmtList.add(criteria.getLimit());
		return jdbcTemplate.query(builder.toString(), preparedStmtList.toArray(), new SingleColumnRowMapper<>(String.class));
    }
	
	@Override
	public List<WaterConnection> getPlainWaterConnectionSearch(SearchCriteria criteria) {
        List<Object> preparedStmtList = new ArrayList<>();
        String query = wsQueryBuilder.getWCPlainSearchQuery(criteria, preparedStmtList);
        log.info("Query: " + query +  "\n preparedStmtList:"+ preparedStmtList);
      
        List<WaterConnection> waterconnection =  jdbcTemplate.query(query, preparedStmtList.toArray(), waterRowMapper);
        return waterconnection;
    }

}

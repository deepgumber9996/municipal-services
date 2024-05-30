package org.egov.wscalculation.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.egov.wscalculation.repository.WSCalculationDao;
import org.egov.wscalculation.service.BillGeneratorService;
import org.egov.wscalculation.util.ResponseInfoFactory;
import org.egov.wscalculation.validator.BillGenerationValidator;
import org.egov.wscalculation.web.models.BillGenerationReq;
import org.egov.wscalculation.web.models.BillGenerationSearchCriteria;
import org.egov.wscalculation.web.models.BillScheduler;
import org.egov.wscalculation.web.models.BillSchedulerResponse;
import org.egov.wscalculation.web.models.RequestInfoWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@RestController
@RequestMapping("/watercharges")
public class BillGeneratorController {

	@Autowired
	private final ResponseInfoFactory responseInfoFactory;

	@Autowired
	private BillGeneratorService billGeneratorService;
	
	@Autowired
	private BillGenerationValidator billGenerationValidator;

	@Autowired
	private WSCalculationDao waterCalculatorDao;

	@PostMapping("/scheduler/_create")
	public ResponseEntity<BillSchedulerResponse> billSchedulerCreate(
			@Valid @RequestBody BillGenerationReq billGenerationReq) {

		BillSchedulerResponse response=new BillSchedulerResponse();
		List<BillScheduler> billDetails1 = new ArrayList<BillScheduler>();
		boolean isBatch=billGenerationReq.getBillScheduler().isBatch();
		System.out.println("isBatch value"+isBatch);
		
		if(isBatch) {		
			List<String> listOfLocalities = waterCalculatorDao.getLocalityList(billGenerationReq.getBillScheduler().getTenantId(),billGenerationReq.getBillScheduler().getLocality());
			for(String localityName : listOfLocalities) {		
				billGenerationReq.getBillScheduler().setLocality(localityName);			
				billGenerationValidator.validateBillingCycleDates(billGenerationReq, billGenerationReq.getRequestInfo());
				List<BillScheduler> billDetails = billGeneratorService.saveBillGenerationDetails(billGenerationReq);
				billDetails1.addAll(billDetails);
		}
		}else {
					billGenerationValidator.validateBillingCycleDates(billGenerationReq, billGenerationReq.getRequestInfo());
					List<BillScheduler> billDetails = billGeneratorService.saveBillGenerationDetails(billGenerationReq);
				    billDetails1.addAll(billDetails);
			}

		 response = BillSchedulerResponse.builder().billSchedulers(billDetails1)
				.responseInfo(
						responseInfoFactory.createResponseInfoFromRequestInfo(billGenerationReq.getRequestInfo(), true))
				.build();
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/scheduler/_search")
	public ResponseEntity<BillSchedulerResponse> billSchedulerSearch(
			@Valid @RequestBody RequestInfoWrapper requestInfoWrapper,
			@Valid @ModelAttribute BillGenerationSearchCriteria criteria) {
		List<BillScheduler> billSchedulers = billGeneratorService.getBillGenerationDetails(criteria);
		BillSchedulerResponse response = BillSchedulerResponse.builder().billSchedulers(billSchedulers).responseInfo(
				responseInfoFactory.createResponseInfoFromRequestInfo(requestInfoWrapper.getRequestInfo(), true))
				.build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

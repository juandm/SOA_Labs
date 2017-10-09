package br.ufsc.das.discovery;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.jboss.logging.Logger;
import org.uddi.api_v3.AccessPoint;
import org.uddi.api_v3.BindingTemplate;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.CategoryBag;
import org.uddi.api_v3.FindService;
import org.uddi.api_v3.GetServiceDetail;
import org.uddi.api_v3.KeyedReference;
import org.uddi.api_v3.ServiceDetail;
import org.uddi.api_v3.ServiceInfo;
import org.uddi.api_v3.ServiceList;
import org.uddi.v3_service.UDDIInquiryPortType;

public abstract class ServiceDiscoveryHelper {

	private static final Logger logger = Logger.getLogger(ServiceDiscoveryHelper.class);

	private static final UDDIAccessor uddi = new UDDIAccessor();

	public static String discoverServiceEndpoint(String serviceCategory) throws Exception {

		UDDIInquiryPortType inquiryService = uddi.getInquiryService();

		FindService findServiceRequest = new FindService();

		CategoryBag categoryBag = new CategoryBag();

		KeyedReference keyedReference = new KeyedReference();
		keyedReference.setTModelKey("uddi:das.ufsc.br:servicecategory");
		keyedReference.setKeyName("das.ufsc.br:servicecategory");
		keyedReference.setKeyValue(serviceCategory);

		categoryBag.getKeyedReference().add(keyedReference);

		findServiceRequest.setCategoryBag(categoryBag);

		ServiceList result = inquiryService.findService(findServiceRequest);

		List<ServiceInfo> servicesInfo = result.getServiceInfos() != null ? result.getServiceInfos().getServiceInfo()
				: Collections.emptyList();

		logger.info("Found " + servicesInfo.size() + " service(s) for " + serviceCategory);

		if (servicesInfo.isEmpty())
			throw new RuntimeException("There is no service available for " + serviceCategory);

		Collection<String> serviceKeys = new HashSet<String>();

		for (ServiceInfo s : servicesInfo) {
			serviceKeys.add(s.getServiceKey());
		}

		// Getting details of services
		GetServiceDetail gsd = new GetServiceDetail();

		gsd.getServiceKey().addAll(serviceKeys);

		ServiceDetail serviceDetail = inquiryService.getServiceDetail(gsd);

		List<BusinessService> services = serviceDetail.getBusinessService();

		// TODO: sort services by some criteria (ex. QoS attributes modeled as
		// keyedReferences in categoryBag)

		services = Collections.singletonList(services.get(new Random().nextInt(services.size())));

		for (BusinessService service : services) {

			String serviceKey = service.getServiceKey();

			CategoryBag serviceCategoryBag = service.getCategoryBag();

			if (serviceCategoryBag != null) {

				StringBuilder sb = new StringBuilder();

				for (KeyedReference k : serviceCategoryBag.getKeyedReference()) {
					sb.append("\ttModelKey=" + k.getTModelKey() + ", keyName=" + k.getKeyName() + ", keyValue="
							+ k.getKeyValue() + "\n");
				}

				logger.info("Information for serviceKey=" + serviceKey + "\n" + sb);
			}

			for (BindingTemplate b : service.getBindingTemplates().getBindingTemplate()) {

				AccessPoint acessPoint = b.getAccessPoint();

				if (acessPoint.getUseType() != null && acessPoint.getUseType().equalsIgnoreCase("endpoint")) {

					String endpoint = b.getAccessPoint().getValue();

					logger.info("Returning endpoint " + endpoint + " of serviceKey=" + serviceKey + ", serviceCategory="
							+ serviceCategory);

					return endpoint;
				}
			}
		}

		throw new RuntimeException("There is no service binding available for " + serviceCategory);
	}
}

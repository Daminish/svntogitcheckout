package com.capco.travel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.dao.EmployeeDAO;
import com.capco.travel.model.EmployeeDetailsBO;
import com.capco.travel.model.MainRequestBO;
import com.capco.travel.service.EmailNotificationService;
import com.capco.travel.service.EmployeeDetailService;
import com.capco.travel.service.MainRequestService;
import com.capco.travel.util.PropertyUtils;
import com.capco.travel.util.TravelConstants;

import freemarker.template.Configuration;

/**
 * @author e5542274
 *
 */
@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {
	private static final Logger logger = Logger.getLogger(EmailNotificationServiceImpl.class);

	@Autowired
	MainRequestService mainRequestService;

	@Autowired
	EmployeeDetailService employeeDetailService;

	@Autowired
	EmployeeDAO employeeDao;

	/**
	 * This method to sendEmailNotification with request
	 * 
	 * @return *
	 * 
	 * @methodName sendEmailNotification
	 * @input @param int requestId
	 * @throws com.capco.travel.custom.exception.TravelException.TravelServiceException
	 */
	@Override
	public void sendEmailNotification(int requestId, String changeDone) throws TravelServiceException {
		logger.info("EmailNotificationServiceImpl : sendEmailNotification : Started");
		try {
			MainRequestBO mainRequestBO = mainRequestService.getMainRequestBOByRequestId(requestId);
			if (mainRequestBO != null) {
				sendEmail(mainRequestBO, changeDone);
			}

		} catch (TravelServiceException e) {
			logger.error("EmailNotificationServiceImpl : sendEmailNotification : TravelServiceException Caught : " + e);
		}
		logger.info("EmailNotificationServiceImpl : sendEmailNotification : Ended");

	}

	public boolean sendEmail(MainRequestBO mainRequestBO, String changeDone) throws TravelServiceException {
		logger.info("EmailNotificationServiceImpl : sendEmailNotification : Started");
		try {
			// list of employeeId to get the emailIds
			List<Integer> empIdList = new ArrayList<>();
			empIdList.add(mainRequestBO.getRequestedBy());
			empIdList.add(mainRequestBO.getApproverId());

			// getting email ids from employeeDetails table
			Map<Integer, EmployeeDetailsBO> employeeEmailMap = employeeDao.getEmployeeEmail(empIdList);

			// setting the TO email list
			List<String> toEmailList = new ArrayList<>();
			toEmailList.add("pranjal.nartam@capco.com");
			// TODO: add approver email ID
			logger.info("EmailNotificationServiceImpl : sendEmailNotification : TO: "+ employeeEmailMap.get(mainRequestBO.getApproverId()).getEmailId());
			//toEmailList.add(employeeEmailMap.get(mainRequestBO.getApproverId()).getEmailId());

			InternetAddress[] toMailAddressArray = new InternetAddress[toEmailList.size()];
			for (int i = 0; i < toEmailList.size(); i++) {
				toMailAddressArray[i] = new InternetAddress(toEmailList.get(i));
			}

			// setting the CC email list
			List<String> ccEmailList = new ArrayList<>();
			ccEmailList.add("dummy@capco.com");
			// TODO: add requester Email ID
			logger.info("EmailNotificationServiceImpl : sendEmailNotification : CC: "+ employeeEmailMap.get(mainRequestBO.getRequestedBy()).getEmailId());
			//ccEmailList.add(employeeEmailMap.get(mainRequestBO.getRequestedBy()).getEmailId());

			InternetAddress[] ccMailAddressArray = new InternetAddress[ccEmailList.size()];
			for (int i = 0; i < ccEmailList.size(); i++) {
				ccMailAddressArray[i] = new InternetAddress(ccEmailList.get(i));
			}

			final String username = PropertyUtils.getPropertyValue("username");
			final String password = PropertyUtils.getPropertyValue("password");

			Properties props = new Properties();
			props.put("mail.smtp.auth", PropertyUtils.getPropertyValue("mail.smtp.auth"));
			props.put("mail.smtp.starttls.enable", PropertyUtils.getPropertyValue("mail.smtp.starttls.enable"));
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			props.put("mail.smtp.host", PropertyUtils.getPropertyValue("mail.smtp.host"));
			props.put("mail.smtp.port", PropertyUtils.getPropertyValue("mail.smtp.port"));

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			// Creating map for putting into the template
			Map<String, Object> model = new HashMap<>();
			model.put("requestedBy", employeeEmailMap.get(mainRequestBO.getRequestedBy()).getEmployeeName());
			model.put("requestId", mainRequestBO.getRequestId());
			model.put("approverName", employeeEmailMap.get(mainRequestBO.getApproverId()).getEmployeeName());
			model.put("requestedFor", mainRequestBO.getRequestedFor());
			model.put("status", mainRequestBO.getCurrentStatus());
			model.put("remarks", mainRequestBO.getRemark());
			model.put("changeDone", changeDone);

			// Getting content from template
			String emailMessage = getContentFromTemplate(model, mainRequestBO.getCurrentStatus());
			logger.info("EmailNotificationServiceImpl : sendEmailNotification : Email Message: " + emailMessage);

			// Creating the message
			final Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, toMailAddressArray);
			message.setRecipients(Message.RecipientType.CC, ccMailAddressArray);
			message.setSubject(
					"Travle Request " + mainRequestBO.getCurrentStatus() + " [" + mainRequestBO.getRequestId() + "] : "
							+ employeeEmailMap.get(mainRequestBO.getRequestedBy()).getEmployeeName());
			message.setContent(emailMessage, "text/html");
			Transport.send(message);

			logger.info("EmailNotificationServiceImpl : sendEmailNotification : Mail Sent");
			logger.info("EmailNotificationServiceImpl : sendEmailNotification : Ended");
			return true;
		} catch (Exception e) {
			logger.error("EmailNotificationServiceImpl : sendEmailNotification : Exception Caught : " + e);
			throw new TravelServiceException(e);
		}
	}

	/**
	 * This method is to put the data into the email_template
	 * 
	 * @author e5544354
	 * @return String
	 * @methodName getContentFromTemplate
	 * @param Map < String, Object >
	 */
	private String getContentFromTemplate(Map<String, Object> model, String currentStatus) {
		logger.info("EmailNotificationServiceImpl : getContentFromTemplate : Started");
		StringBuilder content = new StringBuilder();

		try {
			FreeMarkerConfigurationFactoryBean fmConfigFactoryBean = new FreeMarkerConfigurationFactoryBean();
			fmConfigFactoryBean.setTemplateLoaderPath("/templates/");
			Configuration config = fmConfigFactoryBean.createConfiguration();

			if (currentStatus.equals(TravelConstants.L1_PENDING) || currentStatus.equals(TravelConstants.L2_PENDING)) {
				content.append(FreeMarkerTemplateUtils
						.processTemplateIntoString(config.getTemplate("email_template.txt"), model));
				logger.info("EmailNotificationServiceImpl : getContentFromTemplate : PENDING template");
			}
			if (currentStatus.equals(TravelConstants.CANCELLED)) {
				content.append(FreeMarkerTemplateUtils
						.processTemplateIntoString(config.getTemplate("cancel_email_template.txt"), model));
				logger.info("EmailNotificationServiceImpl : getContentFromTemplate : CANCELLED tamplate");
			}
			if (currentStatus.equals(TravelConstants.APPROVED) || currentStatus.equals(TravelConstants.REJECTED)) {
				content.append(FreeMarkerTemplateUtils
						.processTemplateIntoString(config.getTemplate("approved_rejected_email_template.txt"), model));
				logger.info("EmailNotificationServiceImpl : getContentFromTemplate : APPROVED/REJECTED template");
			}

		} catch (Exception e) {
			logger.info("EmailNotificationServiceImpl : getContentFromTemplate : Exception cought: " + e);
		}
		logger.info("EmailNotificationServiceImpl : getContentFromTemplate : Ended");
		return content.toString();
	}
}

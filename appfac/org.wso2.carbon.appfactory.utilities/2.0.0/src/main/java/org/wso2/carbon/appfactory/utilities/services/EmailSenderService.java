/*
 * Copyright (c) 2008, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.appfactory.utilities.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.appfactory.common.AppFactoryException;
import org.wso2.carbon.appfactory.core.internal.ServiceHolder;
import org.wso2.carbon.appfactory.core.util.Constants;
import org.wso2.carbon.core.AbstractAdmin;
import org.wso2.carbon.email.verification.util.EmailVerifierConfig;
import org.wso2.carbon.email.verification.util.EmailSender;
import org.wso2.carbon.email.verification.util.Util;
import org.wso2.carbon.utils.CarbonUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmailSenderService extends AbstractAdmin {

    private static final Log log = LogFactory.getLog(EmailSenderService.class);

    /**
     *
     * @param email receivers email address
     * @param configFileName which resides on CARBON_HOME/repository/conf/email
     * @param userParams
     * @return
     * @throws AppFactoryException
     */
    public boolean sendMail(String email, String configFileName, String[][] userParams) throws AppFactoryException {
       // EmailSender sender = new EmailSender(loadEmailSenderConfiguration(configFileName));
        try {
            Map<String, String> paramMap = new HashMap<String, String>();
            for(int i = 0; i < userParams.length; i++) {
                paramMap.put(userParams[i][0], userParams[i][1]);
            }
           // sender.sendEmail(email, paramMap);
        } catch (Exception e) {
            String msg = "Email sending is failed for  " + email;
            log.error(msg, e);
            throw new AppFactoryException(msg, e);
        }
        return true;

    }

    public boolean sendMail(String mailTitle, String mailBody, String configFileName,
                            String userName) throws AppFactoryException {
        EmailVerifierConfig emailConfig = loadEmailSenderConfiguration(configFileName);
        String tenantDomain = userName.split("@")[1];
        Map<String, String> userParams = new HashMap<String, String>();
        String adminName= ServiceHolder.getAppFactoryConfiguration().getFirstProperty(Constants.EMAIL_ADMIN_NAME);
        String mailReceiverAddress = ServiceHolder.getAppFactoryConfiguration().getFirstProperty(Constants.RECEIVER_MAIL_ADDRESS);
        userParams.put("admin", adminName);
        userParams.put("userName", userName);
        userParams.put("tenantDomain", tenantDomain);
        userParams.put("first-name", userName.split("@")[0]);
        emailConfig.setEmailBody(mailBody);
        emailConfig.setSubject(mailTitle);
        EmailSender sender = new EmailSender(emailConfig, mailReceiverAddress, "", tenantDomain ,userParams);
        try {
            sender.sendEmail();
        } catch (Exception e) {
            String msg = "Email sending is failed for  " + mailReceiverAddress;
            log.error(msg, e);
        }
        return true;

    }

    /**
     * Loads the email sender configuration related to the config file given
     *
     * @param configFile
     * @return
     */
    private EmailVerifierConfig loadEmailSenderConfiguration(String configFile) {
        String configFilePath = CarbonUtils.getCarbonConfigDirPath() + File.separator + "email" +
                File.separator + configFile;
        //return EmailSenderConfiguration.loadEmailSenderConfiguration(configFilePath);
        return Util.loadeMailVerificationConfig(configFilePath);
    }

}

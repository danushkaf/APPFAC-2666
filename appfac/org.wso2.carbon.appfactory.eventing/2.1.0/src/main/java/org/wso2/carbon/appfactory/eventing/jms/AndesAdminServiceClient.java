package org.wso2.carbon.appfactory.eventing.jms;

import org.apache.axis2.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.andes.stub.AndesAdminServiceStub;
import org.wso2.carbon.appfactory.common.AppFactoryException;

import java.rmi.RemoteException;


public class AndesAdminServiceClient {
    private static Log log = LogFactory.getLog(AndesAdminServiceClient.class);
    private AndesAdminServiceStub stub;

    public AndesAdminServiceClient(String remoteServiceURL) throws AppFactoryException {
        if (remoteServiceURL == null || remoteServiceURL.isEmpty()) {
            throw new AppFactoryException("Remote service URL can not be null.");
        }

        if (!remoteServiceURL.endsWith("/")) {
            remoteServiceURL += "/";
        }
        remoteServiceURL += "AndesAdminService";

        try {
            stub = new AndesAdminServiceStub(remoteServiceURL);
        } catch (AxisFault axisFault) {
            log.error("Failed to create AndesAdminServiceStub stub.", axisFault);
            throw new AppFactoryException("Failed to create AndesAdminServiceStub stub.", axisFault);
        }
    }

    public String getAccessToken() throws AppFactoryException {
        try {
            return getStub().getAccessKey();
        } catch (RemoteException e) {
            String error = "Failed to get access token from AndesAdminService.";
            log.error(error, e);
            throw new AppFactoryException(error, e);
        }
    }

	public AndesAdminServiceStub getStub() {
		return stub;
	}
}

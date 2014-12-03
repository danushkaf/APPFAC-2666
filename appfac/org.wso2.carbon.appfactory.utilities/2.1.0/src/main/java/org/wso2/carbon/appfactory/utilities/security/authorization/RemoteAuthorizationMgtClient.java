package org.wso2.carbon.appfactory.utilities.security.authorization;


import org.apache.axis2.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.appfactory.common.AppFactoryException;
import org.wso2.carbon.um.ws.api.stub.RemoteAuthorizationManagerServiceStub;
import org.wso2.carbon.um.ws.api.stub.UserStoreExceptionException;

import java.rmi.RemoteException;

/**
 * This a RemoteAuthorizationManagerService client. Mutual authentication is used to authenticate with backend service.
 */
public class RemoteAuthorizationMgtClient {
    private static Log log = LogFactory.getLog(RemoteAuthorizationMgtClient.class);
    private RemoteAuthorizationManagerServiceStub stub;

    public RemoteAuthorizationMgtClient(String remoteServiceURL) throws AppFactoryException {
        if (remoteServiceURL == null || remoteServiceURL.isEmpty()) {
            throw new AppFactoryException("Remote service URL can not be null.");
        }

        if (!remoteServiceURL.endsWith("/")) {
            remoteServiceURL += "/";
        }
        remoteServiceURL += "RemoteAuthorizationManagerService";

        try {
            stub = new RemoteAuthorizationManagerServiceStub(remoteServiceURL);
        } catch (AxisFault axisFault) {
            log.error("Failed to create authorizationMgt stub.", axisFault);
            throw new AppFactoryException("Failed to create authorizationMgt stub.", axisFault);
        }
    }

    public void authorizeRole(String role, String resource, String action) throws UserStoreExceptionException, AppFactoryException {
        try {
            getStub().authorizeRole(role, resource, action);
        } catch (RemoteException e) {
            log.error("Failed to authorize role:" + role + " ,resource:" + resource + " ,action:" + action, e);
            throw new AppFactoryException("Failed to authorize role:" + role + " ,resource:" + resource + " ,action:" + action, e);
        } finally {
            if (getStub() != null) {
                try {
                    getStub()._getServiceClient().cleanupTransport();
                    getStub().cleanup();
                } catch (AxisFault axisFault) {
                    // ignore exception, just log and move on
                    log.warn("Failed to cleanup stub.", axisFault);
                }
            }
        }
    }

    public void denyRole(String role, String resource, String action) throws UserStoreExceptionException, AppFactoryException {
        try {
            getStub().denyRole(role, resource, action);
        } catch (RemoteException e) {
            log.error("Failed to deny role:" + role + " ,resource:" + resource + " ,action:" + action, e);
            throw new AppFactoryException("Failed to deny role:" + role + " ,resource:" + resource + " ,action:" + action, e);
        } finally {
            if (getStub() != null) {
                try {
                    getStub()._getServiceClient().cleanupTransport();
                    getStub().cleanup();
                } catch (AxisFault axisFault) {
                    // ignore exception, just log and move on
                    log.warn("Failed to cleanup stub.", axisFault);
                }
            }
        }
    }

    public void clearAllRoleAuthorization(String role) throws UserStoreExceptionException, AppFactoryException {
        try {
            getStub().clearAllRoleAuthorization(role);
        } catch (RemoteException e) {
            log.error("Failed to clear authorizations for role:" + role , e);
            throw new AppFactoryException("Failed to deny role:" + role , e);
        } finally {
            if (getStub() != null) {
                try {
                    getStub()._getServiceClient().cleanupTransport();
                    getStub().cleanup();
                } catch (AxisFault axisFault) {
                    // ignore exception, just log and move on
                    log.warn("Failed to cleanup stub.", axisFault);
                }
            }
        }
    }

    public boolean isRoleAuthorized(String role, String resource, String action) throws UserStoreExceptionException, AppFactoryException {
        boolean isAuthorized = false;
        try {
            isAuthorized = getStub().isRoleAuthorized(role, resource, action);
        } catch (RemoteException e) {
            log.error("Failed to clear authorizations for role:" + role , e);
            throw new AppFactoryException("Failed to deny role:" + role , e);
        } finally {
            if (getStub() != null) {
                try {
                    getStub()._getServiceClient().cleanupTransport();
                    getStub().cleanup();
                } catch (AxisFault axisFault) {
                    // ignore exception, just log and move on
                    log.warn("Failed to cleanup stub.", axisFault);
                }
            }
        }
        return isAuthorized;
    }
    public boolean isUserAuthorized(String user, String resource, String action) throws UserStoreExceptionException, AppFactoryException {
        boolean isAuthorized = false;
        try {
            isAuthorized = getStub().isUserAuthorized(user, resource, action);
        } catch (RemoteException e) {
            log.error("Failed to clear authorizations for user:" + user , e);
            throw new AppFactoryException("Failed to deny user:" + user , e);
        } finally {
            if (getStub() != null) {
                try {
                    getStub()._getServiceClient().cleanupTransport();
                    getStub().cleanup();
                } catch (AxisFault axisFault) {
                    // ignore exception, just log and move on
                    log.warn("Failed to cleanup stub.", axisFault);
                }
            }
        }
        return isAuthorized;
    }

	public RemoteAuthorizationManagerServiceStub getStub() {
		return stub;
	}
}

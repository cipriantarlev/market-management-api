/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.core;

import org.springframework.security.core.Authentication;

/**
 * Interface used to get details about authenticated user.
 */
public interface AuthenticationInformation {
    /**
     * Method used to get authentication details about current user.
     *
     * @return the user's authentication details
     */
    Authentication getAuthentication();
}

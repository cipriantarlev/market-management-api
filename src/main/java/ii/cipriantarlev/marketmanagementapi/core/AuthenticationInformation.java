package ii.cipriantarlev.marketmanagementapi.core;

import org.springframework.security.core.Authentication;

public interface AuthenticationInformation {
    Authentication getAuthentication();
}

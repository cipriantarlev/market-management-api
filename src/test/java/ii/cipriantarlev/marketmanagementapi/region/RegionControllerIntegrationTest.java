package ii.cipriantarlev.marketmanagementapi.region;

import ii.cipriantarlev.marketmanagementapi.config.IntegrationTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static ii.cipriantarlev.marketmanagementapi.utils.Constants.*;

class RegionControllerIntegrationTest extends IntegrationTestConfiguration {

    @Test
    void getRegions() throws Exception {
        HttpEntity<List<RegionDTO>> entity = new HttpEntity<>(null, new HttpHeaders());

        var response = getRestTemplateWithAuth()
                .exchange(createUri(REGIONS_ROOT_PATH),
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<List<RegionDTO>>() {
                        });

        assertEquals(OK, response.getStatusCodeValue());
        assertEquals(3, Objects.requireNonNull(response.getBody()).size());
    }
}
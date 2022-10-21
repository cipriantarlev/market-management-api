package ii.cipriantarlev.marketmanagementapi.config;

import ii.cipriantarlev.marketmanagementapi.MarketManagementApiApplication;
import ii.cipriantarlev.marketmanagementapi.barcode.BarcodeDTO;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;

@SpringBootTest(classes = MarketManagementApiApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
        locations = "classpath:application-integration-test.properties")
@Sql({ "classpath:schema.sql", "classpath:data.sql" })
public class IntegrationTestConfiguration {

    @LocalServerPort
    protected int port;

    @Autowired
    protected TestRestTemplate restTemplate;

    protected static final int OK = 200;
    protected static final long GOOD_ID = 1L;
    protected static final long BAD_ID = 99L;

    protected static final String GOOD_ID_PATH = "/1";
    protected static final String BAD_ID_PATH = "/99";

    protected static final UUID GOOD_UUID = UUID.fromString("5fd843b1-f782-4134-bf94-112b3790ec7f");
    protected static final String GOOD_UUID_PATH = "/5fd843b1-f782-4134-bf94-112b3790ec7f";
    protected static final UUID BAD_UUID = UUID.fromString("5fd843b1-0000-0000-0000-112b3790ec7f");
    protected static final String BAD_UUID_PATH = "/5fd843b1-0000-0000-0000-112b3790ec7f";

    protected final String createUri(String path) {
        return "http://localhost:" + port + "/api" + path;
    }

    protected final TestRestTemplate getRestTemplateWithAuth() {
        return restTemplate.withBasicAuth("ciprian", "!@#Tpro1");
    }

    protected Executable throwExceptionWhenGet(String url) {
        return () -> getRestTemplateWithAuth()
                .getForEntity(createUri(url), BarcodeDTO.class);
    }

    protected <T> Executable throwException(HttpEntity<T> entity, String url, HttpMethod httpMethod) {
        return () -> getRestTemplateWithAuth()
                .exchange(createUri(url),
                        httpMethod,
                        entity,
                        entity.getClass());
    }
}

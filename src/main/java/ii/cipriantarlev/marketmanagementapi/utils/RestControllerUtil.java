package ii.cipriantarlev.marketmanagementapi.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Component
public class RestControllerUtil {

	public HttpHeaders setHttpsHeaderLocation(String path, Long id) {
		var headers = new HttpHeaders();
		headers.setLocation(UriComponentsBuilder.fromPath(path)
				.buildAndExpand(id).toUri());
		return headers;
	}

	public HttpHeaders setHttpsHeaderLocation(String path, UUID id) {
		var headers = new HttpHeaders();
		headers.setLocation(UriComponentsBuilder.fromPath(path)
				.buildAndExpand(id).toUri());
		return headers;
	}
}

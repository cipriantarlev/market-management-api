package ii.cipriantarlev.marketmanagementapi.util;

import static ii.cipriantarlev.marketmanagementapi.util.Constants.ID_PATH;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RestControllerUtil {

	public HttpHeaders setHttpsHeaderLocation(String rootPath, Long id) {
		var headers = new HttpHeaders();
		headers.setLocation(UriComponentsBuilder.fromPath(rootPath.concat(ID_PATH))
				.buildAndExpand(id).toUri());
		return headers;
	}
}

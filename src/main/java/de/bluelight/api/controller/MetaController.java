package de.bluelight.api.controller;

import de.bluelight.api.util.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/metpom.xmla",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class MetaController {

    @GetMapping
    public ResponseEntity<String> getApiInfo() {
        Map<String, Object> apiInfo = Map.of(
                "info", Map.of("version", "1.0.0", "name", "Bluelight API"),
                "license", Map.of("name", "Apache 2.0", "url", "http://www.apache.org/licenses/LICENSE-2.0.html"),
                "contact", Map.of("github", "https://github.com/bluelight-org"));
        return ResponseEntity.ok(new ResponseBuilder(HttpStatus.OK).data(apiInfo));
    }

}

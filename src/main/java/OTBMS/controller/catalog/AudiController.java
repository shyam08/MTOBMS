package OTBMS.controller.catalog;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import OTBMS.dto.catalog.AudiResponse;
import OTBMS.dto.catalog.AudiUpsertRequest;
import OTBMS.service.catalog.AudiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalog-svc/v1/audies")
@Slf4j
@CrossOrigin
@Tag(name = "catalog-service")
public class AudiController {
    @Autowired
    private AudiService audiService;

    @Operation(summary = "add new audi")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "added successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AudiResponse> save(@Valid @RequestBody AudiUpsertRequest request) throws Exception {
        log.info("add new audi request body={}", request);
        AudiResponse response = audiService.saveAudi(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "get audi using id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AudiResponse> get(@PathVariable Integer id) throws Exception {
        log.info("get audi request id={}", id);
        AudiResponse response = audiService.getAudi(id);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "update existing audi")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces =
            {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AudiResponse> update(@PathVariable Integer id, @RequestBody AudiUpsertRequest request) throws Exception {
        log.info("update audi request id={}, body={}", id, request);
        AudiResponse response = audiService.updateAudi(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "delete audi using id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity delete(@PathVariable Integer id) throws Exception {
        log.info("delete audi request id={}", id);
        audiService.deleteAudi(id);
        return ResponseEntity.ok(null);
    }
}

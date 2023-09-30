package otbms.controller.catalog;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import otbms.dto.catalog.ShowResponse;
import otbms.dto.catalog.ShowUpsertRequest;
import otbms.service.catalog.ShowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalog-svc/v1/shows")
@Slf4j
@CrossOrigin
@Tag(name = "catalog-service")
public class ShowsController {

    @Autowired
    private ShowsService showsService;

    @Operation(summary = "add new show")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "New show saved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ShowResponse> save(@Valid @RequestBody ShowUpsertRequest request) throws Exception {
        log.info("add new show request body={}", request);
        ShowResponse response = showsService.saveShow(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "get city using id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ShowResponse> get(@PathVariable Integer id) throws Exception {
        log.info("get show request id={}", id);
        ShowResponse response = showsService.getShow(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "update existing show")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Show updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces =
            {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ShowResponse> update(@PathVariable Integer id, @RequestBody ShowUpsertRequest request) throws Exception {
        log.info("update show request id={}, body={}", id, request);
        ShowResponse response = showsService.updateShow(id, request);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "delete show using id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity delete(@PathVariable Integer id) throws Exception {
        log.info("delete show request id={}", id);
        showsService.deleteShow(id);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "get free seats count using id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @GetMapping(value = "/{id}/free-seats", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Integer> getFreeSeatsCount(@PathVariable Integer id) throws Exception {
        log.info("get free seats count request id={}", id);
        Integer count = showsService.getFreeSeatsCount(id);
        return ResponseEntity.ok(count);
    }

}

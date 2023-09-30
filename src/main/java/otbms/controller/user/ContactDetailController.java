package otbms.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import otbms.dto.user.ContactResponse;
import otbms.dto.user.UpsertContactRequest;
import otbms.service.user.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-svc/v1/contacts")
@Slf4j
@CrossOrigin
@Tag(name = "user-service")
public class ContactDetailController {

    @Autowired
    private ContactService contactService;
    @Operation(summary = "add new contact")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "New contact saved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ContactResponse> save(@Valid @RequestBody UpsertContactRequest request) throws Exception {
        log.info("add new contact request body={}", request);
        ContactResponse response = contactService.saveContact(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "get contact using id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ContactResponse> get(@PathVariable Integer id) throws Exception {
        log.info("get contact request id={}", id);
        ContactResponse response = contactService.getContact(id);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "update existing contact")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contact updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces =
            {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ContactResponse> update(@PathVariable Integer id,
                                               @Valid @RequestBody UpsertContactRequest request) throws Exception {
        log.info("update contact request id={}, body={}", id, request);
        ContactResponse response = contactService.updateContact(id, request);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "delete contact using id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity delete(@PathVariable Integer id) throws Exception {
        log.info("delete contact request id={}", id);
        contactService.deleteContact(id);
        return ResponseEntity.ok(null);
    }
}

package OTBMS.controller.cart;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import OTBMS.dto.cart.CartUpsertRequest;
import OTBMS.dto.cart.CartUpsertResponse;
import OTBMS.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart-svc/v1/carts")
@Slf4j
@CrossOrigin
@Tag(name = "cart-service")
public class CartController {
    @Autowired
    private CartService cartService;


    @Operation(summary = "create new cart")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "New cart saved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CartUpsertResponse> save(@Valid @RequestBody CartUpsertRequest request) throws Exception {
        log.info("add new cart request body={}", request);
        CartUpsertResponse response = cartService.saveCart(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "update existing cart")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cart updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces =
            {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CartUpsertResponse> update(@PathVariable String id, @RequestBody CartUpsertRequest request) throws Exception {
        log.info("update cart request id={}, body={}", id, request);
        CartUpsertResponse response = cartService.updateCart(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "cart confirmation and lock the seats")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Seats blocked successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @PostMapping(value = "/{id}/confirm", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces =
            {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CartUpsertResponse> confirm(@PathVariable String id,
                                                      @Valid @RequestBody CartUpsertRequest request) throws Exception {
        log.info("confirm cart request id={}, body={}", id, request);
        CartUpsertResponse cartResponse = cartService.confirmCart(id,request);
        return ResponseEntity.ok(cartResponse);
    }

    @Operation(summary = "get cart using id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CartUpsertResponse> get(@PathVariable String id) throws Exception {
        log.info("get cart request id={}", id);
        CartUpsertResponse cartResponse = cartService.getCart(id);
        return ResponseEntity.ok(cartResponse);
    }


    @Operation(summary = "delete cart using id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Error occurred while processing")
    })
    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity delete(@PathVariable String id) throws Exception {
        log.info("delete cart request id={}", id);
        cartService.deleteCart(id);
        return ResponseEntity.ok(null);
    }

}

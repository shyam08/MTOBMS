package otbms.service.cart;

import otbms.dto.cart.CartUpsertRequest;
import otbms.dto.cart.CartUpsertResponse;

public interface CartService {
    CartUpsertResponse saveCart(CartUpsertRequest request) throws Exception;

    CartUpsertResponse updateCart(String id, CartUpsertRequest request) throws Exception;

    CartUpsertResponse getCart(String id) throws Exception;

    void deleteCart(String id) throws Exception;

    CartUpsertResponse confirmCart(String id, CartUpsertRequest request) throws Exception;
}

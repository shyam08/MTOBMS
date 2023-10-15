package otbms.controller.cart;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import otbms.dao.cart.CartBookingCacheRepository;
import otbms.dto.cart.*;
import otbms.utils.Common;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CartControllerTestIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CartBookingCacheRepository cartBookingCache;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveCart() throws Exception {
        CartUpsertRequest request = new CartUpsertRequest("user1001", 1, 1, 1, 1, Sets.newHashSet(23, 24));
        ResultActions response = mockMvc.perform(post("/cart-svc/v1/carts/")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)));
        MvcResult result = response.andDo(print()).andExpect(status().isCreated()).andReturn();
        CartUpsertResponse cartUpsertResponse = objectMapper.readValue(result.getResponse().getContentAsString(), CartUpsertResponse.class);
        assertNotNull(cartUpsertResponse);
        assertNotNull(cartUpsertResponse.getCartId());
        cartBookingCache.deleteBookingDetail(cartUpsertResponse.getCartId());
    }

    @Test
    void updateCart() throws Exception {
        CartDetail cartDetail = new CartDetail(Common.getCartId(), "user1001", 1, 1, 1, 1, Sets.newHashSet(22, 23), new CartBillDetail(),
                new UserDetail());
        cartBookingCache.saveBookingDetail(cartDetail.getCartId(), cartDetail);
        Set<Integer> newSeats = Sets.newHashSet(23, 24);
        CartUpsertRequest request = new CartUpsertRequest("user1001", 1, 1, 1, 1, newSeats);
        ResultActions response = mockMvc.perform(put("/cart-svc/v1/carts/{id}", cartDetail.getCartId())
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)));
        MvcResult result = response.andDo(print()).andExpect(status().isOk()).andReturn();
        CartUpsertResponse cartUpsertResponse = objectMapper.readValue(result.getResponse().getContentAsString(), CartUpsertResponse.class);
        assertNotNull(cartUpsertResponse);
        assertNotNull(cartUpsertResponse.getCartId());
        assertTrue(cartUpsertResponse.getSeats().equals(newSeats));
        cartBookingCache.deleteBookingDetail(cartDetail.getCartId());
    }

    @Test
    void confirmCart() throws Exception {
        CartDetail cartDetail = new CartDetail(Common.getCartId(), "user1001", 1, 1, 1, 1, Sets.newHashSet(22, 23), new CartBillDetail(),
                new UserDetail());
        cartBookingCache.saveBookingDetail(cartDetail.getCartId(), cartDetail);
        CartUpsertRequest request = new CartUpsertRequest("user1001", cartDetail.getCityId(), cartDetail.getTheatreId(), cartDetail.getAudiId(),
                cartDetail.getShowId(), cartDetail.getSeats());
        ResultActions response = mockMvc.perform(post("/cart-svc/v1/carts/{id}/confirm", cartDetail.getCartId())
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)));
        MvcResult result = response.andDo(print()).andExpect(status().isOk()).andReturn();
        CartUpsertResponse cartUpsertResponse = objectMapper.readValue(result.getResponse().getContentAsString(), CartUpsertResponse.class);
        assertNotNull(cartUpsertResponse);
        assertNotNull(cartUpsertResponse.getCartId());
        cartBookingCache.deleteBookingDetail(cartDetail.getCartId());
    }

    @Test
    void getCart() throws Exception {
        CartDetail cartDetail = new CartDetail(Common.getCartId(), "user1001", 1, 1, 1, 1, Sets.newHashSet(22, 23), new CartBillDetail(),
                new UserDetail());
        cartBookingCache.saveBookingDetail(cartDetail.getCartId(), cartDetail);
        ResultActions response = mockMvc.perform(get("/cart-svc/v1/carts/{id}", cartDetail.getCartId()));
        MvcResult result = response.andDo(print()).andExpect(status().isOk()).andReturn();
        CartUpsertResponse cartUpsertResponse = objectMapper.readValue(result.getResponse().getContentAsString(), CartUpsertResponse.class);
        assertNotNull(cartUpsertResponse);
        assertNotNull(cartUpsertResponse.getCartId());
        assertTrue(cartUpsertResponse.getCartId().equals(cartDetail.getCartId()));
        cartBookingCache.deleteBookingDetail(cartUpsertResponse.getCartId());
    }

    @Test
    void deleteCart() throws Exception {
        CartDetail cartDetail = new CartDetail(Common.getCartId(), "user1001", 1, 1, 1, 1, Sets.newHashSet(22, 23), new CartBillDetail(),
                new UserDetail());
        cartBookingCache.saveBookingDetail(cartDetail.getCartId(), cartDetail);
        ResultActions response = mockMvc.perform(delete("/cart-svc/v1/carts/{id}", cartDetail.getCartId()));
        response.andDo(print()).andExpect(status().isOk());
    }
}
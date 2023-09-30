package otbms.service.user;

import otbms.dto.user.ContactResponse;
import otbms.dto.user.UpsertContactRequest;

public interface ContactService {
    ContactResponse saveContact(UpsertContactRequest request) throws Exception;

    ContactResponse getContact(Integer id) throws Exception;

    ContactResponse updateContact(Integer id, UpsertContactRequest request) throws Exception;;

    void deleteContact(Integer id) throws Exception;;
}

package otbms.service.user;

import jakarta.persistence.EntityNotFoundException;
import otbms.dao.user.Customer;
import otbms.dao.user.CustomerRepository;
import otbms.dto.user.ContactResponse;
import otbms.dto.user.UpsertContactRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class ContactServiceImpl implements ContactService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ContactResponse saveContact(UpsertContactRequest request) throws Exception {
        Customer customer = customerRepository.save(new Customer(null, request.getPhoneNo(), request.getEmail(),
                request.getFirstName(),
                request.getLastName(), null, null));
        ContactResponse  contactResponse= new ContactResponse(customer.getId(), customer.getPhoneNumber(), customer.getEmail(),
                customer.getFirstName(), customer.getLastName());

        return contactResponse;
    }

    @Override
    public ContactResponse getContact(Integer id) throws Exception {
        Optional<Customer> existing = customerRepository.findById(id);
        if (existing.isEmpty())
            throw new EntityNotFoundException("Customer not found");
        Customer customer = existing.get();
        ContactResponse contactResponse = new ContactResponse(customer.getId(),
                                            customer.getPhoneNumber(), customer.getEmail(),
                                            customer.getFirstName(), customer.getLastName());
        return contactResponse;
    }

    @Override
    public ContactResponse updateContact(Integer id, UpsertContactRequest request) throws Exception {
        Optional<Customer> existing = customerRepository.findById(id);
        if (existing.isEmpty())
            throw new EntityNotFoundException("Customer not found");
        Customer customer = existing.get();
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNo());
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        Customer updatedCustomer = customerRepository.save(customer);
        ContactResponse response = new ContactResponse(updatedCustomer.getId(), updatedCustomer.getPhoneNumber(), updatedCustomer.getEmail(),
                updatedCustomer.getFirstName(), updatedCustomer.getLastName());

        return response;
    }

    @Override
    public void deleteContact(Integer id) throws Exception {
        customerRepository.deleteById(id);
    }
}

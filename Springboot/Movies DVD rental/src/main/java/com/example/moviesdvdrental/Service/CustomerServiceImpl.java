package com.example.moviesdvdrental.Service;

import com.example.moviesdvdrental.DTOs.CustomerDTO.CustomerRegisterDTO;
import com.example.moviesdvdrental.DTOs.CustomerDTO.CustomerUpdateDTO;
import com.example.moviesdvdrental.Exceptions.EntityAlreadyExistsException;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.Repositories.CustomerRepository;
import com.example.moviesdvdrental.Repositories.UserRepository;
import com.example.moviesdvdrental.mapper.Mapper;
import com.example.moviesdvdrental.model.Customer;
import com.example.moviesdvdrental.model.Director;
import com.example.moviesdvdrental.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private final CustomerRepository customerRepository;  //injected as autowired by annotation @RequiredArgsConstructor
    private final UserRepository userRepository;
    @Override
    @Transactional
    public Customer registerCustomer(CustomerRegisterDTO dto) throws EntityAlreadyExistsException {
        Customer customer;
        User user;
//        String password = new BCryptPasswordEncoder().encode(dto.getPassword());

        try {
//            customer = new Customer(dto.getFirstname(), dto.getLastname());
//            user = User.NEW_CUSTOMER(dto.getUsername(), dto.getPassword());
            customer = Mapper.extractCustomerFromRegisterCustomerDTO(dto);
            user = Mapper.extractUserFromRegisterCustomerDTO(dto);
            user.setPassword(dto.getPassword());
            Optional<User> returnedUser = userRepository.findByUsername(dto.getUsername());
            if (returnedUser.isPresent()) throw new EntityAlreadyExistsException(Customer.class, dto.getUsername());
            customer.addUser(user);
            customerRepository.save(customer);
            log.info("Customer-member added");
        } catch (EntityAlreadyExistsException e) {
            log.error("Problem in create user person" + e.getMessage());
            throw e;
        }
        return customer;
    }

    @Override
    public List<Customer> getCustomersByLastname(String lastname) throws EntityNotFoundException {
        List<Customer> customers = new ArrayList<>();
        try {
            customers = customerRepository.findByLastnameStartingWith(lastname);
            if (customers.isEmpty()) throw new EntityNotFoundException(Director.class,0L);
            log.info("Customers with lastname starting with "+ lastname +" were found");
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return customers;
    }

    @Override
    public Customer getCustomerById(Long id) throws EntityNotFoundException {
        Customer customer;
        try {
//                customer = customerRepository.findCustomerById(id);
//                if(customer==null)throw new EntityNotFoundException(Customer.class,id);
            customer = customerRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(Customer.class,id));
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return customer;
    }

    @Override
    public Customer updateCustomer(CustomerUpdateDTO customerDTO) throws EntityNotFoundException {
        Customer customer;
        Customer customerToUpdate;
        try {
            customer = customerRepository.findById(customerDTO.getId()).orElseThrow(()-> new EntityNotFoundException(Customer.class, customerDTO.getId()));
            customerToUpdate = Mapper.mapToCustomer(customerDTO);
            customerToUpdate.addUser(customer.getUser());
            customer = customerRepository.save(customerToUpdate);
            log.info("Customer with id: "+ customer.getId()+ " was updated");
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return customer;
    }

    @Override
    @Transactional
    public Customer deleteCustomer(Long id) throws EntityNotFoundException {
        Customer customer;
        try {
            customer =  customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Customer.class,id));
            customerRepository.deleteById(id);
            log.info("Customer user deleted");
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return customer;
    }
}

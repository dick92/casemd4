package com.example.case4.Service;

import com.example.case4.Repository.AccountRepository;
import com.example.case4.Repository.CustomerRepository;
import com.example.case4.model.Account;
import com.example.case4.model.Customer;
import com.example.case4.model.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private AccountRepository accountRepo;
    @Autowired private CustomerRepository customerRepo;
    @Autowired private PasswordEncoder passwordEncoder;

    public void registerCustomer(RegisterRequest request) {
        if (accountRepo.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        Account account = new Account();
        account.setUsername(request.getUsername());
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setRole(Account.Role.CUSTOMER);

        Customer customer = new Customer();
        customer.setFullName(request.getFullName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAccount(account);

        account.setCustomer(customer);

        accountRepo.save(account);
    }
    public Account authenticate(String username, String password) {
        Optional<Account> optional = accountRepo.findByUsername(username);
        if (optional.isPresent()) {
            Account account = optional.get();
            if (passwordEncoder.matches(password, account.getPassword())) {
                return account;
            }
        }
        return null;
    }

}

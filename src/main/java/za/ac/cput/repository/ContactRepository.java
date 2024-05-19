package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Contact;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
    Contact findByZipCode(String zipCode);
}

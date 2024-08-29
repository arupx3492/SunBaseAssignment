package com.sunbase.UserManagement.repository;

import com.sunbase.UserManagement.entities.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {


//    Optional<Customer> findById(String uuid);

//    @Query(value = "SELECT c FROM Customer c WHERE "
//            + "(:searchBy = 'first_name' AND LOWER(c.first_name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) OR "
//            + "(:searchBy = 'city' AND LOWER(c.city) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) OR "
//            + "(:searchBy = 'email' AND LOWER(c.email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) OR "
//            + "(:searchBy = 'phone' AND LOWER(c.phone) LIKE LOWER(CONCAT('%', :searchTerm, '%')))",nativeQuery = true)
//    Page<Customer> search(@Param("searchBy") String searchBy, @Param("searchTerm") String searchTerm, Pageable pageable);

//    @Query("SELECT c FROM Customer c WHERE "
//            + "(:searchTerm = '' OR (:searchBy = 'firstName' AND LOWER(c.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')))) OR "
//            + "(:searchTerm = '' OR (:searchBy = 'city' AND LOWER(c.city) LIKE LOWER(CONCAT('%', :searchTerm, '%')))) OR "
//            + "(:searchTerm = '' OR (:searchBy = 'email' AND LOWER(c.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')))) OR "
//            + "(:searchTerm = '' OR (:searchBy = 'phone' AND LOWER(c.phone) LIKE LOWER(CONCAT('%', :searchTerm, '%'))))")
//    Page<Customer> search(@Param("searchBy") String searchBy, @Param("searchTerm") String searchTerm, Pageable pageable);


    @Query(value = "SELECT * FROM Customer WHERE LOWER(first_name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))",
            countQuery = "SELECT count(*) FROM Customer WHERE LOWER(first_name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))",
            nativeQuery = true)
    Page<Customer> findByFirstName(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query(value = "SELECT * FROM Customer WHERE LOWER(city) LIKE LOWER(CONCAT('%', :searchTerm, '%'))",
            countQuery = "SELECT count(*) FROM Customer WHERE LOWER(city) LIKE LOWER(CONCAT('%', :searchTerm, '%'))",
            nativeQuery = true)
    Page<Customer> findByCity(@Param("searchTerm") String searchTerm, Pageable pageable);


    @Query(value = "SELECT * FROM Customer WHERE LOWER(email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))",
            countQuery = "SELECT count(*) FROM Customer WHERE LOWER(email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))",
            nativeQuery = true)
    Page<Customer> findByEmail(@Param("searchTerm") String searchTerm, Pageable pageable);


    @Query(value = "SELECT * FROM Customer WHERE LOWER(phone) LIKE LOWER(CONCAT('%', :searchTerm, '%'))",
            countQuery = "SELECT count(*) FROM Customer WHERE LOWER(phone) LIKE LOWER(CONCAT('%', :searchTerm, '%'))",
            nativeQuery = true)
    Page<Customer> findByPhone(@Param("searchTerm") String searchTerm, Pageable pageable);

}

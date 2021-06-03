package kata.bam.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kata.bam.entities.Client;

@Repository
public interface ClientDao extends JpaRepository<Client, Integer> {

}
package be.vinci.investor.repository;

import be.vinci.investor.models.InvestorData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepository extends CrudRepository<InvestorData, String> {
}
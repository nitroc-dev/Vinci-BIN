package be.vinci.investor;

import be.vinci.investor.models.InvestorData;
import be.vinci.investor.models.InvestorWithPassword;
import be.vinci.investor.models.Position;
import be.vinci.investor.models.UnsafeCredentials;
import be.vinci.investor.repository.AuthsProxy;
import be.vinci.investor.repository.InvestorRepository;
import be.vinci.investor.repository.WalletProxy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestorService {

    private final InvestorRepository repository;
    private final AuthsProxy authentificationProxy;
    private final WalletProxy walletProxy;

    public InvestorService(InvestorRepository repository, AuthsProxy authentificationProxy, WalletProxy walletProxy) {
        this.repository = repository;
        this.authentificationProxy = authentificationProxy;
        this.walletProxy = walletProxy;
    }


    /**
     * @param username
     * @return InvestorData
     *  - null if the investor is not found
     *  - InvestorData if the investor is found
     */
    public InvestorData getInvestor(String username){
        return repository.findById(username).orElse(null);
    }

    /**
     * @param investor
     * @return InvestorDataWithPassword
     *  - null if the investor is not found
     *  - InvestorDataWithPassword if the investor is found
     */
    public InvestorData createOneInvestor(InvestorWithPassword investor){
        InvestorData investordata = investor.getInvestorData();
        UnsafeCredentials cred = new UnsafeCredentials();
        cred.setUsername(investordata.getUsername());
        cred.setPassword(investor.getPassword());
        authentificationProxy.createOne(investordata.getUsername(), cred);
        return repository.save(investordata);
    }

    /**
     * @param investorData
     * @return boolean
     *  - false if the investor is not found
     *  - true if the investor is found and updated
     */
    public boolean updateInvestor(InvestorData investorData){
        if(!repository.existsById(investorData.getUsername())) return false;
        InvestorData getInvestor = repository.findById(investorData.getUsername()).orElse(null);
        if(getInvestor == null) return false;
        getInvestor.setFirstname(investorData.getFirstname());
        getInvestor.setLastname(investorData.getLastname());
        getInvestor.setBirthdate(investorData.getBirthdate());
        getInvestor.setEmail(investorData.getEmail());
        repository.save(getInvestor);
        return true;
    }

    /**
     * @param username
     * @return boolean
     *  - false if the investor is not found
     *  - true if the investor is found and deleted
     */
    public boolean deleteOneInvestor(String username){
        if(!repository.existsById(username)) return false;
        List<Position> positions = walletProxy.getOpenPositions(username);
        for (Position position : positions) {
            if(position.getQuantity() != 0) return false;
        }
        authentificationProxy.deleteCredentials(username);
        repository.deleteById(username);
        return true;
    }

}
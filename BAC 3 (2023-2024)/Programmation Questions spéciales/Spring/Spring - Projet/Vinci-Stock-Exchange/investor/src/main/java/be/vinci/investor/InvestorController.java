package be.vinci.investor;

import be.vinci.investor.models.InvestorData;
import be.vinci.investor.models.InvestorWithPassword;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InvestorController {
    private final InvestorService service;

    public InvestorController(InvestorService service) {
        this.service = service;
    }

    /**
     * @param username
     * @return ResponseEntity<InvestorData>
     *  - HttpStatus.NOT_FOUND if the investor is not found
     *  - HttpStatus.OK if the investor is found
     */
    @GetMapping("/investor/{username}")
    public ResponseEntity<InvestorData> getInvestor(@PathVariable String username){
        InvestorData investor = service.getInvestor(username);
        if(investor == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(investor, HttpStatus.OK);
    }

    /**
     * @param investorWithPassword
     * @return ResponseEntity<InvestorDataWithPassword>
     * - HttpStatus.BAD_REQUEST if the investorData is null or any of its fields are null
     * - HttpStatus.ALREADY_EXISTS if the investor already exists
     * - HttpStatus.CREATED if the investor is successfully created
     */
    @PostMapping("/investor/{username}")
    public ResponseEntity<InvestorData> createInvestor(@PathVariable String username, @RequestBody InvestorWithPassword investorWithPassword){
        if(investorWithPassword.getInvestorData().getUsername() == null || investorWithPassword.getInvestorData().getEmail() == null || investorWithPassword.getInvestorData().getFirstname() == null || investorWithPassword.getInvestorData().getLastname() == null || investorWithPassword.getInvestorData().getBirthdate() == null || investorWithPassword.getPassword() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(service.getInvestor(username) != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        InvestorData savedInvestor = service.createOneInvestor(investorWithPassword);
        return new ResponseEntity<>(savedInvestor, HttpStatus.CREATED);
    }

    /**
     * @param username
     * @param investorData
     * @return ResponseEntity<InvestorData>
     * - HttpStatus.BAD_REQUEST if the investorData is null or any of its fields are null
     * - HttpStatus.NOT_FOUND if the investor is not found
     * - HttpStatus.OK if the investor is successfully updated
     */
    @PutMapping("/investor/{username}")
    public ResponseEntity<InvestorData> updateInvestor(@PathVariable String username, @RequestBody InvestorData investorData){
        if(investorData==null||investorData.getUsername()==null||investorData.getEmail()==null||investorData.getFirstname()==null||investorData.getLastname()==null||investorData.getBirthdate()==null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(service.getInvestor(username)==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        service.updateInvestor(investorData);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @param username
     * @return ResponseEntity<InvestorData>
     * - HttpStatus.NOT_FOUND if the investor is not found
     * - HttpStatus.OK if the investor is successfully deleted
     */
    @DeleteMapping("/investor/{username}")
    public ResponseEntity<InvestorData> deleteInvestor(@PathVariable String username){
        System.out.println("delete investor");
        if(service.getInvestor(username)==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(!service.deleteOneInvestor(username))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

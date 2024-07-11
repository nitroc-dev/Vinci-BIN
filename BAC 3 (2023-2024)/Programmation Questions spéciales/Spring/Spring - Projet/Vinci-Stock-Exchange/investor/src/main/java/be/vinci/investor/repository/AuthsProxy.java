package be.vinci.investor.repository;


import be.vinci.investor.models.UnsafeCredentials;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Repository
@FeignClient(name = "authentication")
public interface AuthsProxy {



    @PostMapping("/authentication/{pseudo}")
     void createOne(@PathVariable String pseudo, @RequestBody UnsafeCredentials credentials);

    @DeleteMapping("/authentication/{pseudo}")
    void deleteCredentials(@PathVariable String pseudo);
}

package be.vinci.execution.data;

import be.vinci.execution.model.PatchOrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
@FeignClient("order")
public interface OrderProxy {
    @PatchMapping("/order/{guid}")
    void updateOrderFilled(@PathVariable String guid, @RequestBody PatchOrderDTO filled);
}

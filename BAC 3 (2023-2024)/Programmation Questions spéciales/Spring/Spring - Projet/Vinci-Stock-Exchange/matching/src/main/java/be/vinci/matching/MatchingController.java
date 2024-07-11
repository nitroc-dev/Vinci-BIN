package be.vinci.matching;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchingController {

  private final MatchingService matchingService;

  public MatchingController(MatchingService matchingService) {
    this.matchingService = matchingService;
  }

  @PostMapping("/trigger/{ticker}")
  public ResponseEntity<Void> trigger(@PathVariable String ticker) {
    matchingService.trigger(ticker);

    return null;
  }

}

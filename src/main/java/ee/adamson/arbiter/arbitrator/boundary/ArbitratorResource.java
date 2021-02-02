package ee.adamson.arbiter.arbitrator.boundary;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/arbitrator")
public class ArbitratorResource {
    private final ArbitratorAppService arbitratorAppService;

    public ArbitratorResource(ArbitratorAppService arbitratorAppService) {
        this.arbitratorAppService = arbitratorAppService;
    }

    @PostMapping("/negotiate")
    public Proposal negotiate(@RequestBody Request request) {
        return arbitratorAppService.negotiate(request);
    }

}

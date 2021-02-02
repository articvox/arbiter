package ee.adamson.arbiter.arbitrator.boundary;

import org.springframework.stereotype.Service;

@Service
public class ArbitratorAppService {

    public Proposal negotiate(Request request) {
        return Proposal.builder()
                .build();
    }

}

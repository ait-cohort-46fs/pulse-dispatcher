package ait.cohort46.pulse.service;

import ait.cohort46.pulse.dto.PulseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class PulseService {
    @Value("${pulse.min}")
    private int minPulse;
    @Value("${pulse.max}")
    private int maxPulse;

    @Bean
    public Consumer<PulseDto> dispatchData() {
        return p -> {
            if (p.getPayload() >= minPulse && p.getPayload() <= maxPulse) {
                long delay = System.currentTimeMillis() - p.getTimestamp();
                System.out.println("delay: " + delay + ", id: " + p.getId() + ", pulse: " + p.getPayload());
            }
        };
    }
}

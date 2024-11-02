package ait.cohort46.pulse.service;

import ait.cohort46.pulse.dto.PulseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class PulseService {
    private final StreamBridge streamBridge;
    @Value("${pulse.min}")
    private int minPulse;
    @Value("${pulse.max}")
    private int maxPulse;

    @Bean
    public Consumer<PulseDto> dispatchData() {
        return p -> {
            if (p.getPayload() < minPulse) {
                streamBridge.send("lowpulse-out-0", p);
                return;
            }
            if (p.getPayload() > maxPulse) {
                streamBridge.send("highpulse-out-0", p);
                return;
            }
            long delay = System.currentTimeMillis() - p.getTimestamp();
            System.out.println("delay: " + delay + ", id: " + p.getId() + ", pulse: " + p.getPayload());
        };
    }
}

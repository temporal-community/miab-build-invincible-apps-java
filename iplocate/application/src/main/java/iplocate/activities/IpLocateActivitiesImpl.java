package iplocate.activities;

import io.temporal.spring.boot.ActivityImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@ActivityImpl(workers = "iplocate-worker")
public class IpLocateActivitiesImpl implements IpLocateActivities {
    private final WebClient webClient;

    public IpLocateActivitiesImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public String getIp() {
        return webClient.get()
                .uri("https://icanhazip.com")
                .retrieve()
                .bodyToMono(String.class)
                .block()
                .trim();
    }

    @Override
    public String getLocationInfo(String ip) {
        IpApiResponse response = webClient.get()
                .uri("http://ip-api.com/json/" + ip)
                .retrieve()
                .bodyToMono(IpApiResponse.class)
                .block();

        return String.format("%s, %s, %s", 
            response.getCity(), 
            response.getRegionName(), 
            response.getCountry());
    }

    private static class IpApiResponse {
        private String city;
        private String regionName;
        private String country;

        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
        public String getRegionName() { return regionName; }
        public void setRegionName(String regionName) { this.regionName = regionName; }
        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }
    }
} 
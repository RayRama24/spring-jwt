package com.springjwt.services.job;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JobService {
    private static final String API_URL = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";

    private RestTemplate restTemplate;

    public JobService() {
        this.restTemplate = new RestTemplate();
    }

    public ResponseEntity<Object> getJobList() {
        return restTemplate.getForEntity(API_URL, Object.class);
    }

    public ResponseEntity<Object> getJobDetail(String id) {
        String apiUrl = API_URL + "/" + id;
        return restTemplate.getForEntity(apiUrl, Object.class);
    }

    public ResponseEntity<byte[]> downloadJobList() {
        return restTemplate.getForEntity(API_URL, byte[].class);
    }
}


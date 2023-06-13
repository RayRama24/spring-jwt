package com.springjwt.controllers;

import com.springjwt.services.job.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("/jobs")
    public ResponseEntity<Object> getJobList() {
        return jobService.getJobList();
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Object> getJobDetail(@PathVariable("id") String id) {
        return jobService.getJobDetail(id);
    }

    @GetMapping("/download")
    public void downloadJobList(HttpServletResponse response) throws IOException {
        ResponseEntity<byte[]> responseEntity = jobService.downloadJobList();
        byte[] jobListData = responseEntity.getBody();

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=jobs.csv");
        response.getOutputStream().write(jobListData);
        response.flushBuffer();
    }
}



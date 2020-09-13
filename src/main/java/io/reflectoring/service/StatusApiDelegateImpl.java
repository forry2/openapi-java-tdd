package io.reflectoring.service;

import io.reflectoring.api.StatusApiDelegate;
import io.reflectoring.model.Problem;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class StatusApiDelegateImpl implements StatusApiDelegate {
    @Override
    public ResponseEntity<Problem> apiGetStatus() {
        Problem problem = new Problem();
        problem.detail(RandomStringUtils.randomAlphabetic(10));
        problem.status(ThreadLocalRandom.current().nextInt(0, 11));
        problem.title(RandomStringUtils.randomAlphabetic(3));
        problem.instance(URI.create("http://instance"));
        problem.type(URI.create("http://type"));
        return ResponseEntity.ok(problem);
    }
}

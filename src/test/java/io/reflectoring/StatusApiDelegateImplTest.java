package io.reflectoring;

import io.reflectoring.service.StatusApiDelegateImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions.*;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StatusApiDelegateImplTest {
    @Test
    public void tddTest() {
        StatusApiDelegateImpl x = new StatusApiDelegateImpl();
        ResponseEntity responseEntity = x.apiGetStatus();
        assertTrue(responseEntity.getBody() != null && x.apiGetStatus().getBody().getStatus() <= 10);
    }
}

package io.reflectoring.service;

import io.reflectoring.api.ItemsApiDelegate;
import io.reflectoring.model.Item;
import io.reflectoring.model.ItemStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

@Service
public class ItemsApiDelegateImpl implements ItemsApiDelegate {
    @Autowired
    ItemsList itemsList;

    @Override
    public ResponseEntity<ItemStatus> apiPostItems(Object body) {
        Item item =
            Item
                .builder()
                .timestamp((new Date()).toInstant().atOffset(ZoneOffset.UTC))
                .item(body)
                .id(UUID.randomUUID())
                .build();
        try {
            itemsList.add(item);
            return ResponseEntity.ok(ItemStatus.builder().id(item.getId()).status(ItemStatus.StatusEnum.SUCCESS).url("TBD").build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ItemStatus.builder().status(ItemStatus.StatusEnum.FAILURE).build());
        }
    }
}
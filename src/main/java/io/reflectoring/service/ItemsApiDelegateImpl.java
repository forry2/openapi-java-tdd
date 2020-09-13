package io.reflectoring.service;

import io.reflectoring.api.ItemsApiDelegate;
import io.reflectoring.model.InlineResponse200;
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
    ItemsInmemoryDatabaseMap itemsInmemoryDatabaseMap;

    @Override
    public ResponseEntity<ItemStatus> apiPostItems(Object body) {
        Item item = new Item();
        item.timestamp((new Date()).toInstant().atOffset(ZoneOffset.UTC));
        item.item(body);
        item.id(UUID.randomUUID());
        try {
            itemsInmemoryDatabaseMap.add(item);
            ItemStatus itemStatus = new ItemStatus();
            itemStatus.id(item.getId());
            itemStatus.status(ItemStatus.StatusEnum.SUCCESS);
            itemStatus.url("TBD");
            return ResponseEntity.ok(itemStatus);
        } catch (Exception e) {
            ItemStatus itemStatus = new ItemStatus();
            itemStatus.status(ItemStatus.StatusEnum.FAILURE);
            return ResponseEntity.badRequest().body(itemStatus);
        }
    }

    @Override
    public ResponseEntity<InlineResponse200> apiGetItems(Integer limit, UUID cursor) {

        InlineResponse200 inlineResponse200 = new InlineResponse200();
        inlineResponse200.offset(cursor);
        inlineResponse200.limit(limit);
        inlineResponse200.items(itemsInmemoryDatabaseMap.getItemsFromCursor(limit, cursor));
        return ResponseEntity.ok(inlineResponse200);
    }
}

package com.rednavis.demo.kafka.messageconsumer.repository;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.rednavis.demo.kafka.messageconsumer.model.ItemDbo;

@Repository
public interface ItemRepository extends MongoRepository<ItemDbo, UUID> {
}

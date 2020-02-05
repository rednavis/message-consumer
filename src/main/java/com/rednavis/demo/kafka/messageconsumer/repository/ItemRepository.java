package com.rednavis.demo.kafka.messageconsumer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.rednavis.demo.kafka.messageconsumer.model.ItemDbo;

@Repository
public interface ItemRepository extends MongoRepository<ItemDbo, String> {
}

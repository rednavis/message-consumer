package com.rednavis.demo.kafka.messageconsumer.service;

import static com.rednavis.demo.kafka.messageconsumer.model.ItemDbo.from;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.stereotype.Service;
import com.rednavis.demo.kafka.messageconsumer.dto.ItemDto;
import com.rednavis.demo.kafka.messageconsumer.repository.ItemRepository;
import lombok.AllArgsConstructor;

@Service
@EnableBinding(Sink.class)
@MessageEndpoint
@AllArgsConstructor
public class MessageStorageService {

  private final ItemRepository itemRepository;

  @StreamListener(Sink.INPUT)
  public void storeItem(ItemDto item) {

    itemRepository.save(from(item));
  }
}

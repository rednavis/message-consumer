package com.rednavis.demo.kafka.messageconsumer.service;

import static com.rednavis.demo.kafka.messageconsumer.model.ItemDbo.from;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import com.rednavis.demo.kafka.messageconsumer.dto.ItemRQDto;
import com.rednavis.demo.kafka.messageconsumer.dto.ItemRSDto;
import com.rednavis.demo.kafka.messageconsumer.model.ItemDbo;
import com.rednavis.demo.kafka.messageconsumer.repository.ItemRepository;
import lombok.AllArgsConstructor;

@Service
@EnableBinding(Processor.class)
@MessageEndpoint
@AllArgsConstructor
public class MessageStorageService {

  private final ItemRepository itemRepository;

  @StreamListener(Processor.INPUT)
  @SendTo(Processor.OUTPUT)
  public ItemRSDto storeItem(ItemRQDto item) throws ExecutionException, InterruptedException {
    CompletableFuture<ItemDbo> itemDboCompletableFuture = CompletableFuture.supplyAsync(() -> itemRepository.save(from(item)));
    return ItemRSDto.from(itemDboCompletableFuture.get());
  }
}

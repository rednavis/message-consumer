package com.rednavis.demo.kafka.messageconsumer.model;

import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.rednavis.demo.kafka.messageconsumer.dto.ItemDto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Document(collection = "items")
public class ItemDbo {

  @Id
  private String id;
  private String name;
  private int amount;
  private double price;
  private String description;
  private boolean isPublic;

  public static ItemDbo from(ItemDto item) {
    return ItemDbo.builder()
        .id(UUID.randomUUID().toString())
        .name(item.getName())
        .amount(item.getAmount())
        .description(item.getDescription())
        .price(item.getPrice())
        .isPublic(item.isPublic())
        .build();
  }
}

package com.rednavis.demo.kafka.messageconsumer.dto;


import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.rednavis.demo.kafka.messageconsumer.model.ItemDbo;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemRSDto implements Serializable {

  private String id;
  private String name;
  private String updatedDescription;
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime created;

  public static ItemRSDto from(ItemDbo itemDbo) {
    return ItemRSDto.builder()
        .id(itemDbo.getId())
        .name(itemDbo.getName())
        .updatedDescription(itemDbo.getDescription())
        .created(LocalDateTime.now())
        .build();
  }
}

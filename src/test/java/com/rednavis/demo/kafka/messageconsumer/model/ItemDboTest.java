package com.rednavis.demo.kafka.messageconsumer.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import com.rednavis.demo.kafka.messageconsumer.dto.ItemDto;

class ItemDboTest {

  private static final int AMOUNT = 2;
  private static final String DESCRIPTION = "description";
  private static final String NAME = "name";
  private static final double PRICE = 3.14;

  @Test
  void testFrom() {
    ItemDto itemDto = new ItemDto();
    itemDto.setAmount(AMOUNT);
    itemDto.setDescription(DESCRIPTION);
    itemDto.setName(NAME);
    itemDto.setPrice(PRICE);
    itemDto.setPublic(false);

    ItemDbo from = ItemDbo.from(itemDto);

    assertThat(from.getId()).isNotNull();
    assertThat(from.getAmount()).isEqualTo(AMOUNT);
    assertThat(from.getDescription()).isEqualTo(DESCRIPTION);
    assertThat(from.getName()).isEqualTo(NAME);
    assertThat(from.getPrice()).isEqualTo(PRICE);
    assertThat(from.isPublic()).isFalse();
  }
}

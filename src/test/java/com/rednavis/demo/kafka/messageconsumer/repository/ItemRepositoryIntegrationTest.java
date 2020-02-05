package com.rednavis.demo.kafka.messageconsumer.repository;

import static com.rednavis.demo.kafka.messageconsumer.model.ItemDbo.from;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.rednavis.demo.kafka.messageconsumer.dto.ItemDto;
import com.rednavis.demo.kafka.messageconsumer.model.ItemDbo;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class ItemRepositoryIntegrationTest {

  private static final int AMOUNT = 2;
  private static final String DESCRIPTION = "description";
  private static final String NAME = "name";
  private static final double PRICE = 3.14;

  @Autowired
  private ItemRepository itemRepository;

  @Test
  public void testStorage() {
    ItemDto itemDto = new ItemDto();
    itemDto.setAmount(AMOUNT);
    itemDto.setDescription(DESCRIPTION);
    itemDto.setName(NAME);
    itemDto.setPrice(PRICE);
    itemDto.setPublic(false);

    itemRepository.save(from(itemDto));

    List<ItemDbo> all = itemRepository.findAll();
    assertThat(all.size()).isEqualTo(1);
    ItemDbo itemDbo = all.get(0);
    assertThat(itemDbo.getId()).isNotNull();
    assertThat(itemDbo.getName()).isEqualTo(NAME);
    assertThat(itemDbo.getPrice()).isEqualTo(PRICE);
    assertThat(itemDbo.getAmount()).isEqualTo(AMOUNT);
    assertThat(itemDbo.isPublic()).isFalse();
  }

}

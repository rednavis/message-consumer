package com.rednavis.demo.kafka.messageconsumer.repository;

import static com.rednavis.demo.kafka.messageconsumer.model.ItemDbo.from;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.rednavis.demo.kafka.messageconsumer.dto.ItemRQDto;
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
    ItemRQDto itemRQDto = new ItemRQDto();
    itemRQDto.setAmount(AMOUNT);
    itemRQDto.setDescription(DESCRIPTION);
    itemRQDto.setName(NAME);
    itemRQDto.setPrice(PRICE);
    itemRQDto.setPublic(false);

    itemRepository.save(from(itemRQDto));

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

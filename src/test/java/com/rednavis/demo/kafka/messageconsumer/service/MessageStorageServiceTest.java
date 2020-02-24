package com.rednavis.demo.kafka.messageconsumer.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import com.rednavis.demo.kafka.messageconsumer.dto.ItemRQDto;
import com.rednavis.demo.kafka.messageconsumer.model.ItemDbo;
import com.rednavis.demo.kafka.messageconsumer.repository.ItemRepository;

class MessageStorageServiceTest {

  @Mock
  private ItemRepository itemRepositoryMock;

  private MessageStorageService uut;

  @BeforeEach
  void setUp() {
    initMocks(this);
    uut = new MessageStorageService(itemRepositoryMock);

    when(itemRepositoryMock.save(any(ItemDbo.class))).thenReturn(ItemDbo.builder().build());
  }

  @Test
  public void testItemStore() throws Exception{
    uut.storeItem(new ItemRQDto());

    verify(itemRepositoryMock, atMostOnce()).save(any(ItemDbo.class));
  }


}

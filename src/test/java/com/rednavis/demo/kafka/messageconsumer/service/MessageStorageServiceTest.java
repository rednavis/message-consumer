package com.rednavis.demo.kafka.messageconsumer.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import com.rednavis.demo.kafka.messageconsumer.dto.ItemDto;
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
  }

  @Test
  public void testItemStore() {
    uut.storeItem(new ItemDto());

    verify(itemRepositoryMock, atMostOnce()).save(any(ItemDbo.class));
  }


}

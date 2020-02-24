package com.rednavis.demo.kafka.messageconsumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.springframework.cloud.stream.test.matcher.MessageQueueMatcher.receivesPayloadThat;

import java.util.concurrent.BlockingQueue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rednavis.demo.kafka.messageconsumer.dto.ItemRQDto;
import com.rednavis.demo.kafka.messageconsumer.dto.ItemRSDto;
import com.rednavis.demo.kafka.messageconsumer.model.ItemDbo;

@SpringBootTest
@EmbeddedKafka
class MessageConsumerApplicationTests {

  @Autowired
  private Processor processor;
  @Autowired
  private MessageCollector messageCollector;

  @Test
  void receiveItemValid() throws Exception {
    ItemRQDto validItem = new ItemRQDto();
    validItem.setName("Not empty");
    validItem.setAmount(15);
    validItem.setPrice(10D);
    validItem.setPublic(false);
    validItem.setDescription("\nUncommon item");

    processor.input().send(MessageBuilder.withPayload(validItem).build());

    BlockingQueue<Message<?>> messages = messageCollector.forChannel(processor.output());

    assertThat(messages, receivesPayloadThat(containsString("created")));
  }

}

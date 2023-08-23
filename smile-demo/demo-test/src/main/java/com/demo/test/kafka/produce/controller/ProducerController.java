package com.demo.test.kafka.produce.controller;

import cn.hutool.core.thread.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author :xiezhi
 * @date : 2023/8/9
 */
@RestController
@RequestMapping("/kafka/producer")
@Slf4j
public class ProducerController {


    @Autowired
    private KafkaTemplate<Object, Object> kafka;


    private ExecutorService kafkaPool = new ThreadPoolExecutor(1, 20,
            10, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(1024),
            new NamedThreadFactory("Kafka-producer",false),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    @RequestMapping("/send")
    public void register() {
        while(true){
            try {
                Thread.sleep(990);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            kafkaPool.execute(new Runnable() {
                //long millis = System.currentTimeMillis();
                @Override
                public void run() {
                    for(int i = 1; i<=8000; i++){
                        byte[] data = new byte[]{35, 35, 2, -2, 56, 56, 54, 53, 54, 56, 56, 54, 68, 48, 48, 90, 56, 88,
                                50, 68, 81, 1, 1, 110, 23, 7, 28, 15, 56, 44, 1, 1, 3, 1, 0, 0, 0, 17, -10, -104,
                                14, 26, 39, 46, 90, 1, 0, 2, -54, 0, 0, 2, 1, 1, 4, 76, 78, 32, 78, 32, 81, 13,
                                -34, 39, 26, 5, 0, 7, 16, -13, -40, 2, 53, 118, -124, 6, 1, 8, 15, -77, 3, 49,
                                15, -103, 1, 4, 82, 1, 1, 79, 8, 1, 1, -1, -1, -1, -1, 0, 90, 0, 1, 90, 15, -78,
                                15, -80, 15, -79, 15, -78, 15, -78, 15, -78, 15, -86, 15, -77, 15, -78, 15, -78,
                                15, -78, 15, -79, 15, -80, 15, -80, 15, -81, 15, -79, 15, -79, 15, -80, 15, -86,
                                15, -80, 15, -79, 15, -81, 15, -84, 15, -78, 15, -78, 15, -79, 15, -79, 15, -79,
                                15, -78, 15, -79, 15, -77, 15, -87, 15, -83, 15, -81, 15, -82, 15, -81, 15, -79,
                                15, -82, 15, -92, 15, -79, 15, -81, 15, -81, 15, -82, 15, -83, 15, -85, 15, -84,
                                15, -81, 15, -80, 15, -102, 15, -80, 15, -80, 15, -81, 15, -80, 15, -86, 15, -83,
                                15, -83, 15, -84, 15, -82, 15, -82, 15, -83, 15, -90, 15, -84, 15, -82, 15, -93, 15,
                                -84, 15, -85, 15, -80, 15, -82, 15, -82, 15, -80, 15, -81, 15, -80, 15, -80, 15, -79,
                                15, -83, 15, -84, 15, -82, 15, -85, 15, -82, 15, -82, 15, -82, 15, -94, 15, -86, 15, -83,
                                15, -89, 15, -89, 15, -88, 15, -87, 15, -88, 15, -90, 9, 1, 1, 0, 32, 79, 81, 81, 82, 80, 80,
                                82, 82, 82, 80, 81, 82, 82, 82, 80, 80, 82, 82, 82, 80, 80, 82, 82, 82, 80, 79, 80, 81, 82, 80,
                                80, 82, 10, 0, 60, -1, -1, 49, 48, 52, 48, 56, -1, 1, 0, 0, 9, -21, 1, 1, 12, 2, 19, 1, 0, 30,
                                -1, 0, 0, 0, 36, 1, 1, 1, -1, 1, 54, -100, 16, 13, 20, 50, 48, 50, 46, 49, 48, 52, 46, 49, 48,
                                49, 46, 51, 49, 58, 49, 56, 48, 57, 51, 0, 0, -1, -1, 12, 0, 5, 1, 0, 0, 0, 48, -108};

                        ProducerRecord<Object,Object> producerRecord = new ProducerRecord<>("test_p20r3_1", data);
                        //写入header
                        producerRecord.headers().add("vin","LA9CKJ3R8N5LM6534".getBytes(Charset.forName("GBK")));
                        //
                        kafka.send(producerRecord);
                        //kafka.send("test_p20r3", data);
                        if(i % 8000 == 0){
                            log.info(Thread.currentThread().getName()+"--------------------"+i);
                        }
                    }
                    /*long end = System.currentTimeMillis();
                    System.out.println("time====="+(end-millis));*/
                }
            });
        }
    }


}

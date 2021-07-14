package org.ths.fruit;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class FruitDeserializer extends ObjectMapperDeserializer<Fruit> {
    public FruitDeserializer() {
        // pass the class to the parent.
        super(Fruit.class);
    }
}
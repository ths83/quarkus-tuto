package org.ths83;

import io.smallrye.mutiny.Uni;

import javax.inject.Singleton;

@Singleton
public class ReactiveHelloService extends MutinyGreeterGrpc.GreeterImplBase {

    @Override
    public Uni<HelloReply> sayHello(HelloRequest request) {
        return Uni.createFrom().item(() ->
                HelloReply.newBuilder().setMessage("Hello " + request.getName()).build()
        );
    }
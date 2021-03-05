package io.vertx.example.grpc.helloworld;

import io.grpc.ManagedChannel;
import io.vertx.core.AbstractVerticle;
import io.vertx.grpc.VertxChannelBuilder;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class Client extends AbstractVerticle {

    public static void main(String[] args) {
        Runner.runExample(Client.class);
    }

    @Override
    public void start() {

        //Although the GCP port in Cloud Run is 8080 it doesnt allow unsecure connections! (it must be port 443)
        ManagedChannel channel = VertxChannelBuilder
                .forAddress(vertx, "grpc-vc3fln3ncq-uc.a.run.app", 443)
                .build();

        VertxGreeterGrpc.GreeterVertxStub stub = VertxGreeterGrpc.newVertxStub(channel);

        HelloRequest request = HelloRequest.newBuilder().setName("Lucas").build();
        stub.sayHello(request).onComplete(asyncResponse -> {
            if (asyncResponse.succeeded()) {
                System.out.println("Succeeded " + asyncResponse.result().getMessage());

                channel.shutdown();

            } else {
                asyncResponse.cause().printStackTrace();
            }
        });
    }
}
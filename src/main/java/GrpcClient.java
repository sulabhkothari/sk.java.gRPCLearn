import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import sk.java.greeter.GreetingServiceOuterClass;
import sk.java.greeter.GreetingServiceGrpc;

import java.util.ArrayList;
import java.util.List;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext(true)
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub
                = GreetingServiceGrpc.newBlockingStub(channel);

        ArrayList<String> arr = new ArrayList<String>(1);
        arr.add("JM");
        GreetingServiceOuterClass.HelloResponse res = stub.greeting(GreetingServiceOuterClass.HelloRequest.newBuilder()
                .addAllHobbies(arr).build());

        System.out.println(res.getGreeting());
        channel.shutdown();
    }
}

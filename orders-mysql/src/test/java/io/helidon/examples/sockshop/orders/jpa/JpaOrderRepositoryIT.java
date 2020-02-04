package io.helidon.examples.sockshop.orders.jpa;

import io.helidon.examples.sockshop.orders.OrderRepository;
import io.helidon.examples.sockshop.orders.OrderRepositoryTest;
import io.helidon.microprofile.server.Server;

import org.junit.jupiter.api.AfterAll;

/**
 * Integration tests for {@link io.helidon.examples.sockshop.orders.jpa.JpaOrderRepository}.
 */
public class JpaOrderRepositoryIT extends OrderRepositoryTest {

    /**
     * Starting server on ephemeral port in order to be able to get the
     * fully configured repository from the CDI container.
     */
    private static final Server SERVER = Server.builder().port(0).build().start();

    @AfterAll
    static void stopServer() {
        SERVER.stop();
    }

    @Override
    protected OrderRepository getOrderRepository() {
        return SERVER.cdiContainer().select(OrderRepository.class).get();
    }

    @Override
    protected void clearRepository(OrderRepository orders) {
        ((JpaOrderRepository) orders).clear();
    }
}

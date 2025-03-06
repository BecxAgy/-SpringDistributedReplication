package ifba.dist.LeaderElection.insfrastructure.queue.rabbit;

import ifba.dist.LeaderElection.domain.queue.RemoveBind;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqRemoveBindFallback implements RemoveBind {

    @Override
    public void unbindQueue(String queueName) {
        // Não faz nada, pois só o líder deve remover filas
    }
}

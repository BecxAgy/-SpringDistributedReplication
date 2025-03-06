package ifba.dist.LeaderElection.shared.config;

import ifba.dist.LeaderElection.application.service.CheckIfReplicaIsAliveService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class JobConfig {

    @Bean
    @Primary
    @ConditionalOnProperty(name = "instance.id", havingValue = "1")
    public CheckIfReplicaIsAliveService checkIfReplicaIsAliveService() {
        return new CheckIfReplicaIsAliveService();
    }

}
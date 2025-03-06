package ifba.dist.LeaderElection.application.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ifba.dist.LeaderElection.domain.queue.RemoveBind;
import ifba.dist.LeaderElection.insfrastructure.queue.rabbit.RabbitMqFailsNotifiyMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CheckIfReplicaIsAliveService {

  private final Map<String, Long> replicaStatus = new ConcurrentHashMap<>();

  @Autowired
  private RabbitMqFailsNotifiyMessageProducer failsNotifyMessageProducer;

  @Autowired
  private RemoveBind removeBindPort;


  public void updateReplicaStatus(String replicaId, Long miliseconds) {
    replicaStatus.put(replicaId, System.currentTimeMillis());
  }

  @Scheduled(fixedRate = 30000)
  public void cleanupExpiredReplicas() {
    long currentTime = System.currentTimeMillis();
    replicaStatus.entrySet().removeIf(entry -> {
      if ((currentTime - entry.getValue()) > 30000) {
        removeBindPort.unbindQueue("instance-" + entry.getKey());
        removeBindPort.unbindQueue("notify-instance-" + entry.getKey());
        failsNotifyMessageProducer.notifyFail(entry.getKey());
        System.out.println("The replica :" + entry.getKey() + " has been removed by leader.");
        return true;
      }
      return false;
    });
  }
}
package ifba.dist.LeaderElection.application.service;

import ifba.dist.LeaderElection.domain.queue.HeartBeatProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class SendHeartBeatService {

  @Autowired
  private HeartBeatProducer beatProducer;

  @Value("${instance.id}")
  private String instaceId;

  @Scheduled(fixedRate = 10000)
  public void sendHeartbeat() {
    System.out.println("Sending heartbeat " + instaceId);
    beatProducer.sendHeartbeat(instaceId);
  }

}
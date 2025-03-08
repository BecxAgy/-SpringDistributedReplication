# 📜 Documentação: Replicação Distribuída com RabbitMQ e Spring Boot

## 📌 Visão Geral

Este projeto implementa um **mecanismo de replicação distribuída** utilizando **RabbitMQ** para garantir a consistência entre bancos de dados PostgreSQL distribuídos. 

A abordagem adotada segue o modelo **Líder e Réplicas**, onde:
- O **Líder** recebe operações de escrita e publica os comandos SQL no RabbitMQ.
- As **Réplica(s)** escutam as mensagens e aplicam as mudanças nos seus respectivos bancos.

---

## 📂 Estrutura do Código

### 📌 `RabbitMqConfiguration.java`
Este arquivo configura as filas e exchanges para comunicação via RabbitMQ.

- **Filas**
  - `heart-beat-queue`: Monitora o status do líder.
  - `instance.queue.name`: Fila para comandos SQL replicados.
  - `instance.queue.notify`: Notificações sobre falhas.

- **Exchanges**
  - `my_fanout_exchange_sql_command`: Distribui comandos SQL para réplicas.
  - `my_fanout_exchange_notify_fail`: Informa sobre falhas.


## 🛠️ Teste no **Insomnia**

📌 **Requisição (heartbeat do líder)**
```
POST http://localhost:8080/command
```
📌 **Body**
```json
{
  "sql": "SELECT * FROM users"
}
```
📌 **O que acontece?**
- O comando SQL é enviado ao **RabbitMQ**.
- Todas as réplicas aplicam o `INSERT` no banco.



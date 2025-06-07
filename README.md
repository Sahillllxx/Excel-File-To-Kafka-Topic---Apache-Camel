# Excel File To Kafka Topic - Apache Camel (Spring Boot)

Welcome to **Excel File To Kafka Topic - Apache Camel**!  
This project leverages **Spring Boot** and [Apache Camel](https://camel.apache.org/) to automate reading Excel files and publishing their contents to a Kafka topic.

---

## 🖥 Tech Stack

- **Java 8+**
- **Spring Boot** – Application configuration and lifecycle
- **Apache Camel** – Integration framework, file routing, and processing
- **Apache POI** – Reading and parsing Excel files (.xlsx, .xls)
- **Apache Kafka** – Distributed event streaming/message broker
- **Maven** – Build and dependency management

---

## 📚 Main Libraries & Frameworks

- `spring-boot-starter`
- `camel-spring-boot-starter`
- `camel-kafka`
- `camel-file`
- `org.apache.poi:poi` and `org.apache.poi:poi-ooxml` (for Excel support)
- `spring-kafka`
- `slf4j` / `logback-classic` (logging)

---

## 🚀 Features

- Parse Excel files automatically using Camel routes
- Seamless integration with Apache Kafka
- Built with Spring Boot for easy configuration and deployment
- Configurable file source and Kafka topic
- Error handling, logging, and monitoring

---

## 📦 Requirements

- Java 8 or above
- Maven
- Apache Kafka & Zookeeper (local or remote)
- Excel files (`.xlsx` or `.xls`)

---

## 🛠️ Setup & Run

### 1. Clone the repository

```bash
git clone https://github.com/Sahillllxx/Excel-File-To-Kafka-Topic---Apache-Camel.git
cd Excel-File-To-Kafka-Topic---Apache-Camel
```

### 2. Configure and Start Kafka

Start Zookeeper and Kafka (if running locally):

```bash
# Start Zookeeper
zookeeper-server-start.sh config/zookeeper.properties

# Start Kafka
kafka-server-start.sh config/server.properties
```

Create a Kafka topic if it doesn’t exist:

```bash
kafka-topics.sh --create --topic excel-data --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

### 3. Configure Application Properties

Edit `src/main/resources/application.properties` as needed:

```properties
# Directory to watch for Excel files
camel.file.directory=input-directory

# Kafka topic name
camel.component.kafka.topic=excel-data

# Kafka bootstrap servers
camel.component.kafka.brokers=localhost:9092
```

### 4. Build and Run the Application

```bash
# Build the JAR with Maven
mvn clean package

# Run the Spring Boot application
java -jar target/*.jar
```
Or run directly via Maven:
```bash
mvn spring-boot:run
```

---

## 📂 Usage

1. Place your Excel files in the configured input directory (default: `input-directory`).
2. The app will automatically parse new files and publish each row as a Kafka message.

### Consume Messages

Use the Kafka console consumer to see output:
```bash
kafka-console-consumer.sh --topic excel-data --bootstrap-server localhost:9092 --from-beginning
```

---

## 🖱️ Interactive FAQ

<details>
  <summary><b>How do I change the source directory or Kafka topic?</b></summary>
  Edit <code>application.properties</code>:
  <ul>
    <li><code>camel.file.directory</code> for the input folder</li>
    <li><code>camel.component.kafka.topic</code> for the Kafka topic</li>
  </ul>
</details>

<details>
  <summary><b>What Excel formats are supported?</b></summary>
  Both <code>.xlsx</code> and <code>.xls</code> files are supported via Apache POI.
</details>

<details>
  <summary><b>How do I debug issues?</b></summary>
  Check application logs for errors. Make sure Kafka and Zookeeper are running and accessible from your app.
</details>

---

## 📚 References

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Apache Camel File Component](https://camel.apache.org/components/latest/file-component.html)
- [Apache Camel Kafka Component](https://camel.apache.org/components/latest/kafka-component.html)
- [Apache POI (Excel support)](https://poi.apache.org/)
- [Apache Kafka Documentation](https://kafka.apache.org/documentation/)

---

> Made with 💻 by [Sahillllxx](https://github.com/Sahillllxx)

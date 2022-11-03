package inrowbackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import inrowbackend.model.StatisticsModel;

@Repository
public interface StatisticsRepository extends MongoRepository<StatisticsModel, String> {
}


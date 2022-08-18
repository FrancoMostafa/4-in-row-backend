package inrowbackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import inrowbackend.model.Game;

@Repository
public interface DemoRepository extends MongoRepository<Game,String> {

}

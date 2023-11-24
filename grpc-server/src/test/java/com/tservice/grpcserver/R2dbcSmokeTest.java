package com.tservice.grpcserver;

import com.tservice.grpcserver.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//@Slf4j
//@DataR2dbcTest
public class R2dbcSmokeTest {
//
//    @Autowired
//    private DatabaseClient databaseClient;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void testDatabaseClientExisted() {
//        assertNotNull(databaseClient);
//    }
//
//    @Test
//    public void testPostRepositoryExisted() {
//        assertNotNull(databaseClient);
//    }
//
//    public Mono<UUID> save() {
//        return this.databaseClient.sql("INSERT INTO  users (user_id, username) VALUES (:userId, :username)")
//                .filter((statement, executeFunction) -> statement.returnGeneratedValues().execute())
//                .bind("userId", UUID.randomUUID())
//                .bind("username", "username")
//                .fetch()
//                .first()
//                .map(r -> (UUID) r.get("user_id"));
//    }
//
//    public Mono<UUID> select() {
//        return this.databaseClient.sql("SELECT (user_id) FROM users")
//                .filter((statement, executeFunction) -> statement.execute())
//                .fetch()
//                .first()
//                .map(r -> (UUID) r.get("user_id"));
//    }
//
//    @Test
//    public void testSave(){
////        Mono<UUID> monoUUID = save();
////        UUID uuid = monoUUID.block();
////        log.info("save uuid: {}", uuid);
//        Mono<UUID> monoUUID1 = select();
//        UUID uuid1 = monoUUID1.block();
//        log.info("select uuid: {}", uuid1);
//    }
//
//    @Test
//    public void testInsertAndQuery() {
//        UUID uuid = UUID.randomUUID();
//        log.info("*******************************************************************************");
//        var x = this.databaseClient.sql("INSERT INTO users (user_id, username) VALUES ($1, $2)")
//                .bind("$1", uuid)
//                .bind("$2", "test").fetch().rowsUpdated().doOnNext(y -> {
//                    log.info("############" + String.valueOf(y));
//                }).subscribe();
//        log.info("x: {}", x);
//        this.databaseClient.sql("SELECT * FROM users").fetch().first().subscribe(row ->{
//            log.info("row: {}", row);
//        });
//        log.info("*******************************************************************************");
//    }
//

}

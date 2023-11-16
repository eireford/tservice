package com.tservice.grpcserver.repositories;

import com.tservice.grpcserver.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;


@Repository
public interface UserRepository extends ReactiveSortingRepository<User, UUID>
{
    Flux<User> findByUsername(String username);

    Flux<User> findByEmail(String email);

    Flux<User> findByFirstName(String firstName);

    Flux<User> findByLastName(String lastName);

    @Async
    Future<List<User>> findDistinctByUserIdInAndContextIdAndUsernameAndEmailAndFirstNameAndLastNameOrderByUsernameAscEmailAscLastNameAsc(@NonNull Collection<UUID> userIds, UUID contextId, String username, String email, String firstName, String lastName, Pageable pageable);
}

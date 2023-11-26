package edu.hw7.task3;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CachingServicesTest {

    @ParameterizedTest
    @MethodSource("cachingServices")
    @DisplayName("Cashing services add and delete methods tests")
    void testAddAndDeleteCachingServicesOperations(CashingService cashingService) {
        addAndDeleteTest(cashingService);
    }

    @ParameterizedTest
    @MethodSource("cachingServices")
    @DisplayName("Cashing services find method tests")
    void testFindCachingServicesOperation(CashingService cashingService) {
        findTest(cashingService);
    }

    void addAndDeleteTest(CashingService cashingService) {
        //Arrange
        Person person1 = new Person(1, "Ivan", "Moscow", "79996662552");
        Person person2 = new Person(2, "Ivan", "Moscow", "79996662552");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(6);

        //Act
        try {
            executorService.execute(() -> {
                cashingService.add(person1);
                countDownLatch.countDown();
            });

            executorService.execute(() -> {
                cashingService.add(person2);
                countDownLatch.countDown();
            });

            executorService.execute(() -> {
                cashingService.delete(1);
                countDownLatch.countDown();
            });

            Future<List<Person>> peopleWithGivenName = executorService.submit(
                () -> {
                    var people = cashingService.findByName("Ivan");
                    countDownLatch.countDown();
                    return people;
                }
            );

            Future<List<Person>> peopleWithGivenAddress = executorService.submit(
                () -> {
                    var people = cashingService.findByAddress("Moscow");
                    countDownLatch.countDown();
                    return people;
                }
            );

            Future<List<Person>> peopleWithGivenPhoneNumber = executorService.submit(
                () -> {
                    var people = cashingService.findByPhone("79996662552");
                    countDownLatch.countDown();
                    return people;
                }
            );

            countDownLatch.await();
            executorService.shutdown();

            //Assert
            assertAll(
                () -> assertThat(peopleWithGivenName.get()).contains(person2),
                () -> assertThat(peopleWithGivenAddress.get()).contains(person2),
                () -> assertThat(peopleWithGivenPhoneNumber.get()).contains(person2),
                () -> assertThat(peopleWithGivenName.get()).doesNotContain(person1),
                () -> assertThat(peopleWithGivenAddress.get()).doesNotContain(person1),
                () -> assertThat(peopleWithGivenPhoneNumber.get()).doesNotContain(person1)
            );

        } catch (InterruptedException ignored) {
        }
    }

    void findTest(CashingService cashingService) {
        //Arrange
        Person person = new Person(1, "Ivan", "Moscow", "79996662552");
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        //Act
        executorService.execute(() -> cashingService.add(person));
        Future<List<Person>> peopleWithGivenName = executorService.submit(
            () -> cashingService.findByName(person.name()));
        Future<List<Person>> peopleWithGivenAddress = executorService.submit(
            () -> cashingService.findByAddress(person.address()));
        Future<List<Person>> peopleWithGivenPhoneNumber = executorService.submit(
            () -> cashingService.findByPhone(person.phoneNumber()));
        executorService.shutdown();

        //Assert
        assertAll(
            () -> assertThat(peopleWithGivenName.get()).contains(person),
            () -> assertThat(peopleWithGivenAddress.get()).contains(person),
            () -> assertThat(peopleWithGivenPhoneNumber.get()).contains(person)
        );
    }

    static Arguments[] cachingServices() {
        return new Arguments[] {
            Arguments.of(new SynchronizedCachingService()),
            Arguments.of(new ReadWriteLockCachingService())
        };
    }
}

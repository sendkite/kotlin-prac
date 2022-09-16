package com.group.libraryapp.service.user


import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.UserStatus
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {
    @AfterEach
    fun cleanUp() {
        userRepository.deleteAll()
    }

    @Test
    fun saveUserTest() {
        // given
        val request = UserCreateRequest("songyeon", null)

        // when
        userService.saveUser(request)

        // then
        val result = userRepository.findAll()
        assertThat(result).hasSize(1)
        assertThat(result[0].name).isEqualTo("songyeon")
        assertThat(result[0].age).isNull()
    }

    @Test
    fun getUserTest() {
        // given
        userRepository.saveAll(listOf(
            User("A", 20, UserStatus.ACTIVE),
            User("B", null, UserStatus.ACTIVE)
        ))

        // when
        val result = userService.getUsers()

        // then
        assertThat(result).hasSize(2)
        assertThat(result).extracting("name").containsExactlyInAnyOrder("A", "B")
    }

    @Test
    fun updateUserNameTest() {
        // given
        val savedUser = userRepository.save(User.fixture())
        val request = UserUpdateRequest(savedUser.id!!, "B")

        // when
        userService.updateUserName(request)

        // then
        val result = userRepository.findAll()
        assertThat(result[0].name).isEqualTo("B")
    }

    @Test
    fun deleteUserTest() {
        // given
        userRepository.save(User.fixture())

        // when
        userService.deleteUser("전송연")

        // then
        userRepository.findAll().isEmpty()
    }

    @Test
    @DisplayName("대출 이력 목록")
    fun getUserLoanHistoryTest1() {
        // given
        userRepository.save(User.fixture())
        // when
        val result = userService.getUserLoanHistories()
        // then
        assertThat(result).hasSize(1)
        assertThat(result[0].name).isEqualTo("전송연")
        assertThat(result[0].books).isEmpty()
    }

    @Test
    @DisplayName("대출하고 반납 상태 획인")
    fun getUserLoanHistoryTest2() {
        // given
        val saveUser = userRepository.save(User.fixture())
        // when
        userLoanHistoryRepository.saveAll(
            listOf(
                UserLoanHistory(saveUser, "책1", UserLoanStatus.LOANED),
                UserLoanHistory(saveUser, "책2", UserLoanStatus.LOANED),
                UserLoanHistory(saveUser, "책3", UserLoanStatus.RETURNED)
            )
        )

        val results = userService.getUserLoanHistories()
        // then
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("전송연")
        assertThat(results[0].books).hasSize(3)
        assertThat(results[0].books).extracting("name").containsExactlyInAnyOrder("책1", "책2", "책3")
        assertThat(results[0].books).extracting("isReturn").containsExactlyInAnyOrder(false, false, true)
    }
}
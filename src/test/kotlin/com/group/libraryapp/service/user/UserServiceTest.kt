package com.group.libraryapp.service.user


import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService
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
            User("A", 20),
            User("B")
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
        val savedUser = userRepository.save(User("A", null))
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
        userRepository.save(User("A"))

        // when
        userService.deleteUser("A")

        // then
        userRepository.findAll().isEmpty()
    }

}
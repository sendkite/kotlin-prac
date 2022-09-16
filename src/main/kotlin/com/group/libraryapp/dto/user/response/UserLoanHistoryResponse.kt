package com.group.libraryapp.dto.user.response

import com.group.libraryapp.domain.user.User

data class UserLoanHistoryResponse(
    val name: String,
    val books: List<BookHistoryResponse>
) {
    companion object {
        fun of(user: User): UserLoanHistoryResponse {
            return UserLoanHistoryResponse(
                name = user.name,
                books = user.userLoanHistories.map {
                    history -> BookHistoryResponse.of(history)
                }
            )
        }
    }
}
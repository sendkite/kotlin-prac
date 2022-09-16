package com.group.libraryapp.dto.user.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus

data class BookHistoryResponse(
    val name: String,
    @get: JsonProperty("isReturn")
    val isReturn: Boolean
) {
    companion object {
        fun of(userLoanHistory: UserLoanHistory): BookHistoryResponse {
            return BookHistoryResponse(
                name = userLoanHistory.bookName,
                isReturn = userLoanHistory.status == UserLoanStatus.RETURNED
            )
        }
    }
}

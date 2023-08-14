package com.alqema.models

import com.alqema.models.constants.AccountType

data class Account(
    val accountNumber: Int, val accountName: String, val accountType: AccountType,
    val address: String, val mobileNumber: Int, val belongsToAccount: Int,
) {
    class Builder {

        private var accountNumber: Int = 1
        private var accountName: String = "default_"
        private var accountType: AccountType = AccountType.Manager
        private var address: String = "default_"
        private var mobileNumber: Int = 123
        private var belongsToAccount: Int = 1


        fun withAccountNumber(accountNumber: Int): Builder {
            this.accountNumber = accountNumber
            return this
        }

        fun withAccountName(accountName: String): Builder {
            this.accountName = accountName
            return this
        }

        fun withAccountType(accountType: AccountType): Builder {
            this.accountType = accountType
            return this
        }

        fun withAddress(address: String): Builder {
            this.address = address
            return this
        }

        fun withMobileNumber(mobileNumber: Int): Builder {
            this.mobileNumber = mobileNumber
            return this
        }

        fun withBelongsToAccount(belongsToAccount: Int): Builder {
            this.belongsToAccount = belongsToAccount
            return this
        }

        fun build(): Account =
            Account(
                accountNumber,
                accountName,
                accountType,
                address,
                mobileNumber,
                belongsToAccount
            )

    }
}

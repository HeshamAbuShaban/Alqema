package com.alqema.database.local_db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alqema.adapters.listeners.AccountContract
import com.alqema.database.local_db.models.constants.account.AccountCurrency
import com.alqema.database.local_db.models.constants.account.AccountDetails
import com.alqema.database.local_db.models.constants.account.AccountNature
import com.alqema.database.local_db.models.constants.account.AccountType

@Entity(tableName = "accounts")
data class Account(
    @PrimaryKey
    @ColumnInfo("Account ID")
    override val accountNumber: Int,
    @ColumnInfo("Account Name")
    override val accountName: String,
    @ColumnInfo("Account Details")
    val accountDetails: String,
    @ColumnInfo("Address")
    val address: String,
    @ColumnInfo("Mobile Number")
    val mobileNumber: String,
    @ColumnInfo("Belongs to account")
    val belongsToAccount: Int,

    //.. Add these
    @ColumnInfo("Account Nature")
    val accountNature: String,
    @ColumnInfo("Account Type")
    val accountType: String,
    @ColumnInfo("Account Currency")
    val accountCurrency: String,

    ) :AccountContract{
    class Builder {

        private var accountNumber: Int = 1
        private var accountName: String = "default_"
        private var accountDetails: String = AccountDetails.Dealers.value
        private var address: String = "default_"
        private var mobileNumber: String = "0"
        private var belongsToAccount: Int = 1


        fun withAccountNumber(accountNumber: Int): Builder {
            this.accountNumber = accountNumber
            return this
        }

        fun withAccountName(accountName: String): Builder {
            this.accountName = accountName
            return this
        }

        fun withAccountDetails(accountDetails: AccountDetails): Builder {
            this.accountDetails = accountDetails.value
            return this
        }

        fun withAddress(address: String): Builder {
            this.address = address
            return this
        }

        fun withMobileNumber(mobileNumber: String): Builder {
            this.mobileNumber = mobileNumber
            return this
        }

        fun withBelongsToAccount(belongsToAccount: Int): Builder {
            this.belongsToAccount = belongsToAccount
            return this
        }

        //.. new fields

        //.. Add these
        private var accountNature: String = AccountNature.DebtorOnly.value
        private var accountType: String = AccountType.BalanceSheet.value
        private var accountCurrency: String = AccountCurrency.NIS.value

        fun withAccountNature(accountNature: AccountNature): Builder {
            this.accountNature = accountNature.value
            return this
        }

        fun withAccountType(accountType: AccountType): Builder {
            this.accountType = accountType.value
            return this
        }

        fun withAccountCurrency(accountCurrency: AccountCurrency): Builder {
            this.accountCurrency = accountCurrency.value
            return this
        }

        fun build(): Account =
            Account(
                accountNumber,
                accountName,
                accountDetails,
                address,
                mobileNumber,
                belongsToAccount,
                accountNature,
                accountType,
                accountCurrency
            )

    }
}

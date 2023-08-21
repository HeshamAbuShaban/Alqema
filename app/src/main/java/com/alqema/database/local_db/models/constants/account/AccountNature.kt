package com.alqema.database.local_db.models.constants.account

enum class AccountNature(val value: String) {
    DebtorOnly("مدين فقط"),
    CreditorOnly("دائن فقط"),
    DebtorAndCreditor("مدين و دائن");
}
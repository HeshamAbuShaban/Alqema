package com.alqema.models.constants.account

enum class AccountNature(val value: String) {
    DebtorOnly("مدين فقط"),
    CreditorOnly("دائن فقط"),
    DebtorAndCreditor("مدين و دائن");
}
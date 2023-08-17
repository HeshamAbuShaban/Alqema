package com.alqema.holder

import com.alqema.controllers.CreationController.Companion.getInstance
import com.alqema.models.constants.account.AccountCurrency
import com.alqema.models.constants.account.AccountDetails
import com.alqema.models.constants.account.AccountNature
import com.alqema.models.constants.account.AccountType

var controller = getInstance()

private fun setup() {
    controller.createAccount(
        1, "hesham", AccountDetails.Customer,
        "Gaza", "1", 1, AccountNature.CreditorOnly, AccountType.Employment, AccountCurrency.NIS
    )
    setup()
}


package com.alqema.database.local_db.models.constants.account

enum class AccountType(val value:String) {

    BalanceSheet("ميزانية عمومية"),
    ProfitAndLoss("أرباح و خسائر"),
    Trade("متاجرة"),
    Employment("تشغيل"),
    RevenuesAndExpenses("ايرادات ومصروفات"),
    ReceivablesOrCreditors("ذمم مدين/ دائن"),
    AgentsAndDistributors("وكلاء وموزعون"),
    Project("مشروع");

}
package com.assistant.cryptoapi.presentation

sealed class Screen(val route: String) {
    object CoinListScreen: Screen("coin_list_screen")
    //object CoinDetailScreen: Screen("coin_detail_screen")
    object CoinDetailScreen: Screen("coin_detail_screen")
    object ExchangeDetailScreen: Screen("exchange_detail_screen")

    object BottomNavigationScreen: Screen("bottom_nav_screen")

    object BottomNavigationHomeScreen: Screen("Рынок")
    object BottomNavigationSearchScreen: Screen("Поиск")
    object BottomNavigationPortfolioScreen: Screen("Портфель")
    object BottomNavigationPortfolioAddNewTransactionScreen: Screen("Добавление новой транзакции")

}

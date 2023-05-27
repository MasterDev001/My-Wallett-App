package com.example.r_usecase.usecases.walletsUseCase

data class WalletsUseCase(
    val addWalletUseC: AddWalletUseC,
    val updateWalletUseC: UpdateWalletUseC,
    val deleteWalletUseC: DeleteWalletUseC,
    val getWalletUseC: GetWalletUseC,
    val getAllWalletsUseC: GetAllWalletsUseC
)
package com.example.r_usecase.usecases.walletsUseCase

data class WalletsUseCase(
    val addWalletUseC: AddWalletUseC,
    val updateWalletUseC: UpdateWalletUseC,
    val deleteWalletUseC: DeleteWalletUseC,
    val isWalletExist: IsWalletExist,
    val getAllWalletsUseC: GetAllWalletsUseC,
    val getWalletOwnerListUseC: GetWalletOwnerListUseC,
    val isCurrencyIdExistsInWalletUseC: IsCurrencyIdExistsInWalletUseC,
    val outComeUseCase: OutComeUseCase,
    val inComeUseCase: InComeUseCase
)
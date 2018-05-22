/*
 * Copyright (c) 2017. Toshi Inc
 *
 *  This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.toshi.manager.store.token

import com.toshi.model.network.token.ERC20Token
import rx.Single

interface TokenStoreInterface {
    fun getAllTokens(networkId: String, walletIndex: Int): Single<List<ERC20Token>>
    fun saveAllTokens(tokens: List<ERC20Token>, networkId: String, walletIndex: Int): Single<List<ERC20Token>>
    fun createListWithPrimaryKeys(tokens: List<ERC20Token>, networkId: String, walletIndex: Int): List<ERC20Token> {
        val tokenListWithPrimaryKey: MutableList<ERC20Token?> = mutableListOf()
        tokens.mapTo(tokenListWithPrimaryKey) { token ->
            token.contractAddress?.let { contractAddress ->
                token.networkId = networkId
                token.walletIndex = walletIndex
                token.setPrimaryKey(contractAddress = contractAddress, networkId = networkId, walletIndex = walletIndex)
            } ?: return@mapTo null
            return@mapTo token
        }
        return tokenListWithPrimaryKey.filterNotNull()
    }
}
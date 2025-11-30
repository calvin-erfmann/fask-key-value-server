package org.calvin.erfmann.theGoodStuff

class PoolManager{
    var pools: MutableMap<String, Pool> = mutableMapOf()

    fun getPool(poolName: String): Pool? {
        return pools[poolName]
    }

    fun createPool(poolName: String): Pool {
        val pool = Pool(poolName)
        pools[poolName] = pool
        return pool
    }

    fun deletePool(poolName: String) {
        pools.remove(poolName)
    }

    fun isPoolPresent(poolName: String): Boolean {
        return pools.containsKey(poolName)
    }

}
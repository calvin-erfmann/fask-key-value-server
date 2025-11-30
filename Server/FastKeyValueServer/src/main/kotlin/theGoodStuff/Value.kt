package org.calvin.erfmann.theGoodStuff

class Value(initKey: String, initValue: String) {

    // Das einfachste Value Element


    var key: String = initKey
    var value: String = initValue
    var lastUpdated: Long = System.currentTimeMillis()
    var createdAt: Long = System.currentTimeMillis()
    var lastAcccessed: Long = System.currentTimeMillis()
    var currentVersion: Long = 0
    var versions = mutableListOf<ValueVersion>()

    fun getValueValue(): String {
        this.lastAcccessed = System.currentTimeMillis()
        return this.value
    }

    fun getCurrentValueVersion(): Long {
        return this.currentVersion
    }


    fun setValueValue(newValue: String) {
        versions.add(ValueVersion(this.value , this.currentVersion))
        this.value = newValue
        this.lastUpdated = System.currentTimeMillis()
        this.currentVersion += 1
    }

    fun getVersion(version: Long): String? {
        val ver = versions.find { it.version == version }
        return ver?.getValueValue()
    }

}
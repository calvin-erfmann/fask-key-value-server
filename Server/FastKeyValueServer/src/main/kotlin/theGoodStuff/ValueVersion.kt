package org.calvin.erfmann.theGoodStuff

class ValueVersion(initValue: String, initVersion: Long) {

    // Eine Version eines Values

    var value: String = initValue
    var lastAcccessed: Long = 1
    var version : Long = initVersion

    fun getValueValue(): String {
        this.lastAcccessed = System.currentTimeMillis()
        return this.value
    }
}
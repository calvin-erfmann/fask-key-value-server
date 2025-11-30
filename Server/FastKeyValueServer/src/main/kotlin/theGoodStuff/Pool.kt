package org.calvin.erfmann.theGoodStuff



class Pool(initName: String) {

    var name: String = initName
    var values: MutableMap<String, Value> = mutableMapOf()

    fun getValueValue(key: String): String? {
        val value = values[key]
        return value?.getValueValue()
    }

    fun setValueValue(key: String, newValue: String) {


        val value = values[key]
        if (value != null) {
            value.setValueValue(newValue)
        } else {
            values[key] = Value(key, newValue)
        }
    }

    fun updateValueValue(key: String, newValue: String) {
        val value = values[key]
        if (value != null) {
            value.setValueValue(newValue)
        }
    }

    fun getVersion(key: String, version: Long): String? {
        val value = values[key]
        return value?.getVersion(version)
    }

    fun deleteValue(key: String) {
        values.remove(key)
    }

    fun isKeyPresent(key: String): Boolean {
        return values.containsKey(key)
    }

}
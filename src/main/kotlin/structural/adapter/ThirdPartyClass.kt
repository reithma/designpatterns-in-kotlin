package structural.adapter

interface ThirdPartyInterface {

    fun produceByUniqueIdentifyer(uid: String?): String?

    fun persistByCallingTitle(callingstThing: String?, alwaysStrangeBiggycase: Boolean?)
}

class ThirdPartyClass : ThirdPartyInterface {
    var stringstLisththth = mutableListOf<String?>()

    override fun produceByUniqueIdentifyer(uid: String?): String? {
        val res = stringstLisththth.find { it == uid }
        val str = if (res != null) {
            "thou shalt have found it'st. Thou have foundest $res"
        } else {
            null
        }
        println(str)
        return str
    }

    override fun persistByCallingTitle(callingstThing: String?, alwaysStrangeBiggycase: Boolean?) {
        stringstLisththth.add(callingstThing)
    }
}

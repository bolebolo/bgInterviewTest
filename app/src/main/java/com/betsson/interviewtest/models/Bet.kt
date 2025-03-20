package com.betsson.interviewtest.models

//This is how I would do it if I know the class doesn't need to be inherited, it's much simpler and more readable.
// It is possible to test if you uncomment it.
//data class Bet(var type: BetType, var sellIn: Int, var odds: Int, var image: String) {
//    override fun toString(): String {
//         return "${type.displayName}, ${sellIn}, $odds"
//    }
//}


//I chose this approach because this class will likely be inherited in the future
open class Bet(var type: BetType, var sellIn: Int, var odds: Int, var image: String) {
    override fun toString(): String {
//        return this.type.displayName + ", " + this.sellIn + ", " + this.odds
        return "${type.displayName}, ${sellIn}, $odds"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Bet) return false

        return type.displayName == other.type.displayName &&
                sellIn == other.sellIn &&
                odds == other.odds &&
                image == other.image
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + sellIn
        result = 31 * result + odds
        result = 31 * result + image.hashCode()
        return result
    }
    fun copy(type: BetType = this.type, sellIn: Int = this.sellIn, odds: Int = this.odds, image: String = this.image): Bet {
        return Bet(type, sellIn, odds, image)
    }
}
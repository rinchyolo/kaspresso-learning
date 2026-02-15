package lesson04.homework

import kotlin.random.Random

class Inventory {

    private val items = mutableListOf<String>()

    operator fun plus(item: String) {
        items.add(item)
    }

    operator fun get(index: Int): String {
        return items[index]
    }

    operator fun contains(item: String): Boolean {
        return item in items
    }
}

class Toggle(private val enabled: Boolean) {

    operator fun not(): Toggle {
        return Toggle(!enabled)
    }

    override fun toString(): String {
        return enabled.toString()
    }
}

class Price(private val amount: Int) {

    operator fun times(number: Int): Int {
        return amount * number
    }
}

class Step(private val number: Int) {

    operator fun rangeTo(other: Step): IntRange {
        return number..other.number
    }

    operator fun IntRange.contains(step: Step): Boolean {
        return step in this
    }
}

class Log {
    private val entries = mutableListOf<String>()

    operator fun plus(log: String): Log {
        entries.add(log)
        return this
    }
}

class Person() {

    private val phrases = mutableListOf<String>()

    infix fun says(phrase: String): Person {
        phrases.add(phrase)
        return this
    }

    infix fun and(phrase: String): Person {
        if (phrase.isEmpty()) {
            throw IllegalStateException("phrases is empty")
        }
        phrases.add(phrase)
        return this
    }

    infix fun or(phrase: String): Person {
        if (phrase.isEmpty()) {
            throw IllegalStateException("phrases is empty")
        }
        val last = phrases.removeLast()
        val selected = selectPhrase(last, phrase)
        phrases.add(selected)

        return this
    }

    private fun selectPhrase(first: String, second: String): String {
        val random = Random.nextInt(0, 2)
        return if (random == 0) first else second
    }

    fun print() {
        println(phrases.joinToString(" "))
    }
}

fun main() {
    val inventory = Inventory()

    inventory + "Summer"
    inventory + "Winter"

    println(inventory[0])

    println("Summer" in inventory)
    println("Summer1" in inventory)

    val toggle = Toggle(true)

    println(!toggle)

    val price = Price(3)

    println(price * 3)

    1..2

    val log = Log()
    log + "one" + "two"

    val someone = Person()
    someone says "Hello" or "Hi" or "How" or "are" and "is" and "you"
    someone.print()
}
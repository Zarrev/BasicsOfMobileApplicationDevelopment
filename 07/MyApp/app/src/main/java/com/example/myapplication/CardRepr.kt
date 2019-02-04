package com.example.myapplication

import java.util.*
import kotlin.collections.ArrayList

fun <E> List<E>.random(): E? = if (size > 0) get(Random().nextInt(size)) else null

open class Card(var conts: String, var chosen: Boolean, var matched: Boolean) {
    var contents: String = this.conts
        get() = getterForContent()

    open fun getterForContent(): String {
        return conts
    }

    open fun simpleDescription(): String {
        return "The card's content is $contents"
    }

    operator fun compareTo(rhs: Card): Int {
        return this.contents.compareTo(rhs.contents)
    }

    override fun equals(other: Any?): Boolean {
        return this.contents == (other as? Card)?.contents
    }

    fun match(otherCards: ArrayList<Card>): Int {
        var score = 0

        for (card in otherCards) {
            if (card.contents == this.contents) {
                score += 1
            }
        }

        return score
    }

    override fun hashCode(): Int {
        var result = conts.hashCode()
        result = 31 * result + chosen.hashCode()
        result = 31 * result + matched.hashCode()
        return result
    }

    override fun toString(): String {
        return "Card(conts='$conts', chosen=$chosen, matched=$matched)"
    }
}

enum class Rank(val value: Int) {
    Ace(1),
    Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(10),
    Jack(11), Queen(12), King(13),
    Joker(100);

    fun simpleDescription(): String {
        when (this.value) {
            Ace.value -> return "ace"
            Jack.value -> return "jack"
            Queen.value -> return "queen"
            King.value -> return "king"
            Joker.value -> return "joker"
            else -> return this.value.toString()
        }
    }
}

enum class Suit(val value: Int) {
    Spades(1), Hearts(2), Diamonds(3), Clubs(4);
    fun simpleDescription(): String {
        when (this.value) {
            Spades.value -> return "spades"
            Hearts.value -> return "hearts"
            Diamonds.value -> return "diamonds"
            else -> return "clubs"
        }
    }
}

class PlayingCard(var rank: Rank, var suit: Suit): Card("", false, false) {
    override fun getterForContent(): String {
        return this.rank.simpleDescription()
    }

    override fun simpleDescription(): String {
        return "The ${rank.simpleDescription()} of ${suit.simpleDescription()}"
    }

    override fun toString(): String {
        return simpleDescription()
    }
}

open class Deck {
    val cards: ArrayList<Card> = ArrayList()

    fun addCard(card: Card, atTop: Boolean) {
        if (atTop) {
            this.cards.add(0, card)
        }
        else {
            this.cards.add(card)
        }
    }

    fun addCard(card: Card) {
        this.addCard(card, false)
    }

    fun drawRandomCard(): Card? {
        var randomCard: Card? = null
        if (this.cards.size > 0) {
            randomCard = this.cards.random()
            this.cards.remove(randomCard)
        }
        return randomCard
    }
}

class PlayingDeck: Deck() {
    init {
            val ranks = Rank.values()
            val suits = Suit.values()
            for (rank in ranks) {
                for (suit in suits) {
                    this.cards.add(PlayingCard(rank, suit))
                }
            }
    }
}

fun test() {
    val deck = PlayingDeck()
    val aCard = PlayingCard(Rank.Ace, Suit.Hearts)
    val anOtherCard = PlayingCard(Rank.Joker, Suit.Hearts)
    deck.addCard(aCard, true)
    println("A new card has been added on the top of deck.")
    deck.addCard(anOtherCard)
    println("A new card has been added to the deck.")
    println("I draw a random card: ${(deck.drawRandomCard() as? PlayingCard)?.simpleDescription()}")
    println("Print out 'anOtherCard' variable's simpleDescription: ${anOtherCard.simpleDescription()}")
    println("Print out 'aCard' variable's contents: ${aCard.contents}")
    println("Rank-Joker-description: ${Rank.Joker.simpleDescription()}")
    println("Rank-Joker-rawValue: ${Rank.Joker.value}")
    println("Suit-Hearts-description: ${Suit.Hearts.simpleDescription()}")
    println("Suit-Hearts-rawValue: ${Suit.Hearts.value}")
    val cards: ArrayList<PlayingCard> = ArrayList()
    cards.add(deck.drawRandomCard()!! as PlayingCard)
    cards.add(deck.drawRandomCard()!! as PlayingCard)
    val existCards: ArrayList<PlayingCard> = arrayListOf(aCard, anOtherCard, PlayingCard(Rank.Ace, Suit.Hearts))
    println("See the drawn cards: ${cards}")
    println("See the prepared cards: ${existCards}")
    println("Check the matching between 'aCard' and drawn cards: ${aCard.match(cards as ArrayList<Card>)}")
    println("Check the matching between 'aCard' and prepared cards: ${aCard.match(existCards as ArrayList<Card>)}")
}

fun main(args: Array<String>) {
    test()
}
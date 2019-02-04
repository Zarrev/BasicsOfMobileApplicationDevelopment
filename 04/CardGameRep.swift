import Foundation

public class Card {
    var contents: String
    var chosen: Bool
    var matched: Bool
    
    init(contents: String, chosen: Bool, matched: Bool) {
        self.contents = contents
        self.chosen = chosen
        self.matched = matched
    }
    
    func simpleDescription() -> String {
        return "The card's content is \(self.contents)"
    }
    
    static func >(lhs: Card, rhs: Card) -> Bool {
        return lhs.contents > rhs.contents
    }
    
    static func <(lhs: Card, rhs: Card) -> Bool {
        return lhs.contents < rhs.contents
    }
    
    static func >=(lhs: Card, rhs: Card) -> Bool {
        return lhs.contents >= rhs.contents
    }
    
    static func <=(lhs: Card, rhs: Card) -> Bool {
        return lhs.contents <= rhs.contents
    }
    
    static func ==(lhs: Card, rhs: Card) -> Bool {
        return lhs.contents == rhs.contents
    }
    
    static func !=(lhs: Card, rhs: Card) -> Bool {
        return lhs.contents != rhs.contents
    }
    
    func match(otherCards cs: [Card]) -> Int {
        var score: Int = 0
        
        for card in cs {
            if (card.contents == self.contents) {
                score += 1
            }
        }
        
        return score
    }
}

internal enum Rank: Int {
    case Ace = 1
    case Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten
    case Jack, Queen, King
    case Joker
    
    func simpleDescription() -> String {
        switch self {
        case .Ace:
            return "ace"
        case .Jack:
            return "jack"
        case .Queen:
            return "queen"
        case .King:
            return "king"
        case .Joker:
            return "joker"
        default:
            return String(self.rawValue)
        }
    }
}

internal enum Suit: Int {
    case Spades = 1, Hearts, Diamonds, Clubs
    func simpleDescription() -> String {
        switch self {
        case .Spades:
            return "spades"
        case .Hearts:
            return "hearts"
        case .Diamonds:
            return "diamonds"
        case .Clubs:
            return "clubs"
        }
    }
}

internal final class PlayingCard: Card {
    var rank: Rank
    var suit: Suit
    override var contents: String {
        get {
            return self.rank.simpleDescription()
        }
        set {
            
        }
    }
    
    init(rank: Rank, suit: Suit) {
        self.rank = rank
        self.suit = suit
        super.init(contents:"", chosen:false, matched:false)
    }
    
    override func simpleDescription() -> String {
        return "The \(rank.simpleDescription()) of \(suit.simpleDescription())"
    }
}

public class Deck {
    var cards: [Card] = []
    
    func addCard(card c: Card, atTop at: Bool) {
        if (at) {
            self.cards.insert(c, at: 0)
        }
        else {
            self.cards.append(c)
        }
    }
    
    func addCard(card c: Card) {
        self.addCard(card: c, atTop: false)
    }
    
    func drawRandomCard() -> Card? {
        var randomCard: Card? = nil
        if (self.cards.count > 0) {
            let index = Int.random(in:0..<self.cards.count)
            randomCard = self.cards[index]
            self.cards.remove(at: index)
        }
        return randomCard
    }
}

internal final class PlayingDeck: Deck {
    override init() {
        super.init()
        if (cards.count > 0) {
            var n = 1
            while let rank = Rank(rawValue: n) {
                var m = 1
                while let suit = Suit(rawValue: m) {
                    cards.append(PlayingCard(rank: rank, suit: suit))
                    m += 1
                }
                n += 1
            }
        }
    }
}

func test() {
    let deck: PlayingDeck = PlayingDeck()
    let aCard: PlayingCard = PlayingCard(rank: .Ace, suit: .Hearts)
    let anOtherCard: PlayingCard = PlayingCard(rank: .Joker, suit: .Hearts)
    deck.addCard(card: aCard, atTop: true)
    print("A new card has been added on the top of deck.")
    deck.addCard(card: anOtherCard)
    print("A new card has been added to the deck.")
    print("I draw a random card: \((deck.drawRandomCard() as! PlayingCard).simpleDescription())")
    print("Print out 'anOtherCard' variable's simpleDescription: ", anOtherCard.simpleDescription())
    print("Print out 'aCard' variable's contents: ", aCard.contents)
    print("Rank-Joker-description: ", Rank.Joker.simpleDescription())
    print("Rank-Joker-rawValue: ", Rank.Joker.rawValue)
    print("Suit-Hearts-description: ", Suit.Hearts.simpleDescription())
    print("Suit-Hearts-rawValue: ", Suit.Hearts.rawValue)
    let cards = [deck.drawRandomCard(), deck.drawRandomCard()]
    let existCards: [PlayingCard] = [aCard, anOtherCard, PlayingCard(rank: .Ace, suit: .Hearts)]
    print("Check the matching between 'aCard' and drawn cards: ", aCard.match(otherCards: cards as? [Card] ?? []))
    print("Check the matching between 'aCard' and prepared cards: ", aCard.match(otherCards: existCards))

}

test()
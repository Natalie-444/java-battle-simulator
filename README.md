# java-battle-simulator
# Java Battle Simulator

A turn-based combat game written in Java where characters from Pokemon, Marvel, DC, and Star Wars battle using stats loaded from external files. Choose your universe, pick your fighter, and battle across multiple game modes.

---

## Game Modes

- Single Player — You vs. a computer-controlled opponent
- Two Player — Local player vs. player within the same universe
- Cross-Universe — Pit characters from different universes against each other (e.g. Marvel vs. Pokemon)
- Recursive AI Battle — Two AI-controlled characters simulate a full battle automatically using recursion

---

## Universes

- Pokemon
- Marvel
- DC
- Star Wars

---

## Character Stats

Each character has three core stats:

- HP — Health Points; reaching zero means defeat
- Attack — Determines damage dealt per turn
- Defense — Reduces incoming damage

Stats are loaded at runtime from .txt data files.

---

## How to Play

Each turn, choose one of three actions:

1. Attack — Deal damage based on your Attack vs. the opponent's Defense
2. Heal — Recover some HP
3. Forfeit — Surrender the match

Battles continue round by round until one character's HP reaches zero.

---

## Project Structure

    java-battle-simulator/
    |-- Menu.java          Main entry point and menu navigation
    |-- Game.java          Core gameplay logic and battle mode routing
    |-- Character.java     Character class with stats and battle methods
    |-- pokemon.txt        Pokemon character data
    |-- marvel.txt         Marvel character data
    |-- dc.txt             DC character data
    |-- starwars.txt       Star Wars character data

---

## Concepts Demonstrated

- Object-Oriented Programming — Classes, objects, and method design
- Encapsulation — Private fields with getters and setters
- Recursion — Used in the AI battle simulation mode
- File I/O — Character stats loaded dynamically via Scanner
- Randomization — Used in AI decision-making and combat variation
- Input Validation — Handles invalid user input gracefully
- Basic AI Logic — Computer opponent makes decisions based on game state

---

## Author

Natalie Rodriguez
Computer Science Student — University of Texas at El Paso
njrodriguez3@miners.utep.edu

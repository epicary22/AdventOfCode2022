# ADVENT OF CODE DAY 14

## Problem to solve
#### How many units of sand need to fall into the cave system for them to start falling into the infinite abyss?

## Parts of the Problem
* How do I get a comprehensive map of the cave? I need all of the x- and y- positions of stone tiles.
* How do I create a falling-sand algorithm?

## Classes Necessary
* `Point(String)`, that can take `481,122` and turn it into a pair of ints.
* `StoneLine(String)`, where `String` is a `"->"`-separated part of the input `"481,122 -> 481,125 -> 476,125 -> etc."`
* `StoneMap()`, which is just a collection of all points from `StoneLine`s that you add.
* `SandParticle()` comes from the sand spawner and has its own logic on where to fall. Uses the `StoneMap` somehow?

## Steps to Solution
1. Create the Point and StoneLine classes to be able to take in the input. 
2. Create the StoneMap class and compile all given StoneLines into it.
3. Create the SandParticle class and give it logic to scan 
   1. As far down as possible
   2. Then as far diagonal left until it has space under it/stone in its way
   3. Then as far diagonal right until it has space under it/stone in its way
4. After the SandParticle is done falling, add its position to the StoneMap.
5. Then from there, find how many SandParticles it takes until one falls into the abyss.

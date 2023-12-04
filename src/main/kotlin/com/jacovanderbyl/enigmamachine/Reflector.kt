package com.jacovanderbyl.enigmamachine

/**
 * Represents a base reflector that can substitute a letter for another given a simple cipher set map.
 */
class Reflector(
    cipherSetMap: CipherSetMap,
    val type: ReflectorFactory,
    val compatibility: Set<EnigmaFactory>
) : CanEncipher {
    private val characterSet: String = cipherSetMap.characterSet
    private val cipherSet: String = cipherSetMap.cipherSet

    override fun encipher(character: Char) : Char {
        require(character in Keys.CHARACTER_SET) {
            "Invalid character. Valid: '${Keys.CHARACTER_SET}'. Given: '${character}'."
        }

        return cipherSet[characterSet.indexOf(character)]
    }
}

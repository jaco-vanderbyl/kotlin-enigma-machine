import com.jacovanderbyl.enigmamachine.*

fun main() {
    val firstEnigmaI = EnigmaBuilder.make(
        type = "ENIGMA_I",
        reflector = "B",
        rotors = "I,II,III"
    )
    val secondEnigmaI = EnigmaBuilder.make(
        type = "ENIGMA_I",
        reflector = "B",
        rotors = "I,II,III"
    )

    val plaintextInput = "AAAAA"
    val ciphertext = firstEnigmaI.encipher(plaintextInput)
    val plaintext = secondEnigmaI.encipher(ciphertext)

    println(plaintextInput)
    println(ciphertext)
    println(plaintext)
}

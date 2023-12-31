package com.jacovanderbyl.enigmamachine

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import kotlin.IllegalArgumentException
import kotlin.test.DefaultAsserter.assertEquals
import kotlin.test.assertContains
import kotlin.test.assertFailsWith

class CipherSetMapTest {
    private val cipherSet = "EKMFLGDQVZNTOWYHXUSPAIBRCJ"
    private val cipherSetMap = CipherSetMap(cipherSet)

    @Test
    fun `ensure character set equals the enigma character set`() {
        assertEquals(
            expected = Letter.characterSet(),
            actual = cipherSetMap.characterSet,
            message = "Failed to ensure character set equals the enigma character set."
        )
    }

    @TestFactory
    fun `ensure invalid cipher set throws`() = listOf(
        "",
        "AAAAAAAAAAAAAAAAAAAAAAAAAA",
        "ABCDEFGHIJKLMNOPQRSTUVWXYZABC",
        "AACDEFGHIJKLMNOPQRSTUVWXYZ",
    ).map { cipherSet ->
        DynamicTest.dynamicTest("Invalid cipher set '${cipherSet}' should throw.") {
            val exception = assertFailsWith<IllegalArgumentException>(
                block = { CipherSetMap(cipherSet) },
                message = "Failed to ensure invalid cipher set throws."
            )
            exception.message?.let {
                assertContains(it, "invalid cipher set", ignoreCase = true)
            }
        }
    }

    @Test
    fun `ensure encipher works`() {
        cipherSetMap.characterSet.forEachIndexed { index, char ->
            assertEquals(
                expected = cipherSet[index],
                actual = cipherSetMap.encipher(char),
                message = "Failed to ensure encipher works."
            )
        }
    }

    @Test
    fun `ensure encipher works in reverse`() {
        cipherSet.forEachIndexed { index, char ->
            assertEquals(
                expected = cipherSetMap.characterSet[index],
                actual = cipherSetMap.encipher(char, reverse = true),
                message = "Failed to ensure encipher works in reverse."
            )
        }
    }

    @TestFactory
    fun `ensure invalid character throws`() = listOf(
        ' ',
        'a',
        '@',
    ).map { char ->
        DynamicTest.dynamicTest("Invalid character '${char}' should throw.") {
            val exception = assertFailsWith<IllegalArgumentException>(
                block = { cipherSetMap.encipher(char) },
                message = "Failed to ensure invalid character throws."
            )
            exception.message?.let {
                assertContains(it, "invalid character", ignoreCase = true)
            }
        }
    }
}
